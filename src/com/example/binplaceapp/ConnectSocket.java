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
	         System.err.println("������ ������ �� �����ϴ�.");
	         System.exit(1); 
	         
	      } catch(IOException e){
	         System.err.println("����� ����");
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(String data){   //String ���·� ������ ����
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         //System.err.println("���� ����");
	         System.err.println(e);
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(String[] data){   //String �迭 ���·� ������ ����
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         //System.err.println("���� ����");
	         System.err.println(e);
	         System.exit(1);
	      }
	   }
	   
	   public void sendData(int[] data){      //int �迭 ���·� ������ ����
	      try{
	          outObj.writeObject(data);
	      }
	      catch(IOException e){
	         System.err.println("���� ����");
	         System.exit(1);
	      }
	   }
	   
	   
	   
	   public String[] receiveData(){   //String �迭 ���·� ������ ����
	      try{
	          receive = (String[])inObj.readObject();
	      }
	      catch(ClassNotFoundException e){
	         System.err.println("����");
	      }
	      catch(SocketTimeoutException e){
	         System.err.println("Ÿ�Ӿƿ�");
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
	   
	   public int[] receiveIntData(){   //int �迭 ���·� ������ ����
	      try{
	         //in = clientSocket.getInputStream();
	          //inObj = new ObjectInputStream(in);
	    	  receiveInt = (int[])inObj.readObject();
	      }
	      catch(ClassNotFoundException e){
	         System.err.println("����");
	      }
	      catch(SocketTimeoutException e){
	         System.err.println("Ÿ�Ӿƿ�");
	         sendingData = new String[2]; 
	         sendingData[0] = "sockettimeout";
	         this.sendData(sendingData);
	         this.setChk_timeout(false);
	      }
	      catch(IOException e){
	         System.err.println("����¿���---");
	      }
	      return receiveInt;
	   }
	   
	   public double[] receiveDoubleData(){   //double �迭 ���·� ������ ����
		      try{
		         //in = clientSocket.getInputStream();
		          //inObj = new ObjectInputStream(in);
		    	  receiveDouble = (double[])inObj.readObject();
		      }
		      catch(ClassNotFoundException e){
		         System.err.println("����");
		      }
		      catch(SocketTimeoutException e){
		         System.err.println("Ÿ�Ӿƿ�");
		         sendingData = new String[2]; 
		         sendingData[0] = "sockettimeout";
		         this.sendData(sendingData);
		         this.setChk_timeout(false);
		      }
		      catch(IOException e){
		         System.err.println("����¿���---");
		      }
		      return receiveDouble;
		   }
	   
	   
	   public boolean receiveVerifyData(){
	      try{
	         confirm = chk_in.readBoolean();
	      }
	      catch(IOException e){
	         System.err.println("����� ����");
	      }
	      
	      return confirm;
	   }
	   
	   public void closeInputStream(){
	      try{
	         in.close();
	         inObj.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("Ŭ���̾�Ʈ ����");
	      }
	      catch(IOException e){
	         System.err.println("�ݱ� ����");
	      }
	   }
	   
	   public void closeDataInputstream(){
	      try{
	         chk_in.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("Ŭ���̾�Ʈ ����");
	      }
	      catch(IOException e){
	         System.err.println("�ݱ� ����");
	      }
	   }
	   
	   public void closeOutputstream(){
	      try{
	         out.close();
	         outObj.close();
	      }
	      catch(NullPointerException e){
	         System.err.println("Ŭ���̾�Ʈ ����");
	      }
	      catch(IOException e){
	         System.err.println("�ݱ� ����");
	      }
	   }
	   
	   
	   public static void closeSocket(){
	      try{
	         clientSocket.close();
	      } 
	      catch(NullPointerException e){
	         System.err.println("Ŭ���̾�Ʈ ����");
	      }
	      catch(IOException e){
	         System.err.println("�ݱ� ����");
	      }
	   }

	   public boolean getChk_timeout() {
	      return chk_timeout;
	   }

	   public void setChk_timeout(boolean chk_timeout) {
	      this.chk_timeout = chk_timeout;
	   }

	   
	   
	   
	}