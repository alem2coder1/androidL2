package kz.test.lab2.model.service

import kz.test.lab2.model.entity.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/historicalfigures")
    fun getPersonList(@Query("name") name: String): Call<List<Person>>
}