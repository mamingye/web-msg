package com.weibo.message.utils;

import com.alibaba.dubbo.common.json.JSONObject;
import com.crawler.WeiboLogin;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.weibo.message.utils.Base.BaseMessage;
import com.weibo.message.utils.Base.DataResult;
import com.weibo.message.utils.Base.SKdto;
import com.weibo.message.utils.Base.WordDefined;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.springframework.web.multipart.MultipartFile;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by REDUDPY on 2018/4/26.
 */
public class SendMessageUtil {
    private static final String sendMsg = "https://m.weibo.cn/msg/chat?uid=6460530200";
    private static final Map<String,String> map = new HashMap();
    private static final Map<String,String> skmap = new HashMap();
    public static  DataResult sendMessage(String account,String password,String message,List<String>list){
    	 List<String> listUid = new ArrayList();
        if (StringUtils.isBlank(account)|| StringUtils.isBlank(password) || StringUtils.isBlank(message)|| list==null || list.size()==0){
            return DataResult.build(404, "请求信息不完整");
        }
        try {
        	String skCode = skmap.get(account);
        	if(StringUtils.isBlank(skCode)) {
        		skCode = getStCode(account,password);
        	}
            //skCode = getStCode(account,password);
            if(StringUtils.equalsIgnoreCase("error", skCode) || StringUtils.isBlank(skCode)|| StringUtils.endsWithIgnoreCase(skCode, "systemError")) {
            	return DataResult.build(500, "微博账户用户明活密码错误");
            }
            String cc = map.get(account);
           // String cc = "9999";
            if(StringUtils.isBlank(cc)) {
            	cc = WeiboLogin.login(account,password);
            }
            if(StringUtils.isBlank(cc)){
                return DataResult.build(500, "微博账户用户明活密码错误");
            }
            for (String uid : list) {
            	if(null==uid || uid.equalsIgnoreCase("")) {
            		continue;
            	}
                //String url = "https://m.weibo.cn/msgDeal/sendMsg?content=" + message.trim() + "&uid=" + uid + "&st="+skCode+"&fileId=";
                String url = "https://m.weibo.cn/msgDeal/sendMsg";
                HttpPost post = new HttpPost(url);
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
                nvps.add(new BasicNameValuePair("content", message));  
                nvps.add(new BasicNameValuePair("uid", uid));  
                nvps.add(new BasicNameValuePair("st", skCode));  
                nvps.add(new BasicNameValuePair("fileId","")); 
                //包装成一个Entity对象
                StringEntity entity = new UrlEncodedFormEntity(nvps, "utf-8");
                post.setEntity(entity);
                //System.out.println(url);
                post.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
                post.setHeader("Cookie", cc);
                post.setHeader("Host", "m.weibo.cn");
                post.setHeader("Referer", "https://m.weibo.cn/");//设置请求报文头里的refer字段
                CloseableHttpClient client = HttpClients.createDefault();

              /*  for (Header h : post.getAllHeaders()) {
                    System.out.println(h.getName() + "-->" + h.getValue());
                }
                System.out.println("=================");
               

                for (Header h : res.getAllHeaders()) {
                    System.out.println(h.getName() + "-->" + h.getValue());
                }*/
                HttpResponse res = client.execute(post);
                if (res.getStatusLine().getStatusCode() == 200) {
                    HttpEntity resEntity = res.getEntity();
                    String message2 = EntityUtils.toString(resEntity, "utf-8");
                    BaseMessage base = JsonUtils.jsonToPojo(message2, BaseMessage.class);
                    if(base==null || base.getOk()!=1) {
                    	getStCode(account,password);
                    	listUid.add(uid);
                    	continue;
                    }
                    //System.out.println(message2);
                } else {
                	listUid.add(uid);
                	//System.out.println("请求失败");
                }
            }
        }catch (Exception e){
             //System.out.println(e.getMessage());
             DataResult.build(400, "系统错误",listUid);
        }

        return DataResult.build(200, "信息发送成功",listUid);
    }
    public static String getStCode(String account,String password){
        String stCode = null;
        try {
            String cc = WeiboLogin.login(account,password);
            if(StringUtils.isBlank(cc)) {
            	return "error";
            }
            map.put(account, cc);
            URL link = new URL(sendMsg);
            WebClient wc = new WebClient(BrowserVersion.CHROME);
            WebRequest request = new WebRequest(link);
            request.setCharset("UTF-8");
            request.setAdditionalHeader("Referer", "https://login.sina.com.cn/sso/login.php?url=https%3A%2F%2Fm.weibo.cn%2Fmsg%2Fchat%3Fuid%3D6460530200&_rand=1524638356.4422&gateway=1&service=sinawap&entry=sinawap&useticket=1&returntype=META&sudaref=&_client_version=0.6.26");//设置请求报文头里的refer字段
//		//// 设置请求报文头里的User-Agent字段
            request.setAdditionalHeader("Host", "m.weibo.cn");
            request.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
            request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36");
            request.setAdditionalHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*;q=0.8");
            request.setAdditionalHeader("Connection", "keep-alive");
            //cc = "_T_WM=2b50610c8f6a1168e1710b81fc69c01c; _WEIBO_UID=1592135940; ALF=1527229923; SCF=Aok5RVUWkXEguM5b9qYXVOlbMDrX4vYTdUYCl4EvZvdkBimSeYYen-ZVJE0WzNboFuuOK4q5m-uWvbc1zggk1W0.; SUB=_2A2535FLFDeRhGedL4lAQ8yvFzzyIHXVVJ36NrDV6PUJbktAKLUfQkW1NVEyb1Js06h7WdxmBg4xGl8LLSlNKqP5X; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W5_dTJ116YlZ4B.ejVcipvk5JpX5K-hUgL.Fo2f1Kzpe0-4Sh52dJLoIEMLxKqL1h.LBKMLxKML1-BL1h5LxKBLB.qLBK2LxK-LB.qLBo-41K.t; SUHB=0TPEQjkkAmGaSu; SSOLoginState=1524638357";
            request.setAdditionalHeader("Cookie", cc);
            //wc.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
            // 其他报文头字段可以根据需要添加
            wc.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
            wc.getOptions().setJavaScriptEnabled(false);// 开启js解析。对于变态网页，这个是必须的
            wc.getOptions().setCssEnabled(false);//开启css解析。对于变态网页，这个是必须的。
            wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
            wc.getOptions().setThrowExceptionOnScriptError(false);
            wc.getOptions().setTimeout(10000);

            // System.out.println(driver.getPageSource());
            HtmlPage p = wc.getPage(request);
            Document doc = Jsoup.parse(p.asXml());
            Elements e = doc.getElementsByTag("script").eq(1);
            for (Element element : e) {
	            /*取得JS变量数组*/
                String[] data = element.data().toString().split("var");
                for (String variable : data) {
                    if (variable.contains("=")) {

	                     /*取到满足条件的JS变量*/
                        if (variable.contains("$CONFIG.exp")) {
                            variable = variable.substring(variable.indexOf("$CONFIG.exp"), variable.length() - 1);
                            variable = variable.substring(0, variable.indexOf(";"));
                            String[] kvp = variable.split("=");
                            System.out.println(kvp[1].toString());
                            SKdto dto = JsonUtils.jsonToPojo(kvp[1].toString(), SKdto.class);
                            stCode = dto.getSt();
         
                            skmap.put(account, stCode);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return "systemError";
        }
        return stCode;
    }
}
