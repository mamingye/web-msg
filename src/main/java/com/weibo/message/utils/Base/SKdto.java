package com.weibo.message.utils.Base;

import java.io.Serializable;

public class SKdto implements Serializable{
	private Integer cacheHit;
	private Boolean isLogin;
	private String deviceType;
	private String browserType;
	private Boolean online;
	private String wm;
	private String st;
	private Integer isInClient;
	private Integer isWechat;
	private Integer hideHeaderBanner;
	private String request_key;
	private Integer hideFooterPage;
	private Integer reply_like;
	private String uid;
	/**
	 * @return the cacheHit
	 */
	public Integer getCacheHit() {
		return cacheHit;
	}
	/**
	 * @param cacheHit the cacheHit to set
	 */
	public void setCacheHit(Integer cacheHit) {
		this.cacheHit = cacheHit;
	}
	/**
	 * @return the isLogin
	 */
	public Boolean getIsLogin() {
		return isLogin;
	}
	/**
	 * @param isLogin the isLogin to set
	 */
	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the browserType
	 */
	public String getBrowserType() {
		return browserType;
	}
	/**
	 * @param browserType the browserType to set
	 */
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	/**
	 * @return the online
	 */
	public Boolean getOnline() {
		return online;
	}
	/**
	 * @param online the online to set
	 */
	public void setOnline(Boolean online) {
		this.online = online;
	}
	/**
	 * @return the wm
	 */
	public String getWm() {
		return wm;
	}
	/**
	 * @param wm the wm to set
	 */
	public void setWm(String wm) {
		this.wm = wm;
	}
	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}
	/**
	 * @param st the st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}
	/**
	 * @return the isInClient
	 */
	public Integer getIsInClient() {
		return isInClient;
	}
	/**
	 * @param isInClient the isInClient to set
	 */
	public void setIsInClient(Integer isInClient) {
		this.isInClient = isInClient;
	}
	/**
	 * @return the isWechat
	 */
	public Integer getIsWechat() {
		return isWechat;
	}
	/**
	 * @param isWechat the isWechat to set
	 */
	public void setIsWechat(Integer isWechat) {
		this.isWechat = isWechat;
	}
	/**
	 * @return the hideHeaderBanner
	 */
	public Integer getHideHeaderBanner() {
		return hideHeaderBanner;
	}
	/**
	 * @param hideHeaderBanner the hideHeaderBanner to set
	 */
	public void setHideHeaderBanner(Integer hideHeaderBanner) {
		this.hideHeaderBanner = hideHeaderBanner;
	}
	/**
	 * @return the request_key
	 */
	public String getRequest_key() {
		return request_key;
	}
	/**
	 * @param request_key the request_key to set
	 */
	public void setRequest_key(String request_key) {
		this.request_key = request_key;
	}
	/**
	 * @return the hideFooterPage
	 */
	public Integer getHideFooterPage() {
		return hideFooterPage;
	}
	/**
	 * @param hideFooterPage the hideFooterPage to set
	 */
	public void setHideFooterPage(Integer hideFooterPage) {
		this.hideFooterPage = hideFooterPage;
	}
	/**
	 * @return the reply_like
	 */
	public Integer getReply_like() {
		return reply_like;
	}
	/**
	 * @param reply_like the reply_like to set
	 */
	public void setReply_like(Integer reply_like) {
		this.reply_like = reply_like;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SKdto [cacheHit=" + cacheHit + ", isLogin=" + isLogin + ", deviceType=" + deviceType + ", browserType="
				+ browserType + ", online=" + online + ", wm=" + wm + ", st=" + st + ", isInClient=" + isInClient
				+ ", isWechat=" + isWechat + ", hideHeaderBanner=" + hideHeaderBanner + ", request_key=" + request_key
				+ ", hideFooterPage=" + hideFooterPage + ", reply_like=" + reply_like + ", uid=" + uid + "]";
	}
	

}
