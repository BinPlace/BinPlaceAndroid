package com.example.binplaceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDataBase {
   private static final String DATABASE_NAME = "mycontacts.db";
   private static final int DATABASE_VERSION = 5;

   private DbHelper mHelper;
   private final Context mContext;
   private SQLiteDatabase mDatabase;
   
   
   public SQLiteDatabase getmDatabase() {
	   return mDatabase;
   }

   private static final String DATABASE_READABLE = "readableInformation";
   private static final String DATABASE_WRITEABLE = "writeableInformation";
   
   public AppDataBase(Context context) {
	   mContext = context;
   }
   
   private class DbHelper extends SQLiteOpenHelper {

	    public DbHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    //Set up database here
	    public void onCreate(SQLiteDatabase db) {
	    	db.execSQL("CREATE TABLE IF NOT EXISTS shop (shop_id TEXT PRIMARY KEY, shop_name TEXT, address TEXT);");

	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    	db.execSQL("DROP TABLE IF EXISTS shop");
	        onCreate(db);
	     }

	}

   public AppDataBase open() throws SQLException {
	    //Set up the helper with the context
	    mHelper = new DbHelper (mContext);
	    //Open the database with our helper
	    mDatabase = mHelper.getWritableDatabase();
	    return this;
	}
   

   public long createEntry(String shop_id, String shop_name, String address) {
	    ContentValues cv = new ContentValues();
	    cv.put("shop_id", shop_id);
	    cv.put("shop_name", shop_name);
	    cv.put("address", address);
	    
	   
	   System.out.println(shop_id+"°¡ Ãß°¡µÊ");
	    
	    return mDatabase.insert("shop", null, cv); 
	}
   

   public void addDB_to_favorite(SQLiteDatabase db, String shop_id,
         String shop_name, String address) {
      db.execSQL("INSERT INTO shop VALUES ('" + shop_id + "', '" + shop_name + "' , '" + address + "' );");
   }

   public void deleteDB_from_favorite(SQLiteDatabase db, String shop_id) {
      db.execSQL("DELETE from shop where shop_id = '" + shop_id + "';");
   }

   private String[] shop_id;
   private String[] shop_name;
   private String[] address;

   public String[] getShop_id() {
      return shop_id;
   }

   public String[] getShop_name() {
      return shop_name;
   }

   public String[] getAddress() {
      return address;
   }

   public void getDB_from_favorite(SQLiteDatabase db) {

      int count = 0;

      Cursor cursor;
      cursor = db.rawQuery("SELECT COUNT(*) FROM shop;", null);

      while (cursor.moveToNext()) {
         count = cursor.getInt(0);
      }

      shop_id = new String[count];
      shop_name = new String[count];
      address = new String[count];

      cursor = db.rawQuery("SELECT shop_id, shop_name, address FROM shop;",
            null);
      int iter = 0;
      while (cursor.moveToNext()) {

         shop_id[iter] = cursor.getString(0);
         shop_name[iter] = cursor.getString(1);
         address[iter] = cursor.getString(2);
         iter++;
      }
      
   }

}