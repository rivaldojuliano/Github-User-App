package com.rivzdev.consumerapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
        val login: String? = null,
        val name: String? = null,
        val avatar_url: String? = null,
        val location: String? = null
): Parcelable