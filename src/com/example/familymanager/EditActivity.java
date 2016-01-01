package com.example.familymanager;

import com.example.familymanager.DB.FlagDAO;
import com.example.familymanager.domain.Tb_flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {
	
	EditText txtFlag;
	Button btnflagSaveButton;
	Button btnflagCancelButton;
	
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.aty_flag);
    	
    	txtFlag = (EditText) findViewById(R.id.txtFlag);
		btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);
		btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);
		btnflagSaveButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						
						String strFlag = txtFlag.getText().toString();
						if (!strFlag.isEmpty()) {
							FlagDAO flagDAO = new FlagDAO(EditActivity.this);
							Tb_flag tb_flag = new Tb_flag(
									flagDAO.getMaxId() + 1, strFlag);
							flagDAO.add(tb_flag);
							
							Intent newIntent = new Intent();
							newIntent.setClass(EditActivity.this, HomeActivity.class);
							startActivity(newIntent);
						} else {
							Toast.makeText(EditActivity.this, "«Î ‰»Î±„«©£°",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		btnflagCancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						txtFlag.setText("");
					}
				});
	}
       
       @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	finish();
    }
    	
    }

