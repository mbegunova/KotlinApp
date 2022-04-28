package com.project.contactskotlin.screen.start


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.project.contactskotlin.APP
import com.project.contactskotlin.R
import com.project.contactskotlin.adapter.PersonAdapter
import com.project.contactskotlin.databinding.FragmentStartBinding
import com.project.contactskotlin.model.PersonModel
import com.project.contactskotlin.utils.containLowerSome

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PersonAdapter
    lateinit var spinner: Spinner
    lateinit var spinnerStatus: Spinner
    lateinit var mContext: Context
    lateinit var localList: List<PersonModel>
    private var statusList: List<String> = emptyList()

    private var sortingId: Int = 0
    private var searchString: String = ""


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        initSpinnerSort()
        initSpinnerFilter()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked;
        when (item.itemId) {
            R.id.checkSort -> {
                binding.sortWrapper.visibility = if (item.isChecked) View.VISIBLE else View.GONE
                if (!item.isChecked) sortingId = 0
            }
            R.id.checkFilter -> {
                binding.filterWrapper.visibility = if (item.isChecked) View.VISIBLE else View.GONE
                if (!item.isChecked) sortingId = 0
            }
            R.id.search_bar -> {
                binding.etSearch.visibility = if (item.isChecked) View.VISIBLE else View.GONE
                if (!item.isChecked) searchString = ""
            }
        }
        setPersonsToAdapter()
        return super.onOptionsItemSelected(item);
    }


    private fun initSpinnerSort() {
        spinner = binding.spinnerSort
        val options = resources.getStringArray(R.array.spinner_array)
        val spinnerAdapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, options)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                sortingId = position
                setPersonsToAdapter()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    private fun initSpinnerFilter() {
        spinnerStatus = binding.spinnerFilter

        val spinnerAdapter =
            ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, statusList)
        spinnerStatus.adapter = spinnerAdapter

        spinnerStatus.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                setPersonsToAdapter(statusList[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun defineSorting(type: Int): List<PersonModel> {
        return when (type) {
            1 -> localList.sortedWith(compareBy { it.name })
            2 -> localList.sortedWith(compareBy { it.status })
            3 -> localList.sortedWith(compareBy { it.lastname }).asReversed()
            4 -> localList.sortedWith(compareBy { it.name }).asReversed()
            5 -> localList.sortedWith(compareBy { it.status }).asReversed()
            else -> localList.sortedWith(compareBy { it.lastname })
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun update(personList: List<PersonModel>) {
        localList = personList
        val list: ArrayList<String> = arrayListOf()
        for (item in localList) {
            val t = list.indexOf(item.status)
            if (list.indexOf(item.status) == -1 && item.status !== "")
                list.add(item.status)
        }
        statusList = list

        spinnerStatus.adapter =
            ArrayAdapter(mContext, android.R.layout.simple_spinner_dropdown_item, statusList)
    }

    private fun setPersonsToAdapter(status: String = "") {
        var sorted = defineSorting(sortingId)

        if (searchString.count() != 0)
            sorted = sorted.filter { item ->
                containLowerSome(
                    searchString,
                    listOf(
                        item.name,
                        item.lastname,
                        item.middlename,
                        item.phone,
                        item.shortPhone
                    )
                )
            }
        if (status.count() != 0) {
            sorted = sorted.filter { item -> item.status === status }
        }

        adapter.setList(sorted)
    }


    private fun initialize() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.list;
        adapter = PersonAdapter();
        recyclerView.adapter = adapter;

        //подписываемся на обновление данных
        viewModel.getPersonList().observe(this, { personList ->
            update(personList)
            setPersonsToAdapter()
        })

        binding.btnNew.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_newPersonFragment)
        }

        binding.list.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_detailsFragment)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                searchString = "$s";
                setPersonsToAdapter()

            }
        })

    }

    companion object {
        fun selectView(personModel: PersonModel) {
            val bundle = Bundle()
            bundle.putSerializable("personView", personModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailsFragment, bundle)
        }
    }

}