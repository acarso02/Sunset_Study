package com.example.sunsetstudy;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RVCardAdapter extends RecyclerView.Adapter<RVCardAdapter.CardViewHolder> {
    static ArrayList<Card> cardList;
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
        int colorId;
        String pos;

        pos = Integer.toString(position + 1);
        holder.myText1.setText(project.getCard(position).getQuestion());
        colorId = getResId("gradient" + pos, R.color.class);
        holder.myCardView.setBackgroundColor(ContextCompat.getColor(context,  colorId));
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
        holder.myView.setOnLongClickListener(new View.OnLongClickListener()
            {@Override
            public boolean onLongClick(View v){
                ((QuestionsActivity) context).displayDelete(position, context);
                return true;
            }});
    }

    public void removeItem(int position){
        cardList.remove(position);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;
        View myView, myCardView;
        char disp = 'Q';

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.project_name);
            myView = itemView;
            myCardView = itemView.findViewById(R.id.relative_layout);

        }
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
