package com.pys.ex_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.pys.ex_livedata.databinding.ActivityMainBinding

// ViewModel -> Activity/Fragment 공유
// Why -> Activity 의 값을 Fragment 쓰고 싶거나
// Fragment에서  값을 ViewModel의 값으로 사용하기 위해서

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.plus.setOnClickListener {
            viewModel.plus()
            binding.result.text = viewModel.getCount().toString()
        }

        binding.minus.setOnClickListener {
            viewModel.minus()
            binding.result.text = viewModel.getCount().toString()
        }

        val manager = supportFragmentManager
        binding.showFragment.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragment = TestFragment()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}