package com.advback.placeholder;

/**
 * @author Jeremy Cerise
 * @version 6-6-10
 * 
 * This project represents a template for an Android project that
 * follows the new UI design patterns laid out at Google IO 2010.
 * It sets up an application with a dsahboard and actionbar as the 
 * first thing the user sees. The actionbar persists throughout 
 * the entire application.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AppTemplate extends Activity{
	
	ImageButton actionOne;
	ImageButton actionTwo;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/*
		 * The following actions set up the appropriate action for the
		 * actionbar that is on each activity.
		 */
		actionOne = (ImageButton) findViewById(R.id.action1);
		actionOne.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AppTemplate.this, AppAction.class);
				intent.putExtra(AppAction.ACTION_ID, 1);
				startActivityForResult(intent, 0);
			}
		});

		actionTwo = (ImageButton) findViewById(R.id.action2);
		actionTwo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AppTemplate.this, Information.class);
				intent.putExtra(AppAction.ACTION_ID, 2);
				startActivityForResult(intent, 0);
			}
		});
		// end actionbar actions
		
		/*
		 * This dashboard is represented by a gridview. This is where
		 * the most important activity links would go for the application.
		 */
		GridView gridview = (GridView) findViewById(R.id.dashboard);
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setSelector(R.drawable.dashboard_icon);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(AppTemplate.this, AppClass.class);
				intent.putExtra(AppClass.GRID_POSITION, position);
				startActivityForResult(intent, 0);
			}
		});
	}
}