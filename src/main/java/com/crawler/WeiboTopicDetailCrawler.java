package com.crawler;

import java.util.ArrayList;
import java.util.HashMap;
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

public class WeiboTopicDetailCrawler extends BreadthCrawler {

	private String cookie;
	private List<WeiboTopicDetail> topicDetailList = new ArrayList();
	private Map<String, List<WeiboTopicDetail>> topicDetailMap = new HashMap();// key=topic link

	public WeiboTopicDetailCrawler(String crawlPath, boolean autoParse) {
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

	@Override
	public void visit(Page page, CrawlDatums next) {
		// TODO Auto-generated method stub
		// 获取视频
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
							String id = href.substring(0, href.lastIndexOf("?"));
							id = id.replaceAll("//weibo.com/p/", "");
							
							List<WeiboTopicDetail> list = topicDetailMap.get(page.url());
							if (list == null) {
								list = new ArrayList();
							}
							WeiboTopicDetail detail = new WeiboTopicDetail();
							detail.setId(id);
							detail.setName(name);
							detail.setLink(href);
							list.add(detail);
							this.topicDetailMap.put(page.url(), list);
							this.topicDetailList.add(detail);
						}
					}
				}
			}
		}
	}

	public Map<String, List<WeiboTopicDetail>> getTopicDetailMap() {
		return topicDetailMap;
	}

	public void setTopicDetailMap(Map<String, List<WeiboTopicDetail>> topicDetailMap) {
		this.topicDetailMap = topicDetailMap;
	}

	public List<WeiboTopicDetail> getTopicDetailList() {
		return topicDetailList;
	}

	public void setTopicDetailList(List<WeiboTopicDetail> topicDetailList) {
		this.topicDetailList = topicDetailList;
	}
}
