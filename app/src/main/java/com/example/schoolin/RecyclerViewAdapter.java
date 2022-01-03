package com.example.schoolin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<school> schoolList;
    private OnItemClickListener listener;
    public RecyclerViewAdapter(List<school> schoolList, OnItemClickListener listener) {
        this.schoolList = schoolList;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(school school);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_2, viewGroup, false);
        return new MyViewHolder(itemView);
    }
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.bind(schoolList.get(position), listener);
    }
    @Override
    public int getItemCount() {
        return schoolList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,location;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.schoolName_favView);
            location = view.findViewById(R.id.schoolLocation_favView);
        }
        public void bind(final school item, final OnItemClickListener listener){
            name.setText(item.getName());
            location.setText(item.getLocation());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
