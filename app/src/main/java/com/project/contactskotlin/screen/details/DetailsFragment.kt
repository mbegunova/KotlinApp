package com.project.contactskotlin.screen.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.project.contactskotlin.APP
import com.project.contactskotlin.R
import com.project.contactskotlin.databinding.FragmentDetailsBinding
import com.project.contactskotlin.model.PersonModel
import android.widget.Toast
import androidx.core.view.isVisible
import com.project.contactskotlin.utils.validateName
import com.project.contactskotlin.utils.validatePhone


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var current: PersonModel
    lateinit var editTexts: HashSet<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        editTexts = getTextViews(binding.root)
        current = arguments?.getSerializable("personView") as PersonModel
        return binding.root
    }

    private fun getTextViews(root: ViewGroup): HashSet<EditText> {
        val views: HashSet<EditText> = HashSet()
        for (i in 0 until root.childCount) {
            val v = root.getChildAt(i)
            if (v is EditText) {
                views.add(v)
            } else if (v is ViewGroup) {
                views.addAll(getTextViews(v)!!)
            }
        }
        return views
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {

        binding.tvName.setText(current.name)
        binding.tvLastname.setText(current.lastname)
        binding.tvMiddlename.setText(current.middlename)
        binding.tvPhone.setText(current.phone)
        binding.tvPhoneShort.setText(current.shortPhone)
        binding.tvStatus.setText(current.status)
        binding.tvAddress.setText(current.address)

        val viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val id = current.id
        Toast.makeText(
            activity,
            "$id",
            Toast.LENGTH_LONG
        ).show()

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_detailsFragment_to_startFragment)
        }

        binding.edit.setOnClickListener {
            enableFields()
        }

        binding.save.setOnClickListener {
            if (validateFields())
                updatePersonModel(viewModel, current)
            else resetFields()
        }

        binding.btnDelete.setOnClickListener {
            viewModel.delete(current) {}
            APP.navController.navigate(R.id.action_detailsFragment_to_startFragment)
        }
    }


    private fun resetFields() {
        enableFields();
        Toast.makeText(
            activity,
            "Фамилия, имя не пустые. Номер содержит не менее 6 цифр",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun updatePersonModel(viewModel: DetailsViewModel, current: PersonModel) {

        val etName = binding.tvName.text.toString()
        val etLastname = binding.tvLastname.text.toString()
        val etMiddlename = binding.tvMiddlename.text.toString()
        val etPhone = binding.tvPhone.text.toString()
        val etPhoneShort = binding.tvPhoneShort.text.toString()
        val etStatus = binding.tvStatus.text.toString()
        val etAddress = binding.tvAddress.text.toString()


        current.name = etName
        current.lastname = etLastname
        current.middlename = etMiddlename
        current.phone = etPhone
        current.shortPhone = etPhoneShort
        current.status = etStatus
        current.address = etAddress

        viewModel.update(current) {}
        binding.edit.isVisible = true;
        binding.save.isVisible = false;
        Toast.makeText(
            activity,
            "Данные обновлены",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun enableFields() {
        for (item in editTexts) {
            item.isEnabled = true
        }
        binding.edit.isVisible = false;
        binding.save.isVisible = true;
    }

    private fun validateFields(): Boolean {
        var validation = true

        for (item in editTexts) {
            item.isEnabled = false
            if (item.id == binding.tvName.id) {
                validation = validateName(item.text.toString())
            } else if (item.id == binding.tvLastname.id) {
                validation = validateName(item.text.toString())
            } else if (item.id == binding.tvPhone.id) {
                validation = validatePhone(item.text.toString())
            }
            if (!validation) break
        }
        return validation
    }

}