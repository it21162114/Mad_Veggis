package com.example.myapplication.Database

import android.provider.BaseColumns


object OrderDetailsProfile {
    // Table contents are grouped together in an anonymous object.
    object OrdersData : BaseColumns {
        const val TABLE_NAME = "UserProfile"
        const val COLUMN_1 = "Name"
        const val COLUMN_2 = "Address"
        const val COLUMN_3 = "Phone"
        const val COLUMN_4 = "Email"
        const val COLUMN_5 = "NIC"
    }
}