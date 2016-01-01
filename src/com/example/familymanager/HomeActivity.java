package com.example.familymanager;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.familymanager.DB.FlagDAO;
import com.example.familymanager.domain.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private GridView gridView;
	private TextView MyEtFlag;
	private SimpleAdapter simpleAdapter;
	private int[] icon = { R.drawable.add_consume, R.drawable.income,
			R.drawable.my_monsume, R.drawable.my_income,
			R.drawable.data_manager, R.drawable.setting, R.drawable.total_edit,
			R.drawable.systemexit };
	private String[] iconName = { "新增支出", "新增收入", "我的支出", "我的收入", "便签管理",
			"系统设置", "收支便签", "退出" };
	private List<Map<String, Object>> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_home);
		
		MyEtFlag = (TextView)this.findViewById(R.id.tv_home_show_flag);
		FlagDAO flagDAO = new FlagDAO(HomeActivity.this);
		List<Tb_flag> data = flagDAO.getScrollData(0, (int)flagDAO.getCount());
		Tb_flag flag = new Tb_flag();
		StringBuffer buffer = null;
		for (int i = 0; i < data.size(); i++) {
			buffer = new StringBuffer();
			flag = data.get(i);
			buffer.append(flag.getFlag());
		}
		MyEtFlag.setText(buffer.toString());

		gridView = (GridView) this.findViewById(R.id.gv_home_icon);

		dataList = new ArrayList<Map<String, Object>>();
		getData();

		String[] from = { "icon", "iconName" };
		int[] to = { R.id.iv_item_picture, R.id.tv_item_name };

		simpleAdapter = new SimpleAdapter(this, dataList,
				R.layout.gridview_item, from, to);
		gridView.setAdapter(simpleAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					Intent intent0 = new Intent();
					intent0.setClass(HomeActivity.this, ConsumeActivity.class);
					startActivity(intent0);
					break;
				case 1:
					Intent intent1 = new Intent();
					intent1.setClass(HomeActivity.this, IncomeActivity.class);
					startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent();
					intent2.setClass(HomeActivity.this, MyConsumeActivity.class);
					startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent();
					intent3.setClass(HomeActivity.this,MyIncomeActivity.class);
					startActivity(intent3);
					break;
				case 4:
					
					Intent intent4 = new Intent();
					intent4.setClass(HomeActivity.this,DataManageActivity.class);
					startActivity(intent4);
					
					break;
	             case 5:
					
	            	 Intent intent5 = new Intent();
				     intent5.setClass(HomeActivity.this,SettingActivity.class);
				     startActivity(intent5);
	            	 
					break;
	             case 6:
		
	            	 Intent intent6 = new Intent();
				     intent6.setClass(HomeActivity.this,EditActivity.class);
				     startActivity(intent6);
				     
		            break;
		
	             case 7:
	           finish();
		           break;
					

				default:
					break;
				}

			}
		});
	}

	public List<Map<String, Object>> getData() {

		for (int i = 0; i < icon.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", icon[i]);
			map.put("iconName", iconName[i]);
			dataList.add(map);

		}

		return dataList;
	}
}
