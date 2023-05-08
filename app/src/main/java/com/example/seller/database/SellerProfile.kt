package com.example.seller.database

import android.provider.BaseColumns


object SellerProfile {
    // Table contents are grouped together in an anonymous object.
    object SellerDetails : BaseColumns {
        const val TABLE_NAME = "SellerDetails"
        const val COLUMN_1 = "title"
        const val COLUMN_2 = "description"
        const val COLUMN_3 = "manufacturedDate"
        const val COLUMN_4 = "expiredDate"
        const val COLUMN_5 = "price"
    }
}