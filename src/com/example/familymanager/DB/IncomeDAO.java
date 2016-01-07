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
		if (ids.length > 0)// �ж��Ƿ����Ҫɾ����id
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
		return tb_inaccount;// ���ؼ���
	}
	public long getCount() {
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		Cursor cursor = db
				.rawQuery("select count(_id) from tb_income", null);// ��ȡ������Ϣ�ļ�¼��
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getLong(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
	
	public int getMaxId() {
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		Cursor cursor = db.rawQuery("select max(_id) from tb_income", null);// ��ȡ������Ϣ���е������
		while (cursor.moveToLast()) {// ����Cursor�е����һ������
			return cursor.getInt(0);// ��ȡ���ʵ������ݣ��������
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
	
}
