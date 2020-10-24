package com.uq.test.models


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("active")
    val active: Int,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("tests")
    val tests: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("todayRecovered")
    val todayRecovered: Int,
    @SerializedName("updated")
    val updated: Long
)