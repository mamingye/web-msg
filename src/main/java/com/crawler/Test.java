package com.crawler;

public class Test {

	public static void main(String[] args) throws Exception {
//		Document doc = Jsoup.connect("https://d.weibo.com/100803_-_page_hot_list?cfs=&Pl_Discover_Pt6Rank__5_filter=hothtlist_type%3D1#_0").get();
//		
//		Elements links = doc.getElementsByAttributeValue("class", "pt_li S_line2");
//		for(Element e : links) {
//			System.out.println(e.text());
//			
//		}
//		String s = "&page_id=100808478d247f4f95472e23eef786daf83c73";
//		
//		int index = s.indexOf("&page_id=");
//		if(index != -1) {
//			s = s.substring(index + 9, s.length());
//		}
//		System.out.println(s);
		
		// 获取话题
//		WeiboTopicCrawler topicCrawler = new WeiboTopicCrawler("WeiboTopicCrawler", false);
//		topicCrawler.addSeed(ExportWeiboDataToExcel.WEIBO_HOUR_24_URL);
//		topicCrawler.setThreads(1);
//		topicCrawler.start(1);
//		List<WeiboTopicType> topicList = topicCrawler.getTopicList();
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = wb.createSheet();
//		
//		CreationHelper createHelper = wb.getCreationHelper();
//		XSSFCellStyle hlinkstyle = wb.createCellStyle();
//		XSSFFont hlinkfont = wb.createFont();  
//        hlinkfont.setUnderline(XSSFFont.U_SINGLE);  
//        hlinkfont.setColor(new XSSFColor(Color.BLUE));
//        hlinkstyle.setFont(hlinkfont);
//		
//		String[] header = { "序号", "话题垂类","垂类地址"};
//		int count = 0;
//		int rownum = 0;
//		
//		sheet.setColumnWidth(0, 36 * 60);
//		sheet.setColumnWidth(1, 36 * 100);
//		sheet.setColumnWidth(2, 36 * 300);
//		
//		XSSFRow row = sheet.createRow(rownum);
//		int col = 0;
//		for (String h : header) {
//			XSSFCell cell = row.createCell(col);
//			cell.setCellValue(h);
//			col++;
//		}
//		rownum++;
//		for(WeiboTopicType topic : topicList) {
//			String htLink = "https:" + topic.getLink();
//			col = 0;
//			row = sheet.createRow(rownum);
//			XSSFCell cell = row.createCell(col);
//			cell.setCellValue(++count);
//			col++;
//			cell = row.createCell(col);
//			cell.setCellValue(topic.getName());//话题垂类
//			col++;
//			cell = row.createCell(col);
//			cell.setCellValue(htLink);//垂类地址
////			cell.setCellType(CellType.FORMULA);
////			cell.setCellFormula(formula);
//			XSSFHyperlink link = cell.getHyperlink(); 
//			if(link == null) {
//				link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);  
//	            link.setAddress(htLink); 
//			}
//			cell.setHyperlink(link);
//			cell.setCellStyle(hlinkstyle);
//			rownum ++;
//		}
//		wb.write(new FileOutputStream("d:\\test222.xlsx"));
//		wb.close();
//		System.out.println(topicList.size());
		
		
		String s = "http://video.weibo.com/show?fid=1034:8431e81fd59cecdd4c00bc660989e3f4";
		System.out.println(s.indexOf("video.weibo.com"));
		
	}
	
	
}
