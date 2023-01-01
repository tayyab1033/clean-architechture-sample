package com.example.task_todoapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.example.task_todoapp.R
import com.example.task_todoapp.remote.Test
import com.example.task_todoapp.ui.fragments.BuyListFragment
import com.example.task_todoapp.ui.fragments.CallListFragment
import com.example.task_todoapp.ui.fragments.SellListFragment
import com.example.task_todoapp.utils.ConstantsUtil.NAVIGATION_FROM
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    lateinit var callListFragment: CallListFragment
    lateinit var sellListFragment: SellListFragment
    lateinit var buyListFragment: BuyListFragment

    @Inject
    lateinit var testing: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        setFragments()

        testing.showTestMessage()
    }

    private fun setFragments() {
        val bundle = Bundle()
        bundle.putString(NAVIGATION_FROM, TAG)

        callListFragment = CallListFragment()
        callListFragment.arguments = bundle

        sellListFragment = SellListFragment()
        sellListFragment.arguments = bundle

        buyListFragment = BuyListFragment()
        buyListFragment.arguments = bundle
    }

    private fun setListeners() {
        iv_close.setOnClickListener {
            frame_container.visibility = GONE
            iv_close.visibility = GONE
            tv_title.visibility = VISIBLE
        }

        btn_call_list.setOnClickListener {
            loadFragment(callListFragment)
        }

        btn_buy_list.setOnClickListener {
            loadFragment(buyListFragment)
        }

        btn_sell_list.setOnClickListener {
            loadFragment(sellListFragment)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        frame_container.visibility = VISIBLE
        iv_close.visibility = VISIBLE
        tv_title.visibility = GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment).commit()
    }

    override fun onBackPressed() {
        if (frame_container.visibility == VISIBLE) {
            iv_close.performClick()
        } else {
            super.onBackPressed()
        }
    }
}
