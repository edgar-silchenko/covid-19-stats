package com.uq.test.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.uq.test.R
import com.uq.test.models.CountryResponseResult
import com.uq.test.models.Country
import com.uq.test.models.CountryViewResult
import com.uq.test.models.CountryViewResultData
import com.uq.test.services.CountryRepository

class MainActivityViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    var countryResult = MutableLiveData<CountryViewResult>()
    var selectedCountry = MutableLiveData<Country>()

    private val countryObserver = Observer<CountryResponseResult<List<Country>>> {
        when (it) {
            is CountryResponseResult.Success -> {
                countryResult.postValue(CountryViewResult(success = CountryViewResultData(countryList = it.data)))
            }
            is CountryResponseResult.ErrorString -> {
                countryResult.postValue(CountryViewResult(error = R.string.error_country_fetch_failed))
            }
            is CountryResponseResult.ErrorException -> {
                countryResult.postValue(CountryViewResult(error = R.string.exception_country_fetch_failed))
            }
        }
    }

    init {
        countryRepository.countryDataSource.countryResult.observeForever(countryObserver)
    }

    fun getCountries() {
        countryRepository.getCountries()
    }

    override fun onCleared() {
        countryRepository.countryDataSource.countryResult.removeObserver(countryObserver)

        super.onCleared()
    }

    fun selectCountry(country: Country) {
        selectedCountry.value = country
    }
}