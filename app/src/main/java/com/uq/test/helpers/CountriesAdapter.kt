package com.uq.test.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uq.test.R
import com.uq.test.models.Country

class CountriesAdapter(private val countriesList: List<Country>, val countryTapListener: CountryTapListener) :
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        return holder.bind(countriesList[position])
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country) {
            itemView.setOnClickListener {
                countryTapListener.onCountryTap(country)
            }
            itemView.findViewById<TextView>(R.id.country_name).text = country.country
            itemView.findViewById<TextView>(R.id.country_cases).text = String.format("Cases %s:", country.cases)
            Picasso.get().load(country.countryInfo.flag).into(itemView.findViewById<ImageView>(R.id.country_flag_image))
        }
    }

    interface CountryTapListener {
        fun onCountryTap(country: Country)
    }
}