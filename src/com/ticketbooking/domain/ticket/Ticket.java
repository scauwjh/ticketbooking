package com.ticketbooking.domain.ticket;

import java.io.Serializable;
import java.util.Date;

/** 
 * @author wjh E-mail: 472174314@qq.com
 * ticket持久化类，对应数据库的t_ticket表
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ticketId;
	
	private Long userId;//哪个user发布的
	
	private String ticketName;
	
	private Float ticketPrice;

	private Float originalPrice;//原价
	
	private Date onTime;//上映时间
	
	private String country;
	
	private String filmType;//电影的类型（喜剧）
	
	private String language;
	
	private String ticketIntro;
	
	private String ticketImg;//剧照
	
	private String prevue;//预告片
	
	private Date releaseTime;//发布时间


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Date getOnTime() {
		return onTime;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTicketIntro() {
		return ticketIntro;
	}

	public void setTicketIntro(String ticketIntro) {
		this.ticketIntro = ticketIntro;
	}

	public String getTicketImg() {
		return ticketImg;
	}

	public void setTicketImg(String ticketImg) {
		this.ticketImg = ticketImg;
	}

	public String getPrevue() {
		return prevue;
	}

	public void setPrevue(String prevue) {
		this.prevue = prevue;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
}
