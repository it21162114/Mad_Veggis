package com.example.mad.Database

import android.provider.BaseColumns

object DeliveryProfile {
    // Table contents are grouped together in an anonymous object.
    object DeliveryDetails : BaseColumns {
        const val TABLE_NAME = "DeliveryDetails"
        const val COLUMN_1 = "Name"
        const val COLUMN_2 = "Address"
        const val COLUMN_3 = "Email"
        const val COLUMN_4 = "PNumber"
    }
}