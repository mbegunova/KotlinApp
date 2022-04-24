//package com.project.contactskotlin
//
//import android.content.ContentValues
//import android.content.Intent
//import android.database.sqlite.SQLiteDatabase
//import android.os.Bundle
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//
//
//class PersonInfo : AppCompatActivity(), View.OnClickListener {
//    var personId: String? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.person_info)
//        val arguments = intent.extras
//        initListeners()
//        displayInfo(arguments)
//    }
//
//    override fun onClick(v: View) {
//        val dbHelper = DbHelper(this)
//        val db = dbHelper.writableDatabase
//        val phoneTV = findViewById<TextView>(R.id.tvPhone)
//        val addressTV = findViewById<TextView>(R.id.tvAddress)
//        val values = ContentValues()
//        val str: String
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        when (v.id) {
//            R.id.btnDelete -> {
//                val dialog = Dialog()
//                dialog.requestDialog(this, onDeleteContact, dbHelper, db)
//            }
//            R.id.editPhone -> {
//                phoneTV.isEnabled = true
//                phoneTV.requestFocus()
//                imm.showSoftInput(phoneTV, InputMethodManager.SHOW_IMPLICIT)
//                toggleBtnEnability(findViewById(R.id.editPhone), findViewById(R.id.donePhone))
//            }
//            R.id.editAddress -> {
//                addressTV.isEnabled = true
//                addressTV.requestFocus()
//                imm.showSoftInput(addressTV, InputMethodManager.SHOW_IMPLICIT)
//                toggleBtnEnability(findViewById(R.id.editAddress), findViewById(R.id.doneAddress))
//            }
//            R.id.donePhone -> {
//                phoneTV.isEnabled = false
//                toggleBtnEnability(findViewById(R.id.donePhone), findViewById(R.id.editPhone))
//                str = phoneTV.text.toString().trim { it <= ' ' }
//                if (str.length == 0) return
//                values.put(DbHelper.KEY_PHONE, str)
//                db.update(
//                    dbHelper.TABLE_CONTACTS,
//                    values,
//                    dbHelper.KEY_ID.toString() + "=" + personId,
//                    null
//                )
//            }
//            R.id.doneAddress -> {
//                addressTV.isEnabled = false
//                toggleBtnEnability(findViewById(R.id.doneAddress), findViewById(R.id.editAddress))
//                str = addressTV.text.toString().trim { it <= ' ' }
//                if (str.length == 0) return
//                values.put(DbHelper.KEY_ADDRESS, str)
//                db.update(
//                    dbHelper.TABLE_CONTACTS,
//                    values,
//                    dbHelper.KEY_ID.toString() + "=" + personId,
//                    null
//                )
//            }
//        }
//    }
//
//    var onDeleteContact: Expression = object : Expression {
//        override fun callback(dbHelper: DbHelper?, db: SQLiteDatabase?) {
//            db?.delete(dbHelper?.TABLE_CONTACTS, dbHelper?.KEY_ID.toString() + "=" + personId, null)
//            val intent = Intent(this@PersonInfo, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun toggleBtnEnability(b: Button, b2: Button) {
//        b.isEnabled = false
//        b2.isEnabled = true
//    }
//
//    private fun initListeners() {
//        val deleteBtn = findViewById<Button>(R.id.btnDelete)
//        val editBtn = findViewById<Button>(R.id.editPhone)
//        val editBtn2 = findViewById<Button>(R.id.editAddress)
//        val doneBtn = findViewById<Button>(R.id.donePhone)
//        val doneBtn2 = findViewById<Button>(R.id.doneAddress)
//        deleteBtn.setOnClickListener(this)
//        editBtn.setOnClickListener(this)
//        editBtn2.setOnClickListener(this)
//        doneBtn.setOnClickListener(this)
//        doneBtn2.setOnClickListener(this)
//    }
//
//    private fun displayInfo(arguments: Bundle?) {
//        val nameTV = findViewById<TextView>(R.id.tvName)
//        val lastnameTV = findViewById<TextView>(R.id.tvLastname)
//        val middleNameTV = findViewById<TextView>(R.id.tvMiddlename)
//        val phoneTV = findViewById<TextView>(R.id.tvPhone)
//        val shortPhoneTV = findViewById<TextView>(R.id.tvPhoneShort)
//        val statusTV = findViewById<TextView>(R.id.tvStatus)
//        val addressTV = findViewById<TextView>(R.id.tvAddress)
//        if (arguments != null) {
//            personId = arguments["id"].toString()
//            val name = arguments["name"].toString()
//            val lastname = arguments["lastname"].toString()
//            val middlename = arguments["middlename"].toString()
//            val phone = arguments["phone"].toString()
//            val shortPhone = arguments["shortPhone"].toString()
//            val status = arguments["status"].toString()
//            val address = arguments["address"].toString()
//            nameTV.text = name
//            lastnameTV.text = lastname
//            middleNameTV.text = middlename
//            phoneTV.text = phone
//            shortPhoneTV.text = shortPhone
//            statusTV.text = status
//            addressTV.text = address
//        }
//    }
//}
//
