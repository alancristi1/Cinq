package com.alan.cinq.ui.characters.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alan.cinq.data.RetrofitInit
import com.alan.cinq.model.ResponseCharacter
import com.alan.cinq.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersViewModel : ViewModel() {

    val characterLiveData : MutableLiveData<List<Result>> = MutableLiveData()

    fun getCharactersAll(page : Int){

        val call = RetrofitInit().cinqService().getCharactersAll(page)
        call.enqueue(object : Callback<ResponseCharacter>{
            override fun onResponse(
                call: Call<ResponseCharacter>,
                response: Response<ResponseCharacter>
            ) {
                if(response.isSuccessful){
                    val characters : MutableList<Result> = mutableListOf()

                    response.body()?.let {characterBodyResponse ->
                        for(results in characterBodyResponse.results){
                            characters.add(results)
                        }
                    }
                    characterLiveData.value = characters
                }
            }

            override fun onFailure(call: Call<ResponseCharacter>, t: Throwable) {

            }
        })
    }

    fun getPagination(){

    }
}