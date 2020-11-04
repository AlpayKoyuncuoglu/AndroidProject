package com.example.deneme.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deneme.Adapters.SanalKarneGecmisAsiAdapter;
import com.example.deneme.Models.AsiModel;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.ChangeFragments;
import com.example.deneme.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiDetayFragment extends Fragment {

    private View view;
    private String musId;
    private String petId;
    private GetSharedPreferences getSharedPreferences;
    private RecyclerView asiDetayRecView;
    private SanalKarneGecmisAsiAdapter adapter;
    private List<AsiModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_asi_detay, container, false);
        tanimla();
        getGecmisAsi();
        return view;

    }

    public void tanimla() {
        petId = getArguments().getString("1");
        getSharedPreferences = new GetSharedPreferences(getActivity());
        musId = getSharedPreferences.getSession().getString("9", null);
        asiDetayRecView=view.findViewById(R.id.asiDetayRecView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        asiDetayRecView.setLayoutManager(layoutManager);
        list=new ArrayList<>();

    }

    public void getGecmisAsi() {
        Call<List<AsiModel>> req = ManagerAll.getInstance().getGecmisAsi("9", "1");
        req.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {
                if(response.body().get(0).isTf())
                {
                    list=response.body();
                    adapter=new SanalKarneGecmisAsiAdapter(list,getContext());
                    asiDetayRecView.setAdapter(adapter);
                    Toast.makeText(getContext(),"petinize ait"+list.size()+" adetgeçmişte yapılan aşıbulunmaktadır",
                            Toast.LENGTH_LONG).show();
                }else{
                    ChangeFragments changeFragments= new ChangeFragments(getContext());
                    changeFragments.change(new SanalKarnePetler());
                    Toast.makeText(getContext(),"hiçbir aşı yapılmamıştır",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {

            }
        });
    }

}
