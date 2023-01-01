package com.example.task_todoapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_todoapp.R
import com.example.task_todoapp.model.Product
import com.example.task_todoapp.ui.adapter.LocalBuyListAdapter
import com.example.task_todoapp.utils.ConstantsUtil.NAVIGATION_FROM
import com.example.task_todoapp.utils.Status
import com.example.task_todoapp.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_list.*
import kotlinx.android.synthetic.main.fragment_buy_list.pv_loader
import kotlinx.android.synthetic.main.fragment_sell_list.*

@AndroidEntryPoint
class SellListFragment : Fragment() {

    private var navigationFrom: String? = null

    private val viewModel: LocalViewModel by viewModels()

    private lateinit var localBuyListAdapter: LocalBuyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            navigationFrom = it.getString(NAVIGATION_FROM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("NavigationFrom", "onCreateView: Navigation From-> $navigationFrom")

        localBuyListAdapter = LocalBuyListAdapter(requireContext())
        viewModel.fetchProducts()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sell_list, container, false)
    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner) {
            localBuyListAdapter.updateAdapter(it)
            setViewState(Status.SUCCESS)
        }
    }

    private fun setAdapter() {
        rv_sell_list.layoutManager = LinearLayoutManager(requireContext())
        rv_sell_list.adapter = localBuyListAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewState(Status.LOADING)
        fillUpList()
        setAdapter()
        observeData()
    }

    private fun fillUpList() {
        viewModel.insertList(
            listOf(
                Product(0, "Table", 1200, 1, 2), Product(1, "TV", 3800, 2, 2),
                Product(2, "iPhone X", 15000, 1, 2)
            )
        )
    }

    private fun setViewState(status: Status) {
        when (status) {
            Status.LOADING -> pv_loader.visibility = View.VISIBLE
            Status.SUCCESS -> pv_loader.visibility = View.GONE
            Status.FAIlED -> Log.d(
                "tayyab",
                "setLoaderView: Failed"
            ) // can be shown an empty state view
        }
    }
}