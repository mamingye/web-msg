package com.weibo.message.utils.Base;

import java.io.Serializable;

public class BaseMessage implements Serializable {
	private Integer ok;
	private String msg;
	private String url;
	/**
	 * @return the ok
	 */
	public Integer getOk() {
		return ok;
	}
	/**
	 * @param ok the ok to set
	 */
	public void setOk(Integer ok) {
		this.ok = ok;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	

}
