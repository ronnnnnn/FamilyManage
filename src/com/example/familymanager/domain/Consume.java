package com.example.familymanager.domain;

public class Consume {

	private int _id;
	private double money;
	private String time;
	private String consumer;
	private String categoties;
	private String info;
	
	public Consume(int _id,double money,String time,String categories,String consumer,String info){
		this._id = _id;
		this.money = money;
		this.time = time;
		this.categoties = categories;
		this.info = info;
		this.consumer = consumer;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getCategoties() {
		return categoties;
	}
	public void setCategoties(String categoties) {
		this.categoties = categoties;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "金额：" + String.valueOf(money) + "元" + ","+"类别：" + categoties ;
	}
}
