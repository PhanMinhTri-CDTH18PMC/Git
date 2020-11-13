package com.example.dvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dvp.Activity_chititet;
import com.example.dvp.MainActivity;
import com.example.dvp.R;
import com.example.dvp.activity_chitiet.tabthongtin;
import com.example.dvp.lop.Phim;

import java.util.ArrayList;
import java.util.LinkedList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.WordViewHolder> {
    private LayoutInflater mInflater;
    private LinkedList<Phim> p = new LinkedList<>();
    Context context;
    public MyAdapter(LinkedList<Phim> p, Context context) {
        this.p = p;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public MyAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.row,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.WordViewHolder holder, int position) {
        holder.ten.setText(p.get(position).getTenphim());
        holder.tlphim.setText(p.get(position).getTheloai());
        int hinhAnh = this.getDrawableResIdByName(p.get(position).getHinhanh());
        holder.img.setImageResource(hinhAnh);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_chititet.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return p.size();
    }
    public void filterl(LinkedList<Phim> filterP){
        p = filterP;
        notifyDataSetChanged();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public  TextView ten,tlphim;
        public ImageView img;
        final MyAdapter mAdapter;
        ConstraintLayout constraintLayout;
        public WordViewHolder(View itemView, MyAdapter adapter) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenphim);
            tlphim = itemView.findViewById(R.id.theloai);
            img = itemView.findViewById(R.id.img);
            this.mAdapter = adapter;
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layoutds);
        }
    }
    public int getDrawableResIdByName(String tenHinh)  {
        String ct = context.getPackageName();
        int resID = context.getResources().getIdentifier(tenHinh , "drawable", ct);
        return resID;
    }
}
