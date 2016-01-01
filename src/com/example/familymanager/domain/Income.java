package com.example.familymanager.domain;

public class Income {
  
	private int _id;
	private String time;
	private String info;
	private double money;
	private String categories;
	private String payer;
	
	public Income(int _id,double money,String time,String categories,String payer ,String info) {
		// TODO Auto-generated constructor stub
		this._id=_id;
		this.money = money;
		this.payer = payer;
		this.info = info;
		this.time = time;
		this.categories = categories;
		
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "金额：" + String.valueOf(money) + "元" + ","+"类别：" + categories ;
	}
	
}
