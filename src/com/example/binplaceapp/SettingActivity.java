package com.example.binplaceapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;


//--------------------------------세팅 화면--------------------------------------
public class SettingActivity extends Activity {
	   
	   SearchView searchView;
	   boolean flag;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.setting_activity);
	      
	      Spinner rangeSpinner =(Spinner)findViewById(R.id.rangeSpinner);
	      
	      ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.range, 
	            android.R.layout.simple_spinner_item);
	      rangeSpinner.setAdapter(adapter);
	      
	      Switch switch2 = (Switch)findViewById(R.id.searchSwitch);
	      
	      searchView = (SearchView)findViewById(R.id.searchView1);
	      searchView.setVisibility(View.GONE);
	      
	      
	      flag = true;
	      switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	              // do something, the isChecked will be
	              // true if the switch is in the On position
	             
	             if(flag == true){
	                searchView.setVisibility(View.SCREEN_STATE_ON);
	                flag = false;
	                
	             }
	             else{
	                searchView.setVisibility(View.GONE);
	                flag = true;
	             }
	          }
	      });
	   }

	   @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
	      return true;
	   }

	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	      // Handle action bar item clicks here. The action bar will
	      // automatically handle clicks on the Home/Up button, so long
	      // as you specify a parent activity in AndroidManifest.xml.
	  
	      return super.onOptionsItemSelected(item);
	   }
}
