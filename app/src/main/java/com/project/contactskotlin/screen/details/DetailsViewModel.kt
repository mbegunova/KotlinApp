package com.project.contactskotlin.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.contactskotlin.REPOSITORY
import com.project.contactskotlin.model.PersonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {


    fun update(personModel: PersonModel, onSuccess:()-> Unit) = viewModelScope.launch (Dispatchers.IO){
        REPOSITORY.update(personModel){
            onSuccess
        }
    }

    fun delete(personModel: PersonModel, onSuccess:()->Unit) = viewModelScope.launch (Dispatchers.IO){
        REPOSITORY.delete(personModel){
            onSuccess
        }
    }
}