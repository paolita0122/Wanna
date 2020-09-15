package com.example.wanna;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<Item> items;
    public Context context;

    public ItemAdapter(Context ct, ArrayList<Item> items) {
        context= ct;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemImage.setImageResource(items.get(position).getImage());
        holder.textTitle.setText(items.get(position).getName());
        holder.itemIconLike.setImageResource(R.drawable.hearticon);
        holder.itemIconPP.setImageResource(R.drawable.personicon);
        holder.itemIconShare.setImageResource(R.drawable.shareicon);
        holder.itemIconSC.setImageResource(R.drawable.sandclock);
        holder.textPP.setText(items.get(position).getPersons());
        holder.textDays.setText(items.get(position).getDays());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage,itemIconLike,itemIconPP,itemIconSC,itemIconShare;
        TextView textTitle,textPP,textDays;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage= itemView.findViewById(R.id.imageCar);
            itemIconLike = itemView.findViewById(R.id.iconLike);
            itemIconPP = itemView.findViewById(R.id.iconPP);
            itemIconSC = itemView.findViewById(R.id.iconSC);
            itemIconShare = itemView.findViewById(R.id.iconShare);
            textTitle = itemView.findViewById(R.id.textName);
            textPP = itemView.findViewById(R.id.textNumber);
            textDays = itemView.findViewById(R.id.textTime);


        }

    }
}
