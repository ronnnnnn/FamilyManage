package com.example.familymanager;

import java.util.Calendar;
import java.util.Date;

import com.example.familymanager.DB.IncomeDAO;
import com.example.familymanager.domain.Income;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class IncomeActivity extends Activity {

	protected static final int DATE_DIALOG_ID = 0;
	private EditText myEtMoney,myEtPayer,myEtTime,myEtInfo;
	private Spinner mySpinnerCategories;
	private Income income;
	private String payer,info,time,categories;
	private double money;
	private int _id;
	private IncomeDAO incomeDAO;
	private Calendar cd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_income);
		
		incomeDAO = new IncomeDAO(this);
		
		myEtMoney = (EditText)this.findViewById(R.id.et_income_money);
		myEtTime = (EditText)this.findViewById(R.id.et_income_time);
		myEtInfo = (EditText)this.findViewById(R.id.et_income_info);
		myEtPayer = (EditText)this.findViewById(R.id.et_payer);
		mySpinnerCategories = (Spinner)this.findViewById(R.id.spinner_income_categories);
		
		cd = Calendar.getInstance();
		Date date = new  Date();
		cd.setTime(date);
		
		myEtTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(IncomeActivity.this, new OnDateSetListener() {
					
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

				new DatePickerDialog(IncomeActivity.this, new OnDateSetListener() {
					
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
		
		this.findViewById(R.id.bt_income_post).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				categories = mySpinnerCategories.getSelectedItem().toString();
				try {
					money =Integer.parseInt(myEtMoney.getText().toString().trim());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time = myEtTime.getText().toString();
				payer = myEtPayer.getText().toString();
				info = myEtInfo.getText().toString();
				_id =  incomeDAO.getMaxId()+1;
				if(time.equals("")){
					Toast.makeText(IncomeActivity.this, "时间不能为空", Toast.LENGTH_LONG).show();
				}else if(payer.equals("")){
					Toast.makeText(IncomeActivity.this, "付款方不能为空", Toast.LENGTH_LONG).show();
				}else if(info.equals("")){
					Toast.makeText(IncomeActivity.this, "备注不能为空", Toast.LENGTH_LONG).show();
				}else if("".equals(myEtMoney.getText().toString())){
					Toast.makeText(IncomeActivity.this, "金额不能为空", Toast.LENGTH_LONG).show();
				}else{
				income = new Income(_id, money, time, categories, payer, info);
							
				incomeDAO.add(income);
			
				Toast.makeText(IncomeActivity.this,"提交成功", Toast.LENGTH_LONG).show();}
			}
		});
	}
	
}
