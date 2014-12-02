package com.example.ghosthunter;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends Activity {

	private static final String TAG = GameActivity.class.getSimpleName();
	private TextView scoreTextView;
	private TextView multiplierTextView;
	private MainGamePanel gamePanel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        setContentView(R.layout.activity_game);
        Log.d(TAG, "View added");
        
        MainGamePanel sfvTrack = (MainGamePanel)findViewById(R.id.gameView);
        sfvTrack.setZOrderOnTop(true);    // necessary
        SurfaceHolder sfhTrack = sfvTrack.getHolder();
        sfhTrack.setFormat(PixelFormat.TRANSPARENT);
        
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		scoreTextView.setText("0");
		
		multiplierTextView = (TextView) findViewById(R.id.multiplierTextView);
		multiplierTextView.setText("1x");
        
		gamePanel = (MainGamePanel) findViewById(R.id.gameView);

	}
	
	public void setScoreTextView(final int score){
	    GameActivity.this.runOnUiThread(new Runnable() {     
	        public void run() {         
	        	scoreTextView.setText(String.valueOf(score));     
	        } 
	     });
	}
	
	public void setMultiplierTextView(final int multiplier){
	    GameActivity.this.runOnUiThread(new Runnable() {     
	        public void run() {         
	        	multiplierTextView.setText(String.valueOf(multiplier)+"x");     
	        } 
	     });
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

	@Override
	protected void onPause() {
		Log.d(TAG, "OnPause...");
		gamePanel.onPause(); 
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume...");
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
	 
	public void saveScore(int score) {
		
	}



	public void moveUp(View view) {
		Log.d(TAG, "Move Up...");
		gamePanel.moveUp();
	}


	public void moveDown(View view) {
		Log.d(TAG, "Move Down...");
		gamePanel.moveDown();
	}

	public void shoot(View view) {
		Log.d(TAG, "Shoot...");
		gamePanel.shoot();

	}
	
	public void pause(View view){
		Log.d(TAG, "Pause...");
		gamePanel.pause();
	}
}
