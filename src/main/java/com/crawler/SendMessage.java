package com.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Capabilities;

public class SendMessage extends BreadthCrawler {

	private String cookie;

	private static final String sendMsg = "https://m.weibo.cn/msg/chat?uid=6460530200";

	public SendMessage(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		// TODO Auto-generated constructor stub
		try {
			cookie = WeiboCN.getSinaCookie();
			//cookie = WeiboLogin.login();
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

		Elements es = page.select("textarea.J-textarea.sendmsg_txt.txt");
		System.out.println(page.html());
		for (Element e : es) {
			System.out.println(e.val());
		}

	}

	public static void main(String[] args) throws Exception {
		// SendMessage test = new SendMessage("tests", false);
		// //
		// test.addSeed("https://m.weibo.cn/msgDeal/sendMsg?content=你是&uid=6460530200&st=f9073f&fileId");
		// test.addSeed(sendMsg);
		// test.setThreads(1);
		// test.start(1);

		// String c = WeiboCN.getSinaCookie();
		// String response = sendGet("https://m.weibo.cn/msgDeal/sendMsg",
		// "content=你是&uid=6460530200&st=f9073f&fileId=",c);
		//
		// System.out.println(response);
		// Set<Cookie> cSet = WeiboCN.getSinaCookieSet();
		// Set<Cookie> cSet = WeiboLogin.getSinaCookieSet();
//		String cc = WeiboLogin.login();
		// TestHtmlUnitDriver driver = new TestHtmlUnitDriver(BrowserVersion.CHROME);
		// HtmlUnitDriver driver = WeiboCN.getHtmlUnitDriver();
		// HtmlUnitDriver driver = WeiboLogin.getHtmlUnitDriver();
		// WebClient wc = driver.getWebClient();
		// wc.getCookieManager().setCookiesEnabled(true);//开启cookie管理
		// wc.getOptions().setJavaScriptEnabled(true);
		// wc.getOptions().setCssEnabled(true);
		//// driver.manage().deleteAllCookies();
		// for(Cookie c : cSet) {
		// com.gargoylesoftware.htmlunit.util.Cookie tc = new
		// com.gargoylesoftware.htmlunit.util.Cookie(c.getDomain(), c.getName(),
		// c.getValue());
		// wc.getCookieManager().addCookie(tc);
		// }
		// driver.get(sendMsg);

//		URL link = new URL(sendMsg);
//		WebClient wc = new WebClient(BrowserVersion.CHROME);
//		WebRequest request = new WebRequest(link);
//		request.setCharset("UTF-8");
////		 request.setAdditionalHeader("Referer", "https://login.sina.com.cn/sso/login.php?url=https%3A%2F%2Fm.weibo.cn%2Fmsg%2Fchat%3Fuid%3D6460530200&_rand=1524638356.4422&gateway=1&service=sinawap&entry=sinawap&useticket=1&returntype=META&sudaref=&_client_version=0.6.26");//设置请求报文头里的refer字段
//		//// 设置请求报文头里的User-Agent字段
//		request.setAdditionalHeader("Host", "m.weibo.cn");
////		request.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
//		request.setAdditionalHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36");
////		request.setAdditionalHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
////		request.setAdditionalHeader("Connection", "keep-alive");
////		cc = "_T_WM=2b50610c8f6a1168e1710b81fc69c01c; _WEIBO_UID=1592135940; ALF=1527229923; SCF=Aok5RVUWkXEguM5b9qYXVOlbMDrX4vYTdUYCl4EvZvdkBimSeYYen-ZVJE0WzNboFuuOK4q5m-uWvbc1zggk1W0.; SUB=_2A2535FLFDeRhGedL4lAQ8yvFzzyIHXVVJ36NrDV6PUJbktAKLUfQkW1NVEyb1Js06h7WdxmBg4xGl8LLSlNKqP5X; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W5_dTJ116YlZ4B.ejVcipvk5JpX5K-hUgL.Fo2f1Kzpe0-4Sh52dJLoIEMLxKqL1h.LBKMLxKML1-BL1h5LxKBLB.qLBK2LxK-LB.qLBo-41K.t; SUHB=0TPEQjkkAmGaSu; SSOLoginState=1524638357";
//		request.setAdditionalHeader("Cookie", cc);
//		// wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
//		// 其他报文头字段可以根据需要添加
//		wc.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
//		wc.getOptions().setJavaScriptEnabled(true);// 开启js解析。对于变态网页，这个是必须的
//		// wc.getOptions().setCssEnabled(true);//开启css解析。对于变态网页，这个是必须的。
//		wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
//		wc.getOptions().setThrowExceptionOnScriptError(false);
//		wc.getOptions().setTimeout(10000);
//
//		// TestHtmlUnitDriver driver = new
//		// TestHtmlUnitDriver(request,BrowserVersion.CHROME);
//		// driver.setWebClient(wc);
//		// System.out.println(driver.isJavascriptEnabled());
//		//// driver.setJavascriptEnabled(true);
//		// driver.get(sendMsg);
//		//
//		// System.out.println(driver.getPageSource());
//		// WebElement msg =
//		// driver.findElementByCssSelector("textarea.J-textarea.sendmsg_txt.txt");
//		// msg.sendKeys("你好。。。");
//		// Thread.sleep(1000);
//		// WebElement submit =
//		// driver.findElementByCssSelector("button.action-write-msg.L-item-tab");
//		// submit.click();
//
//		HtmlPage p = wc.getPage(request);
//		HtmlTextArea textDom = p.querySelector("textarea.J-textarea.sendmsg_txt.txt");
//		textDom.setNodeValue("nihao你好。。");
//		HtmlButton sendDom = p.querySelector("button.action-write-msg.L-item-tab");
//		sendDom.setAttribute("class", "action-write-msg L-item-tab");
//		sendDom.click();

		 String cc = WeiboLogin.login();
		 HttpPost post = new HttpPost("https://m.weibo.cn/msgDeal/sendMsg?content=888888&uid=6460530200&st=7529bd&fileId=");
//		 HttpPost post = new HttpPost("https://m.weibo.cn/msg/messages?uid=6460530200&page=1");//获取消息
		
//		String cc = WeiboCN.getSinaCookie();
//		 HttpPost post = new HttpPost("https://api.weibo.com/webim/uploadx.json?source=209678993&is_chunk=1");//上传
		 post.setHeader("Cookie", cc);
		 post.setHeader("Host", "m.weibo.cn");
//		 post.setHeader("Origin","https://api.weibo.com");
//		 post.setHeader("Host", "api.weibo.com");
//		 post.setHeader("Referer", "https://login.sina.com.cn/sso/login.php?url=https%3A%2F%2Fm.weibo.cn%2Fmsg%2Fchat%3Fuid%3D6460530200&_rand=1524638356.4422&gateway=1&service=sinawap&entry=sinawap&useticket=1&returntype=META&sudaref=&_client_version=0.6.26");//设置请求报文头里的refer字段
		 post.setHeader("Referer", "https://m.weibo.cn/");//设置请求报文头里的refer字段
//		 post.setHeader("Referer", "https://api.weibo.com/chat/");//设置请求报文头里的refer字段
		  CloseableHttpClient client = HttpClients.createDefault();
		// HttpClient client = new DefaultHttpClient();
		 for(Header h : post.getAllHeaders()) {
		 System.out.println(h.getName() + "-->" + h.getValue());
		 }
		 System.out.println("=================");
		 HttpResponse res = client.execute(post);
		
		 for(Header h : res.getAllHeaders()) {
		 System.out.println(h.getName() + "-->" + h.getValue());
		 }
		
		 if (res.getStatusLine().getStatusCode() == 200) {
		 HttpEntity resEntity = res.getEntity();
		 String message = EntityUtils.toString(resEntity, "utf-8");
		 System.out.println(message);
		 } else {
		 System.out.println("请求失败");
		 }
	}

	// /**
	// * 向指定URL发送GET方法的请求
	// *
	// * @param url
	// * 发送请求的URL
	// * @param param
	// * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	// * @return URL 所代表远程资源的响应结果
	// */
	// public static String sendGet(String url, String param, String cookie) {
	// String result = "";
	// BufferedReader in = null;
	// try {
	// String urlNameString = url + "?" + param;
	// URL realUrl = new URL(urlNameString);
	// // 打开和URL之间的连接
	// HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	// // 设置通用的请求属性
	// conn.setRequestProperty("accept", "*/*");
	// conn.setRequestProperty("connection", "Keep-Alive");
	// conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;
	// Windows NT 5.1;SV1)");
	// conn.setRequestProperty("Cookie", cookie);
	// // 建立实际的连接
	// conn.connect();
	// // 获取所有响应头字段
	// Map<String, List<String>> map = conn.getHeaderFields();
	// // 遍历所有的响应头字段
	// for (String key : map.keySet()) {
	// System.out.println(key + "--->" + map.get(key));
	// }
	// // 定义 BufferedReader输入流来读取URL的响应
	// in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	// String line;
	// while ((line = in.readLine()) != null) {
	// result += line;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // 使用finally块来关闭输入流
	// finally {
	// try {
	// if (in != null) {
	// in.close();
	// }
	// } catch (Exception e2) {
	// e2.printStackTrace();
	// }
	// }
	// return result;
	// }
}

class TestHtmlUnitDriver extends com.crawler.htmlunit.HtmlUnitDriver {

	public TestHtmlUnitDriver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestHtmlUnitDriver(WebRequest request, BrowserVersion version) {
		this(version);
		this.setRequest(request);
	}

	public TestHtmlUnitDriver(boolean enableJavascript) {
		super(enableJavascript);
		// TODO Auto-generated constructor stub
	}

	public TestHtmlUnitDriver(BrowserVersion version, boolean enableJavascript) {
		super(version, enableJavascript);
		// TODO Auto-generated constructor stub
	}

	public TestHtmlUnitDriver(BrowserVersion version) {
		super(version);
		// TODO Auto-generated constructor stub
	}

	public TestHtmlUnitDriver(Capabilities desiredCapabilities, Capabilities requiredCapabilities) {
		super(desiredCapabilities, requiredCapabilities);
		// TODO Auto-generated constructor stub
	}

	public TestHtmlUnitDriver(Capabilities capabilities) {
		super(capabilities);
		// TODO Auto-generated constructor stub
	}

	public WebClient getWebClient() {
		return super.getWebClient();
	}

}
