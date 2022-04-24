package com.project.contactskotlin.screen.newpeson

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.project.contactskotlin.APP
import com.project.contactskotlin.R
import com.project.contactskotlin.databinding.FragmentNewPersonBinding
import com.project.contactskotlin.model.PersonModel
import com.project.contactskotlin.utils.validateName
import com.project.contactskotlin.utils.validatePhone


class NewPersonFragment : Fragment() {

    lateinit var binding: FragmentNewPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPersonBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize();
    }

    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun initialize() {

        val viewModel = ViewModelProvider(this).get(NewPersonViewModel::class.java)

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_newPersonFragment_to_startFragment)
        }

        binding.btnAdd.setOnClickListener {

            val etName = binding.etName.text.toString()
            val etLastname = binding.etLastname.text.toString()
            val etMiddlename = binding.etMiddlename.text.toString()
            val etPhone = binding.etPhone.text.toString()
            val etPhoneShort = binding.etPhoneShort.text.toString()
            val etStatus = binding.etStatus.text.toString()
            val etAddress = binding.etAddress.text.toString()

            if (validateName(etName) && validateName(etLastname) && validatePhone(etPhone)) {
                Toast.makeText(
                    activity,
                    "Фамилия, имя и номер не могут быть пустыми",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                var person = PersonModel(
                    name = etName,
                    lastname = etLastname,
                    middlename = etMiddlename,
                    phone = etPhone,
                    shortPhone = etPhoneShort,
                    status = etStatus,
                    address = etAddress
                );


                viewModel.insert(person) {}
                APP.navController.navigate(R.id.action_newPersonFragment_to_startFragment)
            }
        }
    }
}