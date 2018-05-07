package com.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Set;

public class WeiboLogin {
	private final static String DEFAULT_USERNAME = "aaaaa";
	private final static String DEFAULT_PASSWORD = "vvvvv";
	
	public static String login() {
		return login(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	public static String login(String username,String password) {
//		https://passport.weibo.cn/signin/login
		StringBuilder sb = new StringBuilder();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);
		driver.get("https://passport.weibo.cn/signin/login");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement mobile =  driver.findElementById("loginName");
		mobile.sendKeys(username);
		WebElement pass = driver.findElementById("loginPassword");
		pass.sendKeys(password);
		WebElement submit = driver.findElementById("loginAction");
		submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<Cookie> cookieSet = driver.manage().getCookies();
		driver.close();
		for (Cookie cookie : cookieSet) {
			sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
		}
		System.out.println("==================" + sb.toString());
//		String result = sb.toString();
//		if (result.contains("gsid_CTandWM")) {
//			return result;
//		} else {
//			throw new Exception("weibo login failed");
//		}
		return sb.toString();
	}
	
	public static Set<Cookie> getSinaCookieSet() throws Exception {
		return getSinaCookieSet(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	public static Set<Cookie> getSinaCookieSet(String username, String password) throws Exception {
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);
		driver.get("https://passport.weibo.cn/signin/login");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement mobile =  driver.findElementById("loginName");
		mobile.sendKeys(username);
		WebElement pass = driver.findElementById("loginPassword");
		pass.sendKeys(password);
		WebElement submit = driver.findElementById("loginAction");
		submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<Cookie> cookieSet = driver.manage().getCookies();
		driver.close();
		return cookieSet;
	}
	
	public static HtmlUnitDriver getHtmlUnitDriver() {
		return getHtmlUnitDriver(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	public static HtmlUnitDriver getHtmlUnitDriver(String username,String password) {
//		https://passport.weibo.cn/signin/login
		StringBuilder sb = new StringBuilder();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);
		driver.get("https://passport.weibo.cn/signin/login");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement mobile =  driver.findElementById("loginName");
		mobile.sendKeys(username);
		WebElement pass = driver.findElementById("loginPassword");
		pass.sendKeys(password);
		WebElement submit = driver.findElementById("loginAction");
		submit.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
}
