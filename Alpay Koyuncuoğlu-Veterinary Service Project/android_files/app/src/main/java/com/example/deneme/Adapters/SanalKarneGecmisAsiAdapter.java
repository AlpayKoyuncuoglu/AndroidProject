package com.example.deneme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.Models.AsiModel;
import com.example.deneme.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanalKarneGecmisAsiAdapter extends RecyclerView.Adapter<SanalKarneGecmisAsiAdapter.ViewHolder> {

    List<AsiModel> list;
    Context context;

    public SanalKarneGecmisAsiAdapter(List<AsiModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnegecmislayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.sanalKarneGecmisAsiText.setText(list.get(position).getAsiIsim().toString() + " aşısı yapıldı");
        holder.sanalKarneGecmisAsiBilgi.setText(list.get(position).getPetIsim().toString() + " isimli petinize" +
                list.get(position).getAsiTarih() + " tarihinde " + list.get(position).getAsiIsim() + " aşısı yapılmıştır");
        Picasso.get().load(list.get(position).getPetResim().toString()).into(holder.sanalKarneGecmisAsiImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sanalKarneGecmisAsiText, sanalKarneGecmisAsiBilgi;
        CircleImageView sanalKarneGecmisAsiImage;

        public ViewHolder(View itemView) {
            super(itemView);
            sanalKarneGecmisAsiText = itemView.findViewById(R.id.sanalKarneGecmisAsiText);
            sanalKarneGecmisAsiBilgi = itemView.findViewById(R.id.sanalKarneGecmisAsiBilgi);
            sanalKarneGecmisAsiImage = itemView.findViewById(R.id.sanalKarneGecmisAsiImage);
        }
    }


}
