package com.letuse.kotlincurddataandimage.api

import com.letuse.kotlincurddataandimage.model.UserModelItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL="https://600fae2d6c21e1001704f132.mockapi.io/api/"
    private var apiInterface: ApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun addUsers(name: String , age: String , job: String): Call<List<UserModelItem>> {
        return apiInterface.addUsers(name , age , job)
    }

    fun getUsers() : Call<List<UserModelItem>>{
        return apiInterface.getUsers()
    }

    fun deleteUsers(ID: String) : Call<List<UserModelItem>>{
        return apiInterface.deleteUsers(ID)
    }

    fun editUsers(ID: String , Name: String , age: String , job: String) : Call<List<UserModelItem>>{
        return apiInterface.editUsers(ID,Name,age,job)
    }
}