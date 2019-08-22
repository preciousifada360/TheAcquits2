package com.skillseeds.theacquits.Api;

import com.skillseeds.theacquits.Models.trainingModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://theacquits.com/api/";




    @GET("training")
    Call<List<trainingModel>> getTraining();






}
