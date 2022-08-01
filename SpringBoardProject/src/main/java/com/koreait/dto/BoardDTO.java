package com.koreait.dto;

import org.apache.ibatis.type.Alias;

@Alias("board")
public class BoardDTO {
	private int bno;
	private String title;
	private String writer;
	private String nick;
	private int bCount;
	private String bDate;
	private int bLike;
	private int bDislike;
	private String content;
	private int cCount;
	
	public BoardDTO() {	}

	public BoardDTO(String title, String writer, String content) {
		super();
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public BoardDTO(int bno, String title, String writer, String nick, int bCount, String bDate, int bLike, int bDislike,
			int cCount) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.nick = nick;
		this.bCount = bCount;
		this.bDate = bDate;
		this.bLike = bLike;
		this.bDislike = bDislike;
		this.cCount = cCount;
	}

	public BoardDTO(int bno, String title, String writer, String nick, int bCount, String bDate, int bLike, int bDislike,
			String content, int cCount) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.nick = nick;
		this.bCount = bCount;
		this.bDate = bDate;
		this.bLike = bLike;
		this.bDislike = bDislike;
		this.content = content;
		this.cCount = cCount;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getbCount() {
		return bCount;
	}

	public void setbCount(int bCount) {
		this.bCount = bCount;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public int getbLike() {
		return bLike;
	}

	public void setbLike(int bLike) {
		this.bLike = bLike;
	}

	public int getbDislike() {
		return bDislike;
	}

	public void setbDislike(int bDislike) {
		this.bDislike = bDislike;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getcCount() {
		return cCount;
	}

	public void setcCount(int cCount) {
		this.cCount = cCount;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", nick=" + nick + ", bCount="
				+ bCount + ", bDate=" + bDate + ", bLike=" + bLike + ", bDislike=" + bDislike + ", content=" + content
				+ ", cCount=" + cCount + "]";
	}

	
	
	
	
}
