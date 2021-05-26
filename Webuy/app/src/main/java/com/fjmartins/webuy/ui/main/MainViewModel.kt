package com.fjmartins.webuy.ui.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fjmartins.webuy.model.Listing
import com.fjmartins.webuy.repository.WebuyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val webuyRepository: WebuyRepository): ViewModel() {

    private val _listings = MutableLiveData<List<Listing>>().apply {
        value = ArrayList()
    }
    val listings: LiveData<List<Listing>> = _listings
    val loading = ObservableBoolean(false)
    val empty = ObservableBoolean(false)

    fun getListings() {
        loading.set(true)

        viewModelScope.launch {
            val listings  = webuyRepository.getListings().orEmpty()
            _listings.postValue(listings)
            empty.set(listings.isEmpty())

            loading.set(false)
        }
    }
}