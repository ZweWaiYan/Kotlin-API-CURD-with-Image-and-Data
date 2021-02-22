package com.letuse.kotlincurddataandimage.api

import com.letuse.kotlincurddataandimage.model.UserModelItem
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded

    @POST("users")
    fun addUsers(
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("job") job: String
    ): Call<List<UserModelItem>>

    @FormUrlEncoded

    @PUT("users/{id}")
    fun editUsers(
        @Path("id") id: String,
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("job") job: String
    ): Call<List<UserModelItem>>

    @GET("users")
    fun getUsers(
    ): Call<List<UserModelItem>>

    @DELETE("users/{id}")
    fun deleteUsers(
        @Path("id") id: String
    ): Call<List<UserModelItem>>
}