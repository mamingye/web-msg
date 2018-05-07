package com.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class TestCrawler extends BreadthCrawler{
	private String cookie;

	public TestCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		try {
			cookie = WeiboCN.getSinaCookie("jscvbnm@sina.com", "jiangsc358063531");
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

	private List<WeiboTopicType> topicList = new ArrayList();
	
	Map<String, String> htTypeMap = new LinkedHashMap();
	Map<String, String> categoryDetailMap = new LinkedHashMap();
	private Map<String,WeiboTopicDetail> topicDetailMap = new HashMap();//key=topic link value=WeiboTopicDetail
	private String htName;
	private boolean isGetType = false;
	private boolean isGetCategoryDetial = true;// 获取每个垂类下链接
	private boolean isGetVideo = false;

	public Map<String, WeiboTopicDetail> getTopicDetailMap() {
		return topicDetailMap;
	}

	public void setTopicDetailMap(Map<String, WeiboTopicDetail> topicDetailMap) {
		this.topicDetailMap = topicDetailMap;
	}

	public Map<String, String> getHtTypeMap() {
		return htTypeMap;
	}

	public void setHtTypeMap(Map<String, String> htTypeMap) {
		this.htTypeMap = htTypeMap;
	}

	public Map<String, String> getCategoryDetailMap() {
		return categoryDetailMap;
	}

	public void setCategoryDetailMap(Map<String, String> categoryDetailMap) {
		this.categoryDetailMap = categoryDetailMap;
	}

	public String getHtName() {
		return htName;
	}

	public void setHtName(String htName) {
		this.htName = htName;
	}

	public boolean isGetVideo() {
		return isGetVideo;
	}

	public void setGetVideo(boolean isGetVideo) {
		this.isGetVideo = isGetVideo;
	}

	public boolean isGetCategoryDetial() {
		return isGetCategoryDetial;
	}

	public void setGetCategoryDetial(boolean isGetCategoryDetial) {
		this.isGetCategoryDetial = isGetCategoryDetial;
	}

	public boolean isGetType() {
		return isGetType;
	}

	public void setGetType(boolean isGetType) {
		this.isGetType = isGetType;
	}

	public void visit(Page page, CrawlDatums next) {
		// TODO Auto-generated method stub

		// 获取视频
		if (this.isGetVideo) {
//			System.out.println(page.html());
			JSONObject jo = JSONObject.parseObject(page.html());
			String s = jo.getString("data");
			if(s != null) {
				Document doc = Jsoup.parse(s);
				// item current S_line1
				Elements es = doc.select("li.photo_module");
				//取第一个视频
				int count = 0;
				for (Element e1 : es) {
					if(count ++ > 0) {
						break;
					}
					Elements titleEs = e1.select("div.txt_cont.S_txt2.W_f14");
//					System.out.println(titleEs.text());
					
					
				}
			}
		} else {
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
						Elements currentEsType = doc.select("li.item.current.S_line1");
						for (Element ee : currentEsType) {
							// System.out.println(ee.text());
						}
						if (isGetCategoryDetial) {
							String url = page.url();
							Elements es = doc.select("li.pt_li.S_line2");
							for (Element ee : es) {
								Elements tempAes = ee.select("div.title.W_autocut");
								for (Element t1 : tempAes) {
									String name = t1.text();
									String href = "";
									// System.out.println(t1.text());
									// 取链接
									Elements aes = t1.select("a.S_txt1");
									for (Element t2 : aes) {
										href = t2.attr("href");
										// System.out.println(t2.attr("href"));
									}
									this.categoryDetailMap.put(name, href);
									
									WeiboTopicDetail detail = new WeiboTopicDetail();
									detail.setName(name);
									detail.setLink(href);
									this.topicDetailMap.put(page.url(), detail);
								}
							}
						}
						if (this.isGetType) {
							this.setHuaTiCategory(doc);
						}

					}
				}
			}
		}
	}

	private void setHuaTiCategory(Document doc) {
		// 取话题分类
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
				htTypeMap.put(text, t2.attr("href"));
				WeiboTopicType topic = new WeiboTopicType();
				topic.setName(text);
				topic.setLink(t2.attr("href"));
				this.topicList.add(topic);
				// System.out.println(t2.attr("href"));
				// System.out.println(t2.text());
			}
		}
	}

	// 24小时话题榜地址
	public static final String WEIBO_HOUR_24_URL = "https://d.weibo.com/100803_-_page_hot_list?cfs=&Pl_Discover_Pt6Rank__5_filter=hothtlist_type%3D1#_0";

	/// topic_video?from=page_100808&mod=TAB#place
	public static void main(String[] args) throws Exception {
		TestCrawler crawler = new TestCrawler("weibo_crawler", false);
		crawler.setGetType(true);
		// CrawlDatum crawDatum = new
		// CrawlDatum("https://d.weibo.com/100803_ctg1_7_-_ctg17#");
		// crawler.addSeed(crawDatum);
		crawler.addSeed(WEIBO_HOUR_24_URL);
		crawler.setThreads(1);
		crawler.setResumable(false);
		crawler.start(1);

		// 获取垂类下面的博文和链接
		TestCrawler c1 = new TestCrawler("weibo_crawler", false);
		for (String name : crawler.htTypeMap.keySet()) {
			String link = crawler.htTypeMap.get(name);
			c1.addSeed("https:" + link);
		}
		c1.setThreads(5);
		c1.setResumable(false);
		c1.start(1);
		// 根据链接再获取每个垂类下第一个视频

//		// https://weibo.com/p/aj/proxy?api=http://i.huati.weibo.com/pcpage/papp&ajwvr=6&atype=video&viewer_uid=1592135940&since_id=&page_id=10080802a71f3c6b291273136d141a0e3b77d1&page=2&ajax_call=1&appname=album&module=feed&is_feed=1&__rnd=1523612825885
//		TestCrawler c2 = new TestCrawler("weibo_crawler", false);
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
		
	}
}
