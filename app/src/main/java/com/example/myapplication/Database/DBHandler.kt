package com.example.myapplication.Database

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
        const val DATABASE_NAME = "Database.db"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${OrderDetailsProfile.OrdersData.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${OrderDetailsProfile.OrdersData.COLUMN_1} TEXT," +
                "${OrderDetailsProfile.OrdersData.COLUMN_2} TEXT," +
                "${OrderDetailsProfile.OrdersData.COLUMN_3} TEXT," +
                "${OrderDetailsProfile.OrdersData.COLUMN_4} TEXT," +
                "${OrderDetailsProfile.OrdersData.COLUMN_5} TEXT)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${OrderDetailsProfile.OrdersData.TABLE_NAME}"


    fun addInfo(name: String, address: String, phone: String, email: String, nic: String) {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(OrderDetailsProfile.OrdersData.COLUMN_1, name)
            put(OrderDetailsProfile.OrdersData.COLUMN_2, address)
            put(OrderDetailsProfile.OrdersData.COLUMN_3, phone)
            put(OrderDetailsProfile.OrdersData.COLUMN_4, email)
            put(OrderDetailsProfile.OrdersData.COLUMN_5, nic)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(OrderDetailsProfile.OrdersData.TABLE_NAME, null, values)

    }

    fun updateInfo(name: String, address: String, phone: String, email: String, nic: String) {

        val db = writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(OrderDetailsProfile.OrdersData.COLUMN_2, address)
            put(OrderDetailsProfile.OrdersData.COLUMN_3, phone)
            put(OrderDetailsProfile.OrdersData.COLUMN_4, email)
        }

        // Which row to update, based on the title
        val selection = "${OrderDetailsProfile.OrdersData.COLUMN_1} LIKE ?"
        val selectionArgs = arrayOf(name)
        val count = db.update(
            OrderDetailsProfile.OrdersData.TABLE_NAME,
            values,
            selection,
            selectionArgs)

    }

    fun deleteInfo(name: String) {
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = "${OrderDetailsProfile.OrdersData.COLUMN_1} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(name)
        // Issue SQL statement.
        val deletedRows = db.delete(OrderDetailsProfile.OrdersData.TABLE_NAME, selection, selectionArgs)
    }

    fun readAllInfo(): MutableList<Long> {

        val name: String = "miyuru"

        val db = readableDatabase

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(
            BaseColumns._ID,
            OrderDetailsProfile.OrdersData.COLUMN_1,
            OrderDetailsProfile.OrdersData.COLUMN_2,
            OrderDetailsProfile.OrdersData.COLUMN_3,
        OrderDetailsProfile.OrdersData.COLUMN_4,
        OrderDetailsProfile.OrdersData.COLUMN_5,
        )

        // Filter results WHERE "title" = 'My Title'
        val selection = "${OrderDetailsProfile.OrdersData.COLUMN_1} = ?"
        val selectionArgs = arrayOf(name)

        // How you want the results sorted in the resulting Cursor
        val sortOrder = "${OrderDetailsProfile.OrdersData.COLUMN_1} ASC"

        val cursor = db.query(
            OrderDetailsProfile.OrdersData.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val Name = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val name = getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_1))
                Name.add(name)

            }
            cursor.close()
            return Name
        }




        fun readAllInfo(name: String): List<Any> {


            val db = readableDatabase

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(
                BaseColumns._ID,
                OrderDetailsProfile.OrdersData.COLUMN_1,
                OrderDetailsProfile.OrdersData.COLUMN_2,
                OrderDetailsProfile.OrdersData.COLUMN_3,
                OrderDetailsProfile.OrdersData.COLUMN_4,
                OrderDetailsProfile.OrdersData.COLUMN_5,
            )

            // Filter results WHERE "title" = 'My Title'
            val selection = "${OrderDetailsProfile.OrdersData.COLUMN_1} = LIKE ?"
            val selectionArgs = arrayOf(name)

            // How you want the results sorted in the resulting Cursor
            val sortOrder = "${OrderDetailsProfile.OrdersData.COLUMN_1} ASC"

            val cursor = db.query(
                OrderDetailsProfile.OrdersData.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
            )

            val userInfo = mutableListOf<Long>()
            with(cursor) {
                while (moveToNext()) {
                    val name =
                        getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_1))
                    val address =
                        getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_2))
                    val phone =
                        getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_3))
                    val email =
                        getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_4))
                    val nic =
                        getLong(getColumnIndexOrThrow(OrderDetailsProfile.OrdersData.COLUMN_5))
                    userInfo.add(name)
                    userInfo.add(address)
                    userInfo.add(phone)
                    userInfo.add(email)
                    userInfo.add(nic)

                }
                cursor.close()
                return userInfo
            }


        }
    }

}