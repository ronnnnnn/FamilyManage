package com.example.familymanager.DB;

import java.util.ArrayList;
import java.util.List;

import com.example.familymanager.domain.Consume;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConsumeDAO {

	private DBHelper helper;
	private SQLiteDatabase db;
	
	public ConsumeDAO(Context context){	
		helper = new DBHelper(context);		
	}
	
	public void add(Consume consume){
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into tb_consume (_id,money,time,categories,consumer,info) values (?,?,?,?,?,?)",
				new Object[] { consume.get_id(), consume.getMoney(),
						consume.getTime(), consume.getCategoties(),
						consume.getConsumer(), consume.getInfo() });
	}
	
	public void update(Consume consume) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update tb_consume set money = ?,time = ?,categories = ?,consumer = ?,info = ? where _id = ?",
				new Object[] { consume.getMoney(),
						consume.getTime(), consume.getCategoties(),
						consume.getConsumer(), consume.getInfo(),
						consume.get_id() });
	}

	public Consume find(int id) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db
				.rawQuery(
						"select _id,money,time,categories,consumer,info from tb_consume where _id = ?",
						new String[] { String.valueOf(id) });
		if (cursor.moveToNext())
		{
			
			return new Consume(
					cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("categories")),
					cursor.getString(cursor.getColumnIndex("consumer")),
					cursor.getString(cursor.getColumnIndex("info")));
		}
		return null;
	}
	
	public void detele(Integer... ids) {
		if (ids.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			
			db.execSQL("delete from tb_consume where _id in (" + sb + ")",
					(Object[]) ids);
		}
	}
	
	public List<Consume> getScrollData(int start, int count) {
		List<Consume> dataList = new ArrayList<Consume>();
		db = helper.getWritableDatabase();
	
		Cursor cursor = db.rawQuery("select * from tb_consume limit ?,?",
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())
		{
		
			dataList.add(new Consume(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("categories")), cursor.getString(cursor
					.getColumnIndex("consumer")), cursor.getString(cursor
					.getColumnIndex("info"))));
		}
		return dataList;
	}
	
	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from tb_consume",
				null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}


	public int getMaxId() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select max(_id) from tb_consume", null);
		while (cursor.moveToLast()) {
			return cursor.getInt(0);
		}
		return 0;
	}
	
}
