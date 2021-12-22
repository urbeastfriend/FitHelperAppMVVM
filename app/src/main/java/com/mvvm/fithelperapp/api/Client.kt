package com.mvvm.fithelperapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class Client {


    fun  getClient() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(provideOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun provideOkHttp() : OkHttpClient{
        return client
    }

    companion object{

        val client: OkHttpClient by lazy {
            OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .build()
        }

        const val BASE_URL = "http://192.168.0.93/"
        //const val BASE_URL = "http://10.0.2.2:8080/"
    }
}