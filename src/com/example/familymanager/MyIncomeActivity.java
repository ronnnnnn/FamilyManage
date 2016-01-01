package com.example.familymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.familymanager.DB.IncomeDAO;
import com.example.familymanager.domain.Income;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyIncomeActivity extends Activity {
	
	private ListView myListView;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> dataList;
	private List<Income> data;
	private Map<String,Object> map;
	private Income income;
	private IncomeDAO incomeDAO;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.aty_my_income);
	
	myListView = (ListView)this.findViewById(R.id.lv_income);
	
	incomeDAO = new IncomeDAO(MyIncomeActivity.this);
	int start = 1;
	int end = (int)incomeDAO.getCount();
	data = incomeDAO.getScrollData(start,end);
	dataList =new ArrayList<Map<String,Object>>();
	for (int i = 0; i < data.size(); i++) {
		map = new HashMap<String, Object>();
		income = data.get(i);
		int id = income.get_id();
		String time = income.getTime();
	    String mainInfo = income.toString();
	    String info = income.getInfo();
	    map.put("id", id);
	    map.put("mainInfo", mainInfo);
	    map.put("info", info);
	    map.put("time", time);
	    dataList.add(map);
	}
	
	String[] from = {"id","mainInfo","info","time"};
	int[] to = {R.id.tv_item_myincome_id,R.id.tv_item_myincome_main_info,R.id.tv_item_myincome_info,R.id.tv_item_myincome_time};
	
	simpleAdapter = new SimpleAdapter(MyIncomeActivity.this, dataList, R.layout.myincome_simpleadapter_item, from, to);
    myListView.setAdapter(simpleAdapter);	
    
    myListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			  TextView tvId = (TextView)view.findViewById(R.id.tv_item_myincome_id);
		      int  myid = Integer.valueOf(tvId.getText().toString());
			  Intent intentToManage = new Intent();
		      intentToManage.putExtra("id",myid);
		      intentToManage.setClass(MyIncomeActivity.this, ManageIncomeActivity.class);
		      startActivity(intentToManage);
		}
	});
}
}
