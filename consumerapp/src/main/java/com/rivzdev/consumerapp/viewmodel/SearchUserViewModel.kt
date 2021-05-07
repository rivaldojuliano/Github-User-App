package com.rivzdev.consumerapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivzdev.consumerapp.model.data.UserResponse
import com.rivzdev.consumerapp.model.data.Users
import com.rivzdev.consumerapp.model.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel: ViewModel() {

    private val listSearch = MutableLiveData<ArrayList<Users>>()

    fun setSearchUsers(username: String) {
        ApiService.apiInstance
                .getSearch(username)
                .enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.isSuccessful) {
                            listSearch.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Log.d("onFailure", t.message.toString())
                    }

                })
    }

    fun getSearchUsers(): LiveData<ArrayList<Users>> {
        return listSearch
    }
}