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

public class RVCardAdapter extends RecyclerView.Adapter<RVCardAdapter.CardViewHolder> {
    ArrayList<Card> cardList;
    Project project;
    int count = 0;
    Context context;

    public RVCardAdapter(Context ct, Project proj){
        context = ct;
        project = proj;
        cardList = project.getCardList();
        count = cardList.size();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_card, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, final int position) {
        holder.myText1.setText(project.getCard(position).getQuestion());
        holder.myView.setOnClickListener(new View.OnClickListener()
            {@Override
            public void onClick(View v){
                if(holder.disp == 'Q'){
                    holder.myText1.setText(project.getCard(position).getAnswer());
                    holder.disp = 'A';
                }
                else{
                    holder.myText1.setText(project.getCard(position).getQuestion());
                    holder.disp = 'Q';
                }
            }});
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;
        View myView;
        char disp = 'Q';

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.project_name);
            myView = itemView;
        }
    }
}
