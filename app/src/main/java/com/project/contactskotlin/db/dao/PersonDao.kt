package com.project.contactskotlin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.contactskotlin.model.PersonModel

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerson(person: PersonModel)

    @Update
    suspend fun updatePerson(person: PersonModel)

    @Delete
    suspend fun deletePerson(person: PersonModel)

    @Query("SELECT * FROM persons_table ORDER BY id DESC")
    fun getAllPersons(): LiveData<List<PersonModel>>

    @Query("DELETE FROM persons_table")
    suspend fun deleteAll()
    
}