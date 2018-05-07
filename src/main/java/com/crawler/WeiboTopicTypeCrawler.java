package com.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class WeiboTopicTypeCrawler extends BreadthCrawler {
	private String cookie;
	private List<WeiboTopicType> topicList = new ArrayList();
	// 24小时话题榜地址
	public static final String WEIBO_HOUR_24_URL = "https://d.weibo.com/100803_-_page_hot_list?cfs=&Pl_Discover_Pt6Rank__5_filter=hothtlist_type%3D1#_0";
		
	public WeiboTopicTypeCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		try {
			cookie = WeiboCN.getSinaCookie();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Page getResponse(CrawlDatum crawlDatum) throws Exception {
		// TODO Auto-generated method stub
		HttpRequest request = new HttpRequest(crawlDatum);
		request.setCookie(cookie);
		return request.responsePage();
	}
	
	public void visit(Page page, CrawlDatums next) {
		// TODO Auto-generated method stub
		Elements ws = page.select("script");
		for (Element e : ws) {
			String html = e.html();
			if (html.indexOf("FM.view(") != -1) {
				html = html.substring(html.indexOf("FM.view(") + 8, html.length());
				html = html.substring(0, html.lastIndexOf(")"));
				JSONObject jo = JSONObject.parseObject(html);
				String s = jo.getString("html");
				if (s != null) {
					s = jo.getString("html").replaceAll("\\t", "").replaceAll("\\n", "");
					Document doc = Jsoup.parse(s);
					// item current S_line1
					//当前选中的分类
					Elements currentEsType = doc.select("li.item.current.S_line1");
					for (Element ee : currentEsType) {
						// System.out.println(ee.text());
					}
					//所有分类
					Elements htTypeEs = doc.select("ul.ul_item.clearfix");
					for (Element t1 : htTypeEs) {
						// .li.item.a.item_link.S_txt1
						Elements aes = t1.select("a.item_link.S_txt1");
						for (Element t2 : aes) {
							String text = t2.text();
							if (text.indexOf("更多") != -1 || text.indexOf("话题榜") != -1 || text.indexOf("新时代") != -1
									|| text.indexOf("本地") != -1 || text.indexOf("我的") != -1) {
								continue;
							}
							Elements textEs = t2.select("span.item_title.S_txt1");
							for (Element t3 : textEs) {
								text = t3.text();
							}
							WeiboTopicType topic = new WeiboTopicType();
							topic.setName(text);
							topic.setLink(t2.attr("href"));
//							System.out.println(text);
							this.topicList.add(topic);
						}
					}
				}
			}
		}
	}
	
	public List<WeiboTopicType> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<WeiboTopicType> topicList) {
		this.topicList = topicList;
	}
	
	/// topic_video?from=page_100808&mod=TAB#place
	public static void main(String[] args) throws Exception {
		WeiboTopicTypeCrawler crawler = new WeiboTopicTypeCrawler("weibo_crawler", false);
		// CrawlDatum crawDatum = new
		// CrawlDatum("https://d.weibo.com/100803_ctg1_7_-_ctg17#");
		// crawler.addSeed(crawDatum);
		crawler.addSeed(WEIBO_HOUR_24_URL);
		crawler.setThreads(1);
		crawler.setResumable(false);
		crawler.start(1);
		
//		// 获取垂类下面的博文和链接
//		WeiboTopicCrawler c1 = new WeiboTopicCrawler("weibo_crawler", false);
//		for (String name : crawler.htTypeMap.keySet()) {
//			String link = crawler.htTypeMap.get(name);
//			c1.addSeed("https:" + link);
//		}
//		c1.setThreads(5);
//		c1.setResumable(false);
//		c1.start(1);
//		// 根据链接再获取每个垂类下第一个视频
//		
//		// https://weibo.com/p/aj/proxy?api=http://i.huati.weibo.com/pcpage/papp&ajwvr=6&atype=video&viewer_uid=1592135940&since_id=&page_id=10080802a71f3c6b291273136d141a0e3b77d1&page=2&ajax_call=1&appname=album&module=feed&is_feed=1&__rnd=1523612825885
//		WeiboTopicCrawler c2 = new WeiboTopicCrawler("weibo_crawler", false);
//		c2.setGetVideo(true);
//		for (String name : c1.categoryDetailMap.keySet()) {
//			String href = c1.categoryDetailMap.get(name);
//			href = href.substring(0, href.lastIndexOf("?"));
//			href = href.replaceAll("//weibo.com/p/", "");
//			// System.out.println(href);
//			// 视频标签通过Ajax返回
//			String pre = "https://weibo.com/p/aj/proxy?api=http://i.huati.weibo.com/pcpage/papp&ajwvr=6&atype=video&viewer_uid=1592135940&since_id=&page_id=";
//			String sub = "&page=2&ajax_call=1&appname=album&module=feed&is_feed=1&__rnd=1523612825885";
//			String url = pre + href + sub;
//			c2.addSeed(url);
//		}
//		c2.setThreads(5);
//		c2.start(1);
//		
//		
//		//导出到Excel
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = wb.createSheet();
//		String[] header = {"序号","话题","博文ID","博文标题","博文地址","视频标题"};
//		int count = 0;
//		int rownum = 0;
//		XSSFRow row = sheet.createRow(rownum);
//		for(String h : header) {
//			int col = 0;
//			XSSFCell cell = row.createCell(col);
//			cell.setCellValue(h);
//			col ++;
//		}
//		rownum ++;
//		for (String name : crawler.htTypeMap.keySet()) {
//			//获取博文
//			String htLink = crawler.getHtTypeMap().get(name);
//			WeiboTopicDetail topicDetail = c1.getTopicDetailMap().get(htLink);
//			if(topicDetail == null) {
//				continue;
//			}
//			
//			int col = 0;
//			row = sheet.createRow(rownum);
//			XSSFCell cell = row.createCell(col);
//			cell.setCellValue( ++ count);
//			col ++;
//			cell = row.createCell(col);
//			cell.setCellValue(name);
//			
//			
//			
//			
//			rownum ++;
//		}
		
	}
	
	
	
	
	

}
