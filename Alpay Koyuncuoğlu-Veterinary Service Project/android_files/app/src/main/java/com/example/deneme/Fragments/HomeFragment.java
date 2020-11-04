package com.example.deneme.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.deneme.Adapters.AnswersAdapter;
import com.example.deneme.Models.AnswersModel;
import com.example.deneme.Models.AskQuestionModel;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.ChangeFragments;
import com.example.deneme.Utils.GetSharedPreferences;
import com.example.deneme.Utils.Warnings;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    public View view;
    private LinearLayout petlerimLayout, sorusorlinearlayout,cevapLayout,kampanyaLinearLayout,asiTakipLayout,sanalKarneLayout;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;
    private AnswersAdapter answersAdapter;
    private List<AnswersModel> answerList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        tanimla();
        action();

        return view;
    }

    public void tanimla() {
        petlerimLayout = view.findViewById(R.id.petlerimLayout);
        sorusorlinearlayout = view.findViewById(R.id.sorusorlinearlayout);
        cevapLayout= view.findViewById(R.id.cevapLayout);
        asiTakipLayout=view.findViewById(R.id.asiTakipLayout);
        sanalKarneLayout=view.findViewById(R.id.sanalKarneLayout);
        answerList=new ArrayList<>();
        kampanyaLinearLayout=view.findViewById(R.id.kampanyaLinearLayout);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences=new GetSharedPreferences(getActivity());

        id=getSharedPreferences.getSession().getString("id",null);

    }

    public void action() {
        petlerimLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new UserPetsFragment());
            }
        });
        sorusorlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestionAlert();
            }
        });
        cevapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswers(id) ;
            }
        });
        kampanyaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new KampanyaFragment());
            }
        });
        asiTakipLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AsiFragment());
            }
        });
        sanalKarneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new SanalKarnePetler());
            }
        });
    }

    public void openQuestionAlert() {
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.sorusoralertlayout,null);

        final EditText sorusoredittext=view.findViewById(R.id.sorusoredittext);
        MaterialButton sorusorbuton=view.findViewById(R.id.sorusorbuton);
        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();
        sorusorbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soru =sorusoredittext.getText().toString();
                sorusoredittext.setText("");
                alertDialog.cancel();
                askQuestion(id,soru,alertDialog);

            }
        });
        alertDialog.show();
    }

    public  void askQuestion(String musId,String text,AlertDialog alr)
    {
        Call<AskQuestionModel>req=ManagerAll.getInstance().soruSor(musId,text);
        req.enqueue(new Callback<AskQuestionModel>() {
            @Override
            public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    alr.cancel();
                }
                else
                {
                    Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AskQuestionModel> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAnswers(String musId)
    {
        Call<List<AnswersModel>> req =ManagerAll.getInstance().getAnswers(musId);
        req.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
                if(response.body().get(0).isTf())
                {
                    if(response.isSuccessful())
                    {
                        answerList=response.body();
                        answersAdapter=new AnswersAdapter(answerList,getContext());
                        openAnswersAlert();
                    }
                }
                else
                {
                    Toast.makeText(getContext(),"henuz bir cevap verilmedi",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AnswersModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warnings.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openAnswersAlert() {
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.cevapalertlayout,null);

        RecyclerView cevapRectlerView=view.findViewById(R.id.cevapRecylerView);

        AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog=alert.create();

        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        cevapRectlerView.setLayoutManager(layoutManager);
        cevapRectlerView.setAdapter(answersAdapter);

        alertDialog.show();
    }

}
