package com.crawler;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class WeiboCN {

	private final static String DEFAULT_USERNAME = "18303010556";
	private final static String DEFAULT_PASSWORD = "dou061755";
	
	public static String getSinaCookie() throws Exception {
		return getSinaCookie(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
	public static String getSinaCookie(String username, String password) throws Exception {
		
//	    //模拟浏览器打开一个目标网址  
//		String url = "https://weibo.com/login.php";
//	    HtmlPage rootPage = client.getPage(url);
//		HttpClientBuilder
		StringBuilder sb = new StringBuilder();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
//		HtmlUnitDriver driver = new HtmlUnitDriver(true);
		driver.setJavascriptEnabled(true);
//		driver.get("https://weibo.com/login.php");
		driver.get("http://login.sina.com.cn/");
//		WebElement mobile = driver.findElementByCssSelector("input[name=username]");
//		System.out.println("=================driver.getPageSource()" + driver.getPageSource());
//		Thread.sleep(3000);
//		WebElement mobile =  driver.findElementById("loginname");
		WebElement mobile =  driver.findElementById("username");
		mobile.sendKeys(username);
//		WebElement pass = driver.findElementByCssSelector("input[name=password]");
//		WebElement pass = driver.findElementByName("password");
		WebElement pass = driver.findElementById("password");
		pass.sendKeys(password);
		// WebElement rem = driver.findElementByCssSelector("input[name=remember]");
		// rem.click();
		// WebElement submit = driver.findElementByCssSelector(".W_btn_a btn_32px");
//		WebElement submit = driver.findElementByXPath("//input[@class='W_btn_a btn_32px']");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_32px");
//		WebElement submit = driver.findElementByCssSelector("a.W_btn_a.btn_32px");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_34px");
//		WebElement submit = driver.findElement(By.className("W_btn_a btn_34px"));
//		WebElement submit = driver.findElement(By.xpath("//*[text()='登录']"));
		WebElement submit = driver.findElementByCssSelector("input.W_btn_a.btn_34px");
		submit.click();
		Thread.sleep(2000);
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
		
//	    //模拟浏览器打开一个目标网址  
//		String url = "https://weibo.com/login.php";
//	    HtmlPage rootPage = client.getPage(url);
//		HttpClientBuilder
		StringBuilder sb = new StringBuilder();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
//		HtmlUnitDriver driver = new HtmlUnitDriver(true);
		driver.setJavascriptEnabled(true);
//		driver.get("https://weibo.com/login.php");
		driver.get("http://login.sina.com.cn/");
//		WebElement mobile = driver.findElementByCssSelector("input[name=username]");
//		System.out.println("=================driver.getPageSource()" + driver.getPageSource());
//		Thread.sleep(3000);
//		WebElement mobile =  driver.findElementById("loginname");
		WebElement mobile =  driver.findElementById("username");
		mobile.sendKeys(username);
//		WebElement pass = driver.findElementByCssSelector("input[name=password]");
//		WebElement pass = driver.findElementByName("password");
		WebElement pass = driver.findElementById("password");
		pass.sendKeys(password);
		// WebElement rem = driver.findElementByCssSelector("input[name=remember]");
		// rem.click();
		// WebElement submit = driver.findElementByCssSelector(".W_btn_a btn_32px");
//		WebElement submit = driver.findElementByXPath("//input[@class='W_btn_a btn_32px']");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_32px");
//		WebElement submit = driver.findElementByCssSelector("a.W_btn_a.btn_32px");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_34px");
//		WebElement submit = driver.findElement(By.className("W_btn_a btn_34px"));
//		WebElement submit = driver.findElement(By.xpath("//*[text()='登录']"));
		WebElement submit = driver.findElementByCssSelector("input.W_btn_a.btn_34px");
		submit.click();
		Thread.sleep(2000);
		Set<Cookie> cookieSet = driver.manage().getCookies();
		driver.close();
		return cookieSet;
	}
	
	public static HtmlUnitDriver getHtmlUnitDriver() throws Exception {
		return getHtmlUnitDriver(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}
	
public static HtmlUnitDriver getHtmlUnitDriver(String username, String password) throws Exception {
		
//	    //模拟浏览器打开一个目标网址  
//		String url = "https://weibo.com/login.php";
//	    HtmlPage rootPage = client.getPage(url);
//		HttpClientBuilder
		StringBuilder sb = new StringBuilder();
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
//		HtmlUnitDriver driver = new HtmlUnitDriver(true);
		driver.setJavascriptEnabled(true);
//		driver.get("https://weibo.com/login.php");
		driver.get("http://login.sina.com.cn/");
//		WebElement mobile = driver.findElementByCssSelector("input[name=username]");
//		System.out.println("=================driver.getPageSource()" + driver.getPageSource());
//		Thread.sleep(3000);
//		WebElement mobile =  driver.findElementById("loginname");
		WebElement mobile =  driver.findElementById("username");
		mobile.sendKeys(username);
//		WebElement pass = driver.findElementByCssSelector("input[name=password]");
//		WebElement pass = driver.findElementByName("password");
		WebElement pass = driver.findElementById("password");
		pass.sendKeys(password);
		// WebElement rem = driver.findElementByCssSelector("input[name=remember]");
		// rem.click();
		// WebElement submit = driver.findElementByCssSelector(".W_btn_a btn_32px");
//		WebElement submit = driver.findElementByXPath("//input[@class='W_btn_a btn_32px']");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_32px");
//		WebElement submit = driver.findElementByCssSelector("a.W_btn_a.btn_32px");
//		WebElement submit = driver.findElementByClassName("W_btn_a btn_34px");
//		WebElement submit = driver.findElement(By.className("W_btn_a btn_34px"));
//		WebElement submit = driver.findElement(By.xpath("//*[text()='登录']"));
		WebElement submit = driver.findElementByCssSelector("input.W_btn_a.btn_34px");
		submit.click();
		Thread.sleep(2000);
//		Set<Cookie> cookieSet = driver.manage().getCookies();
//		driver.close();
		return driver;
	}

}
