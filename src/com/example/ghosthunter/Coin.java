package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Coin extends Sprite {

	private int speed = 2;
	private int health;
	private int worth;
	private boolean touched; // if droid is touched/picked up

	public Coin(Bitmap bitmap, int level) {
		super(bitmap, 1920, (int) (1200 * Math.random()));
		
		switch (level) {
		case 1:
			this.health = 1;
			this.speed = 2;
			break;
		case 2:
			this.health = 2;
			this.speed = 3;
			break;
		case 3:
			this.health = 5;
			this.speed = 3;
			break;	

		default:
			this.health = 1;
			this.speed = 2;
			break;
		}
		worth = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getWorth() {
		return worth;
	}

	public boolean isTouched() {
		return touched;
	}

	/*
	 * Method: return if target is dead (health<=0)
	 * @param: damage of projectile
	 * @return: boolean true if dead
	 */
	public boolean isDead(int damage){
		this.health -= damage;
		return (this.health <= 0);
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		setX(getX() - speed);
	}

}