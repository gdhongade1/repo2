package com.myproj.pojo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserRegistration {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_reg_id;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String password;
    
    private String confirmPassword;

    @OneToOne(fetch = FetchType.EAGER,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private UserAddress userAddress;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEmail", cascade = CascadeType.ALL)
    private List<EmailFormatter> email;

    // Hibernate requires a no-arg constructor
    public UserRegistration() {

    }


	public Long getUser_reg_id() {
		return user_reg_id;
	}


	public void setUser_reg_id(Long user_reg_id) {
		this.user_reg_id = user_reg_id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public UserAddress getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public List<EmailFormatter> getEmail() {
		return email;
	}


	public void setEmail(List<EmailFormatter> email) {
		this.email = email;
	}
    

}
