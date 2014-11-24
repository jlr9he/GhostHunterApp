package com.example.ghosthunter;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {

	private long lastUpdate = 0;
	private float last_x, last_y, last_z;
	private int spaceshipCoordsY;
	private int spaceshipCoordsX;
	private final int SPACESHIP_INCREMENT = 40;
	private final int LASER_INCREMENT = 300;
	private int screenHeight;
	private int screenWidth;
	private ImageView spaceship1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		screenHeight = metrics.heightPixels;
		screenWidth = metrics.widthPixels;
		spaceship1 = (ImageView) findViewById(R.id.spaceship1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		ImageView spaceship1 = (ImageView) findViewById(R.id.spaceship1);
		spaceshipCoordsY = (spaceship1.getTop() + spaceship1.getBottom()) / 2;
		spaceshipCoordsX = (spaceship1.getLeft() + spaceship1.getRight()) / 2;
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

	public void startGhostMovement(View button) {
		// this.startActivity(intent);
		// Dismiss Start button
		// Populate ghosts
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
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

	public void moveUp(View view) {


		if (!((spaceshipCoordsY - SPACESHIP_INCREMENT) < 0)) { // Keep spaceship
																// in screen
			spaceshipCoordsY -= SPACESHIP_INCREMENT; // Subtracts because top of
														// the screen is 0

			spaceship1.setY(spaceshipCoordsY);
		}
	}

	public void moveDown(View view) {
		// TODO: collision for bottom of screen (this isn't working)
		if (!((spaceshipCoordsY + SPACESHIP_INCREMENT) > screenWidth)) {
			spaceshipCoordsY += SPACESHIP_INCREMENT;
			spaceship1.setY(spaceshipCoordsY);
		}
	}

	public void shoot(View view) {
		ImageView laser = (ImageView) findViewById(R.id.laser);
		for (int i = 0; i < 3; i++) {
			laser = (ImageView) findViewById(R.id.laser);
			laser.setX(spaceshipCoordsX + LASER_INCREMENT);
			laser.setY(spaceshipCoordsY);
			laser.invalidate();
		}
	}
}
