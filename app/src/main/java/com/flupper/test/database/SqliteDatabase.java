package com.flupper.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flupper.test.Product;

import java.util.ArrayList;
import java.util.List;


public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "product";
    private	static final String TABLE_PRODUCTS = "products";

    private static final String COLUMN_ID = "_id";
    private static final	String COLUMN_PRODUCTNAME = "productname";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String PRODUCT_NAME = "PRODUCT_NAME";
    private static final String PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final String REGULAR_PRICE = "REGULAR_PRICE";
    private static final String SALE_PRICE = "SALE_PRICE";
    private static final String PRODUCT_PHOTO = "PRODUCT_PHOTO";
    private static final String PRODUCT_COLOR = "PRODUCT_COLOR";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS
            + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + PRODUCT_NAME + " TEXT,"
            + PRODUCT_DESCRIPTION + " TEXT,"
            + REGULAR_PRICE + " REAL, "
            + SALE_PRICE + " REAL, "
            + PRODUCT_PHOTO + " TEXT, "
            + PRODUCT_COLOR + " TEXT "
            + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public List<Product> listProducts(){
        String sql = "select * from " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Product> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int productIdIndex = cursor.getColumnIndex(COLUMN_ID);
                int productNameIndex = cursor.getColumnIndex(PRODUCT_NAME);
                int productDescriptionIndex = cursor.getColumnIndex(PRODUCT_DESCRIPTION);
                int salePriceIndex = cursor.getColumnIndex(SALE_PRICE);
                int regularPriceIndex = cursor.getColumnIndex(REGULAR_PRICE);
                int colorIndex = cursor.getColumnIndex(PRODUCT_COLOR);
                int photoIndex = cursor.getColumnIndex(PRODUCT_PHOTO);

                Product contact = new Product(
                    cursor.getInt(productIdIndex),
                    cursor.getString(productNameIndex),
                    cursor.getString(productDescriptionIndex),
                    cursor.getDouble(regularPriceIndex),
                    cursor.getDouble(salePriceIndex),
                    cursor.getString(photoIndex),
                    cursor.getString(colorIndex)
                );
                storeProducts.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
/*

    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
    }

    public void updateProduct(Product product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getName());
        values.put(COLUMN_QUANTITY, product.getQuantity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(product.getId())});
    }

    public Product findProduct(String name){
        String query = "Select * FROM "	+ TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = " + "name";
        SQLiteDatabase db = this.getWritableDatabase();
        Product mProduct = null;
        Cursor cursor = db.rawQuery(query,	null);
        if	(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(0));
            String productName = cursor.getString(1);
            int productQuantity = Integer.parseInt(cursor.getString(2));
            mProduct = new Product(id, productName, productQuantity);
        }
        cursor.close();
        return mProduct;
    }
*/

    public void deleteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }

    public boolean insertProduct(String name,
                                 String description,
                                 String sale_price,
                                 String regular_price,
                                 String photo,
                                 String color) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, name);
        values.put(PRODUCT_DESCRIPTION, description);
        values.put(PRODUCT_COLOR, color);
        values.put(PRODUCT_PHOTO, photo);
        values.put(SALE_PRICE, sale_price);
        values.put(REGULAR_PRICE, regular_price);

        long rowInserted = db.insert(TABLE_PRODUCTS, null, values);

        db.close();
        return rowInserted != -1;
    }
    public boolean updateProduct(String id,
                                 String name,
                                 String description,
                                 String sale_price,
                                 String regular_price,
                                 String photo,
                                 String color) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, name);
        values.put(PRODUCT_DESCRIPTION, description);
        values.put(PRODUCT_COLOR, color);
        values.put(PRODUCT_PHOTO, photo);
        values.put(SALE_PRICE, sale_price);
        values.put(REGULAR_PRICE, regular_price);

        long rowInserted = db.update(TABLE_PRODUCTS, values, COLUMN_ID + "=?",
            new String[]{String.valueOf(id)});

        db.close();
        return rowInserted != -1;
    }

    public void deleteProduct(String product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + "=?", new String[]{product_id});
        db.close();
    }
}
