package com.example.sunsetstudy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    static ArrayList<Project> projectList;
    int count = 0;
    Context context;

    public RVAdapter(Context ct, ArrayList<Project> projects){
        context = ct;
        projectList = projects;
        count = projects.size();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.project_card, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {
        holder.myText1.setText(projectList.get(position).Name);
        holder.myTextCount.setText(Integer.toString(projectList.get(position).getListLength()));
        holder.myView.setOnClickListener(new View.OnClickListener()
        {@Override
        public void onClick(View v){
            Intent move = new Intent(v.getContext(), QuestionsActivity.class);
            move.putExtra("position", position);
            context.startActivity(move);
        }});
        holder.myView.setOnLongClickListener(new View.OnLongClickListener()
        {@Override
        public boolean onLongClick(View v){
            ((MainActivity) context).displayDelete(position, context);
            return true;
        }});
    }

    public void removeItem(int position){
        projectList.remove(position);
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myTextCount;
        View myView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.project_name);
            myTextCount = itemView.findViewById(R.id.question_count);
            myView = itemView;
        }
    }
}
