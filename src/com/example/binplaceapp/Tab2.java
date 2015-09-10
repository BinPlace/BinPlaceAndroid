package com.example.binplaceapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
// -------------------MenuInfoActivity ¿Ã¥Ÿ
public class Tab2 extends Fragment {
	Context mContext;

	Button menuButton[];
	TextView test;
	LinearLayout menuLinear;

	String[] received_menuName;
	int[] received_menuPrice;
	String[] received_menuInfo;
	int received_size;

	public Tab2(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab2, null);
		
		
		menuLinear = (LinearLayout) view.findViewById(R.id.menuLinearLayout);
		
		received_menuName = getArguments().getStringArray("menuName");
		received_menuPrice = getArguments().getIntArray("menuPrice");
		received_menuInfo = getArguments().getStringArray("menuInfo");
		received_size =  getArguments().getInt("size");
			    
		 GradientDrawable drawable = new GradientDrawable();
		    drawable.setShape(GradientDrawable.RECTANGLE);
		    drawable.setStroke(5, 0xff475368);
		
		    
		    drawable.setColor(0xffeeeeee);

		
		menuButton = new Button[received_size];
	    
	    
	    for(int i = 0; i <received_size; i++){
	       menuButton[i] = new Button(mContext);
	     
	       menuButton[i].setText(received_menuName[i]+ " " + received_menuPrice[i]+" ø¯");
	       menuButton[i].setId(i);
	       menuButton[i].setBackgroundColor(0xffeeeeee);
	       menuButton[i].setTextColor(0xff415581);
	       menuButton[i].setBackgroundDrawable(drawable);;
	      

	       menuLinear.addView(menuButton[i]);
	    }
	    
		
		return view;
	}
}
