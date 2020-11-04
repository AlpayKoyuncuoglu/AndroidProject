package com.example.deneme.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.Fragments.AsiDetayFragment;
import com.example.deneme.Models.PetModel;
import com.example.deneme.R;
import com.example.deneme.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SanalKarnePetAdapter extends RecyclerView.Adapter<SanalKarnePetAdapter.ViewHolder> {

    List<PetModel> list;
    Context context;

    public SanalKarnePetAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sanalkarnepetlayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sanalKarnePetText.setText(list.get(position).getPetisim().toString());
        holder.sanalKarneBilgiText.setText("türü " + list.get(position).getPettur() + " cinsi:+ " + list.get(position).getPetcins() +
                " petinizin geçmiş aşılarını görmek için tıklayınız");

        Picasso.get().load(list.get(position).getPetresim()).into(holder.sanalKarnePetImage);
        holder.sanalLayoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragments changeFragments=new ChangeFragments(context);
                changeFragments.changeWithParameters(new AsiDetayFragment(),list.get(position).getPetid());
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sanalKarnePetText, sanalKarneBilgiText;
        CircleImageView sanalKarnePetImage;
        CardView sanalLayoutCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            sanalKarnePetText = itemView.findViewById(R.id.sanalKarnePetText);
            sanalKarneBilgiText = itemView.findViewById(R.id.sanalKarneBilgiText);
            sanalKarnePetImage = itemView.findViewById(R.id.sanalKarnePetImage);
            sanalLayoutCardView = itemView.findViewById(R.id.sanalLayoutCardView);
        }
    }


}
