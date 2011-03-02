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
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.util.Log;

public class AppTemplate extends Activity implements LocationListener, Listener {
	
	ImageButton actionOne;
	ImageButton actionTwo;
	double longitude, latitude, accuracy;
	String provider;
	LocationManager locManager;
	private TextView gpsEnabled;
	
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
		
		/*
		 * This block of code checks whether or not the GPS is enabled.
		 * We need to register a GpsStatusListner, in this case the activity
		 * itself will act as the listener. This will ensure that we know when
		 * the user turns the GPS on or off so we can update the text 
		 * accordingly.
		 */
		gpsEnabled = (TextView) findViewById(R.id.gps_indicator);
		//Find which provider is being used (Fine or Coarse)
		locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		//register the GpsStatusListener
		locManager.addGpsStatusListener(this);
		//Check to see if the GPS is already enabled, and if so, grab a location
		//every so often to keep the listener alive.
		if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000000000, 0, this);
			gpsEnabled.setText("GPS Enabled!");
		}
	}

	public void onLocationChanged(Location location) {
		
	}

	public void onProviderDisabled(String provider) {
		Log.i("Main","Provider Has been disabled.");
		gpsEnabled.setText("GPS is currently disabled! Tap to enable");
	}

	public void onProviderEnabled(String provider) {
		Log.i("main", "Provider has been enabled.");
		gpsEnabled.setText("GPS Enabled!");
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	public void onGpsStatusChanged(int event) {
		Log.i("main", "GPS Status has changed.");
		if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000000000, 0, this);
			gpsEnabled.setText("GPS Enabled!");
		}
	}
}