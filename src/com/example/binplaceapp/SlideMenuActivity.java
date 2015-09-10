package com.example.binplaceapp;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlideMenuActivity extends Activity{
	
	Button LocalSearch;
	Button Setting;
	
	ButtonListener listener;
	protected void onCreate(Bundle savedInstanceState) {
		
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.sliding_activity);
		   
		   LocalSearch = (Button) findViewById(R.id.findResButton1);
		   LocalSearch.setOnClickListener(listener);
	}	
	
	class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()) {
			
				case R.id.findResButton1 :
					LocalSearchAsync localSearch = new LocalSearchAsync();
					localSearch.execute();
					break;
				
			}
					
		}
	}
	
	class LocalSearchAsync extends AsyncTask<Void, Void, Void> {

		ConnectSocket sock;

		final static int SIZE = 1000;
		String[] sendingData;
		String[] receivingData;

		private String localGu[] = new String[SIZE];
		private String localDong[] = new String[SIZE];
		
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			sock = new ConnectSocket();

			sendingData = new String[1];
			sendingData[0] = "localSearchListViewApp";
			sock.sendData(sendingData);

			localGu = sock.receiveData();
			localDong = sock.receiveData();
			
			return null;
		}

		protected void onPostExecute(Void result) {
			
			Intent LocalSearchActivity = new Intent(SlideMenuActivity.this, LocalSearchActivity.class);
			
			LocalSearchActivity.putExtra("localGu", localGu);
			LocalSearchActivity.putExtra("localDong", localDong);

			startActivity(LocalSearchActivity);

			super.onPostExecute(result);
		}
	}
}
