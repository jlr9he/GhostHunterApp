package com.example.ghosthunter;

import com.example.ghosthunter.ChangeGhosts;
import com.example.ghosthunter.GhostView;
import android.app.Activity;	
import android.os.Bundle;
import android.view.View;


public class Popup extends Activity {
	
	// Override the onCreate method
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			GhostView ghost = new GhostView(this); //entire pop up takes CircleView
			//this- the savedInstanceState passed into onCreate method
			
			// For dynamic display (added)
			ChangeGhosts changer = new ChangeGhosts(); // new class
			
			setContentView(ghost); //set the ContentView to be the circle view
			
			// For dynamic display (added) 
			changer.execute(ghost); //goes after setContentView
		}
}
