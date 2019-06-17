package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVO {
	
	private int board_id;
	private String userId;
	private String board_name;
	private String board_use;
	private Date board_dt;
	
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

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_use() {
		return board_use;
	}

	public void setBoard_use(String board_use) {
		this.board_use = board_use;
	}

	public Date getBoard_dt() {
		return board_dt;
	}

	public void setBoard_dt(Date board_dt) {
		this.board_dt = board_dt;
	}

	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", userId=" + userId
				+ ", board_name=" + board_name + ", board_use=" + board_use
				+ ", board_dt=" + board_dt + "]";
	}
	
	public BoardVO(String userId, String board_name, String board_use) {
		super();
		this.userId = userId;
		this.board_name = board_name;
		this.board_use = board_use;
	}

	public BoardVO(int board_id, String board_name, String board_use) {
		super();
		this.board_id = board_id;
		this.board_name = board_name;
		this.board_use = board_use;
	}

	public BoardVO() {
		
	}
	
}
