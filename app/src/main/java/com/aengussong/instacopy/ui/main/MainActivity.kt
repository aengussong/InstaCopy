package com.aengussong.instacopy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.aengussong.instacopy.DataViewModel
import com.aengussong.instacopy.R
import com.aengussong.instacopy.adapter.PostAdapter
import com.aengussong.instacopy.const.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: DataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val postAdapter = PostAdapter(vm.loadData())

        postsRv.apply {
            setHasFixedSize(true)
            adapter = postAdapter
        }

        lifecycleScope.launch {
            while (isActive) {
                val msg = postAdapter.getOnClickChannel().receive()
                displayNotifications(msg)
            }
        }

        setToolbarClickListeners()
        setBottomNavigationClickListeners()
    }

    private fun displayNotifications(notif: String) {
        Toast.makeText(this@MainActivity, notif, Toast.LENGTH_SHORT).show()
    }

    private fun setToolbarClickListeners() {
        tb_photo.setOnClickListener { displayNotifications(N_MAKE_PHOTO) }
        tb_msg.setOnClickListener { displayNotifications(N_MESSAGE) }
    }

    private fun setBottomNavigationClickListeners() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            val msg = when (it.itemId) {
                R.id.home -> N_HOME
                R.id.search -> N_SEARCH
                R.id.add_photo -> N_MAKE_PHOTO
                R.id.likes -> N_LIKES
                R.id.user_profile -> N_PROFILE
                else -> throw IllegalArgumentException("Unsupported item in bottom navigation: ${it.itemId}")
            }

            displayNotifications(msg)
            true
        }
    }
}
