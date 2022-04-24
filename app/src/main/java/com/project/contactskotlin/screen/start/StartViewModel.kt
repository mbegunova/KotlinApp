package com.project.contactskotlin.screen.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.contactskotlin.REPOSITORY
import com.project.contactskotlin.db.PersonsDatabase
import com.project.contactskotlin.db.repository.PersonRealization
import com.project.contactskotlin.model.PersonModel

class StartViewModel(application: Application) : AndroidViewModel(application) {

    val context = application;

    fun initDatabase() {
        val dao = PersonsDatabase.getDatabase(context).getPersonDao()
        REPOSITORY = PersonRealization(dao)
    }

    fun getPersonList(): LiveData<List<PersonModel>>{
        return REPOSITORY.allPersons
    }

}