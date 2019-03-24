package com.myproj.pojo;


import java.util.Date;

import javax.mail.Address;

public class Email {
	Address from[];
	Address to[];
	Address cc[];
	Address bcc[];
	String subject;
	Date date ;
	
	public Address[] getFrom() {
		return from;
	}
	public void setFrom(Address[] from) {
		this.from = from;
	}
	
	public Address[] getTo() {
		return to;
	}
	public void setTo(Address[] to) {
		this.to = to;
	}
	public Address[] getCc() {
		return cc;
	}
	public void setCc(Address[] cc) {
		this.cc = cc;
	}
	public Address[] getBcc() {
		return bcc;
	}
	public void setBcc(Address[] bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
