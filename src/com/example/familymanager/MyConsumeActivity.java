package com.example.familymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.familymanager.DB.ConsumeDAO;
import com.example.familymanager.domain.Consume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyConsumeActivity extends Activity {

	private ListView myListView ;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> dataList;
	private List<Consume> data;
	private ConsumeDAO consumeDAO;
	private Consume consume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_consume);
		
		myListView = (ListView)this.findViewById(R.id.lv_consume);
		
		consumeDAO = new ConsumeDAO(MyConsumeActivity.this);
		data = consumeDAO.getScrollData(0, consumeDAO.getMaxId());
		dataList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < data.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			consume = data.get(i);
			int id = consume.get_id();
			String time = consume.getTime();
		    String mainInfo = consume.toString();
		    String info = consume.getInfo();
		    map.put("id", id);
		    map.put("mainInfo", mainInfo);
		    map.put("info", info);
		    map.put("time", time);
		    dataList.add(map);
		}
		
		String[] from = {"id","mainInfo","info","time"};
		int[] to = {R.id.tv_item_myincome_id,R.id.tv_item_myincome_main_info,R.id.tv_item_myincome_info,R.id.tv_item_myincome_time};
		
		simpleAdapter = new SimpleAdapter(MyConsumeActivity.this, dataList, R.layout.myincome_simpleadapter_item, from, to);
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
				      intentToManage.setClass(MyConsumeActivity.this, ManageConsumeActivity.class);
				      startActivity(intentToManage);
				      
				}
			});
	}
}
