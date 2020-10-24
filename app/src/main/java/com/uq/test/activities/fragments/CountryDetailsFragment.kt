package com.uq.test.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.uq.test.R
import com.uq.test.activities.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_country_details, container, false)

        mainActivityViewModel.selectedCountry.observe(viewLifecycleOwner, Observer {
            val country = it ?: return@Observer

            cases_value.text = country.cases.toString()
            recovered_value.text = country.recovered.toString()
            critical_value.text = country.critical.toString()
            active_value.text = country.active.toString()
            today_cases_value.text = country.todayCases.toString()
            total_deaths_value.text = country.deaths.toString()
            today_deaths_value.text = country.todayDeaths.toString()
            country_value.text = country.country
            tests_value.text = country.tests.toString()
        })

        return rootView
    }
}