package com.uq.test.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uq.test.services.CountryDataSource
import com.uq.test.services.CountryRepository

class MainActivityViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(
                countryRepository = CountryRepository(countryDataSource = CountryDataSource())
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}