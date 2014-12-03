package com.example.ghosthunter;

import java.util.ArrayList;
import java.util.Date;

import com.example.ghosthunter.Ghost;
import com.example.ghosthunter.Spaceship;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

public class MainGamePanel extends SurfaceView implements
SurfaceHolder.Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();

	private MainThread thread;
	private boolean isPaused;
	private Spaceship spaceship;
	private TextView  scoreTextView;
	private Sprite[] heartsArray;
	private ArrayList<Sprite> drawables = new ArrayList<Sprite>();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<Ghost> targets = new ArrayList<Ghost>();

	private long lastGhostTime;
	private int moveDelta = 75;

	// run time variables
	private int score = 0;
	private int multiplier = 1;
	private int streak = 0;
	private int bestStreak = 0;
	private int ghostsAdded = 0;
	private boolean gameOver;
	private Explosion[] explosions;

	public MainGamePanel(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		spaceship = new Spaceship(BitmapFactory.decodeResource(getResources(), R.drawable.spaceshipanimation));
		drawables.add(spaceship);


		// create the game loop thread
		thread = new MainThread(getHolder(), this);

		// make the GamePanel focusable so it can handle events
		//setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "Surface is being changed");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop

		Canvas c = getHolder().lockCanvas();
		draw(c);

		// draw hearts
		heartsArray = new Sprite[spaceship.getHeatlh()+2];
		for(int i = 0; i < spaceship.getHeatlh(); i++){
			Sprite heart = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 600+100*i, 50);
			heartsArray[i] = heart;
			drawables.add(heart);
		}
		getHolder().unlockCanvasAndPost(c);

		thread.setRunning(true);
		thread.start();

		explosions = new Explosion[15];
		for (int i = 0; i < explosions.length; i++) {
			explosions[i] = null;
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly from destroy");
	}

	public void onPause(){
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		thread.setRunning(false);
	}

	public boolean shoot(){
		// create bullet, then add to projectiles for collision detect and
		// drawbles for redrawing
		Projectile bullet = new Projectile(BitmapFactory.decodeResource(getResources(), R.drawable.laser),
				spaceship.getX()+350, spaceship.getY()+60, 15);
		return projectiles.add(bullet);
	}

	public void moveUp(){
		if(spaceship.getY() > spaceship.getBitmap().getHeight()/2 + moveDelta)
			spaceship.setY(spaceship.getY() - moveDelta);//spaceship.move(moveDelta);//
	}

	public void moveDown(){
		if(spaceship.getY() < 1200 - spaceship.getBitmap().getHeight()/2 - moveDelta)
			spaceship.setY(spaceship.getY() + moveDelta);//spaceship.move(-moveDelta);//
	}

	public boolean addGhost(){

		// check if 1.5 seconds has passed between last ghost
		Date date = new Date();
		if (date.getTime() >= lastGhostTime + 2000 && !isPaused){

			int level = 1 + ghostsAdded/5;
			if (level > 3) level = 3;

			Ghost ghostTemp = null;
			switch (level) {
			case 1:
				ghostTemp = new Ghost(BitmapFactory.decodeResource(getResources(), R.drawable.ghost), level);
				break;
			case 2:
				ghostTemp = new Ghost(BitmapFactory.decodeResource(getResources(), R.drawable.bossghost), level);
				break;
			case 3:
				ghostTemp = new Ghost(BitmapFactory.decodeResource(getResources(), R.drawable.bossghost), level);
				break;

			default:
				break;
			}
			ghostsAdded++;
			lastGhostTime = date.getTime();
			if (ghostsAdded % 9 == 0) addCoin();
			return targets.add(ghostTemp);
		}
		else return false;
	}

	private void addCoin(){

		Ghost coin = new Ghost(BitmapFactory.decodeResource(getResources(), R.drawable.coin), -1);
		targets.add(coin);

	}

	public void collisionDetection(){
		for (int i = 0; i < targets.size(); i++ ){

			Ghost target = targets.get(i);

			/*
			 *  Target and Left Wall Collision Detection
			 *  check if target right point is outside screen
			 *  then check if spaceship is dead
			 */
			if(target.getX() + target.getBitmap().getWidth()/2 < 0 && target.getWorth() > 0){
				Log.d(TAG, "target passed at edge");

				targets.remove(i);
				spaceship.setHeatlh(spaceship.getHeatlh() - 1);
				Sprite heart = heartsArray[spaceship.getHeatlh()];
				drawables.remove(heart);

				// check if dead i.e. end of game
				if(spaceship.getHeatlh() <= 0){
					// end game
					Log.d(TAG, "spaceship dead");
					isPaused = true;
					gameOver = true;
					
					Sprite gameOver = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.gameover), 1000, 600);
					drawables.add(gameOver);
					//Canvas c = getHolder().lockCanvas();
					//draw(c);
					//gameOver.draw(c);
					//getHolder().unlockCanvasAndPost(c);
					
					thread.setRunning(false);
					Log.d(TAG, "thread stoped");
				}
				if (targets.size() <= 0) return;
			}

			for(int j = 0; j < projectiles.size(); j++){
				Projectile projectile = projectiles.get(j);

				/*
				 *  Projectile and Target Collision Detection 
				 *  check if projectile right point is inside target left point and right point
				 */ 
				if (projectile.getX() + projectile.getBitmap().getWidth()/2 >= target.getX() - target.getBitmap().getWidth()/2 && 
						projectile.getX() - projectile.getBitmap().getWidth()/2 <= target.getX() + target.getBitmap().getWidth()/2 &&
						projectile.getY() - projectile.getBitmap().getHeight()/2 <= target.getY() + target.getBitmap().getHeight()/2 &&
						projectile.getY() + projectile.getBitmap().getHeight()/2 >= target.getY() - target.getBitmap().getHeight()/2){

					Log.d(TAG, "collision detected with target health = "+target.getHealth());

					// check if coin if worth is -1
					if (target.getWorth() == -1 && spaceship.getHeatlh() < heartsArray.length){
						Sprite heart = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 600+100*spaceship.getHeatlh(), 50);
						heartsArray[spaceship.getHeatlh()] = heart;
						spaceship.setHeatlh(spaceship.getHeatlh() + 1);
						drawables.add(heart);
					}

					// else ghost and subtract damage and remove if heath is below zero
					else if(target.isDead(projectile.getDamage())){
						Log.d(TAG, "target killed with health = "+target.getHealth());
						updateScore(target.getWorth());

						targets.remove(i);

						Log.d(TAG, "target removed after");

						/*// check if explosion is null or if it is still active
						int currentExplosion = 0;
						Explosion explosion = explosions[currentExplosion];
						while (explosion != null && explosion.isAlive() && currentExplosion < explosions.length) {
							currentExplosion++;
							explosion = explosions[currentExplosion];
						}
						if (explosion == null || explosion.isDead()) {
							explosion = new Explosion(EXPLOSION_SIZE, (int)event.getX(), (int)event.getY());
							explosions[currentExplosion] = explosion;
						}*/

					}
					projectiles.remove(j);
					if (projectiles.size() <= 0 || targets.size() <= 0) return;

				}

				/*
				 *  Projectile and Right Wall Collision Detection
				 *  check if projectile left point is outside screen
				 */
				else if(projectile.getX() - projectile.getBitmap().getWidth()/2 > 1920){
					Log.d(TAG, "projectile pasesd at edge");
					projectiles.remove(j); //i--;
					updateScore(0);
					if (projectiles.size() <= 0 || targets.size() <= 0) return;
				}
			}
		}
	}

	public void updateScore(int scoreN){
		Log.d(TAG, "updating score + "+scoreN);

		if (scoreN > 0){
			streak++;
			if (streak > bestStreak) bestStreak = streak;

			multiplier = streak / 3;
			if (multiplier < 1) multiplier = 1;
			if (multiplier > 10) multiplier = 10;

			this.score += scoreN * 100 * multiplier;
		}
		if (scoreN <= 0){
			streak = 0;
			multiplier = 1;
		}
		((GameActivity) getContext()).setScoreTextView(score);
		((GameActivity) getContext()).setMultiplierTextView(multiplier);

		//scoreTextView.setText(score.toString());
		//Log.d(TAG, "new streak at  "+streak);
	}

	public int getScore(){
		return score;
	}

	public int getGhostsAdded(){
		return ghostsAdded;
	}

	public int getStreak(){
		return streak;
	}

	public void pause(){
		if(gameOver) return;
		isPaused = !isPaused;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}

	public void resumeGame(int score, int streak, int ghostsAdded){
		this.score = score;
		this.streak = streak;
		this.ghostsAdded = ghostsAdded;

		Log.d(TAG, "resume w/ score "+score+" streak "+ streak+" ghosts added "+ ghostsAdded);

		multiplier = streak / 3;
		if (multiplier < 1) multiplier = 1;
		if (multiplier > 10) multiplier = 10;

		((GameActivity) getContext()).setScoreTextView(score);
		((GameActivity) getContext()).setMultiplierTextView(multiplier);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// clears canvas with transparency then draws sprites
		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

		for(Sprite sprite : drawables){
			sprite.draw(canvas);
		}
		if (!isPaused){
			for(Sprite sprite : projectiles){
				sprite.draw(canvas);
			}
			for(Sprite sprite : targets){
				sprite.draw(canvas);
			}
		}
	}

}