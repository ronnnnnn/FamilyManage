package com.example.familymanager.DB;

import java.util.ArrayList;
import java.util.List;

import com.example.familymanager.domain.Income;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class IncomeDAO {

	private DBHelper helper;
	private SQLiteDatabase db;

	public IncomeDAO(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBHelper(context);
	}

	public void add(Income income) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into  tb_income (_id,money,time,categories,payer,info) values (?,?,?,?,?,?)",
				new Object[] { income.get_id(), income.getMoney(),
						income.getTime(), income.getCategories(),
						income.getPayer(), income.getInfo() });
	}

	public void updata(Income income) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update tb_income set money=?,time=?,categories=?,payer=?,info=? where _id=?",
				new Object[] { income.getMoney(), income.getTime(),
						income.getCategories(), income.getPayer(),
						income.getInfo(), income.get_id() });
	}

	public Income find(int id) {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select  _id,money,time,categories,payer,info from tb_income where _id = ?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Income(cursor.getInt(cursor.getColumnIndex("_id")),
					cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
					cursor.getString(cursor.getColumnIndex("categories")),
					cursor.getString(cursor.getColumnIndex("payer")),
					cursor.getString(cursor.getColumnIndex("info")));
		}
		return null;
	}
	
	public void detele(Integer... ids) {
		if (ids.length > 0)// 判断是否存在要删除的id
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			
			db.execSQL("delete from tb_income where _id in (" + sb + ")",
					(Object[]) ids);
		}
	}

	public List<Income> getScrollData(int start, int count) {
		List<Income> tb_inaccount = new ArrayList<Income>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_income limit ?,?",
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())
		{
			tb_inaccount.add(new Income(cursor.getInt(cursor
					.getColumnIndex("_id")), cursor.getDouble(cursor
					.getColumnIndex("money")), cursor.getString(cursor
					.getColumnIndex("time")), cursor.getString(cursor
					.getColumnIndex("categories")), cursor.getString(cursor
					.getColumnIndex("payer")), cursor.getString(cursor
					.getColumnIndex("info"))));
		}
		return tb_inaccount;// 返回集合
	}
	public long getCount() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db
				.rawQuery("select count(_id) from tb_income", null);// 获取收入信息的记录数
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getLong(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
	
	public int getMaxId() {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		Cursor cursor = db.rawQuery("select max(_id) from tb_income", null);// 获取收入信息表中的最大编号
		while (cursor.moveToLast()) {// 访问Cursor中的最后一条数据
			return cursor.getInt(0);// 获取访问到的数据，即最大编号
		}
		return 0;// 如果没有数据，则返回0
	}
	
}
