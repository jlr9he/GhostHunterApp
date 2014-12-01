package com.example.ghosthunter;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameActivity extends Activity {

	private static final String TAG = GameActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        setContentView(R.layout.activity_game);
        //setContentView(new MainGamePanel(this));
        Log.d(TAG, "View added");
        
		// TODO: Remove Deprecated Below
		//super.onCreate(savedInstanceState);
        

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		//ImageView spaceship1 = (ImageView) findViewById(R.id.spaceship1);
		//spaceshipCoordsY = (spaceship1.getTop() + spaceship1.getBottom())/2;
		//spaceshipCoordsX = (spaceship1.getLeft() + spaceship1.getRight())/2;
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
		super.onPause();
	}

	@Override
	protected void onResume() {
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
	 
	public void updateScore(int score) {

		//TextView textView = (TextView) findViewById(R.id.scoreTextView);
		//textView.setText(score);
	}



	public void moveUp(View view) {
		/*ImageView spaceship1 = (ImageView) findViewById(R.id.spaceship1);

		if (!((spaceshipCoordsY - SPACESHIP_INCREMENT) < 0)) { // Keep spaceship
																// in screen
			spaceshipCoordsY -= SPACESHIP_INCREMENT; // Subtracts because top of
													// the screen
			// is 0
			spaceship1.setY(spaceshipCoordsY);
		}*/
		Log.d(TAG, "Move Up...");
		MainGamePanel gamePanel = (MainGamePanel) findViewById(R.id.gameView);
		gamePanel.moveUp();
	}


	public void moveDown(View view) {
		// TODO: collision for bottom of screen
		//ImageView spaceship1 = (ImageView) findViewById(R.id.spaceship1);
		//spaceshipCoordsY += SPACESHIP_INCREMENT;
		//spaceship1.setY(spaceshipCoordsY);
		Log.d(TAG, "Move Down...");
		MainGamePanel gamePanel = (MainGamePanel) findViewById(R.id.gameView);
		gamePanel.moveDown();
	}

	public void shoot(View view) {
		/*ImageView laser = (ImageView) findViewById(R.id.laser);
		for (int i = 0; i < 3; i++) {
			
			laser.setX(spaceshipCoordsX + LASER_INCREMENT);
			laser.setY(spaceshipCoordsY);
		}*/
		Log.d(TAG, "Shoot...");
		MainGamePanel gamePanel = (MainGamePanel) findViewById(R.id.gameView);
		gamePanel.shoot();

	}
	
	public void pause(View view){
		Log.d(TAG, "Pause...");
		MainGamePanel gamePanel = (MainGamePanel) findViewById(R.id.gameView);
		gamePanel.pause();
	}
}
