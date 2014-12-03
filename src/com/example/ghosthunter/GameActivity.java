package com.example.ghosthunter;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {

	private static final String TAG = GameActivity.class.getSimpleName();
	private Button resumeButton;
	private Button newGameButton;
	private TextView scoreTextView;
	private TextView multiplierTextView;
	private MainGamePanel gamePanel;
	private int highScore;
	private int score;
	private int streak;
	private int ghostsAdded;

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


		SharedPreferences prefs = getSharedPreferences("userPrefs", MODE_PRIVATE); 
		score = prefs.getInt("score", 0); //0 is the default value.
		streak = prefs.getInt("streak", 0); //0 is the default value.
		ghostsAdded = prefs.getInt("ghostsAdded", 0); //0 is the default value.
		highScore = prefs.getInt("highScore", 0); //0 is the default value.


		MainGamePanel sfvTrack = (MainGamePanel)findViewById(R.id.gameView);
		sfvTrack.setZOrderOnTop(true);    // necessary
		SurfaceHolder sfhTrack = sfvTrack.getHolder();
		sfhTrack.setFormat(PixelFormat.TRANSPARENT);

		scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		scoreTextView.setText("0");

		multiplierTextView = (TextView) findViewById(R.id.multiplierTextView);
		multiplierTextView.setText("1x");

		gamePanel = (MainGamePanel) findViewById(R.id.gameView);
		gamePanel.pause();

		resumeButton = (Button) findViewById(R.id.resumeGame);
		newGameButton = (Button) findViewById(R.id.newGame);

		if (ghostsAdded < 2) resumeButton.setVisibility(View.GONE);

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

		SharedPreferences.Editor editor = getSharedPreferences("userPrefs", MODE_PRIVATE).edit();

		editor.putInt("score", gamePanel.getScore());
		editor.putInt("ghostsAdded", gamePanel.getGhostsAdded());
		editor.putInt("streak", gamePanel.getStreak());
		if(gamePanel.getScore() > highScore){ 
			Log.d(TAG, "setting high score: "+ gamePanel.getScore());
			editor.putInt("highScore", gamePanel.getScore());
		}

		editor.commit();
		Log.d(TAG, "End On Pause...");

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

	public void resumeGame(View view) {
		if (gamePanel.isGameOver()) {
			Log.d(TAG, "Game Over loading main activity...");
			startActivity(new Intent("com.example.ghosthunter.MAINACTIVITY"));
			return;
		}

		resumeButton.setVisibility(View.INVISIBLE);
		newGameButton.setVisibility(View.INVISIBLE);

		gamePanel.resumeGame(score, streak, ghostsAdded);

		gamePanel.pause();
	}

	public void newGame(View view) {
		if (gamePanel.isGameOver()) return;
		resumeButton.setVisibility(View.INVISIBLE);
		newGameButton.setVisibility(View.INVISIBLE);

		gamePanel.pause();
	}

	public void moveUp(View view) {
		Log.d(TAG, "Move Up...");
		gamePanel.moveUp();
	}
	
	public void gameOver(){
		Log.d(TAG, "gameOver()...");

		//resumeButton.setVisibility(View.VISIBLE);
		//newGameButton.setVisibility(View.VISIBLE);
		Log.d(TAG, "buttons set visible...");

		//newGameButton.setText("Game Over");
		//resumeButton.setText("Exit");
		
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
