package com.example.deneme.RestApi;

import com.example.deneme.Models.AnswersModel;
import com.example.deneme.Models.AsiModel;
import com.example.deneme.Models.AskQuestionModel;
import com.example.deneme.Models.DeleteAnswerModel;
import com.example.deneme.Models.KampanyaModel;
import com.example.deneme.Models.LoginModel;

import com.example.deneme.Models.PetModel;
import com.example.deneme.Models.RegisterPojo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/veterinerservis/kayitol.php")
    Call<RegisterPojo> registerUser(@Field("mailAdres") String mailAdres, @Field("kadi") String kadi, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/veterinerservis/girisyap.php")
    Call<LoginModel> loginUser(@Field("mailadres") String mailAdres, @Field("sifre") String pass);

    @FormUrlEncoded
    @POST("/veterinerservis/petlerim.php")
    Call<List<PetModel>> getPets(@Field("musid") String musID );

    @FormUrlEncoded
    @POST("/veterinerservis/sorusor.php")
    Call<AskQuestionModel> soruSor(@Field("id") String id , @Field("soru") String soru);

    @FormUrlEncoded
    @POST("/veterinerservis/cevaplar.php")
    Call<List<AnswersModel>> getAnswers(@Field("musId") String musID );

    @FormUrlEncoded
    @POST("/veterinerservis/cevapsil.php")
    Call<DeleteAnswerModel> deleteAnswer(@Field("cevap") String cevapId , @Field("soru") String soruId);


    @GET("/veterinerservis/kampanya.php")
    Call<List<KampanyaModel>> getKampanya();

    @FormUrlEncoded
    @POST("/veterinerservis/asiTakip.php")
    Call<List<AsiModel>> getAsi(@Field("id") String id );

    @FormUrlEncoded
    @POST("/veterinerservis/gecmisAsi.php")
    Call<List<AsiModel>> getGecmisAsi(@Field("id") String id,@Field("petId") String petId );

}
