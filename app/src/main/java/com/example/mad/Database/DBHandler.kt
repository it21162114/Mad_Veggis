package com.example.mad.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Delivery.db"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${DeliveryProfile.DeliveryDetails.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DeliveryProfile.DeliveryDetails.COLUMN_1} TEXT," +
                "${DeliveryProfile.DeliveryDetails.COLUMN_2} TEXT," +
                "${DeliveryProfile.DeliveryDetails.COLUMN_3} TEXT," +
                "${DeliveryProfile.DeliveryDetails.COLUMN_4} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DeliveryProfile.DeliveryDetails.TABLE_NAME}"

    //Insert Function
    fun addInfo(Name: String, Address: String, Email: String, PNumber: String): Long? {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DeliveryProfile.DeliveryDetails.COLUMN_1, Name)
            put(DeliveryProfile.DeliveryDetails.COLUMN_2, Address)
            put(DeliveryProfile.DeliveryDetails.COLUMN_3, Email)
            put(DeliveryProfile.DeliveryDetails.COLUMN_4, PNumber)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(DeliveryProfile.DeliveryDetails.TABLE_NAME, null, values)

        return newRowId
    }

    //Update Function
    fun updateInfo(Name: String, Address: String, Email: String, PNumber: String){
        val db = writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(DeliveryProfile.DeliveryDetails.COLUMN_2, Address)
            put(DeliveryProfile.DeliveryDetails.COLUMN_3, Email)
            put(DeliveryProfile.DeliveryDetails.COLUMN_4, PNumber)
        }

        // Which row to update, based on the title
        val selection = "${DeliveryProfile.DeliveryDetails.COLUMN_1} LIKE ?"
        val selectionArgs = arrayOf(Name)
        val count = db.update(
            DeliveryProfile.DeliveryDetails.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }

    //Delete Function
    fun deleteInfo(PNumber: String){
        val db = writableDatabase

        // Define 'where' part of query.
        val selection = "${DeliveryProfile.DeliveryDetails.COLUMN_4} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(PNumber)
        // Issue SQL statement.
        val deletedRows = db.delete(DeliveryProfile.DeliveryDetails.TABLE_NAME, selection, selectionArgs)
    }

    //Read Function
    fun readAllInfo(): MutableList<Long> {
        val Name: String = "Kavishka"
        val db = readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(BaseColumns._ID, DeliveryProfile.DeliveryDetails.COLUMN_1
                                                , DeliveryProfile.DeliveryDetails.COLUMN_2
                                                , DeliveryProfile.DeliveryDetails.COLUMN_3
                                                , DeliveryProfile.DeliveryDetails.COLUMN_4)

        // Filter results WHERE "title" = 'My Title'
        val selection = "${DeliveryProfile.DeliveryDetails.COLUMN_1} = ?"
        val selectionArgs = arrayOf(Name)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${DeliveryProfile.DeliveryDetails.COLUMN_1} DESC"

        val cursor = db.query(
            DeliveryProfile.DeliveryDetails.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val deliveryData = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val Name:String = getString(getColumnIndexOrThrow(DeliveryProfile.DeliveryDetails.COLUMN_1))
                val Address = getString(getColumnIndexOrThrow(DeliveryProfile.DeliveryDetails.COLUMN_2))
                val Email = getString(getColumnIndexOrThrow(DeliveryProfile.DeliveryDetails.COLUMN_3))
                val PNumber = getString(getColumnIndexOrThrow(DeliveryProfile.DeliveryDetails.COLUMN_4))
                deliveryData.add(Name)//0
                deliveryData.add(Address)//1
                deliveryData.add(Email)//2
                deliveryData.add(PNumber)//3
            }
        }
        cursor.close()
        return deliveryData
    }
}

private fun <E> MutableList<E>.add(name: String) {
    TODO("Not yet implemented")
}
