package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Coin extends Sprite {

	private int speed;

	public Coin(Bitmap bitmap, int level) {
		super(bitmap, 1920, (int) (1000 * Math.random()));
		if (getY() < 800) setY(800);
		speed = 10;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		setX(getX() - speed);
		setY(getY() - speed/2);
	}

}