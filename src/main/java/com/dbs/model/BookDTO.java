package com.dbs.model;

import java.io.Serializable;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer bookid;
	private String bookname;
	private StudentDTO student;
	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	

}
