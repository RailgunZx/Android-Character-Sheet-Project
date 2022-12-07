package com.example.charactersheetempty.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        if (it == 3 || it == 4){
            "COMING SOON"
        }
        else{
            ""
        }

    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun getIndex(): Int? {

        return _index.value
    }
}