package com.myproj.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserAddress {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long address_id;
		 private int pinCode;

	    private String area;
	    private String city;
	    private String state;
	    private String education;
	    private String gender;

	    private String dob;

	    private String status;

	    
	    @Column(name = "user_id")
	    private Long add_user_id;

		@OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id", nullable = false,insertable=false,updatable=false)
		@JsonIgnore
	    private UserRegistration user;
		
		  public Long getUser_id() {
				return add_user_id;
		}

		public void setUser_id(Long user_id) {
				this.add_user_id = user_id;
		}

		public Long getAddress_id() {
			return address_id;
		}

		public void setAddress_id(Long address_id) {
			this.address_id = address_id;
		}


		public UserRegistration getUser() {
			return user;
		}

		public void setUser(UserRegistration user) {
			this.user = user;
		}
		

	    public int getPinCode() {
			return pinCode;
		}

		public void setPinCode(int pinCode) {
			this.pinCode = pinCode;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getEducation() {
			return education;
		}

		public void setEducation(String education) {
			this.education = education;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}


		public UserAddress() {

	    }


}
