package com.example.deneme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.Models.AnswersModel;
import com.example.deneme.Models.DeleteAnswerModel;
import com.example.deneme.Models.PetModel;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.Warnings;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    List<AnswersModel> list;
    Context context;

    public AnswersAdapter(List<AnswersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cevapitemlayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cevapSoruText.setText("soru: "+list.get(position).getSoru().toString());
        holder.cevapCevapText.setText("cevap: "+list.get(position).getCevap().toString());
        holder.cevapSilbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteToService(list.get(position).getCevapId().toString(),list.get(position).getSoruId().toString(),position);
            }
        });

    }

    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void deleteToService(String cevapId,String soruId,int pos)
    {
        Call<DeleteAnswerModel> req= ManagerAll.getInstance().deleteAnswer(cevapId, soruId);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {

                if(response.body().isTf()){
                    if(response.isSuccessful())
                    {
                        Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                        deleteToList(pos);
                    }
                }
                else{
                    Toast.makeText(context,response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context, Warnings.internetProblemText,Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cevapSoruText, cevapCevapText;
        MaterialButton cevapSilbuton;

        public ViewHolder(View itemView) {
            super(itemView);
            cevapSoruText=itemView.findViewById(R.id.cevapSoruText);
            cevapCevapText=itemView.findViewById(R.id.cevapCevapText);
            cevapSilbuton=itemView.findViewById(R.id.cevapSilbuton);


        }
    }


}
