package kr.or.ddit.myfile.model;

public class MyfileVO {
	private int myfile_id;
	private int post_id;
	private String myfile_path;
	private String myfile_name;
	public int getMyfile_id() {
		return myfile_id;
	}
	public void setMyfile_id(int myfile_id) {
		this.myfile_id = myfile_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getMyfile_path() {
		return myfile_path;
	}
	public void setMyfile_path(String myfile_path) {
		this.myfile_path = myfile_path;
	}
	public String getMyfile_name() {
		return myfile_name;
	}
	public void setMyfile_name(String myfile_name) {
		this.myfile_name = myfile_name;
	}
	public MyfileVO(int post_id, String myfile_path, String myfile_name) {
		super();
		this.post_id = post_id;
		this.myfile_path = myfile_path;
		this.myfile_name = myfile_name;
	}
	public MyfileVO() {
		
	}
	
}
