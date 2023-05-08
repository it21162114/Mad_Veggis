package com.example.seller.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBhandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Companion.SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Companion.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Seller.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${SellerProfile.SellerDetails.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${SellerProfile.SellerDetails.COLUMN_1} TEXT," +
                    "${SellerProfile.SellerDetails.COLUMN_2} TEXT," +
                    "${SellerProfile.SellerDetails.COLUMN_3} TEXT," +
                    "${SellerProfile.SellerDetails.COLUMN_4} TEXT," +
                    "${SellerProfile.SellerDetails.COLUMN_5} TEXT,)";
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${SellerProfile.SellerDetails.TABLE_NAME}"
    }


    fun addInfo(title: String, description: String, manufacturedDate: String, expiredDate:String, price:String): Long? {

        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(SellerProfile.SellerDetails.COLUMN_1, title)
            put(SellerProfile.SellerDetails.COLUMN_2, description)
            put(SellerProfile.SellerDetails.COLUMN_3, manufacturedDate)
            put(SellerProfile.SellerDetails.COLUMN_4, expiredDate)
            put(SellerProfile.SellerDetails.COLUMN_5, price)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(SellerProfile.SellerDetails.TABLE_NAME, null, values)

        return newRowId;
    }

    //UPDATE
    fun updateInfo(title: String, description: String, manufacturedDate: String, expiredDate:String, price:String){

        val db = writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(SellerProfile.SellerDetails.COLUMN_1, title)
            put(SellerProfile.SellerDetails.COLUMN_2, description)
            put(SellerProfile.SellerDetails.COLUMN_3, manufacturedDate)
            put(SellerProfile.SellerDetails.COLUMN_4, expiredDate)
            put(SellerProfile.SellerDetails.COLUMN_5, price)
        }

        // Which row to update, based on the title
        val selection = "${SellerProfile.SellerDetails.COLUMN_1} LIKE ?"
        val selectionArgs = arrayOf("title")
        val count = db.update(
            SellerProfile.SellerDetails.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        //DELETE

        fun deleteInfo(title: String){

            val db = writableDatabase

            // Define 'where' part of query.
            val selection = "${SellerProfile.SellerDetails.COLUMN_1} LIKE ?"
            // Specify arguments in placeholder order.
            val selectionArgs = arrayOf("title")
            // Issue SQL statement.
            val deletedRows = db.delete(SellerProfile.SellerDetails.TABLE_NAME, selection, selectionArgs)
        }

    }

        //view

    fun readAllInfo(): MutableList<Long> {
        val Name : String = "title"
        val db = readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, SellerProfile.SellerDetails.COLUMN_1, SellerProfile.SellerDetails.COLUMN_2, SellerProfile.SellerDetails.COLUMN_3, SellerProfile.SellerDetails.COLUMN_4, SellerProfile.SellerDetails.COLUMN_5)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${SellerProfile.SellerDetails.COLUMN_1} = ?"
        val selectionArgs = arrayOf("title")

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${SellerProfile.SellerDetails.COLUMN_1} ASC"

        val cursor = db.query(
            SellerProfile.SellerDetails.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        );

        val sellerData = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val title = getLong(getColumnIndexOrThrow(SellerProfile.SellerDetails.COLUMN_1))
                sellerData.add(title)
            }
        }
        cursor.close()
        return sellerData;

    }

    //----------------------------------------





}