package com.uq.test.services

class CountryRepository(val countryDataSource: CountryDataSource) {

    fun getCountries() {
        countryDataSource.getCountries()
    }
}