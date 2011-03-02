package com.advback.placeholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AppAction extends Activity {
	
	public static final String ACTION_ID = "com.advback.apptemplate.action_id";
	private Button back;
	private TextView infoText;
	private TextView logoNav;
	private ImageButton actionOne;
	private ImageButton actionTwo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_template);
		
		logoNav = (TextView)findViewById(R.id.logo_nav);
		logoNav.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		/*
		 * The following actions set up the appropriate action for the
		 * actionbar that is on each activity.
		 */
		actionOne = (ImageButton) findViewById(R.id.action1);
		actionOne.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AppAction.this, AppAction.class);
				intent.putExtra(AppAction.ACTION_ID, 1);
				startActivityForResult(intent, 0);
			}
		});

		actionTwo = (ImageButton) findViewById(R.id.action2);
		actionTwo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AppAction.this, Information.class);
				intent.putExtra(AppAction.ACTION_ID, 2);
				startActivityForResult(intent, 0);
			}
		});
		
		//end actionbar actions
		
		back = (Button) findViewById(R.id.back_button);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		infoText = (TextView) findViewById(R.id.activity_label);
		infoText.setText("You selected action " + getIntent().getIntExtra(ACTION_ID, 0) + 
				". These actions will usually have commonly used actions within them, " +
				"such as searching, adding new content, or other app specific actions.");

	}

}
