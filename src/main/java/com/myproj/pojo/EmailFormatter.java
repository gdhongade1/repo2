package com.myproj.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmailFormatter {
	@Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	int email_id;
	String toEmail;
	String fromEmail;
	String ccEmail;
	String bccEmail;
	String attachment;
	String subject;
	String fromCustomer;
	String subjectCustomer;
	String actualDate;
	String actualTime;
	boolean isTaskCompleted;
	String labels;
	boolean isFavourite;
	boolean isVisible;
	String bodyContent;
	
		@Column(name = "user_id")
	    private Long add_user_id;
	
	 	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	    @JoinColumn(name = "user_id", nullable = false)
	    @JsonIgnore
	    private UserRegistration userEmail;
	 
	public int getEmail_id() {
		return email_id;
	}
	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getCcEmail() {
		return ccEmail;
	}
	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}
	public String getBccEmail() {
		return bccEmail;
	}
	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFromCustomer() {
		return fromCustomer;
	}
	public void setFromCustomer(String fromCustomer) {
		this.fromCustomer = fromCustomer;
	}
	public String getSubjectCustomer() {
		return subjectCustomer;
	}
	public void setSubjectCustomer(String subjectCustomer) {
		this.subjectCustomer = subjectCustomer;
	}
	public String getActualDate() {
		return actualDate;
	}
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}
	public String getActualTime() {
		return actualTime;
	}
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}
	public boolean isTaskCompleted() {
		return isTaskCompleted;
	}
	public void setTaskCompleted(boolean isTaskCompleted) {
		this.isTaskCompleted = isTaskCompleted;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public String getBodyContent() {
		return bodyContent;
	}
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}
	public Long getUser_id() {
		return add_user_id;
	}
	public void setUser_id(Long user_id) {
		this.add_user_id = user_id;
	}
	
	/*public Long getUser_id() {
		return getUserEmail().getUser_reg_id();
	}
	public void setUser_id(Long user_id) {
		getUserEmail().setUser_reg_id(user_id);
	}*/
	public UserRegistration getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(UserRegistration userEmail) {
		this.userEmail = userEmail;
	}
	
}
