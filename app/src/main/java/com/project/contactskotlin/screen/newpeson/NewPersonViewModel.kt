package com.project.contactskotlin.screen.newpeson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactskotlin.REPOSITORY
import com.project.contactskotlin.model.PersonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewPersonViewModel : ViewModel() {

    // работа в параллельном потоке
    fun insert(personModel: PersonModel, onSuccess:( )-> Unit) = viewModelScope.launch (Dispatchers.IO){
        REPOSITORY.insert(personModel){
            onSuccess
        }
    }
}