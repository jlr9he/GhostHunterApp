package com.example.ghosthunter;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Spaceship extends Sprite {

	private int heatlh;
	private boolean touched; // if droid is touched/picked up
	private int destY;
	private int moveDelta = 2;
	private static final String TAG = Spaceship.class.getSimpleName();

	private Bitmap bitmap;		// the animation sequence
	private Rect sourceRect;	// the rectangle to be drawn from the animation bitmap
	private int frameNr;		// number of frames in animation
	private int currentFrame;	// the current frame
	private long lastFrameTime;


	private int spriteWidth;	// the width of the sprite to calculate the cut out rectangle
	private int spriteHeight;	// the height of the sprite

	private int x;				// the X coordinate of the object (top left of the image)
	private int y;				// the Y coordinate of the object (top left of the image)

	

	public Spaceship(Bitmap bitmap) {
		super(bitmap, 300, 550);
		this.heatlh = 3;
		destY = 300;
		
		currentFrame = 0;
		int frameCount = 2;
		frameNr = 2;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
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
		/*if (getY() == destY || getY() == destY - moveDelta/2 || getY() == destY + moveDelta/2)
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2), null);
		else if (destY > getY()) {
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2) + moveDelta, null);			
		}
		else {
			canvas.drawBitmap(getBitmap(), getX() - (getBitmap().getWidth() / 2), getY() - (getBitmap().getHeight() / 2) - moveDelta, null);			
		}*/

		update();
		
		Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);

		canvas.drawBitmap(getBitmap(), sourceRect, destRect, null);
		
	}
	public void update() {
		Date date = new Date();
		if (date.getTime() >= lastFrameTime + 50){
			lastFrameTime = date.getTime();
			// increment the frame
			currentFrame++;
			if (currentFrame >= frameNr) {
				currentFrame = 0;
			}
		}
		// define the rectangle to cut out sprite
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
	}

}