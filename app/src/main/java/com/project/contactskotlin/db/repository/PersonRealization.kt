package com.project.contactskotlin.db.repository

import androidx.lifecycle.LiveData
import com.project.contactskotlin.db.dao.PersonDao
import com.project.contactskotlin.model.PersonModel

class PersonRealization(private val personDao: PersonDao) : PersonRepository {
    override val allPersons: LiveData<List<PersonModel>>
        get() = personDao.getAllPersons()

    override suspend fun insert(personModel: PersonModel, onSuccess: () -> Unit) {
        personDao.addPerson(personModel)
        onSuccess()
    }

    override suspend fun delete(personModel: PersonModel, onSuccess: () -> Unit) {
        personDao.deletePerson(personModel)
        onSuccess()
    }

    override suspend fun update(personModel: PersonModel, onSuccess: () -> Unit) {
        personDao.updatePerson(personModel)
        onSuccess()
    }
}