package com.rivzdev.githubuserapp.helper

import android.database.Cursor
import com.rivzdev.githubuserapp.model.data.Users
import com.rivzdev.githubuserapp.model.database.DatabaseContract

object MappingHelper {

    fun mapCursorToArrayList(favoriteCursor: Cursor?): ArrayList<Users> {
        val favoriteList = ArrayList<Users>()

        favoriteCursor?.apply {
            while (moveToNext()) {
                val login = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOGIN))
                val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR_URL))
                favoriteList.add(Users(login, avatar))
            }
        }
        return favoriteList
    }

    fun mapCursorToObject(favoriteCursor: Cursor?): Users {
        var users = Users()
        favoriteCursor?.apply {
            moveToFirst()
            val login = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOGIN))
            val avatar = getString(getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR_URL))
            users = Users(login, avatar)
        }
        return users
    }
}