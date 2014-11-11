package com.example.ghosthunter;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public void updateScore(int score) {

	    TextView textView = (TextView) findViewById(R.id.scoreTextView);
	    textView.setText(score);

	}
	
	/* 
	 * Data: 11-Nov-14
	 * @Method: addGhosts
	 * @Purpose: add ghosts on an interval at random y locations off 
	 * 			 screen to the right. They will move left
	 * @Param: none
	 * @Return none
	 * 
	 */
	
	public void addGhosts(){
		
		//LinearLayOut Setup
        LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        //ImageView Setup
        ImageView imageView = new ImageView(this);
        //setting image resource
        imageView.setImageResource(R.drawable.ghost);
        //setting image position
        imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        //adding view to layout
        linearLayout.addView(imageView);
        //make visible to program
        setContentView(linearLayout);
		
	}
	
}