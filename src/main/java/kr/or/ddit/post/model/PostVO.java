package kr.or.ddit.post.model;

import java.util.Date;

public class PostVO {
	
	private int post_id;
	private int board_id;
	private String userId;
	private int	post_parent;
	private String post_title;
	private String post_content;
	private Date post_dt;
	private String post_use;
	private int lv;
	private int group_seq;
	
	public PostVO(){
		
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPost_parent() {
		return post_parent;
	}
	public void setPost_parent(int post_parent) {
		this.post_parent = post_parent;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public Date getPost_dt() {
		return post_dt;
	}
	public void setPost_dt(Date post_dt) {
		this.post_dt = post_dt;
	}
	public String getPost_use() {
		return post_use;
	}
	public void setPost_use(String post_use) {
		this.post_use = post_use;
	}
	public int getGroup_seq() {
		return group_seq;
	}
	public void setGroup_seq(int group_seq) {
		this.group_seq = group_seq;
	}

	public PostVO(int board_id, String userId, int post_parent,
			String post_title, String post_content) {
		super();
		this.board_id = board_id;
		this.userId = userId;
		this.post_parent = post_parent;
		this.post_title = post_title;
		this.post_content = post_content;
	}
	public PostVO(int post_id, String post_title, String post_content,
			String post_use) {
		super();
		this.post_id = post_id;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_use = post_use;
	}
	public PostVO(int post_id, int group_seq) {
		super();
		this.post_id = post_id;
		this.group_seq = group_seq;
	}
	@Override
	public String toString() {
		return "PostVO [post_id=" + post_id + ", board_id=" + board_id
				+ ", userId=" + userId + ", post_parent=" + post_parent
				+ ", post_title=" + post_title + ", post_content="
				+ post_content + ", post_dt=" + post_dt + ", post_use="
				+ post_use + ", lv=" + lv + ", group_seq=" + group_seq + "]";
	}
	
	
}
