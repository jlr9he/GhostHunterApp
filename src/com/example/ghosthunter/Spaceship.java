package com.example.ghosthunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Spaceship extends Sprite {

	private int heatlh;
	private boolean touched; // if droid is touched/picked up
	private int destY;
	private int moveDelta = 2;

	public Spaceship(Bitmap bitmap) {
		super(bitmap, 300, 550);
		this.heatlh = 3;
		destY = 300;
	}

	public int getHeatlh() {
		return heatlh;
	}

	public void setHeatlh(int heatlh) {
		this.heatlh = heatlh;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void move(int n){
		destY -= n;
	}

	@Override
	public void draw(Canvas canvas) {
		if (getY() == destY || getY() == destY - moveDelta/2 || getY() == destY + moveDelta/2)
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		else if (destY > getY()) {
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2) + moveDelta, null);			
		}
		else {
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2) - moveDelta, null);			
		}
	}

	/*public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}*/
}