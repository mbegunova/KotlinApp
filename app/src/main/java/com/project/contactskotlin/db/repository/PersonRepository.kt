package com.project.contactskotlin.db.repository

import androidx.lifecycle.LiveData
import com.project.contactskotlin.model.PersonModel

interface PersonRepository {

    val allPersons: LiveData<List<PersonModel>>
    suspend fun insert(personModel: PersonModel, onSuccess: () -> Unit)

    suspend fun delete(personModel: PersonModel, onSuccess: () -> Unit)
}