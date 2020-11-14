package com.example.spung

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class ResponseRecords(var records: List<String>? = null)

interface RecordService {
//    @GET("/api/records")
//    //fun getRequest(name: List<String>): Call<ResponseRecords>
//
//    @POST("api/records/register")

}