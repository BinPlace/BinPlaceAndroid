package com.example.binplaceapp;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LocalSearchRestaurantActivity extends Activity{
	
	
	ArrayAdapter _arrAdapter;
	ListView listView;
	
	Intent SlidingTabActivity;
	
	private String[] localShop_id;
	private String[] localShop_name;
	private String[] localShop_address;
	private String[] localShop_telnumber;
	private int[] localShop_numberOfTable;
	
	ProgressDialog dialog; // ProgressDialog 참조변수
	int pos_dilaog = 0; // ProgressDialog의 진행 위치

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localsearchrestaurant_activity);
		
		Intent intent = getIntent();
		
		localShop_id = intent.getExtras().getStringArray("localShop_id");
		localShop_name = intent.getExtras().getStringArray("localShop_name");
		localShop_address = intent.getExtras().getStringArray("localShop_address");
		localShop_telnumber = intent.getExtras().getStringArray("localShop_telnumber");
		localShop_numberOfTable = intent.getExtras().getIntArray("localShop_numberOfTable");
		
		_arrAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		// 리스트뷰에 추가해주기

		listView = (ListView) findViewById(R.id.localSearchRestaurantListView);
		listView.setAdapter(_arrAdapter);
		listView.setVisibility(View.INVISIBLE);
		_arrAdapter.notifyDataSetChanged();
		
		
		for (int i = 0; i < localShop_name.length; i++) {
			if(localShop_name[i]== null || localShop_name[i].equals("") == true){
				break;
			}
			_arrAdapter.add(localShop_name[i] +"  "+localShop_telnumber[i]);
		}
		listView.setVisibility(View.VISIBLE);
		_arrAdapter.notifyDataSetChanged();
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
            	
                StaticVariable.setCurrent_id(localShop_id[position]);
                StaticVariable.setCurrent_RestaurantName(localShop_name[position]);
                RestaurantDetailAsync restaurantasync = new RestaurantDetailAsync();
				restaurantasync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
	}
	
	
	class RestaurantDetailAsync extends AsyncTask<Void, Void, Void> {
		
		
		public ConnectSocket sock;

		final static int SIZE = 100;
		String[] sendingData;
		String[] receivingData;

		private int[] tableNo;
		private int[] tableState;
		private double[] tableXcoordinate;
		private double[] tableYcoordinate;
		private int[] tableSize;
		
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);

		}

		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			dialog = new ProgressDialog(LocalSearchRestaurantActivity.this); // ProgressDialog 객체
															// 생성
			// dialog.setTitle("Progress"); // ProgressDialog 제목
			dialog.setMessage("로딩중..."); // ProgressDialog 메세지
											// ProgressDialog
											// 스타일
											// 설정
			dialog.setCanceledOnTouchOutside(false); // ProgressDialog가 진행되는 동안
														// dialog의 바깥쪽을 눌러 종료하는
														// 것을 금지

			dialog.show(); // ProgressDialog 보여주기
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			// ProgressDialog에 변경된 위치 적용 ..
			publishProgress(); // onProgressUpdate()메소드 호출.

			// while을 끝마치고 여기까지 오면 Progress가 종료되었다는 것을 의미함.
			pos_dilaog = 0; // 다음 프로세스를 위해 위치 초기화

			sock = new ConnectSocket();

			sendingData = new String[2];
			sendingData[0] = "requestTableInfoAppNew";
			sendingData[1] = StaticVariable.getCurrent_id();
			sock.sendData(sendingData);

			tableNo = sock.receiveIntData();
			tableState = sock.receiveIntData();
			tableXcoordinate = sock.receiveDoubleData();
			tableYcoordinate = sock.receiveDoubleData();
			tableSize = sock.receiveIntData();

			return null;
		}

		protected void onPostExecute(Void result) {

			sock.closeSocket();
			
			SlidingTabActivity = new Intent(LocalSearchRestaurantActivity.this,
					SlidingTabActivity.class);
			SlidingTabActivity.putExtra("tableNo", tableNo);
			SlidingTabActivity.putExtra("tableState", tableState);
			SlidingTabActivity.putExtra("tableXcoordinate", tableXcoordinate);
			SlidingTabActivity.putExtra("tableYcoordinate", tableYcoordinate);
			SlidingTabActivity.putExtra("tableSize", tableSize);

			MenuShowAsync menuAsync = new MenuShowAsync();

			menuAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

			dialog.dismiss(); // ProgressDialog 보이지 않게 하기

			dialog = null; // 참조변수 초기화

			super.onPostExecute(result);
		}

	}

	class MenuShowAsync extends AsyncTask<Void, Void, Void> {

		ConnectSocket sock;

		final static int SIZE = 100;
		String[] sendingData;
		String[] receivingData;

		private String menu_Name[] = new String[SIZE];
		private int[] menu_Price = new int[SIZE];
		private String[] menu_Info = new String[SIZE];

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			sock = new ConnectSocket();

			sendingData = new String[2];
			sendingData[0] = "requestMenuInfoApp";
			sendingData[1] = StaticVariable.getCurrent_id();
			sock.sendData(sendingData);

			menu_Name = sock.receiveData();
			menu_Price = sock.receiveIntData();
			menu_Info = sock.receiveData();

			return null;
		}

		protected void onPostExecute(Void result) {

			sock.closeSocket();
			
			int size = 0;
			for (int i = 0; i < SIZE; i++) {
				if (menu_Name[i] == null) {
					size = i;
					break;
				}
			}

			SlidingTabActivity.putExtra("menuName", menu_Name);
			SlidingTabActivity.putExtra("menuPrice", menu_Price);
			SlidingTabActivity.putExtra("menuInfo", menu_Info);
			SlidingTabActivity.putExtra("size", size);

			if (!StaticVariable.getCurrentWaitingName().equals("")) {
				ShowWaitingAsync showwaiting = new ShowWaitingAsync();
				showwaiting.executeOnExecutor(THREAD_POOL_EXECUTOR);
			} else {

				startActivity(SlidingTabActivity);
			}

			super.onPostExecute(result);
		}
	}

	class ShowWaitingAsync extends AsyncTask<Void, Void, Void> {

		ConnectSocket sock;

		String[] sendingData;

		String[] receivingData;
		int[] receivindIntData;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			sock = new ConnectSocket();

			sendingData = new String[2];
			sendingData[0] = "requestWaitingNumberApp";
			sendingData[1] = StaticVariable.getCurrent_id();
			sock.sendData(sendingData);

			receivingData = sock.receiveData();
			receivindIntData = sock.receiveIntData();

			return null;
		}

		protected void onPostExecute(Void result) {

			sock.closeSocket();
			
			SlidingTabActivity.putExtra("waiting_name", receivingData);
			SlidingTabActivity.putExtra("waiting_number", receivindIntData);
			startActivity(SlidingTabActivity);
			super.onPostExecute(result);
		}
	}
	
}
