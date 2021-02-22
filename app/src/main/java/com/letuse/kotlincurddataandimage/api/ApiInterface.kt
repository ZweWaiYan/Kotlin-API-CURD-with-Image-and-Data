package com.letuse.kotlincurddataandimage.api

import com.letuse.kotlincurddataandimage.model.UserModelItem
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded

    @POST("user")
    fun addUsers(
        @Field("name") name: String
    ): Call<List<UserModelItem>>

    @FormUrlEncoded

    @PUT("user/{id}")
    fun editUsers(
        @Path("id") id: String,
        @Field("name") name: String
    ): Call<List<UserModelItem>>

    @GET("user")
    fun getUsers(
    ): Call<List<UserModelItem>>

    @DELETE("user/{id}")
    fun deleteUsers(
        @Path("id") id: String
    ): Call<List<UserModelItem>>
}