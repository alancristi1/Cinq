package com.alan.cinq.ui.characters

import android.os.Bundle
import android.util.Log
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
import com.alan.cinq.ui.characters.adapter.CharacterAdapter
import com.alan.cinq.ui.characters.viewModel.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlin.math.sign

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private var count : Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)

        loadingLayout_characters.visibility = View.VISIBLE
        if(count == 1){
            btnPrevious.visibility = View.GONE
        }

        btnNext.setOnClickListener {
            loadingLayout_characters.visibility = View.VISIBLE
            count ++
            if(count > 1){
                btnPrevious.visibility = View.VISIBLE
            }
            viewModel.getCharactersAll(count)
        }

        btnPrevious.setOnClickListener {
            loadingLayout_characters.visibility = View.VISIBLE
            count --
            if(count <= 1){
                btnPrevious.visibility = View.GONE
            }
            viewModel.getCharactersAll(count)
        }

        viewModel.characterLiveData.observe(this, Observer {
            it?.let {item ->
                with(listCharacters){
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    adapter = CharacterAdapter(item)

                    loadingLayout_characters.visibility = View.GONE
                }
            }
        })
        viewModel.getCharactersAll(count)
    }
}