package com.example.familymanager;

import com.example.familymanager.DB.PwdDAO;
import com.example.familymanager.domain.Tb_pwd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity {

	EditText txtpwd;
	Button btnSet, btnsetCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_system_set);

		txtpwd = (EditText) findViewById(R.id.txtPwd);
		btnSet = (Button) findViewById(R.id.btnSet);
		btnsetCancel = (Button) findViewById(R.id.btnsetCancel);

		btnSet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PwdDAO pwdDAO = new PwdDAO(SettingActivity.this);
				Tb_pwd tb_pwd = new Tb_pwd(txtpwd.getText().toString());
				if (pwdDAO.getCount() == 0) {
					pwdDAO.add(tb_pwd);
				} else {
					pwdDAO.update(tb_pwd);
				}
				// ������Ϣ��ʾ
				Toast.makeText(SettingActivity.this, "���óɹ���", Toast.LENGTH_SHORT)
						.show();
			}

		});

		btnsetCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtpwd.setText("");// ��������ı���
				txtpwd.setHint("����������");// Ϊ�����ı���������ʾ
			}
		});
	}
}
