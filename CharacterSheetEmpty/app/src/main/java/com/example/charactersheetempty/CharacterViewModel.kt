package com.example.charactersheetempty

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class CharacterViewModel(private val repository: CharacterRepository): ViewModel() {


    val allCharacters: LiveData<Map<Int, CharacterItem>> = repository.allCharacters.asLiveData()

    fun insert(toDoItem: CharacterItem) = viewModelScope.launch {
        repository.insert(toDoItem)
    }

    fun delete(entry: CharacterItem) = viewModelScope.launch {
        entry.id?.let { repository.delete(it) }
    }

    fun edit(entry: CharacterItem) = viewModelScope.launch {
        repository.update(entry)
    }

    class CharacterViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory{
        override fun <T: ViewModel> create(modelClass: Class<T>): T{
            if(modelClass.isAssignableFrom(CharacterViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return CharacterViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }


}