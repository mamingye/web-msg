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

public class WeiboTopicDetailVideoCrawler extends BreadthCrawler {

	private String cookie;
	private Map<String, List<WeiboTopicDetailVideo>> topicDetailVideoMap = new HashMap();//key=topicDetail.id
	//前缀URL
	public static final String PRE_URL = "https://weibo.com/p/aj/proxy?api=http://i.huati.weibo.com/pcpage/papp&ajwvr=6&atype=video&viewer_uid=1592135940&since_id=&page_id=";
	//后缀URL
//	public static final String SUB_URL = "&page=1&ajax_call=1&appname=album&module=feed&is_feed=1&__rnd=1523612825885";
	public static final String SUB_URL = "&ajax_call=1&appname=album&module=feed&is_feed=1";

	public WeiboTopicDetailVideoCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		try {
			this.cookie = WeiboCN.getSinaCookie();
		} catch (Exception e) {
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
		String url = page.url();
//		String detailId = url.replaceAll(PRE_URL, "");
//		detailId = url.replaceAll(SUB_URL, "");
		String detailId = url;
		detailId = detailId.replaceAll("&page=2" + SUB_URL, "");
		int index = url.indexOf("&page_id=");
		if(index != -1) {
			detailId = detailId.substring(index + 9, detailId.length());
		}
//		System.out.println(detailId);
//		System.out.println(page.html());
		JSONObject jo = JSONObject.parseObject(page.html());
		if(jo == null || jo.isEmpty()) {
			return;
		}
		String s = jo.getString("data");
		if(s != null) {
			Document doc = Jsoup.parse(s);
			// item current S_line1
			Elements es = doc.select("li.photo_module");
			//取第一个视频
			int count = 0;
			for (Element e1 : es) {
//				if(count > 0) {
//					break;
//				}
				Elements aes = e1.select("a");//取一个链接就好，链接地址都一样
				String aHref = "";
				for(Element ae : aes) {
					aHref = ae.attr("href");
					break;
				}
//				if(aHref.indexOf("video.weibo.com") == -1) {
//					continue;
//				}
				Elements titleEs = e1.select("div.txt_cont.S_txt2.W_f14");
//				System.out.println(titleEs.text());
				List<WeiboTopicDetailVideo> videoList = this.topicDetailVideoMap.get(detailId);
				if(videoList == null) {
					videoList = new ArrayList();
				}
				WeiboTopicDetailVideo video = new WeiboTopicDetailVideo();
				video.setName(titleEs.text());
				video.setLink(aHref);
				videoList.add(video);
				this.topicDetailVideoMap.put(detailId, videoList);
				count ++;
			}
		}
	}

	public Map<String, List<WeiboTopicDetailVideo>> getTopicDetailVideoMap() {
		return topicDetailVideoMap;
	}

	public void setTopicDetailVideoMap(Map<String, List<WeiboTopicDetailVideo>> topicDetailVideoMap) {
		this.topicDetailVideoMap = topicDetailVideoMap;
	}

}
