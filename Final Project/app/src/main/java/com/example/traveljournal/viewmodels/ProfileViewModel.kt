package com.example.traveljournal.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    var first_name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var full_name = MutableLiveData<String>()
}