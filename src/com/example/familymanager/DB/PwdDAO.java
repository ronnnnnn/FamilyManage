package com.example.familymanager.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.familymanager.domain.Tb_pwd;


public class PwdDAO {
	private DBHelper helper;
	private SQLiteDatabase db;

	public PwdDAO(Context context)
	{
		helper = new DBHelper(context);
	}


	public void add(Tb_pwd tb_pwd) {
		db = helper.getWritableDatabase();
	
		db.execSQL("insert into tb_pwd (password) values (?)",
				new Object[] { tb_pwd.getPassword() });
	}


	public void update(Tb_pwd tb_pwd) {
		db = helper.getWritableDatabase();
		
		db.execSQL("update tb_pwd set password = ?",
				new Object[] { tb_pwd.getPassword() });
	}


	public Tb_pwd find() {
		db = helper.getWritableDatabase();
	
		Cursor cursor = db.rawQuery("select password from tb_pwd", null);
		if (cursor.moveToNext())
		{
			
			return new Tb_pwd(cursor.getString(cursor
					.getColumnIndex("password")));
		}
		return null;
	}

	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(password) from tb_pwd", null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}
}
