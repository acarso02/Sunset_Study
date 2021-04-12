package com.example.sunsetstudy;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private int listLength = 0;
    public String Name;

    public Project(String name){
        Name = name;
        List<Card> cardList;
    }
    public void setProjectName(String projectName) {
        this.Name = projectName;
    }
    public String getProjectName(){
        return Name;
    }
    public void addCard(String question, String answer){
        Card newCard = new Card(question, answer);
        cardList.add(newCard);
        listLength++;
    }
    public ArrayList<Card> getCardList(){
        return cardList;
    }
    public Card getCard(int i){
        return cardList.get(i);
    }

    public int getListLength(){
        return listLength;
    }
}
