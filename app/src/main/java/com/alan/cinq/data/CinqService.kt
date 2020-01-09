package com.alan.cinq.data

import com.alan.cinq.model.Character
import com.alan.cinq.model.ResponseCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CinqService {

    @GET("people/")
    fun getCharactersAll(
        @Query("page") page : Int) : Call<ResponseCharacter>

    @GET("people/{personId}")
    fun getCharacter(
        @Path("personId") personId : String) : Call<Character>

}