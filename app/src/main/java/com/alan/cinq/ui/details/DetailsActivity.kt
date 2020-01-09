package com.alan.cinq.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alan.cinq.R
import com.alan.cinq.ui.details.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val url  = intent.getStringExtra("url")
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        loadingLayout.visibility = View.VISIBLE
        viewModel.getCharacter(url!!)
        viewModel.characterLiveData.observe(this, Observer {
            txtName.text = it.name
            txtGender.text = it.gender
            txtHair_color.text = it.hair_color
            txtSkin_color.text = it.skin_color
            txtEye_color.text = it.eye_color

            loadingLayout.visibility = View.GONE
        })

        btnBackDetails.setOnClickListener {
            finish()
        }
    }
}
