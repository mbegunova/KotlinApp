//package com.project.contactskotlin
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.app.AlertDialog
//import android.content.ContentValues
//import android.content.Intent
//import android.database.sqlite.SQLiteDatabase
//import android.os.Bundle
//import android.telephony.PhoneNumberUtils
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//
//class NewPerson : Activity(), View.OnClickListener {
//
//    //var dbHelper: DbHelper? = null
//    var btnAdd: Button? = null
//    var etName: EditText? = null
//    var etLastname:EditText? = null
//    var etMiddlename:EditText? = null
//    var etPhone:EditText? = null
//    var etPhoneShort:EditText? = null
//    var etStatus:EditText? = null
//    var etAddress:EditText? = null
//
//
//    @SuppressLint("MissingSuperCall")
//    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.new_peson)
////        btnAdd = findViewById<View>(R.id.btnAdd) as Button?
////        btnAdd!!.setOnClickListener(this)
////        etName = findViewById<View>(R.id.etName) as EditText?
////        etLastname = findViewById<View>(R.id.etLastname) as EditText
////        etMiddlename = findViewById<View>(R.id.etMiddlename) as EditText
////        etPhone = findViewById<View>(R.id.etPhone) as EditText
////        etPhoneShort = findViewById<View>(R.id.etPhoneShort) as EditText
////        etStatus = findViewById<View>(R.id.etStatus) as EditText
////        etAddress = findViewById<View>(R.id.etAddress) as EditText
////        dbHelper = DbHelper(this)
//    }
//
//    override fun onClick(v: View?) {
////        val name = etName!!.text.toString().trim { it <= ' ' }
////        val lastname: String = etLastname?.getText().toString().trim { it <= ' ' }
////        val middlename: String = etMiddlename?.getText().toString().trim { it <= ' ' }
////        val phone: String = etPhone?.getText().toString().trim { it <= ' ' }
////        val shortPhone: String = etPhoneShort?.getText().toString().trim { it <= ' ' }
////        val status: String = etStatus?.getText().toString().trim { it <= ' ' }
////        val address: String = etAddress?.getText().toString().trim { it <= ' ' }
////        val database = dbHelper!!.writableDatabase
////        if (!validateValues(name, lastname, middlename, phone)) {
////            AlertDialog.Builder(this)
////                .setMessage("Проверьте правильность ввода")
////                .show()
////            return
////        }
////        val contentValues = ContentValues()
////        contentValues.put(DbHelper.KEY_NAME, name)
////        contentValues.put(DbHelper.KEY_LASTNAME, lastname)
////        contentValues.put(DbHelper.KEY_MIDDLE_NAME, middlename)
////        contentValues.put(DbHelper.KEY_PHONE, phone)
////        contentValues.put(DbHelper.KEY_SHORT_PHONE, shortPhone)
////        contentValues.put(DbHelper.KEY_STATUS, status)
////        contentValues.put(DbHelper.KEY_ADDRESS, address)
////        database.insert(DbHelper.TABLE_CONTACTS, null, contentValues)
////        val intent = Intent(this@NewPerson, MainActivity::class.java)
////        startActivity(intent)
//    }
//
//
//    open fun validateValues(
//        name: String,
//        lastname: String,
//        middlename: String,
//        phone: String
//    ): Boolean {
//        return PhoneNumberUtils.isGlobalPhoneNumber(phone) and (name.length != 0
//                ) and (lastname.length != 0) and (middlename.length != 0)
//    }
//}