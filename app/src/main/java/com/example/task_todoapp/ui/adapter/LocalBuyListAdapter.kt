package com.example.task_todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task_todoapp.R
import com.example.task_todoapp.databinding.ListItemProductBinding
import com.example.task_todoapp.model.Product

class LocalBuyListAdapter(private var context: Context?) :
    RecyclerView.Adapter<LocalBuyListAdapter.MyViewHolder>() {

    var productList: ArrayList<Product> = ArrayList()

    inner class MyViewHolder constructor(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.list_item_product,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productIt = productList.get(position)
        holder.bind(productIt)
    }

    override fun getItemCount(): Int {
        return if (productList.isEmpty()) 0 else productList.size
    }

    fun updateAdapter(products: List<Product>) {
        this.productList.clear()
        this.productList.addAll(products)
        notifyDataSetChanged()
    }
}