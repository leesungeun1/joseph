package net.crawl.db;

public class search_list_Bean {	
	private String search_com_No;//번호
	private String com_qual;//업체 지원자격
	private String com_preex;//업체 우대사항
	private String com_name;//지원자격포함 기업명
	private String com_link;//지원자격포함 기업링크
	//재구축
	public String getSearch_com_No() {
		return search_com_No;
	}
	public void setSearch_com_No(String search_com_No) {
		this.search_com_No = search_com_No;
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
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_link() {
		return com_link;
	}
	public void setCom_link(String com_link) {
		this.com_link = com_link;
	}
	
	
}