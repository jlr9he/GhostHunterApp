package com.example.ghosthunter;

//import com.example.ghosthunter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.widget.LinearLayout;  

public class MainActivity extends Activity {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_splash); 
	        Thread logoTimer = new Thread(){
	        	public void run(){
	        		try{
	        			sleep(5000);
	        			Intent menuIntent = new Intent("com.example.ghosthunter.SPLASH");
	        			startActivity(menuIntent);
	        		}
	        		catch (InterruptedException e) {
	        			e.printStackTrace();
	        		}
	        		finally{
	        			finish();
	        		}
	        	}
	        };
	        logoTimer.start();
	 }
}

	        
//	        if (savedInstanceState == null) {
//	        	getFragmentManager().beginTransaction().add(R.id.container, new PlaceHolderFragment()).commit();
//	        }
//	    } 
//	}
	
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
