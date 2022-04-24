package com.project.contactskotlin
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.project.contactskotlin.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP = this;
        navController = Navigation.findNavController(this, R.id.nav_fragment)
    }
}


//class MainActivity : AppCompatActivity(), View.OnClickListener {
//    var newPersonBtn: Button? = null
//    var giftBtn: Button? = null
//    var dbHelper: DbHelper? = null
//    var persons: ArrayList<Person>? = null
//    var isLastnameCompare = true
//    var linearContacts: LinearLayout? = null
//
//    override fun onRestart() {
//        super.onRestart()
//        val userData = dbHelper?.let { UserData(it) }
//        persons = userData?.initData()
//        createItems(linearContacts, layoutInflater)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        isLastnameCompare = true
//        newPersonBtn = findViewById<View>(R.id.newPersonBtn) as Button
//        //newPersonBtn!!.setOnClickListener(this)
//        giftBtn = findViewById<View>(R.id.invite) as Button
//        //giftBtn!!.setOnClickListener(this)
//        linearContacts = findViewById<View>(R.id.linearContacts) as LinearLayout
//        dbHelper = DbHelper(this)
//        val userData = UserData(dbHelper!!)
//        persons = userData.initData()
//        createItems(linearContacts, layoutInflater)
//    }
//
//    fun onRadioButtonClicked(view: View) {
//        val checked = (view as RadioButton).isChecked
//        when (view.getId()) {
//            R.id.lastnameChoose -> if (checked) {
//                isLastnameCompare = true
//            }
//            R.id.statusChoose -> if (checked) {
//                isLastnameCompare = false
//            }
//        }
//        createItems(linearContacts, layoutInflater)
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    fun onClick(v: View) {
//        val intent: Intent
//        when (v.id) {
//            R.id.newPersonBtn -> {
//                intent = Intent(this@MainActivity, NewPerson::class.java)
//                startActivity(intent)
//            }
//            else -> {
//                intent = Intent(this@MainActivity, PersonInfo::class.java)
//                initPersonInfo(v, intent)
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private fun initInvitation(v: View, intent: Intent) {
//        val arr = ArrayList<String>()
//        arr.add("")
//        persons?.forEach(Consumer<Person> { person: Person ->
//            if (!arr.contains(
//                    person.status
//                )
//            ) arr.add(person.status)
//        })
//        val extra = Bundle()
//        extra.putSerializable("objects", arr)
//        intent.putExtra("status", extra)
//        startActivity(intent)
//    }
//
//    private fun initPersonInfo(v: View, intent: Intent) {
//        val index = v.tag as Int
//        val person: Person = persons!![index]
//        intent.putExtra("id", person.id)
//        intent.putExtra("name", person.name)
//        intent.putExtra("lastname", person.lastname)
//        intent.putExtra("middlename", person.middlename)
//        intent.putExtra("phone", person.phone)
//        intent.putExtra("shortPhone", person.shortPhone)
//        intent.putExtra("status", person.status)
//        intent.putExtra("address", person.address)
//        startActivity(intent)
//    }
//
//    private fun createItems(linearContacts: LinearLayout?, ltInflater: LayoutInflater) {
//        linearContacts!!.removeAllViews()
//        var textView: TextView
//        if (persons == null) return
////        Collections.sort(persons,
////            Comparator<Any?> { v1, v2 ->
////                if (isLastnameCompare) v1.lastname.compareTo(v2.lastname) else v1.status.compareTo(
////                    v2.status
////                )
////            })
//        for (i in persons!!.indices) {
//            val personView = ltInflater.inflate(R.layout.person_view, linearContacts, false)
//            val view2 = (personView as ViewGroup).getChildAt(1)
//            val view = (view2 as ViewGroup).getChildAt(0)
//            view.tag = i
//            textView = (view as ViewGroup).getChildAt(0) as TextView
//            textView.setText(persons!![i].lastname)
//            textView = view.getChildAt(1) as TextView
//            textView.setText(persons!![i].name)
//            textView = view2.getChildAt(1) as TextView
//            textView.setText(persons!![i].status)
//            linearContacts.addView(personView)
//            //view.setOnClickListener(this)
//        }
//    }
//}