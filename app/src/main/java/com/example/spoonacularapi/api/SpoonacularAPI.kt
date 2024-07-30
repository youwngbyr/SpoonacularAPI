package com.example.spoonacularapi.api

import com.example.spoonacularapi.model.Namamenu
import retrofit2.Call
import retrofit2.http.GET

interface SpoonacularAPI {
    @GET("food/menuItems/424571?apiKey=c146f3a51ffe427aa997cc0cd1129f27")
    fun getNamaMenu(): Call<Namamenu>
}