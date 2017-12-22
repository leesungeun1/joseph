package net.crawl.db;

public class search_qual_Bean {	
	private String search_com_No;//번호
	private int No;//사용자번호
	private String com_qual;//업체지원자격
	private String com_preex;//업체우대사항
	private int com_frequency;//빈도수
	
	public String getSearch_com_No() {
		return search_com_No;
	}
	public void setSearch_com_No(String string) {
		this.search_com_No = string;
	}
	public String getCom_qual() {
		return com_qual;
	}
	public void setCom_qual(String com_qual) {
		this.com_qual = com_qual;
	}
	public String getCom_preex() {
		return com_preex;
	}
	public void setCom_preex(String com_preex) {
		this.com_preex = com_preex;
	}
	public int getCom_frequency() {
		return com_frequency;
	}
	public void setCom_frequency(int com_frequency) {
		this.com_frequency = com_frequency;
	}
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	
	
}