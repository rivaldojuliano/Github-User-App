package com.rivzdev.consumerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivzdev.consumerapp.model.data.Users
import com.rivzdev.consumerapp.model.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {

    val listFollower = MutableLiveData<ArrayList<Users>>()

    fun setFollower(username: String) {
        ApiService.apiInstance
                .getFollowers(username)
                .enqueue(object : Callback<ArrayList<Users>> {
                    override fun onResponse(call: Call<ArrayList<Users>>, response: Response<ArrayList<Users>>) {
                        if (response.isSuccessful) {
                            listFollower.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                        Log.d("onFailure", t.message.toString())
                    }

                })
    }

    fun getFollower(): LiveData<ArrayList<Users>> {
        return listFollower
    }
}