package com.koreait.dto;

import org.apache.ibatis.type.Alias;

@Alias("qna")
public class BoardQnaDTO {
	private int qno;
	private String title;
	private String content;
	private String writer;
	private String wdate;
	private String response;
	private int status;
	private int page;
	public BoardQnaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "BoardQnaDTO [qno=" + qno + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", wdate=" + wdate + ", response=" + response + ", status=" + status + ", page=" + page + "]";
	}
	
	
	
}
