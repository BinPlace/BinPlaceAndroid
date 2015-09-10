package com.example.binplaceapp;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SlidingTabActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private int[] received_tableNo;
	private int[] received_tableState;
	private double[] received_tableXcoordinate;
	private double[] received_tableYcoordinate;
	private int[] received_tableSize;

	private String[] received_menuName;
	private int[] received_menuPrice;
	private String[] received_menuInfo;
	private int received_size;
	private String[] received_waiting_name;
	private int[] received_waiting_number;

	Bundle Tab1_bundle, Tab2_bundle, Tab4_bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingtab_activity);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getApplicationContext(), getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		// for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
		// Create a tab with text corresponding to the page title defined by
		// the adapter. Also specify this Activity object, which implements
		// the TabListener interface, as the callback (listener) for when
		// this tab is selected.
		actionBar
				.addTab(actionBar.newTab().setText("빈자리").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("메뉴").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("대기표받기")
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("대기표보기")
				.setTabListener(this));

		actionBar.setStackedBackgroundDrawable(new ColorDrawable(0xFF415581));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		// Mainactivity로부터 해당 음식점의 테이블 정보와 테이블갯수 정보를 받아옴

		Intent intent = getIntent();

		received_tableNo = intent.getExtras().getIntArray("tableNo");
		received_tableState = intent.getExtras().getIntArray("tableState");
		received_tableXcoordinate = intent.getExtras().getDoubleArray(
				"tableXcoordinate");
		received_tableYcoordinate = intent.getExtras().getDoubleArray(
				"tableYcoordinate");
		received_tableSize = intent.getExtras().getIntArray("tableSize");
		// table정보를 getExtra로 받음

		received_menuName = intent.getExtras().getStringArray("menuName");
		received_menuPrice = intent.getExtras().getIntArray("menuPrice");
		received_menuInfo = intent.getExtras().getStringArray("menuInfo");
		received_size = intent.getExtras().getInt("size");

		if (!StaticVariable.getCurrentWaitingName().equals("")) {
			StaticVariable.setTab3Flag(true);
			received_waiting_name = intent.getExtras().getStringArray(
					"waiting_name");
			received_waiting_number = intent.getExtras().getIntArray(
					"waiting_number");
			
			int flag = -1;
			for (int i = 0; i < received_waiting_name.length; i++) {

				if (received_waiting_name[i].equals(StaticVariable
						.getCurrentWaitingName())) {
					flag = i;
					break;
				}
			}

			if (flag == -1) {
				StaticVariable.setCurrentWaitingName("");

			}
		}

		// Tab1에 데이터를 전달해줌
		Tab1_bundle = new Bundle();
		Tab1_bundle.putIntArray("tableNo", received_tableNo);
		Tab1_bundle.putIntArray("tableState", received_tableState);
		Tab1_bundle.putDoubleArray("tableXcoordinate",
				received_tableXcoordinate);
		Tab1_bundle.putDoubleArray("tableYcoordinate",
				received_tableYcoordinate);
		Tab1_bundle.putIntArray("tableSize", received_tableSize);

		Tab2_bundle = new Bundle();
		Tab2_bundle.putStringArray("menuName", received_menuName);
		Tab2_bundle.putIntArray("menuPrice", received_menuPrice);
		Tab2_bundle.putStringArray("menuInfo", received_menuName);
		Tab2_bundle.putInt("size", received_size);

		Tab4_bundle = new Bundle();

		StaticVariable.setWaiting_name(received_waiting_name);
		StaticVariable.setWaiting_number(received_waiting_number);

		

		if (!StaticVariable.getCurrentWaitingName().equals("")) {

			Tab4_bundle.putStringArray("waiting_name", received_waiting_name);
			Tab4_bundle.putIntArray("waiting_number", received_waiting_number);
		}
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		Context mContext;

		public SectionsPagerAdapter(Context mContext, FragmentManager fm) {
			super(fm);
			this.mContext = mContext;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position) {
			case 0:

				Tab1 tab1 = new Tab1(mContext);
				tab1.setArguments(Tab1_bundle);
				return tab1;
			case 1:
				Tab2 tab2 = new Tab2(mContext);
				tab2.setArguments(Tab2_bundle);
				return tab2;

			case 2:
				return new Tab3(mContext);
			case 3:
				Tab4 tab4 = new Tab4(mContext);
				tab4.setArguments(Tab4_bundle);
				return tab4;
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

	}

}