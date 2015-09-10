package com.example.binplaceapp;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")

//------------------------------WaitingActivity 이다
public class Tab3 extends Fragment {
	Context mContext;

	ButtonListener listener;
	Button confirm;
	Button cancel;

	EditText nameInput;
	TextView result;
	
	Intent SlidingTabActivity;
	
	Bundle  Tab4_bundle;
	String[] received_waiting_name;
	int[] received_waiting_number;
	
	TextView waiting_name;
	TextView waiting_number;
	
	Toast toast;
	Tab4 tab4;
	
	private WeakReference<WaitingAsync> asyncTaskWeakRef;

	public Tab3(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab3, null);
		
		
		
		listener = new ButtonListener();
		
		
		nameInput = (EditText)view.findViewById(R.id.nameInput);
		
		confirm = (Button)view.findViewById(R.id.waiting_confirm);
		confirm.setOnClickListener(listener);
		
		if(!StaticVariable.getCurrentWaitingName().equals("")){
			nameInput.setText(StaticVariable.getCurrentWaitingName()+" 고객님 신청이 완료되었습니다.");
			confirm.setEnabled(false);
			nameInput.setEnabled(false);
			
		}
		
		
			
		
		
		return view;
	}
	
	class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()) {
			
				case R.id.waiting_confirm :
					
					if(nameInput.getText().toString().equals("")){
						//toast 로 이름을 입력하세요라고 띄워줘야됨
						toast = Toast.makeText(getActivity(), "이름을 입력하세요.", Toast.LENGTH_SHORT);
						toast.show();
					}
					else{
						confirm.setEnabled(false);
						nameInput.setEnabled(false);
						toast = Toast.makeText(getActivity(), "대기열에 등록되었습니다.", Toast.LENGTH_SHORT);
						toast.show();
						WaitingAsync waitingasync = new WaitingAsync();
						asyncTaskWeakRef = new WeakReference<WaitingAsync>(waitingasync);
						waitingasync.execute();
					}
					break;
				case R.id.waiting_cancel :
					
					break;
			}
					
		}
	}
	
	
	class WaitingAsync extends AsyncTask<Void, Void, Void> {

		public ConnectSocket sock;

		final static int SIZE = 100;
		String[] sendingData;
		private String currentid;

		private int[] numberOfTable = new int[1];
		private int[] Table_arr;
		
		public String getCurrentid() {
			return currentid;
		}

		public void setCurrentid(String currentid) {
			this.currentid = currentid;
		}


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			
			sock = new ConnectSocket();

			sendingData = new String[3];
			sendingData[0] = "sendingWaitingNameApp";
			sendingData[1] = StaticVariable.getCurrent_id();
			sendingData[2] = nameInput.getText().toString();
			sock.sendData(sendingData);
			
			received_waiting_name = sock.receiveData();
			received_waiting_number = sock.receiveIntData();
			
			StaticVariable.setCurrentWaitingName(nameInput.getText().toString());
			
			return null;
		}
		
		
		protected void onPostExecute(Void result){
			
			sock.closeSocket();
			
			nameInput.setText(StaticVariable.getCurrentWaitingName()+" 고객님 신청이 완료되었습니다.");
			
			StaticVariable.setWaitingFlag(true);
			StaticVariable.setTab3Flag(true);
			
			StaticVariable.setWaiting_name(received_waiting_name);
			StaticVariable.setWaiting_number(received_waiting_number);
			
			nameInput.setEnabled(false);
			
			
			
			waiting_name = Tab4.getWaiting_name();
			waiting_number = Tab4.getWaiting_number();
		    
		    waiting_name.setText(StaticVariable.getCurrentWaitingName()+" 고객님");
	    	  
	  		int flag = -1;
	  		for(int i = 0 ; i <received_waiting_name.length ;i++){
	  			  
	  			if(received_waiting_name[i].equals(StaticVariable.getCurrentWaitingName())){
	  				flag = i;
	  				break;
	  			}
	  		}
	  		
	  		int waitingNumber = 0;
	  		
	  		for(int i = 0; i<received_waiting_number.length; i++){
	  			
	  			if(received_waiting_name[i].equals(StaticVariable.getCurrentWaitingName())){
	  				waitingNumber = received_waiting_number[i];
	  				break;
	  			}
	  		}
	  		
	  		  
	  		if(flag == -1){
	  			waiting_number.setText("대기번호가 리스트에 없습니다.");
	  		}
	  		else{
	  			
	  			waiting_number.setText("대기번호는 "+ waitingNumber+"번이며\n"+"앞에 "+flag+"명의 대기자가 있습니다.");
	  			
	  		}
			
			super.onPostExecute(result);
		}
		
	}
}
