package com.example.task_todoapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_todoapp.R
import com.example.task_todoapp.ui.adapter.BuyListAdapter
import com.example.task_todoapp.utils.ConstantsUtil.NAVIGATION_FROM
import com.example.task_todoapp.utils.Status
import com.example.task_todoapp.viewmodel.BuyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_list.*

@AndroidEntryPoint
class BuyListFragment : Fragment() {

    private var navigationFrom: String? = null

    private val viewModel: BuyListViewModel by viewModels()

    private lateinit var buyListAdapter: BuyListAdapter

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

        buyListAdapter = BuyListAdapter(requireContext())
        viewModel.buyList()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buy_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewState(Status.LOADING)
        setAdapter()
        observeData()
    }

    private fun observeData() {
        viewModel.buyList.observe(viewLifecycleOwner) {
            buyListAdapter.updateAdapter(it)
            setViewState(Status.SUCCESS)
        }
    }

    private fun setAdapter() {
        rv_buy_list.layoutManager = LinearLayoutManager(requireContext())
        rv_buy_list.adapter = buyListAdapter
    }

    private fun setViewState(status: Status) {
        when (status) {
            Status.LOADING -> pv_loader.visibility = VISIBLE
            Status.SUCCESS -> pv_loader.visibility = GONE
            Status.FAIlED -> Log.d(
                "tayyab",
                "setLoaderView: Failed"
            ) // can be shown an empty state view
        }
    }
}