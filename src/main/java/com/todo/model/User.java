package com.todo.model;

public class User {

	private int userid;
	private String fname;
	private String lname;
	private String email;
	private String uname;
	private String regdate;

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", fname=" + fname + ", lname="
				+ lname + ", email=" + email + ", uname=" + uname
				+ ", regdate=" + regdate + "]";
	}

}
