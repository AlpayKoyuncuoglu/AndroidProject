package com.example.deneme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.Models.PetModel;
import com.example.deneme.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {

    List<PetModel> list;
    Context context;

    public PetsAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.petlistitemlayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.petlayoutcinsname.setText("cinsi: "+list.get(position).getPetcins().toString());
        holder.petlayoutpetname.setText("ismi: "+list.get(position).getPetisim().toString());
        holder.petlayoutturname.setText("turu:"+list.get(position).getPettur().toString());

        Picasso.get().load(list.get(position).getPetresim()).into(holder.petlayoutpetimage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView petlayoutpetname, petlayoutcinsname, petlayoutturname;
        CircleImageView petlayoutpetimage;

        public ViewHolder(View itemView) {
            super(itemView);
            petlayoutpetname = itemView.findViewById(R.id.petlayoutpetname);
            petlayoutcinsname = itemView.findViewById(R.id.petlayoutcinsname);
            petlayoutturname = itemView.findViewById(R.id.petlayoutturname);
            petlayoutpetimage = itemView.findViewById(R.id.petlayoutpetimage);

        }
    }


}
