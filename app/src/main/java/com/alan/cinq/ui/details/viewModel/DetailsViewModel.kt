package com.alan.cinq.ui.details.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alan.cinq.data.RetrofitInit
import com.alan.cinq.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {

    val characterLiveData : MutableLiveData<Character> = MutableLiveData()

    fun getCharacter(url : String){
        val call = RetrofitInit().cinqService().getCharacter(Uri.parse(url).lastPathSegment!!)
        call.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if(response.isSuccessful){
                    response.body()?.let {characterBodyResponse ->
                        characterLiveData.value = characterBodyResponse
                    }
                }
            }
            override fun onFailure(call: Call<Character>, t: Throwable) {

            }
        })
    }
}