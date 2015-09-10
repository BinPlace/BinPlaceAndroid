package com.example.binplaceapp;

import android.widget.TextView;

public class StaticVariable {
	/**
	 * 
	 */
	private static String currentWaitingName="";

	private static String current_id="";
	
	private static String current_RestaurantName="";
	
	private static String current_LocalGu;
	
	private static String current_LocalDong;
	
	private static boolean waitingFlag = false;
	
	private static String current_RestaurantAddress = "";
	
	private final static int SIZE = 1000;
	
	private static String[] waiting_name = new String[SIZE];
	
	
	private static int[] waiting_number = new int[SIZE];
	
	
	
	public static String[] getWaiting_name() {
		return waiting_name;
	}

	public static void setWaiting_name(String[] waiting_name) {
		StaticVariable.waiting_name = waiting_name;
	}

	public static int[] getWaiting_number() {
		return waiting_number;
	}

	public static void setWaiting_number(int[] waiting_number) {
		StaticVariable.waiting_number = waiting_number;
	}

	
	
	private static boolean Tab3Flag = false;
	
		
	public static boolean isTab3Flag() {
		return Tab3Flag;
	}

	public static void setTab3Flag(boolean tab3Flag) {
		Tab3Flag = tab3Flag;
	}

	public static String getCurrent_RestaurantAddress() {
		return current_RestaurantAddress;
	}

	public static void setCurrent_RestaurantAddress(String current_RestaurantAddress) {
		StaticVariable.current_RestaurantAddress = current_RestaurantAddress;
	}

	public static boolean isWaitingFlag() {
		return waitingFlag;
	}

	public static void setWaitingFlag(boolean waitingFlag) {
		StaticVariable.waitingFlag = waitingFlag;
	}

	public static String getCurrent_RestaurantName() {
		return current_RestaurantName;
	}

	public static void setCurrent_RestaurantName(String current_RestaurantName) {
		StaticVariable.current_RestaurantName = current_RestaurantName;
	}

	public static String getCurrent_id() {
		return current_id;
	}

	public static void setCurrent_id(String current_id) {
		StaticVariable.current_id = current_id;
	}

	public static String getCurrentWaitingName() {
		return currentWaitingName;
	}

	public static void setCurrentWaitingName(String currentWaitingName) {
		StaticVariable.currentWaitingName = currentWaitingName;
	}
	
	public static String getCurrent_LocalGu() {
		return current_LocalGu;
	}

	public static void setCurrent_LocalGu(String current_LocalGu) {
		StaticVariable.current_LocalGu = current_LocalGu;
	}

	public static String getCurrent_LocalDong() {
		return current_LocalDong;
	}

	public static void setCurrent_LocalDong(String current_LocalDong) {
		StaticVariable.current_LocalDong = current_LocalDong;
	}

	
}
