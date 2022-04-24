package com.project.contactskotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.contactskotlin.R
import com.project.contactskotlin.model.PersonModel
import com.project.contactskotlin.screen.start.StartFragment
import kotlinx.android.synthetic.main.person_view.view.*

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    var items = emptyList<PersonModel>()

    class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_view, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.itemView.person_lastname.text = items[position].lastname
        holder.itemView.person_name.text = items[position].name
        holder.itemView.person_status.text = items[position].status
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<PersonModel>) {
        items = list
        // Подписываемся на изменения бд
        notifyDataSetChanged()
    }


    override fun onViewAttachedToWindow(holder: PersonViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
            StartFragment.selectView(items[holder.adapterPosition])
        }
    }


    override fun onViewDetachedFromWindow(holder: PersonViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}