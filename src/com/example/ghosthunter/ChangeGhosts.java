package com.example.ghosthunter;

import android.os.AsyncTask;
import com.example.ghosthunter.GhostView;;

public class ChangeGhosts extends AsyncTask<GhostView, GhostView, Double> {

	// Local Variable
	GhostView theView;
	
	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
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
		for(GhostView cv : params) {
			cv.invalidate(); // re-draw (delete current and replace)
			                 // current image is no longer valid
		}
	}
	
	@Override
	protected void onPostExecute(Double result) { //param based on return type
		
	}
	
	@Override
	protected void onPreExecute() {
		
	}
	
	@Override
	protected void onCancelled() {
		
	}	
	
}
