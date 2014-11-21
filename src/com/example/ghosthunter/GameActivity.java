package com.example.ghosthunter;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {
	/*
	 * http://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile
	 * -22125
	 */

	private SensorManager senSensorManager;
	private Sensor senAccelerometer;
	private long lastUpdate = 0;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 600; // TODO: modify to change
													// for change in x-value

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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
	
	 public void topButtonClicked(View button) {
	    	// Create an intent to associate button clicked with Popup class
	    	Intent intent = new Intent (this, Popup.class);
	    	this.startActivity(intent);
	}	

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onSensorChange(SensorEvent sensorEvent) {
	    Sensor mySensor = sensorEvent.sensor;
	 
	    if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	        float x = sensorEvent.values[0];
	        float y = sensorEvent.values[1];
	        float z = sensorEvent.values[2];
	 
	        //the following code exists to control the sensitivity of the accelerometer
	        //updates on slower intervals
	        //TODO: Adjust to fit change in x-angle instead of detect shake
	        long curTime = System.currentTimeMillis();
	        
	        if ((curTime - lastUpdate) > 100) {
	            long diffTime = (curTime - lastUpdate);
	            lastUpdate = curTime;
	 
	            //float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;
	            float tilt = 0; //TODO: implement 
	 
	            if (tilt > SHAKE_THRESHOLD) { 
	 
	            }
	 
	            last_x = x;
	            last_y = y;
	            last_z = z;
	        }
	    }
	}

	public void updateScore(int score) {

		TextView textView = (TextView) findViewById(R.id.scoreTextView);
		textView.setText(score);
	}

	/*
	 * Data: 11-Nov-14
	 * 
	 * @Method: addGhosts
	 * 
	 * @Purpose: add ghosts on an interval at random y locations off screen to
	 * the right. They will move left
	 * 
	 * @Param: none
	 * 
	 * @Return none
	 */

	public void addGhosts() {

		// LinearLayOut Setup
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		linearLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		// ImageView Setup
		ImageView imageView = new ImageView(this);
		// setting image resource
		imageView.setImageResource(R.drawable.ghost);
		// setting image position
		imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		// adding view to layout
		linearLayout.addView(imageView);
		// make visible to program
		setContentView(linearLayout);

	}
	public boolean moveUp() {
		return false;
	}
	public boolean moveDown() {
		return false;
		
	}
	public boolean shoot() {
		return false;
	}
}
