package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Ghost extends Sprite {

	private int speed = 2;
	private int health;
	private int worth;
	private int level;

	public Ghost(Bitmap bitmap, int level) {
		super(bitmap, 1920, (int) (1100 * Math.random()));
		
		if (super.getY() < bitmap.getHeight()/2+100) super.setY(bitmap.getHeight()/2+100);
		if (super.getY() > 1200 - bitmap.getHeight()) super.setY(1200 - bitmap.getHeight());
		
		switch (level) {
		case -1:
			this.health = -1;
			this.speed = 10;
			if (super.getY() < 800) super.setY(800);
			break;	
		case 1:
			this.health = 1;
			this.speed = 3;
			break;
		case 2:
			this.health = 2;
			this.speed = 5;
			break;
		case 3:
			this.health = 2;
			this.speed = 10;
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


	/*
	 * Method: return if target is dead (health<=0)
	 * @param: damage of projectile
	 * @return: boolean true if dead
	 */
	public boolean isDead(int damage){
		this.health -= damage;
		if (this.health <= 0) return true;
		return false;
	}


	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		setX(getX() - speed);
		if (worth == -1) setY(getY() - speed/2);
	}

}