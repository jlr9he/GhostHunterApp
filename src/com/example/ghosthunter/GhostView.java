package com.example.ghosthunter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.View;

public class GhostView extends View {

	public GhostView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	// Need to override the onDraw method to tell it what to draw
		@Override
		public void onDraw(Canvas c) {
			super.onDraw(c); // this will erase everything and make it white
			  // (the default background color), or else we will see the 
			  // previous version of view (overwriting)
			
			// find out height and width of this Canvas:
			int h = this.getMeasuredHeight();
			int w = this.getMeasuredWidth();
			
			// ** DYNAMIC DISPLAY ** //
			int x = (int)(Math.random()*w); // so that x is different each time
			Resources res = getResources();
			Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ghost);
			c.drawBitmap(bitmap, 30, x * h, null);
		}

}
