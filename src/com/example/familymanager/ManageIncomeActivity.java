package com.example.familymanager;

import java.util.Calendar;
import java.util.Date;

import com.example.familymanager.DB.IncomeDAO;
import com.example.familymanager.domain.Income;

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


public class ManageIncomeActivity extends Activity {

	private int id;
	private int type;
	
	private IncomeDAO incomeDAO;
	private Income income;
	
	private EditText myEtMoney,myEtPayer,myEtInfo,myEtTime;
	private Spinner mySpinnerCategories;
	private Button myBtUpdata,myBtDelete;
	private Calendar cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_updata_delete_myincome);
		
		myBtUpdata = (Button)this.findViewById(R.id.bt_income_updata);
		myBtDelete = (Button)this.findViewById(R.id.bt_income_delete);
		myEtInfo = (EditText)this.findViewById(R.id.et_income_info);
		myEtMoney = (EditText)this.findViewById(R.id.et_income_money);
		myEtTime = (EditText)this.findViewById(R.id.et_income_time);
		myEtPayer = (EditText)this.findViewById(R.id.et_payer);
		mySpinnerCategories = (Spinner)this.findViewById(R.id.spinner_income_categories);
		
		id = this.getIntent().getIntExtra("id", 2);
		
		incomeDAO = new IncomeDAO(ManageIncomeActivity.this);
		income = incomeDAO.find(id);
		String time = income.getTime();
		String info = income.getInfo();
		String payer = income.getPayer();
		String categories = income.getCategories();
		double money = income.getMoney();
		if(categories.equals("¹¤×Ê")){
			type = 0;
		}else if(categories.equals("¹ÉÆ±")){
			type = 1;
		}else if(categories.equals("²ÊÆ±")){
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
				new DatePickerDialog(ManageIncomeActivity.this, new OnDateSetListener() {
					
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

				new DatePickerDialog(ManageIncomeActivity.this, new OnDateSetListener() {
					
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
				incomeDAO.detele(id);
				Intent intent = new Intent();
				intent.setClass(ManageIncomeActivity.this, MyIncomeActivity.class);
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
				Income income = new Income(id, newMoney, newTime, newCategories, newPayer, newInfo);
				incomeDAO.updata(income);
				Intent intent1 = new Intent();
				intent1.setClass(ManageIncomeActivity.this, HomeActivity.class);
				startActivity(intent1);
			}
		});
		
	}
	
}
