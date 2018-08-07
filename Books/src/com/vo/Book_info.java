package com.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Book_info {
	int book_id ;			//--ID
	String book_name ;		//--ͼ������ 
	String book_author ;	//--���� 
	BigDecimal book_price ; //--�۸� 
	String book_;			//--������
	Date publish_dare ;  	//�������� 
	
	@Override
	public String toString() {
		return "Book_info [book_id=" + book_id + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_price=" + book_price + ", book_=" + book_ + ", publish_dare=" + publish_dare + "]";
	}

	public Book_info(){
	}
	
	public Book_info(int book_id, String book_name, String book_author, BigDecimal book_price, String book_,
			Date publish_dare) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_price = book_price;
		this.book_ = book_;
		this.publish_dare = publish_dare;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public BigDecimal getBook_price() {
		return book_price;
	}

	public void setBook_price(BigDecimal book_price) {
		this.book_price = book_price;
	}

	public String getBook_() {
		return book_;
	}

	public void setBook_(String book_) {
		this.book_ = book_;
	}

	public Date getPublish_dare() {
		return publish_dare;
	}

	public void setPublish_dare(Date publish_dare) {
		this.publish_dare = publish_dare;
	}
	
}
