package com.example.deneme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.Models.AnswersModel;
import com.example.deneme.Models.DeleteAnswerModel;
import com.example.deneme.Models.KampanyaModel;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.Warnings;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.ViewHolder> {

    List<KampanyaModel> list;
    Context context;

    public KampanyaAdapter(List<KampanyaModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kampanyaitemlayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.kampanyaBaslikText.setText(list.get(position).getBaslik().toString());
        holder.kampanyatext.setText(list.get(position).getText().toString());
        Picasso.get().load(list.get(position).getResim()).into(holder.kampanyaImageView);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kampanyaBaslikText, kampanyatext;
        ImageView kampanyaImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            kampanyatext=itemView.findViewById(R.id.kampanyatext);
            kampanyaBaslikText=itemView.findViewById(R.id.kampanyaBaslikText);
            kampanyaImageView=itemView.findViewById(R.id.kampanyaImageView);


        }
    }


}
