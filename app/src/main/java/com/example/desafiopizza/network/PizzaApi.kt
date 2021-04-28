package com.example.desafiopizza

import com.example.desafiopizza.network.Credencials
import com.example.desafiopizza.network.LoginRetorno
import com.example.desafiopizza.network.Pizza
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://p3teufi0k9.execute-api.us-east-1.amazonaws.com/v1/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface PizzaApiService{

    @GET("pizza")
    fun getAllData(): Call<List<Pizza>>


    @POST("signin")
    fun login(@Body userData: Credencials): Call<LoginRetorno>

}

object PizzaApi {
    val retrofitService: PizzaApiService by lazy {retrofit.create(PizzaApiService::class.java)}
}