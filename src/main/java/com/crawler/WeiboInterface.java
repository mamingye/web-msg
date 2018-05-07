package com.crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeiboInterface {
	
	public static String getResponseData(String str,Map<String,Object> params) throws Exception{
		String response = "";
		try {
//			URL url = new URL("http://localhost/cm/ZqAppServlet");
			URL url = new URL(str);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			conn.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);
			// 设置是否从缓存读
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charset", "UTF-8");

			conn.connect();
			// 输入POST表单参数
			StringBuffer content = new StringBuffer(200);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			bw.write(content.toString());
			bw.flush();
			bw.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = "";
			
			while ((line = br.readLine()) != null) {
				response += line;
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) throws Exception {
//		WeiboCrawler crawler = new WeiboCrawler("weibo_crawler", false);
//		crawler.setGetType(true);
//		String str = "https://weibo.com/p/aj/proxy?api=http://i.huati.weibo.com/pcpage/papp&ajwvr=6&atype=video&viewer_uid=1592135940&since_id=&page_id=10080802a71f3c6b291273136d141a0e3b77d1&page=2&ajax_call=1&appname=album&module=feed&is_feed=1&__rnd=1523612825885";
//		crawler.addSeed(str);
//		crawler.setGetVideo(true);
//		crawler.setThreads(1);
//		crawler.start(1);
//		String response = WeiboInterface.getResponseData(str, new HashMap());
//		System.out.println(response);
		
		String str = "//weibo.com/p/10080802a71f3c6b291273136d141a0e3b77d1";
		System.out.println(str.replaceAll("//weibo.com/p/", ""));
		
	}
	
}
