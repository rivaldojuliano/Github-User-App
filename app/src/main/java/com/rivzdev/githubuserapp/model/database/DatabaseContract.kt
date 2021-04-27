package com.rivzdev.githubuserapp.model.database

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class FavoriteColumns: BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"
        }
    }
}