package com.project.contactskotlin.screen.start


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.project.contactskotlin.APP
import com.project.contactskotlin.MyDialogFragment
import com.project.contactskotlin.R
import com.project.contactskotlin.adapter.PersonAdapter
import com.project.contactskotlin.databinding.FragmentStartBinding
import com.project.contactskotlin.model.PersonModel
import org.jetbrains.anko.AlertDialogBuilder
import java.nio.file.attribute.AclEntry

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }


    private fun initialize() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.list;
        adapter = PersonAdapter();
        recyclerView.adapter = adapter;

        //подписываемся на обновление данных
        viewModel.getPersonList().observe(this, { personList ->
            //TODO: сортировка
            adapter.setList(personList)
        })

        // TODO: клик на кнопку нового чела
        binding.btnNew.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_newPersonFragment)
        }

        binding.list.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_detailsFragment)
        }

    }

    companion object {
        fun selectView(personModel: PersonModel) {
            val bundle = Bundle()
            bundle.putSerializable("personView", personModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailsFragment, bundle)
        }
    }

}