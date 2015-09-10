package com.example.binplaceapp;



import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FavoriteActivity extends Activity {

   
   private ArrayAdapter<String> list_adapter;
   private ListView listView;
   
   AppDataBase dbHelper;

   ProgressDialog dialog; // ProgressDialog 참조변수
   int pos_dilaog = 0; // ProgressDialog의 진행 위치

   Intent SlidingTabActivity;
   
   private Toast toast;
   
   private ArrayList<String> items;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.favorite_activity);

      dbHelper = new AppDataBase(this);
      dbHelper.open();
      dbHelper.getDB_from_favorite(dbHelper.getmDatabase());
      
      

      
      list_adapter = new ArrayAdapter<String>(getApplicationContext(),
            R.layout.item);

      listView = (ListView) findViewById(R.id.resListView_favorite);
      listView.setAdapter(list_adapter);

      
      items = new ArrayList<String>();
      
      for(int i = 0; i<dbHelper.getShop_name().length; i++){
        
    	  items.add(dbHelper.getShop_name()[i]);
      }
      
      for(int i = 0; i<items.size(); i++){
          list_adapter.add( items.get(i).toString());
       }
      
      listView.setOnItemClickListener(new OnItemClickListener() {

         @Override
         public void onItemClick(AdapterView<?> parent, View view,
               int position, long id) {
            // TODO Auto-generated method stub
        	 //리스트클릭리스너
        	 
        	 	int flag = 0;
				for (int i = 0; i < dbHelper.getShop_name().length; i++) {
					if (dbHelper.getShop_name()[i].equals(listView.getItemAtPosition(position).toString())) {
						flag = i;
						break;
					}
				}
				
				StaticVariable.setCurrent_id(dbHelper.getShop_id()[flag]);
				StaticVariable.setCurrent_RestaurantName(dbHelper.getShop_name()[flag]);

				RestaurantDetailAsync restaurantasync = new RestaurantDetailAsync();
				restaurantasync
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
         }
      });
      
      
      listView.setOnItemLongClickListener(new OnItemLongClickListener() {

          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view,
                final int position, long id) {
             // TODO Auto-generated method stub
         	 //리스트 롱클릭리스너
	         	
        	  AlertDialog.Builder alert_confirm = new AlertDialog.Builder(FavoriteActivity.this);
        	  alert_confirm.setMessage("해당 음식점을 즐겨찾기에서 제거하시겠습니까?").setCancelable(false).setPositiveButton("확인",
        	  new DialogInterface.OnClickListener() {
        	      @Override
        	      public void onClick(DialogInterface dialog, int which) {
        	          // 'YES'
        	    	  
        	    	  
        	    	  dbHelper.deleteDB_from_favorite(dbHelper.getmDatabase(), dbHelper.getShop_id()[position]);
        	    	  //DB에서 제거하고
        	    	  // listview 초기화해줌
        	    	  dbHelper.open();
        	          
        	    	  dbHelper.getDB_from_favorite(dbHelper.getmDatabase());
        	    	  items.clear();
        	    	  list_adapter.clear();
        	    	  for(int i = 0; i<dbHelper.getShop_name().length; i++){
        	    	        
        	        	  items.add(dbHelper.getShop_name()[i]);
        	          }
        	          
        	          for(int i = 0; i<items.size(); i++){
        	              list_adapter.add( items.get(i).toString());
        	           }
        	    	  
        	    	  return;
        	      }
        	  }).setNegativeButton("취소",
        	  new DialogInterface.OnClickListener() {
        	      @Override
        	      public void onClick(DialogInterface dialog, int which) {
        	          // 'No'
        	      return;
        	      }
        	  });
        	  AlertDialog alert = alert_confirm.create();
        	  alert.show();

        	  
              return false;

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

			dialog = new ProgressDialog(FavoriteActivity.this); // ProgressDialog 객체
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

			SlidingTabActivity = new Intent(FavoriteActivity.this,
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

			SlidingTabActivity.putExtra("waiting_name", receivingData);
			SlidingTabActivity.putExtra("waiting_number", receivindIntData);
			startActivity(SlidingTabActivity);
			super.onPostExecute(result);
		}
	}
}