package com.alan.cinq.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alan.cinq.R
import com.alan.cinq.ui.main.adapter.MainAdapter
import com.alan.cinq.ui.main.viewModel.MainViewModel
import com.alan.cinq.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btnAdd.setOnClickListener {
            val intent = Intent(layoutInflater.context, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel.userLiveData.observe(this, Observer {
            it?.let {item ->
                with(listUser){
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter = MainAdapter(item)
                }
            }
        })
        viewModel.getAllUser()
    }
}