package com.example.sunsetstudy;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    ArrayList<Project> projectList;
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
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.myText1.setText(projectList.get(position).Name);
        holder.myTextCount.setText(Integer.toString(projectList.get(position).getListLength()));
        holder.myView.setOnClickListener(new View.OnClickListener()
            {@Override
            public void onClick(View v){
                RelativeLayout rl = v.findViewById(R.id.relative_layout);
                rl.setBackgroundColor(Color.BLUE);
            }});
    }

    @Override
    public int getItemCount() {
        return count;
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
