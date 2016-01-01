package com.example.familymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText etUsername;
	private EditText etPassword;
	private String password, username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);

		etUsername = (EditText) this.findViewById(R.id.et_username);
		etPassword = (EditText) this.findViewById(R.id.et_password);

		this.findViewById(R.id.bt_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						password = etPassword.getText().toString();
						username = etUsername.getText().toString();
						if(password.equals("")){
							Toast.makeText(MainActivity.this, "密码不能为空",Toast.LENGTH_LONG).show();
						}else if(username.equals("")){
							Toast.makeText(MainActivity.this, "账号不能为空",Toast.LENGTH_LONG).show();
						}else if ("admin".equals(username)
								&& "pass".equals(password)) {
							Intent intent = new Intent();
							intent.setClass(MainActivity.this,
									HomeActivity.class);
							startActivity(intent);
						
						}else{
							Toast.makeText(MainActivity.this, "密码或密码错误",Toast.LENGTH_LONG).show();
						}
						
					}
				});  

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
