package com.example.familymanager;

import java.util.Calendar;
import java.util.Date;

import com.example.familymanager.DB.ConsumeDAO;
import com.example.familymanager.domain.Consume;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ConsumeActivity extends Activity {

	private EditText myEtMoney, myEtTime, myEtInfo, myEtConsumer;
	private Spinner mySpinnerCategories;

	private String info, consumer, time, categories;
	private double money;
	private int _id;
	private boolean isNumber;

	private ConsumeDAO consumeDAO;
	private Consume consume;
	private Calendar cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_consume);

		consumeDAO = new ConsumeDAO(ConsumeActivity.this);

		myEtMoney = (EditText) this.findViewById(R.id.et_consume_money);
		myEtTime = (EditText) this.findViewById(R.id.et_consume_time);
		myEtInfo = (EditText) this.findViewById(R.id.et_consume_info);
		myEtConsumer = (EditText) this.findViewById(R.id.et_consumer);
		mySpinnerCategories = (Spinner) this
				.findViewById(R.id.spinner_consume_categories);

		cd = Calendar.getInstance();
		Date date = new Date();
		cd.setTime(date);

		myEtTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(ConsumeActivity.this,
						new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								int mymonthOfYear = monthOfYear + 1;
								myEtTime.setText(year + "-" + mymonthOfYear
										+ "-" + dayOfMonth);
							}
						}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		myEtTime.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub

				new DatePickerDialog(ConsumeActivity.this,
						new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// TODO Auto-generated method stub
								int mymonthOfYear = monthOfYear + 1;
								myEtTime.setText(year + "-" + mymonthOfYear
										+ "-" + dayOfMonth);
							}
						}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd
								.get(Calendar.DAY_OF_MONTH));

			}
		});

		this.findViewById(R.id.bt_consume_post).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						_id = consumeDAO.getMaxId() + 1;
						try {
							money = Double.parseDouble(myEtMoney.getText()
									.toString().trim());
							isNumber = true;
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							isNumber = false;
						}
						time = myEtTime.getText().toString();
						info = myEtInfo.getText().toString();
						consumer = myEtConsumer.getText().toString();
						categories = mySpinnerCategories.getSelectedItem()
								.toString();

						if (time.equals("")) {
							Toast.makeText(ConsumeActivity.this, "时间不能为空",
									Toast.LENGTH_LONG).show();
						} else if (consumer.equals("")) {
							Toast.makeText(ConsumeActivity.this, "付款方不能为空",
									Toast.LENGTH_LONG).show();
						} else if (info.equals("")) {
							Toast.makeText(ConsumeActivity.this, "备注不能为空",
									Toast.LENGTH_LONG).show();
						} else if ("".equals(myEtMoney.getText().toString())
								&& isNumber) {
							Toast.makeText(ConsumeActivity.this, "金额不能为空并且为数字",
									Toast.LENGTH_LONG).show();
						} else {

							consume = new Consume(_id, money, time, categories,
									consumer, info);

							consumeDAO.add(consume);
							Toast.makeText(ConsumeActivity.this, "数据添加成功",
									Toast.LENGTH_LONG).show();
						}
					}
				});

	}
}
