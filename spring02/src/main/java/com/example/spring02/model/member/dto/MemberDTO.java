package com.example.spring02.model.member.dto;

import java.util.Date;

public class MemberDTO {

	private String userId;
	private String passwd;
	private String name;
	private String email;
	private Date joinDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", passwd=" + passwd + ", name=" + name + ", email=" + email
				+ ", joinDate=" + joinDate + "]";
	}
	
}
