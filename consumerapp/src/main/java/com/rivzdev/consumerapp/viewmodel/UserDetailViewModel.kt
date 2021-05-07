package com.rivzdev.consumerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivzdev.consumerapp.model.api.ApiService
import com.rivzdev.consumerapp.model.data.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {
    val listDetailUser = MutableLiveData<Users>()

    fun setDetailUser(username: String) {
        ApiService.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<Users> {
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful) {
                        listDetailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                }

            })
    }

    fun getDetailUser(): LiveData<Users> {
        return listDetailUser
    }
}