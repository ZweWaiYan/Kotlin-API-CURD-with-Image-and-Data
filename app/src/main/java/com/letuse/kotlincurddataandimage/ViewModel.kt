package com.letuse.kotlincurddataandimage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.kotlincurddataandimage.api.ApiClient
import com.letuse.kotlincurddataandimage.model.UserModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel(){
    private var users : MutableLiveData<List<UserModelItem>> = MutableLiveData()

    fun getUsers() : LiveData<List<UserModelItem>> = users

    fun loadUsers() {
        var apiClient = ApiClient()
        val callApi = apiClient.getUsers()

        callApi.enqueue(object :Callback<List<UserModelItem>>{
            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                Log.d("error" , t.toString())
            }

            override fun onResponse(call: Call<List<UserModelItem>>, response: Response<List<UserModelItem>>) {
                users.value = response.body()!!
                Log.d("gg" , "this is data => ${response.body().toString()}")
            }

        })
    }
}