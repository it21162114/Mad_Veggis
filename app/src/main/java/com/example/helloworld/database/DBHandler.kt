package com.example.helloworld.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.util.Date

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
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
        const val DATABASE_NAME = "Database.db"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${FeedReaderContract.FeedEntry.COLUMN_1} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_2} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_3} INTEGER," +
                    "${FeedReaderContract.FeedEntry.COLUMN_4} TEXT," +
                    "${FeedReaderContract.FeedEntry.COLUMN_5} INTEGER)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"


    }

    fun addInfo(cardholder: String,card: String, cardnum: Int , Date: String, cvc: Int ): Long? {
        // Implementation code goes here
        // Gets the data repository in write mode
        val db = writableDatabase

// Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_1,cardholder )
            put(FeedReaderContract.FeedEntry.COLUMN_2, card)
            put(FeedReaderContract.FeedEntry.COLUMN_3, cardnum)
            put(FeedReaderContract.FeedEntry.COLUMN_4, Date)
            put(FeedReaderContract.FeedEntry.COLUMN_5, cvc)

        }

// Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)

        return newRowId

    }

    fun updateinfo(cardholder: String, card: String, cardnum: Int , Date: String, cvc: Int ): Boolean {
        val db = writableDatabase

// New value for one column

        val values = ContentValues().apply {
            put(FeedReaderContract.FeedEntry.COLUMN_2, card)
            put(FeedReaderContract.FeedEntry.COLUMN_3, cardnum)

            put(FeedReaderContract.FeedEntry.COLUMN_4, Date)
            put(FeedReaderContract.FeedEntry.COLUMN_5, cvc)
        }

// Which row to update, based on the title
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_1} LIKE ?"
        val selectionArgs = arrayOf(cardholder)
        val count = db.update(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        if (count >= 1) {
            return true
        } else {
            return false
        }

    }

    fun deleteInfo(cardholder: String) {

        val db = writableDatabase
        
        // Define 'where' part of query.
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_1} LIKE ?"
// Specify arguments in placeholder order.
        val selectionArgs = arrayOf(cardholder)
// Issue SQL statement.
        val deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)
    }

    fun readAllInfo(): List<Any> {
        val cardholder: String = "avinash"

        val db = readableDatabase

     // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_1,
            FeedReaderContract.FeedEntry.COLUMN_2,
            FeedReaderContract.FeedEntry.COLUMN_3,
            FeedReaderContract.FeedEntry.COLUMN_4,
            FeedReaderContract.FeedEntry.COLUMN_5)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_1} = ?"
        val selectionArgs = arrayOf(cardholder)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_1} ASC"

        val cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val cardholders = ArrayList<String>()
        while (cursor.moveToNext()) {
            val card = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_1))
            cardholders.add(card)
        }
        cursor.close()
        return cardholders


    }

    fun readAllInfo(cardholder: String): List<Any> {


        val db = readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, FeedReaderContract.FeedEntry.COLUMN_1,
            FeedReaderContract.FeedEntry.COLUMN_2,
            FeedReaderContract.FeedEntry.COLUMN_3,
            FeedReaderContract.FeedEntry.COLUMN_4,
            FeedReaderContract.FeedEntry.COLUMN_5)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${FeedReaderContract.FeedEntry.COLUMN_1} LIKE ?"
        val selectionArgs = arrayOf(cardholder)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${FeedReaderContract.FeedEntry.COLUMN_1} ASC"

        val cursor = db.query(
            FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val paymentInfo = ArrayList<String>()
        while (cursor.moveToNext()) {
            val cardholder = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_1))
            val card  = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_2))
            val cardnum = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_3))
            val Date  = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_4))
            val cvc = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_5))
            paymentInfo.add(cardholder)//0
            paymentInfo.add(card)//1
            paymentInfo.add(cardnum)//2
            paymentInfo.add(Date)//3
            paymentInfo.add(cvc)//4
        }
        cursor.close()
        return paymentInfo


    }

}



