package com.example.binplaceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class LocalSearchActivity extends Activity{
	
	ArrayAdapter _arrAdapter;
	ArrayAdapter _arrAdapter_2;
	ListView listView;
	ListView listDong;
	
	private String[] received_localGu;
	private String[] received_localDong;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localsearch_activity);
		
		//Intent intent = getIntent();
		//received_localGu = intent.getExtras().getStringArray("localGu");
		//received_localDong = intent.getExtras().getStringArray("localDong");
		
		received_localGu = getResources().getStringArray(R.array.address);
		
		_arrAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,received_localGu);
		// 리스트뷰에 추가해주기

		listView = (ListView) findViewById(R.id.localSearchListView);
		listView.setAdapter(_arrAdapter);
		listView.setVisibility(View.INVISIBLE);
		_arrAdapter.notifyDataSetChanged();
		
		/*
		for (int i = 0; i < received_localGu.length; i++) {
			if(received_localGu[i]== null || received_localGu[i].equals("") == true){
				break;
			}
			_arrAdapter.add(received_localGu[i] +"  "+received_localDong[i]);
			
		}*/
		listView.setVisibility(View.VISIBLE);
		
		_arrAdapter.notifyDataSetChanged();
		
		
		//listDong = (ListView) findViewById(R.id.localSearchListView2);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            		Intent LocalSearchDongActivity = new Intent(LocalSearchActivity.this, LocalSearchDongActivity.class);
            		
            		LocalSearchDongActivity.putExtra("position", position);
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
                    
            		startActivity(LocalSearchDongActivity);
            		/*
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.jongro);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();*/
            	/*
            	else if(position==1){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.yongsan);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==2){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.sungdong);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==3){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gwangjin);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==4){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.dongdaemun);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==5){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.jungrang);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==6){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.sungbuk);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==7){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gangbuk);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==8){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.dobong);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==9){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.nowon);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==10){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.eunpyung);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==11){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.seodamun);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==12){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.mapo);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==13){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.yangchun);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==14){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gangseo);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==15){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.guro);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==16){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.geumchun);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==17){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.yeongdeungpo);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==18){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.dongjak);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==19){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gwanak);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==20){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.seocho);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==21){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gangnam);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==22){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.songpa);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}
            	else if(position==23){
            		StaticVariable.setCurrent_LocalGu(received_localGu[position]);
            		received_localDong = getResources().getStringArray(R.array.gangdong);
            		_arrAdapter_2 = new ArrayAdapter<String>(LocalSearchActivity.this,
            				android.R.layout.simple_list_item_1,received_localDong);
            		listDong = (ListView) findViewById(R.id.localSearchListView);
            		listDong.setAdapter(_arrAdapter_2);
            		listDong.setVisibility(View.INVISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            		listDong.setVisibility(View.VISIBLE);
            		_arrAdapter_2.notifyDataSetChanged();
            	}*/
            }
        });
		
		
		
		
		/*listDong.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            	
                StaticVariable.setCurrent_LocalDong(received_localDong[position]);
                LocalSearchRestaurantAsync localrestaurantasync = new LocalSearchRestaurantAsync();
                localrestaurantasync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });*/
		
	}
	
	/*
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
			
			Intent LocalSearchRestaurantActivity = new Intent(LocalSearchActivity.this, LocalSearchRestaurantActivity.class);
			
			LocalSearchRestaurantActivity.putExtra("localShop_id", localShop_id);
			LocalSearchRestaurantActivity.putExtra("localShop_name", localShop_name);
			LocalSearchRestaurantActivity.putExtra("localShop_address", localShop_address);
			LocalSearchRestaurantActivity.putExtra("localShop_telnumber", localShop_telnumber);
			LocalSearchRestaurantActivity.putExtra("localShop_numberOfTable", localShop_numberOfTable);
			
			startActivity(LocalSearchRestaurantActivity);

			super.onPostExecute(result);
		}
	}*/

}
