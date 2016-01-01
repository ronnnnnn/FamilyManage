package com.example.familymanager.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "manage.db";
	private static final int DATABASE_VERSION = 4;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	
		db.execSQL("create table tb_income (_id integer primary key,money decimal,time varchar(10),"
				+ "categories varchar(10),payer varchar(100),info varchar(200))");

		db.execSQL("create table tb_consume (_id integer primary key,money decimal,time varchar(10),"
				+ "categories varchar(10),consumer varchar(100),info varchar(200))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("create table tb_pwd (password varchar(20))");
		db.execSQL("create table tb_flag (_id integer primary key,flag varchar(200))");
	}

}
