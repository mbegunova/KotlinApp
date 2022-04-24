//package com.project.contactskotlin
//
//import android.database.sqlite.SQLiteDatabase
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import java.util.ArrayList
//
//class UserData(dbHelper: DbHelper) : AppCompatActivity() {
//    private val dataStr = "Иванов Сергей Юрьюевич\n Караулова Юлия Сергеевна"
//    private val data = arrayOf(
//        arrayOf("Иванов", "Сергей"),
//        arrayOf("Караулова", "Юлия"),
//        arrayOf("Караулова", "Катерина")
//    )
//    private val persons = ArrayList<Person>()
//    var database: SQLiteDatabase
//    fun initData(): ArrayList<Person> {
//        val cursor = database.query(DbHelper.TABLE_CONTACTS, null, null, null, null, null, null)
//        if (cursor.moveToFirst()) {
//            val idIndex = cursor.getColumnIndex(DbHelper.KEY_ID)
//            val nameInd = cursor.getColumnIndex(DbHelper.KEY_NAME)
//            val lNameInd = cursor.getColumnIndex(DbHelper.KEY_LASTNAME)
//            val mNameInd = cursor.getColumnIndex(DbHelper.KEY_MIDDLE_NAME)
//            val phoneInd = cursor.getColumnIndex(DbHelper.KEY_PHONE)
//            val phoneSInd = cursor.getColumnIndex(DbHelper.KEY_SHORT_PHONE)
//            val statusInd = cursor.getColumnIndex(DbHelper.KEY_STATUS)
//            val addressInd = cursor.getColumnIndex(DbHelper.KEY_ADDRESS)
//            do {
//                val person = Person(
//                    cursor.getString(idIndex),
//                    cursor.getString(nameInd),
//                    cursor.getString(lNameInd),
//                    cursor.getString(mNameInd),
//                    cursor.getString(phoneInd),
//                    cursor.getString(phoneSInd),
//                    cursor.getString(statusInd),
//                    cursor.getString(addressInd)
//                )
//                persons.add(person)
//                Log.d("mLog", "ID = $person")
//            } while (cursor.moveToNext())
//        } else Log.d("mLog", "0 rows")
//        cursor.close()
//
//
////        for (int i = 0; i < data.length; i++) {
////            Person person = new Person(data[i][0], data[i][1], "", "", "", "", "");
////            persons.add(person);
////        }
//        return persons
//    }
//
//    init {
//        database = dbHelper.writableDatabase
//    }
//}