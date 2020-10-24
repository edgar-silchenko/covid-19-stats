package com.uq.test.services

import androidx.lifecycle.MutableLiveData
import com.uq.test.models.CountryResponseResult
import com.uq.test.models.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CountryDataSource {

    var countryResult = MutableLiveData<CountryResponseResult<List<Country>>>()

    fun getCountries() {
        try {
            val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(CountryApiInterface::class.java)

            retrofitInstance.getAffectedCountryList().enqueue(object : Callback<List<Country>> {

                override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                    if (response.code() == 200) {
                        countryResult.postValue(CountryResponseResult.Success(response.body()!!))
                    } else {
                        countryResult.postValue(CountryResponseResult.ErrorString(String.format("Country fetch, Response code: %d", response.code())))
                    }
                }

                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    countryResult.postValue(CountryResponseResult.ErrorException(IOException("Country fetch exception", t)))
                }
            })

        } catch (e: Throwable) {
            countryResult.postValue(CountryResponseResult.ErrorException(IOException("Country fetch exception", e)))
        }
    }
}