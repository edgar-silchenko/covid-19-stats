package com.uq.test.services

import com.uq.test.models.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountryApiInterface {

    @Headers("Content-Type:application/json", "Accept: application/json")
    @GET("countries")
    fun getAffectedCountryList(): Call<List<Country>>
}