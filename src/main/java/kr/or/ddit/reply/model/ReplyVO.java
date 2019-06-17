package kr.or.ddit.reply.model;

import java.util.Date;

public class ReplyVO {
	
	private int reply_id;
	private int post_id;
	private String userId;
	private String reply_content;
	private Date reply_dt;
	private String reply_use;
	
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_dt() {
		return reply_dt;
	}
	public void setReply_dt(Date reply_dt) {
		this.reply_dt = reply_dt;
	}
	public String getReply_use() {
		return reply_use;
	}
	public void setReply_use(String reply_use) {
		this.reply_use = reply_use;
	}
	public ReplyVO() {
	
	}
	public ReplyVO(int post_id, String userId, String reply_content) {
		super();
		this.post_id = post_id;
		this.userId = userId;
		this.reply_content = reply_content;
	}
	
}
