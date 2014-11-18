package com.example.ghosthunter;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.view.View;


/*
 * Author: Jack Ross
 * Date: 11/18/14
 * About: Singleton class to keep track of all ghosts and bullets
 * 		  removes bullet and ghost if they collide
 * 
 */
public class CollisionDetection extends AsyncTask<GhostView, GhostView, Double> {
	
		private ArrayList<View> targetList = new ArrayList<View>();
		private ArrayList<View> projectileList = new ArrayList<View>();
	
	   private static CollisionDetection singleton = new CollisionDetection( );
	   
	   /* A private Constructor prevents any other 
	    * class from instantiating.
	    */
	   private CollisionDetection(){ }
	   
	   /* Static 'instance' method */
	   public static CollisionDetection getInstance( ) {
	      return singleton;
	   }
	   
	   public boolean addTarget(View target){
		   return targetList.add(target);
	   }
	   
	   public boolean addProjectile(View projectile){
		   return projectileList.add(projectile);
	   }

	@Override
	protected Double doInBackground(GhostView... params) {
		while (!this.isCancelled()) {		// while not cancelled
			this.publishProgress(params);
			try {
			Thread.sleep(1000); // 1sec		// sleep (wait) one second
			} catch (InterruptedException e) {
				
			}
		}
		return 0.0;
	}
	
	protected void onProgressUpdate(GhostView... params){
		// check collision update
	}
	   
	   
	   
	   

}
