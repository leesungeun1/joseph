package com.naver.model;

public class ComBean {//댓글 자바 저장빈 클래스

	private int comment_no;
	private String comment_name;
	private String comment_cont;
	private String comment_date;
	private int g_no;
	
	/* 방명록 */
	private String g_name;
	private String g_title;
	private String g_pwd;
	private String g_cont;
	private int g_hit;
	private String g_date;	
	
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	public String getG_pwd() {
		return g_pwd;
	}
	public void setG_pwd(String g_pwd) {
		this.g_pwd = g_pwd;
	}
	public String getG_cont() {
		return g_cont;
	}
	public void setG_cont(String g_cont) {
		this.g_cont = g_cont;
	}
	public int getG_hit() {
		return g_hit;
	}
	public void setG_hit(int g_hit) {
		this.g_hit = g_hit;
	}
	public String getG_date() {
		return g_date;
	}
	public void setG_date(String g_date) {
		this.g_date = g_date;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_cont() {
		return comment_cont;
	}
	public void setComment_cont(String comment_cont) {
		this.comment_cont = comment_cont;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}		
}
