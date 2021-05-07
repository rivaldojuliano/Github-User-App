package com.rivzdev.githubuserapp.model.database

import android.net.Uri
import android.provider.BaseColumns
import com.rivzdev.githubuserapp.model.database.DatabaseContract.FavoriteColumns.Companion.TABLE_NAME

object DatabaseContract {

    const val AUTHORITY = "com.rivzdev.githubuserapp"
    const val SCHEME = "content"

    class FavoriteColumns: BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite"
            const val LOGIN = "login"
            const val AVATAR_URL = "avatar_url"
        }
    }

    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()
}