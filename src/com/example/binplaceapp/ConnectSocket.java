package com.example.binplaceapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ConnectSocket {
	   
	   static Socket clientSocket = null;
	   static InputStream in = null;
	   static ObjectInputStream inObj = null;
	   static OutputStream out = null;
	   static ObjectOutputStream outObj = null;
	   static DataInputStream chk_in = null;
	   static boolean confirm;
	   final private int portnum = 6388;
	   private static String[] sendingData;
	   private boolean chk_timeout=false;

	   String[] receive = new String[1000];
	   int[] receiveInt = new int[1000];
	   double[] receiveDouble = new double[1000];
	   
	   public ConnectSocket() {
	      try{
	         clientSocket = new Socket("183.96.25.221",portnum);
	          out = clientSocket.getOutputStream();
	          outObj = new ObjectOutputStream(out);
	          in = clientSocket.getInputStream();
	          inObj = new ObjectInputStream(in);
	          //chk_in = new DataInputStream(clientSocket.getInputStream());
	      } catch(UnknownHostException e){
	         System.err.println("서버에 접근할 수 없슴니다.");
	         System.exit(1); 
	         
	      } catch(IOException e){
	         System.err.println("입출력 오류");
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(String data){   //String 형태로 데이터 보냄
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         //System.err.println("쓰기 오류");
	         System.err.println(e);
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(String[] data){   //String 배열 형태로 데이터 보냄
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         //System.err.println("쓰기 오류");
	         System.err.println(e);
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(int[] data){      //int 배열 형태로 데이터 보냄
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         System.err.println("쓰기 오류");
	         System.exit(1);
	      }
	   }
	   
	   
	   
	   public String[] receiveData(){   //String 배열 형태로 데이터 받음
	      try{
	          receive = (String[])inObj.readObject();
	      }
	      catch(ClassNotFoundException e){
	         System.err.println("오류");
	      }
	      catch(SocketTimeoutException e){
	         System.err.println("타임아웃");
	         sendingData = new String[2]; 
	         sendingData[0] = "sockettimeout";
	         this.sendData(sendingData);
	         this.setChk_timeout(false);
	      }
	      catch(IOException e){
	         System.err.println(e);
	      }
	      return receive;
	   }
	   
	   public int[] receiveIntData(){   //int 배열 형태로 데이터 받음
	      try{
	         //in = clientSocket.getInputStream();
	          //inObj = new ObjectInputStream(in);
	    	  receiveInt = (int[])inObj.readObject();
	      }
	      catch(ClassNotFoundException e){
	         System.err.println("오류");
	      }
	      catch(SocketTimeoutException e){
	         System.err.println("타임아웃");
	         sendingData = new String[2]; 
	         sendingData[0] = "sockettimeout";
	         this.sendData(sendingData);
	         this.setChk_timeout(false);
	      }
	      catch(IOException e){
	         System.err.println("입출력오류---");
	      }
	      return receiveInt;
	   }
	   
	   public double[] receiveDoubleData(){   //double 배열 형태로 데이터 받음
		      try{
		         //in = clientSocket.getInputStream();
		          //inObj = new ObjectInputStream(in);
		    	  receiveDouble = (double[])inObj.readObject();
		      }
		      catch(ClassNotFoundException e){
		         System.err.println("오류");
		      }
		      catch(SocketTimeoutException e){
		         System.err.println("타임아웃");
		         sendingData = new String[2]; 
		         sendingData[0] = "sockettimeout";
		         this.sendData(sendingData);
		         this.setChk_timeout(false);
		      }
		      catch(IOException e){
		         System.err.println("입출력오류---");
		      }
		      return receiveDouble;
		   }
	   
	   
	   public boolean receiveVerifyData(){
	      try{
	         confirm = chk_in.readBoolean();
	      }
	      catch(IOException e){
	         System.err.println("입출력 오류");
	      }
	      
	      return confirm;
	   }
	   
	   public void closeInputStream(){
	      try{
	         in.close();
	         inObj.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("클라이언트 에러");
	      }
	      catch(IOException e){
	         System.err.println("닫기 실패");
	      }
	   }
	   
	   public void closeDataInputstream(){
	      try{
	         chk_in.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("클라이언트 에러");
	      }
	      catch(IOException e){
	         System.err.println("닫기 실패");
	      }
	   }
	   
	   public void closeOutputstream(){
	      try{
	         out.close();
	         outObj.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("클라이언트 에러");
	      }
	      catch(IOException e){
	         System.err.println("닫기 실패");
	      }
	   }
	   
	   
	   public static void closeSocket(){
	      try{
	         clientSocket.close();
	      } 
	      catch(NullPointerException e){
	         System.err.println("클라이언트 에러");
	      }
	      catch(IOException e){
	         System.err.println("닫기 실패");
	      }
	   }

	   public boolean getChk_timeout() {
	      return chk_timeout;
	   }

	   public void setChk_timeout(boolean chk_timeout) {
	      this.chk_timeout = chk_timeout;
	   }

	   
	   
	   
	}