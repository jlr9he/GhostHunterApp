package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Projectile extends Sprite {

	private int speed; // if droid is touched/picked up
	private int damage;

	public Projectile(Bitmap bitmap, int x, int y, int speed) {
		super(bitmap,x,y);
		this.speed = speed;
		this.damage = 1;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		setX(getX() + speed);
	}

}