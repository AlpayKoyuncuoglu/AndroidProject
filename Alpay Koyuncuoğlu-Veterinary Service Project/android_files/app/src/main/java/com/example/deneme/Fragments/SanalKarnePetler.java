package com.example.deneme.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deneme.Adapters.PetsAdapter;
import com.example.deneme.Adapters.SanalKarnePetAdapter;
import com.example.deneme.Models.PetModel;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.ChangeFragments;
import com.example.deneme.Utils.GetSharedPreferences;
import com.example.deneme.Utils.Warnings;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanalKarnePetler extends Fragment {

    private RecyclerView sanalKarnePetler;
    private View view;
    private List<PetModel> petList;
    private ChangeFragments changeFragments;
    private String musId;
    private GetSharedPreferences getSharedPreferences;
    private SanalKarnePetAdapter sanalKarnePetAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_pets, container, false);
        tanimla();
        getPets("9");
        return view;
    }

    public void tanimla() {
        petList = new ArrayList<>();
        sanalKarnePetler = view.findViewById(R.id.petlistrecylerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(), 2);
        sanalKarnePetler.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        musId = getSharedPreferences.getSession().getString("id", null);

    }

    public void getPets(String musId) {
        Call<List<PetModel>> req = ManagerAll.getInstance().getPets(musId);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if (response.body().get(0).isTf()) {
                    petList = response.body();
                    sanalKarnePetAdapter = new SanalKarnePetAdapter(petList, getContext());
                    sanalKarnePetler.setAdapter(sanalKarnePetAdapter);
                 //   Toast.makeText(getContext(), "sistemde kayitli " + petList.size() + " pet bulunmamaktadir", Toast.LENGTH_LONG).show();

                } else {
                 //   Toast.makeText(getContext(), "sistemde kayitli pet bulunmamaktadir", Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(getContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });
    }
}
