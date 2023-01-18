package com.pojo;

public class Account {

	//| actno | name    | email          | city    | balance
	
	private int actno;
	private String name;
	private String email;
	private String city;
	private Double balance;
	public Account(int actno, String name, String email, String city, Double balance) {
		super();
		this.actno = actno;
		this.name = name;
		this.email = email;
		this.city = city;
		this.balance = balance;
	}
	public Account() {
		super();
	}
	public int getActno() {
		return actno;
	}
	public void setActno(int actno) {
		this.actno = actno;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [actno=" + actno + ", name=" + name + ", email=" + email + ", city=" + city + ", balance="
				+ balance + "]";
	}
	
	
}
