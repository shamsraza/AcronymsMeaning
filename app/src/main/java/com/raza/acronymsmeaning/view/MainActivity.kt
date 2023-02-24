package com.raza.acronymsmeaning.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.raza.acronymsmeaning.R
import com.raza.acronymsmeaning.databinding.ActivityMainBinding
import com.raza.acronymsmeaning.utils.ValUtil
import com.raza.acronymsmeaning.viewmodel.AcronymsViewModel

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var acronymsViewModel: AcronymsViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter=AcronymsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.recyclerview.adapter=adapter
        acronymsViewModel = ViewModelProvider(this,)[AcronymsViewModel::class.java]
        binding.acronymsViewModel=acronymsViewModel
        binding.lifecycleOwner = this
        observeData()
    }
    private fun observeData(){
        acronymsViewModel.largeFormList.observe(this) {
            adapter.setData(it)
            acronymsViewModel.rvVisibility.postValue(View.VISIBLE)
        }

        acronymsViewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.searchBtn.id -> {
                binding.abbEditText.hideKeyboard()
                val abbr = binding.abbEditText.text.toString()
                val isValid = ValUtil.isValid(abbr)

                when {
                    !isValid.first -> {
                        Toast.makeText(this, isValid.second, Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> {
                        acronymsViewModel.getAcronyms(abbr)
                        binding.recyclerview.smoothScrollToPosition(0)
                    }
                }
            }
            binding.resetBtn.id -> {
                binding.abbEditText.text?.clear()
                acronymsViewModel.rvVisibility.postValue(View.GONE)
            }
        }
    }
    private fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}