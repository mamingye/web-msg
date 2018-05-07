package com.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URL;

public class TestSendMsg {

	public static void main(String[] args) throws Exception {
		String cc = WeiboCN.getSinaCookie();
		URL link = new URL("https://weibo.com/u/6460530200");
		WebClient wc = new WebClient();
		WebRequest request = new WebRequest(link);
		request.setCharset("UTF-8");
//		 request.setAdditionalHeader("Referer", "");//设置请求报文头里的refer字段
		//// 设置请求报文头里的User-Agent字段
		request.setAdditionalHeader("Host", "weibo.com");
		request.setAdditionalHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
		request.setAdditionalHeader("Cookie", cc);
		// wc.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:6.0.2)
		// Gecko/20100101 Firefox/6.0.2");
		// wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
		// 其他报文头字段可以根据需要添加
		wc.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
		wc.getOptions().setJavaScriptEnabled(true);// 开启js解析。对于变态网页，这个是必须的
		// wc.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
		wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
		wc.getOptions().setThrowExceptionOnScriptError(false);
		wc.getOptions().setTimeout(10000);

		HtmlPage p = wc.getPage(request);
		DomNode sixin =  p.querySelector("a.W_btn_d btn_34px");
		System.out.println(sixin);
		
		
	}
	
}
