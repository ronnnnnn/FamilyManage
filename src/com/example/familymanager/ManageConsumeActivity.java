package com.example.familymanager;

import java.util.Calendar;
import java.util.Date;

import com.example.familymanager.DB.ConsumeDAO;
import com.example.familymanager.domain.Consume;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ManageConsumeActivity extends Activity {

	private int id;
	private int type;
	
	private ConsumeDAO consumeDAO;
	private Consume consume;
	
	private EditText myEtMoney,myEtPayer,myEtInfo,myEtTime;
	private Spinner mySpinnerCategories;
	private Button myBtUpdata,myBtDelete;
	private Calendar cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_updata_delete_myconsume);
		
		myBtUpdata = (Button)this.findViewById(R.id.bt_consume_updata);
		myBtDelete = (Button)this.findViewById(R.id.bt_consume_delete);
		myEtInfo = (EditText)this.findViewById(R.id.et_consume_info);
		myEtMoney = (EditText)this.findViewById(R.id.et_consume_money);
		myEtTime = (EditText)this.findViewById(R.id.et_consume_time);
		myEtPayer = (EditText)this.findViewById(R.id.et_consumer);
		mySpinnerCategories = (Spinner)this.findViewById(R.id.spinner_consume_categories);
	
id = this.getIntent().getIntExtra("id", 2);
		
		consumeDAO = new ConsumeDAO(ManageConsumeActivity.this);
		consume = consumeDAO.find(id);
		String time = consume.getTime();
		String info = consume.getInfo();
		String payer = consume.getConsumer();
		String categories = consume.getCategoties();
		double money = consume.getMoney();
		if(categories.equals("饮食支出")){
			type = 0;
		}else if(categories.equals("日常支出")){
			type = 1;
		}else if(categories.equals("娱乐支出")){
			type = 2;
		}
		
		myEtInfo.setText(info);
		myEtTime.setText(time);
		myEtPayer.setText(payer);
		myEtMoney.setText(String.valueOf(money));
		mySpinnerCategories.setSelection(type);
		
		cd = Calendar.getInstance();
		Date date = new  Date();
		cd.setTime(date);
		
		myEtTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ManageConsumeActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						int mymonthOfYear = monthOfYear +1;
						myEtTime.setText(year+"-"+mymonthOfYear+"-"+dayOfMonth);
					}
				}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		myEtTime.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub

				new DatePickerDialog(ManageConsumeActivity.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						int mymonthOfYear = monthOfYear +1;
						myEtTime.setText(year+"-"+mymonthOfYear+"-"+dayOfMonth);
					}
				}, cd.get(Calendar.YEAR),cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH));
				
			}
		});
		
		myBtDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumeDAO.detele(id);
				Intent intent = new Intent();
				intent.setClass(ManageConsumeActivity.this, MyConsumeActivity.class);
				startActivity(intent);
			}
		});
		
		myBtUpdata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String newInfo = myEtInfo.getText().toString();
				String newTime = myEtTime.getText().toString();
				String newPayer = myEtPayer.getText().toString();
				String newCategories = mySpinnerCategories.getSelectedItem().toString();
				double newMoney =Double.parseDouble(myEtMoney.getText().toString()); 
				Consume  consume1 = new Consume(id, newMoney, newTime, newCategories, newPayer, newInfo);
				consumeDAO.update(consume1);
				Intent intent1 = new Intent();
				intent1.setClass(ManageConsumeActivity.this, HomeActivity.class);
				startActivity(intent1);
			}
		});
	}
	
}
