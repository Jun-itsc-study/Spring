package com.koreait.dto;

import org.apache.ibatis.type.Alias;

@Alias("comment")
public class BoardCommentDTO {
	private int cno;
	private int bno;
	private String content;
	private String writer;
	private String cdate;
	private int clike;
	private int cDislike;

	public BoardCommentDTO() {
	}

	public BoardCommentDTO(int cno, int bno, String content, String writer, String date, int like, int cDislike) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.content = content;
		this.writer = writer;
		this.cdate = date;
		this.clike = like;
		this.cDislike = cDislike;
	}

	public BoardCommentDTO(int bno, String content, String writer) {
		super();
		this.bno = bno;
		this.content = content;
		this.writer = writer;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getClike() {
		return clike;
	}

	public void setClike(int clike) {
		this.clike = clike;
	}

	public int getcDislike() {
		return cDislike;
	}

	public void setcDislike(int cDislike) {
		this.cDislike = cDislike;
	}

	
	
}
