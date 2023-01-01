package com.example.task_todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task_todoapp.R
import com.example.task_todoapp.databinding.ListItemPersonBinding
import com.example.task_todoapp.model.Person
import com.example.task_todoapp.model.Persons

class CallListAdapter(private var context: Context?) :
    RecyclerView.Adapter<CallListAdapter.MyViewHolder>() {

    var personList: ArrayList<Person> = ArrayList()

    inner class MyViewHolder constructor(val binding: ListItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.person = person
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListItemPersonBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.list_item_person,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val person = personList.get(position)
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return if (personList.isEmpty()) 0 else personList.size
    }

    fun updateAdapter(persons: Persons) {
        this.personList.clear()
        this.personList.addAll(persons)
        notifyDataSetChanged()
    }
}