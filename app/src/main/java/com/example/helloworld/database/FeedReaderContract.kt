package com.example.helloworld.database

import android.provider.BaseColumns


object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "PaymentInfo"
        const val COLUMN_1 = "cardholder"
        const val COLUMN_2 = "card"
        const val COLUMN_3 = "cardnum"
        const val COLUMN_4 = "Date"
        const val COLUMN_5 = "cvc"
    }
}