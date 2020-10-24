package com.uq.test.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uq.test.R
import com.uq.test.activities.MainActivityViewModel
import com.uq.test.helpers.CountriesAdapter
import com.uq.test.models.Country
import kotlinx.android.synthetic.main.fragment_country_list.*
import java.util.*

class CountryListFragment : Fragment(), CountriesAdapter.CountryTapListener {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var timer: Timer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_country_list, container, false)

        mainActivityViewModel.countryResult.observe(viewLifecycleOwner, Observer { result ->
            val countryResult = result ?: return@Observer

            showSuccessToast(getString(R.string.country_data_fetched))

            countryResult.success?.let {
                country_recycler.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = CountriesAdapter(it.countryList, this@CountryListFragment)
                }
            }

            countryResult.error?.let {
                showErrorToast(it)
            }
        })

        timer = Timer()

        timer?.schedule(object : TimerTask() {
            override fun run() = mainActivityViewModel.getCountries()
        }, 0, 10000)

        return rootView
    }

    private fun showErrorToast(@StringRes errorString: Int) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onCountryTap(country: Country) {
        mainActivityViewModel.selectCountry(country)

        timer?.cancel()

        findNavController().navigate(R.id.navigation_country_details)
    }
}