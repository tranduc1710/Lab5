package com.minh.lab5.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minh.lab5.R;
import com.minh.lab5.databinding.IconchatBinding;
import com.minh.lab5.model.data.Content;
import com.minh.lab5.model.data.Lab5;

import java.util.List;

public class AdapterIconChat extends RecyclerView.Adapter<AdapterIconChat.ViewHodel> {
    private List<Lab5> lab5s;
    private Context context;

    public AdapterIconChat(List<Lab5> lab5s, Context context) {
        this.lab5s = lab5s;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        IconchatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.iconchat, viewGroup, false);
        return new ViewHodel(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHodel viewHodel, int i) {
        Lab5 lab5 = lab5s.get(i);
        viewHodel.bind(lab5);
        if (i % 2 == 0) {
            viewHodel.binding.contr.setBackgroundColor(Color.WHITE);
        } else {
            viewHodel.binding.contr.setBackgroundColor(Color.YELLOW);

        }

    }

    @Override
    public int getItemCount() {
        return lab5s.size();
    }

    public static class ViewHodel extends RecyclerView.ViewHolder{
    IconchatBinding binding;
        public ViewHodel(@NonNull IconchatBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
        public void bind(Lab5 lab5) {
            binding.setRender(lab5);
            binding.executePendingBindings();
        }
    }

    public void AddItem(List<Lab5> lab5){
        for(Lab5 item : lab5){
            lab5s.add(item);
        }
        notifyDataSetChanged();
    }
}
