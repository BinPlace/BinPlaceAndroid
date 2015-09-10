package com.example.binplaceapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


@SuppressLint("ValidFragment")

//-------------------------------ShowWaitingActivity�̴�
public class Tab4 extends Fragment {
	Context mContext;

	static TextView  waiting_name;
	static TextView waiting_number;
	
	public static TextView getWaiting_name() {
		return waiting_name;
	}

	public static TextView getWaiting_number() {
		return waiting_number;
	}


	
	//Button confirm;
	

	String[] received_waiting_name;
	int[] received_waiting_number;

	public Tab4(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab4, null);
		 
		
		 waiting_name = (TextView)view.findViewById(R.id.show_waiting_name);
	     waiting_number = (TextView)view.findViewById(R.id.show_waiting_number);
	     
	    
	     
	     
	     if(!StaticVariable.getCurrentWaitingName().equals("") && StaticVariable.isTab3Flag() == true){
	    	 
	    	 waiting_name.setText(StaticVariable.getCurrentWaitingName()+" ����");
	    	 
	    	 received_waiting_name = StaticVariable.getWaiting_name();
	 		 received_waiting_number =  StaticVariable.getWaiting_number();
	 		 
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
	  			  waiting_number.setText("����ȣ�� ����Ʈ�� �����ϴ�.");
	  			  
	  		  }
	  		  else{
	  			
	  			waiting_number.setText("����ȣ�� "+ waitingNumber+"���̸�\n\n"+"�տ� "+flag+"���� ����ڰ� �ֽ��ϴ�.");
	  			
	  		  }
	    	 
	     }
	     
	     if(!StaticVariable.getCurrentWaitingName().equals("") && StaticVariable.isTab3Flag() == false){
	    	
	    	  waiting_name.setText(StaticVariable.getCurrentWaitingName()+" ����");
	    	
	    	  received_waiting_name = getArguments().getStringArray("waiting_name");
	 		  received_waiting_number =  getArguments().getIntArray("waiting_number");
	    	  
	     
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
	  			  waiting_number.setText("����ȣ�� ����Ʈ�� �����ϴ�.");
	  			  StaticVariable.setTab3Flag(false);
	  			  
	  		  }
	  		  else{
	  			
	  			waiting_number.setText("����ȣ�� "+ waitingNumber+"���̸�\n\n"+"�տ� "+flag+"���� ����ڰ� �ֽ��ϴ�.");
	  			
	  		  }
	      }
	      else{
	    	  waiting_name.setText("����ȣ�� ��û���ּ���.");
	      }
		
		
		return view;
	}
	
	
}
