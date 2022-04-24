package com.project.contactskotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.contactskotlin.db.dao.PersonDao
import com.project.contactskotlin.model.PersonModel

@Database(entities = [PersonModel::class], version = 1, exportSchema = false)
abstract class PersonsDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonsDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): PersonsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonsDatabase::class.java,
                    "db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


        @Synchronized
        fun getInstance(context: Context) {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, PersonsDatabase::class.java, "database").build()
                INSTANCE as PersonsDatabase
            }
            else {
                INSTANCE as PersonsDatabase
            }
        }
    }
}