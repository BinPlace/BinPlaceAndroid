package com.example.binplaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocalSearchDongActivity extends Activity {

	ListView listDong;
	ArrayAdapter _arrAdapter;
	private String[] received_localDong;
	int position; //어떤 구를 눌렀는지 확인
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localsearchdong_activity);
		
		Intent intent = getIntent();
		position = intent.getExtras().getInt("position");
		
		if (position==0) {
			received_localDong = getResources().getStringArray(R.array.jongro);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==1){
			received_localDong = getResources().getStringArray(R.array.junggu);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==2){
			received_localDong = getResources().getStringArray(R.array.yongsan);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==3){
			received_localDong = getResources().getStringArray(R.array.sungdong);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==4){
			received_localDong = getResources().getStringArray(R.array.gwangjin);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==5){
			received_localDong = getResources().getStringArray(R.array.dongdaemun);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==6){
			received_localDong = getResources().getStringArray(R.array.jungrang);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==7){
			received_localDong = getResources().getStringArray(R.array.sungbuk);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==8){
			received_localDong = getResources().getStringArray(R.array.gangbuk);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==9){
			received_localDong = getResources().getStringArray(R.array.dobong);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==10){
			received_localDong = getResources().getStringArray(R.array.nowon);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==11){
			received_localDong = getResources().getStringArray(R.array.eunpyung);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==12){
			received_localDong = getResources().getStringArray(R.array.seodamun);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==13){
			received_localDong = getResources().getStringArray(R.array.mapo);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==14){
			received_localDong = getResources().getStringArray(R.array.yangchun);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==15){
			received_localDong = getResources().getStringArray(R.array.gangseo);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==16){
			received_localDong = getResources().getStringArray(R.array.guro);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==17){
			received_localDong = getResources().getStringArray(R.array.geumchun);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==18){
			received_localDong = getResources().getStringArray(R.array.yeongdeungpo);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==19){
			received_localDong = getResources().getStringArray(R.array.dongjak);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==20){
			received_localDong = getResources().getStringArray(R.array.gwanak);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==21){
			received_localDong = getResources().getStringArray(R.array.seocho);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==22){
			received_localDong = getResources().getStringArray(R.array.gangnam);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==23){
			received_localDong = getResources().getStringArray(R.array.songpa);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		else if(position==24){
			received_localDong = getResources().getStringArray(R.array.gangdong);
			_arrAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, received_localDong);
			listDong = (ListView) findViewById(R.id.localSearchDong);
			listDong.setAdapter(_arrAdapter);
			listDong.setVisibility(View.INVISIBLE);
			_arrAdapter.notifyDataSetChanged();
			listDong.setVisibility(View.VISIBLE);
			_arrAdapter.notifyDataSetChanged();
		}
		
		
		listDong.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            	
            	StaticVariable.setCurrent_LocalDong(received_localDong[position]);
                LocalSearchRestaurantAsync localrestaurantasync = new LocalSearchRestaurantAsync();
                localrestaurantasync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
		
	}

	class LocalSearchRestaurantAsync extends AsyncTask<Void, Void, Void> {

		ConnectSocket sock;

		final static int SIZE = 1000;
		String[] sendingData;
		String[] receivingData;
		
		private String localShop_id[] = new String[SIZE];
		private String localShop_name[] = new String[SIZE];
		private String localShop_address[] = new String[SIZE];
		private String localShop_telnumber[] = new String[SIZE];
		private int localShop_numberOfTable[] = new int[SIZE];
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			sock = new ConnectSocket();

			sendingData = new String[2];
			sendingData[0] = "localSearchRestaurantApp";
			sendingData[1] = StaticVariable.getCurrent_LocalGu()+" "+StaticVariable.getCurrent_LocalDong();//선택된거 주소 전송
			sock.sendData(sendingData);
			
			localShop_id = sock.receiveData();
			localShop_name = sock.receiveData();
			localShop_address = sock.receiveData();
			localShop_telnumber = sock.receiveData();
			localShop_numberOfTable = sock.receiveIntData();
			
			return null;
		}

		protected void onPostExecute(Void result) {
			
			sock.closeSocket();
			
			Intent LocalSearchRestaurantActivity = new Intent(LocalSearchDongActivity.this, LocalSearchRestaurantActivity.class);
			
			LocalSearchRestaurantActivity.putExtra("localShop_id", localShop_id);
			LocalSearchRestaurantActivity.putExtra("localShop_name", localShop_name);
			LocalSearchRestaurantActivity.putExtra("localShop_address", localShop_address);
			LocalSearchRestaurantActivity.putExtra("localShop_telnumber", localShop_telnumber);
			LocalSearchRestaurantActivity.putExtra("localShop_numberOfTable", localShop_numberOfTable);
			
			startActivity(LocalSearchRestaurantActivity);

			super.onPostExecute(result);
		}
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_search_dong, menu);
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
	}*/
}
