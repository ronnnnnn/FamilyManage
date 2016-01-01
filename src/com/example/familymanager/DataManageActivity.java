package com.example.familymanager;

import java.io.Flushable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.familymanager.DB.FlagDAO;
import com.example.familymanager.domain.Tb_flag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DataManageActivity extends Activity {

	private ListView myListView;
	private SimpleAdapter simpleAdapter;
	private Tb_flag flag;
	private List<Tb_flag> data;
	private List<Map<String, Object>> dataList;
	
	private FlagDAO flagDAO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_flag_show);
		
		myListView = (ListView)this.findViewById(R.id.lv_flag);
		
		flagDAO = new FlagDAO(DataManageActivity.this);
		data = flagDAO.getScrollData(1, flagDAO.getMaxId());
		dataList = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < data.size(); i++) {
			
			flag = data.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", flag.getid());
			map.put("flag", flag.getFlag());
			dataList.add(map);
		}
		
		String from[] ={"id","flag"};
		int to[] = {R.id.tv_item_flag_id,R.id.tv_item_flag_main_info};
		
		simpleAdapter = new SimpleAdapter(DataManageActivity.this, dataList, R.layout.simpleadapter_item, from, to);
		myListView.setAdapter(simpleAdapter);
		
		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView textView = (TextView)view.findViewById(R.id.tv_item_flag_id);
				final int id1 = Integer.valueOf(textView.getText().toString());
				
				AlertDialog.Builder builder = new AlertDialog.Builder(DataManageActivity.this);
				builder.setTitle("提示");
				builder.setMessage("确定要删除此便签吗？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						flagDAO.detele(id1);
					refresh();
					}
				});
				
				builder.setNegativeButton("取消", null);
				
				builder.show();
			}
		});
		
	}
	
	private void refresh(){
		onCreate(null);
	}
	

}
