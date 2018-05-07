package com.weibo.message.utils.Base;

import java.io.Serializable;

public class LookDto implements Serializable{
     private String phrase;
     private String type;
     private String url;
     private Boolean hot;
     private Boolean common;
     private String category;
     private String icon;
     private String value;
     private String picid;
	/**
	 * @return the phrase
	 */
	public String getPhrase() {
		return phrase;
	}
	/**
	 * @param phrase the phrase to set
	 */
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	/**
	 * @return the hot
	 */
	public Boolean getHot() {
		return hot;
	}
	/**
	 * @param hot the hot to set
	 */
	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	/**
	 * @return the common
	 */
	public Boolean getCommon() {
		return common;
	}
	/**
	 * @param common the common to set
	 */
	public void setCommon(Boolean common) {
		this.common = common;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the picid
	 */
	public String getPicid() {
		return picid;
	}
	/**
	 * @param picid the picid to set
	 */
	public void setPicid(String picid) {
		this.picid = picid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LookDto [phrase=" + phrase + ", type=" + type + ", url=" + url + ", hot=" + hot + ", common=" + common
				+ ", category=" + category + ", icon=" + icon + ", value=" + value + ", picid=" + picid + "]";
	}
	
     
}
