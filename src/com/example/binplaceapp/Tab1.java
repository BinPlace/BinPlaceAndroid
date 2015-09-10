package com.example.binplaceapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
// --------------------RestaurantDetailActivity
// 이다--------------------------------------
public class Tab1 extends Fragment {

	private int[] received_tableNo;
	private int[] received_tableState;
	private double[] received_tableXcoordinate;
	private double[] received_tableYcoordinate;
	private int[] received_tableSize;
	private tablePaint tpaint;
	private float layoutx;
	private float layouty;
	
	Button favoriteAdd;
	ButtonListener favoriteAddListener;
	
	
	AppDataBase addDB;
	SQLiteDatabase db;
	
	Context mContext;

	public Tab1(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab1, null);
		
		
		favoriteAddListener = new ButtonListener();
		addDB = new AppDataBase(getActivity());
		
		addDB.open();
		
		
		received_tableNo = getArguments().getIntArray("tableNo");
		received_tableState = getArguments().getIntArray("tableState");
		received_tableXcoordinate = getArguments().getDoubleArray("tableXcoordinate");
		received_tableYcoordinate = getArguments().getDoubleArray("tableYcoordinate");
		received_tableSize = getArguments().getIntArray("tableSize");

		TextView temp = (TextView)view.findViewById(R.id.selectedRestaurantName);
        temp.setText(StaticVariable.getCurrent_RestaurantName());
        
        RelativeLayout tlayout = (RelativeLayout)view.findViewById(R.id.activity_tab1_viewTable);
        System.out.println((int)tlayout.getWidth());
        tpaint = new tablePaint(mContext);
        tlayout.addView(tpaint);
        
		int count = 0;
		for (int i = 0; i < received_tableState.length; i++) {
			if (received_tableState[i] == 0) {
				count++;
			}
		}

		TextView numberOfTable = (TextView) view
				.findViewById(R.id.activity_tab1_numberOfTable);
		numberOfTable.setText("총 테이블 수 :  " + received_tableState.length);

		TextView numberOfEmptyTable = (TextView) view
				.findViewById(R.id.activity_tab1_numberOfEmptyTable);
		numberOfEmptyTable.setText("빈 테이블 수 :  " + count);
		
		favoriteAdd = (Button)view.findViewById(R.id.favoriteAddButton);
		favoriteAdd.setOnClickListener(favoriteAddListener);
		
		
		return view;
	}
	
	class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()) {
			
				case R.id.favoriteAddButton :
					Toast toast = Toast.makeText(getActivity(), "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT);
					toast.show();



					addDB.createEntry(StaticVariable.getCurrent_id(), StaticVariable.getCurrent_RestaurantName(), StaticVariable.getCurrent_RestaurantAddress());
					break;
			}
					
		}
	}
	
	
	
	class tablePaint extends View {
		
		int x;
		int y;
		float layx;
		float layy;
		
		public tablePaint(Context context) {
			super(context);
			x =600;
			y = 600;
			setlayx(layoutx);
			setlayy(layouty);
			
		}
		
		public void setlayx (float x) {
			layx = x;
		}
		
		public void setlayy (float y) {
			layy = y;
		}
		
		public void onDraw (Canvas canvas) {
			
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			
			for(int i=0; i<received_tableSize.length; i++) {
				
				if(received_tableSize[i] == 0) {
					int xcord = (int)(received_tableXcoordinate[i]*1.40);
					int ycord = (int)(received_tableYcoordinate[i]*1.40);
					int width = (int)(80*1.40);
					
					if(received_tableState[i] == 0) {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(60 , 179, 113));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);

					} else {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(199, 21, 133));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);
					}
				} else if(received_tableSize[i] == 1) {
					int xcord = (int)(received_tableXcoordinate[i]*1.40);
					int ycord = (int)(received_tableYcoordinate[i]*1.40);
					int width = (int)(100*1.40);
					if(received_tableState[i] == 0) {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(60 , 179, 113));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);
					} else {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(199, 21, 133));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);
					}
				} else if(received_tableSize[i] == 2) {
					int xcord = (int)(received_tableXcoordinate[i]*1.40);
					int ycord = (int)(received_tableYcoordinate[i]*1.40);
					int width = (int)(120*1.40);
					if(received_tableState[i] == 0) {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(60 , 179, 113));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);
					} else {
						paint.setStyle(Paint.Style.FILL);
						paint.setColor(Color.rgb(255, 105, 180));
						canvas.drawRect(xcord, ycord, xcord + width, ycord + width, paint);
					}
				}
			}
		}
	}

}


