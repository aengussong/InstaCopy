package com.aengussong.instacopy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aengussong.instacopy.adapter.PostAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vm: DataViewModel by viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsRv.apply {
            setHasFixedSize(true)
            adapter = PostAdapter(vm.loadData())
        }
    }
}
