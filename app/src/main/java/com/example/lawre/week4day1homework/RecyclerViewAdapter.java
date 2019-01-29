package com.example.lawre.week4day1homework;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawre.week4day1homework.repository.Item;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    ArrayList<Item> repoList;

    public RecyclerViewAdapter(ArrayList<Item> repoList) {
        this.repoList = repoList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repository,viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position)
    {
        Item newItem = repoList.get(position);
        if(newItem != null)
        {
            viewHolder.setItemUserResponse(newItem);
            viewHolder.tvName.setText(newItem.getFullName());
            viewHolder.tvId.setText(""+newItem.getId());
           // Log.d("RVA_", "onBindViewHolder: " + viewHolder.tvUrl);
            viewHolder.tvUrl.setText(newItem.getHtmlUrl());
        }
    }

    @Override
    public int getItemCount() {
        return repoList != null ? repoList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
        {
        TextView tvId, tvName, tvUrl;
        Item itemUser;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRepoName);
            tvId = itemView.findViewById(R.id.tvRepoId);
            tvUrl = itemView.findViewById(R.id.tvRepoUrl);
        }

        public Item getItemUserResponse() {
            return itemUser;
        }

        public void setItemUserResponse(Item itemUserResponse) {
            this.itemUser = itemUserResponse;
        }
    }

    public void addItem(Item mu)
    {
        repoList.add(mu);
        notifyDataSetChanged();
    }

    public void addItems(List<Item> items)
    {
        if(items == null)
            return;
        for(int i=0;i<items.size();i++)
        {
            addItem(items.get(i));
        }
        notifyDataSetChanged();
    }
}
