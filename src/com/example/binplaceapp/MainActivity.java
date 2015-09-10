package com.example.binplaceapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	GoogleMap mMap;

	double latitude;
	double longitude;

	final double LATDISTANCE = 0.0045050118256;
	final double LONGDISTANCE = 0.005659725956;

	final int SIZE = 100;

	ButtonListener listener;

	Animation animationIn = null;
	PopupWindow mPopupWindow;
	View popupView;
	int scrollSizeWidth;

	Button menuSlidingButton;
	Button showRestaurantList;

	private BackPressCloseHandler backPressCloseHandler;

	boolean isSlideOn = false;
	boolean isListOn = false;
	ArrayAdapter _arrAdapter;
	ListView listView;

	Intent SlidingTabActivity;
	Intent LocalSearchActivity;

	double[] receive_latitude = new double[SIZE]; // 서버로부터 음식점 정보를 받아옴
	double[] receive_longitude = new double[SIZE];

	String[] receive_ShopName = new String[SIZE];
	String[] receive_ShopAddress = new String[SIZE];
	String[] receive_ShopId = new String[SIZE];

	LatLng locArr[] = new LatLng[SIZE];
	ArrayList<Marker> cur_Marker = new ArrayList<Marker>();

	ProgressDialog dialog; // ProgressDialog 참조변수
	int pos_dilaog = 0; // ProgressDialog의 진행 위치

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_activity);
		startActivity(new Intent(this, SplashActivity.class));

		_arrAdapter = new ArrayAdapter<String>(this, R.layout.listitemstyle);
		// 리스트뷰에 추가해주기

		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(_arrAdapter);
		listView.setVisibility(View.INVISIBLE);
		_arrAdapter.notifyDataSetChanged();
		listener = new ButtonListener();

		MapAsync map = new MapAsync();
		map.execute();

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				int flag = 0;
				for (int i = 0; i < receive_ShopId.length; i++) {
					if (receive_ShopName[i].equals(listView.getItemAtPosition(
							position).toString())) {
						flag = i;
						break;
					}
				}

				StaticVariable.setCurrent_id(receive_ShopId[flag]);
				StaticVariable
						.setCurrent_RestaurantName(receive_ShopName[flag]);
				StaticVariable
						.setCurrent_RestaurantAddress(receive_ShopAddress[flag]);
				RestaurantDetailAsync restaurantasync = new RestaurantDetailAsync();
				restaurantasync
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		});

		showRestaurantList = (Button) findViewById(R.id.showRestaurantList);
		showRestaurantList.setOnClickListener(listener);
		showRestaurantList.setSelected(false);
		setMenuButton();

		mMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.googleMap)).getMap();
		
		
		// 줌 하는 기능
		mMap.setMyLocationEnabled(true);
		UiSettings uiSettings = mMap.getUiSettings();
		uiSettings.setZoomControlsEnabled(true);

		// 현재 위치 표시

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		String locationProvider = lm.getBestProvider(new Criteria(), true);
		Location location = lm.getLastKnownLocation(locationProvider);

		latitude = location.getLatitude();
		longitude = location.getLongitude();

		final LatLng LOC = new LatLng(latitude, longitude);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOC, 16));

		/*
		mMap.addMarker(new MarkerOptions()
				.position(LOC)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
				.title("나의 위치 ").snippet("김영준"));
		 */
		// ////

		// 5. draw:
		/*
		 * ridausview = new RidausView(this,this);
		 * 
		 * MarkerOptions options = new MarkerOptions();
		 * options.position(ridausview.getCoords(latitude, longitude));
		 * options.icon
		 * (BitmapDescriptorFactory.fromBitmap(ridausview.getBitmap()));
		 * 
		 * mMap.addMarker(options);
		 */
		//

		// 중앙좌표 보내서 마커찍기
		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			double center_latitude;
			double center_longitude;
			/*
			 * double nw_latitude; //븍서쪽 좌표 double nw_longitude; double
			 * se_latitude; //남동쪽 좌표 double se_longitude;
			 */
			Double[] location = new Double[4];

			@Override
			public void onCameraChange(CameraPosition arg0) {

				LatLng location_center = arg0.target;
				center_latitude = arg0.target.latitude;
				center_longitude = arg0.target.longitude;

				location[0] = center_latitude + LATDISTANCE;
				location[1] = center_longitude - LONGDISTANCE;
				location[2] = center_latitude - LATDISTANCE;
				location[3] = center_longitude + LONGDISTANCE;

				/*
				 * while(!cur_Marker.isEmpty()){ cur_Marker.get(0).remove();
				 * cur_Marker.remove(0); }
				 */

				_arrAdapter.clear();
				int j = 0;
				for (int i = 0; i < receive_latitude.length; i++) {

					if (receive_latitude[i] < location[0]
							&& receive_longitude[i] > location[1]
							&& receive_latitude[i] > location[2]
							&& receive_longitude[i] < location[3]) {

						_arrAdapter.add(receive_ShopName[i]);

						locArr[j] = new LatLng(receive_latitude[i],
								receive_longitude[i]);

						cur_Marker.add(mMap.addMarker(new MarkerOptions()
								.position(locArr[j])
								.icon(BitmapDescriptorFactory
										.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
								.title(receive_ShopName[i])
								.snippet(receive_ShopAddress[i] + " ▷")));
						j++;
					}
				}
			}
		});

		// ////마커 info 클릭시 이벤트
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			@Override
			public void onInfoWindowClick(Marker marker) {
				int flag = 0;
				//StaticVariable.setCurrent_RestaurantName(marker.getTitle());
				
				for(int i=0;i<receive_ShopName.length;i++){
					if(marker.getTitle().equals(receive_ShopName[i])){
						flag = i;
						
						break;
					}
				}
				
				StaticVariable.setCurrent_id(receive_ShopId[flag]);
				StaticVariable
						.setCurrent_RestaurantName(receive_ShopName[flag]);
				StaticVariable
						.setCurrent_RestaurantAddress(receive_ShopAddress[flag]);
				
				//StaticVariable.setCurrent_id(marker.getId());
				
				RestaurantDetailAsync restaurantasync = new RestaurantDetailAsync();
				restaurantasync
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		});

		// 실시간 위치 추적

		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				alertStatus(provider);
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				alertProvider(provider);
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				checkProvider(provider);
			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				updateMap(location);

			}
		};

		backPressCloseHandler = new BackPressCloseHandler(this);

	}// oncreate

	// 뒤로 버튼 누를시 종료
	@Override
	public void onBackPressed() {
		// super.onBackPressed();

		if (isSlideOn) {
			
			mPopupWindow.dismiss();
			
			isSlideOn = false;
		} else {
			backPressCloseHandler.onBackPressed();
		}

	}

	private void setMenuButton() {

		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		int screenWidth = disp.getWidth();

		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) this).getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);

		this.getResources().getDisplayMetrics().density = metrics.density;

		scrollSizeWidth = (int) (screenWidth - (48 * this.getResources()
				.getDisplayMetrics().density));

		menuSlidingButton = (Button) findViewById(R.id.menuSlidingView);

		// Popup View Setting
		popupView = getLayoutInflater()
				.inflate(R.layout.sliding_activity, null);
		mPopupWindow = new PopupWindow(popupView, scrollSizeWidth,
				LayoutParams.MATCH_PARENT);
		// mPopupWindow.setWidth(scrollSizeWidth);
		mPopupWindow.setOutsideTouchable(false);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		// mPopupWindow.setAnimationStyle(R.style); // �븷�땲硫붿씠�뀡
		// �꽕�젙(-1:�꽕�젙�븞�븿,
		// 0:�꽕�젙)
		// mPopupWindow.showAsDropDown(btn_Popup, 50, 50);

		menuSlidingButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!isSlideOn) {
					mPopupWindow.showAsDropDown(menuSlidingButton, 0,
							-menuSlidingButton.getHeight());
					isSlideOn = true;
				}

			}
		});

		mPopupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					// popupView.startAnimation(anim2);
				}
				return false;
			}
		});

		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				// popupView.startAnimation(anim2);
			}
		});

	}

	// --------------------------------이벤트

	public void findrestaurant_clickListener(View target) {

		Intent LocalSearchActivity = new Intent(MainActivity.this,
				LocalSearchActivity.class);
		startActivity(LocalSearchActivity);

	}

	public void favorite_clickListener(View target) {

		Intent FavoriteActivity = new Intent(MainActivity.this,
				FavoriteActivity.class);
		startActivity(FavoriteActivity);

	}

	class ButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) { // 목록 토글 버튼 리스너
			// TODO Auto-generated method stub

			switch (v.getId()) { //
			case R.id.showRestaurantList:
				if (!isListOn) {

					listView.setVisibility(View.VISIBLE);
					_arrAdapter.notifyDataSetChanged();
					isListOn = true;
					showRestaurantList.setSelected(true);
					
				} else {
					
					listView.setVisibility(View.INVISIBLE);
					isListOn = false;
					showRestaurantList.setSelected(false);
				

				}
				break;

			}
		}

	}

	public void updateMap(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		final LatLng LOC = new LatLng(latitude, longitude);

		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LOC, 16));

		Marker mk = mMap.addMarker(new MarkerOptions()
				.position(LOC)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
				.title("나의 위치 ").snippet("김영준"));

		mk.showInfoWindow();
	}

	public void checkProvider(String provider) {
		Toast.makeText(this, provider + "에 의한 위치서비스가 꺼져 있습니다. 켜주세요",
				Toast.LENGTH_LONG).show();

		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);

	}

	public void alertProvider(String provider) {
		Toast.makeText(this, provider + "서비스가 켜졌습니다!", Toast.LENGTH_LONG)
				.show();
	}

	public void alertStatus(String provider) {
		Toast.makeText(this, "위치서비스가 " + provider + "로 변경되었습니다.",
				Toast.LENGTH_LONG).show();
	}

	/*
	 * public double calDistance(double lat1, double lon1, double lat2, double
	 * lon2) {
	 * 
	 * double theta, dist; theta = lon1 - lon2; dist = Math.sin(deg2rad(lat1)) *
	 * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) *
	 * Math.cos(deg2rad(lat2)) Math.cos(deg2rad(theta)); dist = Math.acos(dist);
	 * dist = rad2deg(dist);
	 * 
	 * dist = dist * 60 * 1.1515; dist = dist * 1.609344; // 단위 mile 에서 km 변환.
	 * dist = dist * 1000.0; // 단위 km 에서 m 로 변환
	 * 
	 * return dist; }
	 * 
	 * 
	 * // 주어진 도(degree) 값을 라디언으로 변환 private double deg2rad(double deg) { return
	 * (double) (deg * Math.PI / (double) 180d); }
	 * 
	 * // 주어진 라디언(radian) 값을 도(degree) 값으로 변환 private double rad2deg(double rad)
	 * { return (double) (rad * (double) 180d / Math.PI); }
	 */

	// 서버에서 음식점 위도 경도 좌표받아와서 마커 표시
	class MapAsync extends AsyncTask<Void, Void, Void> {

		public ConnectSocket sock;

		String[] sendingData;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			sock = new ConnectSocket();

			sendingData = new String[1];
			sendingData[0] = "requestLocationInfoApp";

			sock.sendData(sendingData);

			receive_latitude = sock.receiveDoubleData();
			receive_longitude = sock.receiveDoubleData();
			receive_ShopName = sock.receiveData();
			receive_ShopAddress = sock.receiveData();
			receive_ShopId = sock.receiveData();

			locArr = new LatLng[receive_latitude.length];

			return null;
		}

		protected void onPostExecute(Void result) {
			
			sock.closeSocket();
			
			for (int i = 0; i < receive_latitude.length; i++) {

				locArr[i] = new LatLng(receive_latitude[i],
						receive_longitude[i]);

				cur_Marker.add(mMap.addMarker(new MarkerOptions()
						.position(locArr[i])
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
						.title(receive_ShopName[i])
						.snippet(receive_ShopAddress[i] + " ▷")));
			}

			// 여기서 리스트뷰에 추가하기

			super.onPostExecute(result);
		}

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

			dialog = new ProgressDialog(MainActivity.this); // ProgressDialog 객체
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
			
			SlidingTabActivity = new Intent(MainActivity.this,
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
			sock.closeSocket();
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

			sock.closeSocket();
			
			Intent LocalSearchActivity = new Intent(MainActivity.this,
					LocalSearchActivity.class);

			LocalSearchActivity.putExtra("localGu", localGu);
			LocalSearchActivity.putExtra("localDong", localDong);

			startActivity(LocalSearchActivity);

			super.onPostExecute(result);
		}
	}

}
