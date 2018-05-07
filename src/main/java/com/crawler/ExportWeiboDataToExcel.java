package com.crawler;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ExportWeiboDataToExcel {

	// 24小时话题榜地址
	public static final String WEIBO_HOUR_24_URL = "https://d.weibo.com/100803_-_page_hot_list?cfs=&Pl_Discover_Pt6Rank__5_filter=hothtlist_type%3D1#_0";
	
	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			Date currentDate = cal.getTime();
			cal.add(Calendar.DATE, -1);
			Date lastDate = cal.getTime();
			File logFile = new File("d:\\weiboTopicLog\\log.txt");
			//读取log
			BufferedReader br = new BufferedReader(new FileReader(logFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter("d:\\weiboTopicLog\\log" + sdf.format(lastDate)  + ".txt"));
			Set<String> logContentSet = new HashSet();
			String line = "";
			while((line = br.readLine()) != null) {
				bw.write(line + "\n");
				if("".equals(line.trim())) {
					continue;
				}
				logContentSet.add(line);
			}
			//备份这一天的数据
			bw.flush();
			bw.close();
			br.close();
			// 获取话题
			WeiboTopicTypeCrawler topicCrawler = new WeiboTopicTypeCrawler("WeiboTopicCrawler", false);
			topicCrawler.addSeed(WEIBO_HOUR_24_URL);
			topicCrawler.setThreads(1);
			topicCrawler.start(1);
			List<WeiboTopicType> topicList = topicCrawler.getTopicList();
			//获取话题下的博文列表
			WeiboTopicDetailCrawler topicDetailCrawler = new WeiboTopicDetailCrawler("WeiboTopicDetailCrawler", false);
			for(WeiboTopicType topic : topicList) {
				//Pl_Discover_Pt6Rank__4_page=1,按分页取
				topicDetailCrawler.addSeed("https:" + topic.getLink());
			}
			topicDetailCrawler.setThreads(5);
			topicDetailCrawler.start(1);
			Map<String,List<WeiboTopicDetail>> topicDetailMap = topicDetailCrawler.getTopicDetailMap();
			//获取视频
			WeiboTopicDetailVideoCrawler videoCrawler = new WeiboTopicDetailVideoCrawler("WeiboTopicDetailVideoCrawler", false);
			
			int c = 0;
			for (WeiboTopicDetail topicDetail : topicDetailCrawler.getTopicDetailList()) {
				// System.out.println(href);
				// 视频标签通过Ajax返回
//				for(int i =1 ; i < 2 ; i ++) {
					String url = WeiboTopicDetailVideoCrawler.PRE_URL + topicDetail.getId() + "&page=2" + WeiboTopicDetailVideoCrawler.SUB_URL;
					videoCrawler.addSeed(url);
					c ++;
//					if(topicDetailCrawler.getTopicDetailList().size()/2 == c) {
//						break;
//					}
//				}
			}
			videoCrawler.setThreads(20);
			videoCrawler.start(1);
//			WeiboTopicDetailVideoCrawler videoCrawler1 = new WeiboTopicDetailVideoCrawler("WeiboTopicDetailVideoCrawler222", false);
//			for(int i =c ; i < topicDetailCrawler.getTopicDetailList().size(); i ++) {
////			for (WeiboTopicDetail topicDetail : topicDetailCrawler.getTopicDetailList()) {
//				WeiboTopicDetail topicDetail = topicDetailCrawler.getTopicDetailList().get(i);
//				// System.out.println(href);
//				// 视频标签通过Ajax返回
////				for(int i =1 ; i < 2 ; i ++) {
//				String url = WeiboTopicDetailVideoCrawler.PRE_URL + topicDetail.getId() + "&page=1" + WeiboTopicDetailVideoCrawler.SUB_URL;
//				videoCrawler1.addSeed(url);
////				}
//			}
//			videoCrawler1.setThreads(2);
//			videoCrawler1.start(1);
			
			
			Map<String, List<WeiboTopicDetailVideo>> allVideoMap = new HashMap();
			allVideoMap.putAll(videoCrawler.getTopicDetailVideoMap());
//			allVideoMap.putAll(videoCrawler1.getTopicDetailVideoMap());
			
			// 导出到Excel
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet();
			CreationHelper createHelper = wb.getCreationHelper();
			XSSFCellStyle hlinkstyle = wb.createCellStyle();
			XSSFFont hlinkfont = wb.createFont();  
	        hlinkfont.setUnderline(XSSFFont.U_SINGLE);  
	        hlinkfont.setColor(new XSSFColor(Color.BLUE));
	        hlinkstyle.setFont(hlinkfont);
			
			
			String[] header = { "序号", "垂类","话题标题", "博文标题","博文地址" };
			int count = 0;
			int rownum = 0;
			
			sheet.setColumnWidth(0, 36 * 60);
			sheet.setColumnWidth(1, 36 * 100);
			sheet.setColumnWidth(2, 36 * 200);
			sheet.setColumnWidth(3, 36 * 300);
			sheet.setColumnWidth(4, 36 * 300);
			
			XSSFRow row = sheet.createRow(rownum);
			int col = 0;
			for (String h : header) {
				XSSFCell cell = row.createCell(col);
				cell.setCellValue(h);
				
				col++;
			}
			rownum++;
			StringBuffer sb = new StringBuffer();
			for (WeiboTopicType topic : topicList) {
				// 获取博文
				String htLink = "https:" + topic.getLink();
				List<WeiboTopicDetail> topicDetailList = topicDetailMap.get(htLink);
				if(topicDetailList == null) {
					continue;
				}
				for(WeiboTopicDetail topicDetail : topicDetailList) {
					//取视频
					List<WeiboTopicDetailVideo> videoList = allVideoMap.get(topicDetail.getId());
					int videoIndex = 0;
					WeiboTopicDetailVideo detailVideo = getTopicDetailVideo(logContentSet, videoList, videoIndex);
					if(detailVideo == null || detailVideo.getLink() == null || "".equals(detailVideo.getLink().trim())) {
						continue;
					}
					col = 0;
					row = sheet.createRow(rownum);
					XSSFCell cell = row.createCell(col);
					cell.setCellValue(++count);
					col++;
					cell = row.createCell(col);
					cell.setCellValue(topic.getName());//话题垂类
					col++;
					cell = row.createCell(col);
					cell.setCellValue(topicDetail.getName());//话题标题
					
					col++;
					cell = row.createCell(col);
					cell.setCellValue(detailVideo.getName());//博文标题
					col++;
					cell = row.createCell(col);
					cell.setCellValue(detailVideo.getLink());//博文链接
					XSSFHyperlink htyperLink = cell.getHyperlink(); 
					if(htyperLink == null) {
						htyperLink = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);  
			            htyperLink.setAddress(detailVideo.getLink()); 
					}
					cell.setHyperlink(htyperLink);
					cell.setCellStyle(hlinkstyle);
					rownum++;
					
					//记录日志
					sb.append(detailVideo.getLink()).append("\n");
				}
			}
			
			wb.write(new FileOutputStream("d:\\微博话题数据" + sdf.format(new Date()) + ".xlsx"));
			wb.close();
			
			//记录日志
			FileWriter fw = new FileWriter(logFile,true);//追加
			fw.write(sb.toString());
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static WeiboTopicDetailVideo getTopicDetailVideo(Set<String> logContentSet,List<WeiboTopicDetailVideo> videoList,int index) {
		if(videoList == null || videoList.size() == 0) {
			return null;
		}
		if(logContentSet == null) {
			logContentSet = new HashSet();
		}
		if(index >= videoList.size()) {
			return null;
		}
		WeiboTopicDetailVideo detailVideo = videoList.get(index);
		if(logContentSet.contains(detailVideo.getLink())) {
			return getTopicDetailVideo(logContentSet, videoList, index + 1);
		}
		return detailVideo;
	}

}
