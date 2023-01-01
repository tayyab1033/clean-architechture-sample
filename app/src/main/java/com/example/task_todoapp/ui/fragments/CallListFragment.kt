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
import com.example.task_todoapp.ui.adapter.CallListAdapter
import com.example.task_todoapp.utils.ConstantsUtil.NAVIGATION_FROM
import com.example.task_todoapp.utils.Status
import com.example.task_todoapp.viewmodel.CallListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_buy_list.pv_loader
import kotlinx.android.synthetic.main.fragment_call_list.*

@AndroidEntryPoint
class CallListFragment : Fragment() {

    private var navigationFrom: String? = null

    private val viewModel: CallListViewModel by viewModels()

    private lateinit var callListAdapter: CallListAdapter

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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callListAdapter = CallListAdapter(requireContext())
        viewModel.callList()

        setViewState(Status.LOADING)
        setAdapter()
        observeData()
    }

    private fun observeData() {
        viewModel.callList.observe(viewLifecycleOwner) {
            it.data?.let { it1 -> callListAdapter.updateAdapter(it1) }
            setViewState(Status.SUCCESS)
        }
    }

    private fun setAdapter() {
        rv_call_list.layoutManager = LinearLayoutManager(requireContext())
        rv_call_list.adapter = callListAdapter
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