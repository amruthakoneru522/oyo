package com.infinite.OyoBookingProject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Billing")
public class Billing {
	
	
	@Id
	@Column(name="bid")
	private int bid;
	
	@Column(name="bookId")
	private String bookId;
	
	@Column(name="roomId")
	private String roomId;
	
	@Column(name="noOfDays")
	private int noOfDays;
	
	@Column(name="billAmt")
	private long billAmt;

	
	
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public long getBillAmt() {
		return billAmt;
	}

	public void setBillAmt(long billAmt) {
		this.billAmt = billAmt;
	}

	
}

