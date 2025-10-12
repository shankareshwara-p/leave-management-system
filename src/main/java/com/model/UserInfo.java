package com.model;


public class UserInfo {

    
    private int id;

   
    private String email;

    
    private String password;

   
    private String role;

   
    private String firstName;

   
    private String lastName;

   
    

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
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

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", firstName="
				+ firstName + ", lastName=" + lastName + ", getId()=" + getId() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", getRole()=" + getRole() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

   
}