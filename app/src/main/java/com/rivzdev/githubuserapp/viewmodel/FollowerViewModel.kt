package com.rivzdev.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivzdev.githubuserapp.model.data.Users
import com.rivzdev.githubuserapp.model.api.ApiService
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