package com.example.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.onlineshop.models.Orders;
import com.example.onlineshop.models.Products;
import com.example.onlineshop.models.User;

import java.util.ArrayList;
import java.util.List;

    public class myDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 17;
    private static final String DB_NAME="Users";
    private static final String TABLE_Users="user_details";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_CITY = "city";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";


    private static final String TABLE_Products = "products";
    private static final String PRODUCTS_ID = "id";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_SIZE="size";

    private static final String TABLE_Orders = "orders";
    private static final String ORDERS_ID="id";
    private static final String ORDERS_PRICE="price";
    private static final String ORDERS_IMAGE="image";
    private static final String ORDERS_SIZE="size";



    public myDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = " CREATE TABLE " + TABLE_Users + "("
                + KEY_ID +  "INTEGER PRIMARY KEY AUTOINCREMENT " + KEY_NAME + "TEXT,"
                + KEY_EMAIL + "TEXT, "
                + KEY_PASSWORD + "TEXT, "
                + KEY_CITY + "TEXT, "
                + KEY_ADDRESS + "TEXT, "
                + KEY_PHONE + "TEXT" + ")";
            db.execSQL(CREATE_TABLE1);
        String CREATE_TABLE2 = " CREATE TABLE " + TABLE_Products + "("
                + PRODUCTS_ID +  "INTEGER PRIMARY KEY AUTOINCREMENT "
                + KEY_PRICE + "TEXT, "
                + KEY_IMAGE + "TEXT, "
                + KEY_SIZE + "TEXT, " + ")";
            db.execSQL(CREATE_TABLE2);
        String CREATE_TABLE3 = " CREATE TABLE " + TABLE_Orders + "("
                + ORDERS_ID +  "INTEGER PRIMARY KEY AUTOINCREMENT "
                + ORDERS_PRICE + " TEXT, "
                + ORDERS_IMAGE + "TEXT, "
                + ORDERS_SIZE + "TEXT, " + ")";
            db.execSQL(CREATE_TABLE3);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_Products);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_Orders);
        onCreate(db);
    }
    void insertUser(String name, String email, String password, String city, String address, String phone) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_PASSWORD, password);
        cValues.put(KEY_CITY, city);
        cValues.put(KEY_ADDRESS, address);
        cValues.put(KEY_PHONE, phone);

        long newRowId=db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    public boolean checkUser(String username, String password) {
        String [] columns = {
                KEY_ID
        };
        SQLiteDatabase db=this.getReadableDatabase();
        String selection= KEY_EMAIL + "=?" + "AND" + KEY_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor=db.query(TABLE_Users,
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order)
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
public List<Products> getProducts(){

        SQLiteDatabase db=this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Products> returnList= new ArrayList<>();
        String query= "SELECT * FROM " +  TABLE_Products;

        Cursor cursor=db.rawQuery(query, new String[]{});

    while (cursor.moveToNext()) {
        int id = cursor.getInt(0);
        String price = cursor.getString(1);
        int image = cursor.getInt(2);
        String size = cursor.getString(3);


        Products product =
                new Products(id, price, image, size);
        returnList.add(product);
    }

    cursor.close();
    db.close();


    return returnList;
}
    void insertProduct(String price, Integer image, String size){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_PRICE, price);
        cValues.put(KEY_IMAGE, image);
        cValues.put(KEY_SIZE, size);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Products, null, cValues);
        db.close();
    }
    public void deleteOne(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Products, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_Products);
        db.close();
    }

    public List<Orders> getOrders() {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Orders> returnList = new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_Orders;


        Cursor cursor = db.rawQuery(query, new String[]{});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String price = cursor.getString(1);
            int image = cursor.getInt(2);
            String size = cursor.getString(3);

            Orders orders =
                    new Orders(id, price, image, size);
            returnList.add(orders);
        }
        cursor.close();
        db.close();

        return returnList;
    }
    void insertOrder(String price, Integer image, String size){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(ORDERS_PRICE, price);
        cValues.put(ORDERS_IMAGE, image);
        cValues.put(ORDERS_SIZE, size);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Orders, null, cValues);
        db.close();
    }

    public List<User> getUser() {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> returnList = new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_Users;

        Cursor cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String email = cursor.getString(1);
            String name = cursor.getString(2);
            String city = cursor.getString(3);
            String address = cursor.getString(4);
            String phone = cursor.getString(5);

            User user =
                    new User(id, email, name, city, address, phone);
            returnList.add(user);
        }
        cursor.close();
        db.close();
        return returnList;
    }

}



