package com.example.sunsetstudy;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private ArrayList<Card> cardList;
    public Boolean isActive;
    public int ID;
    public String Name;

    public Project(){
        Name = "";
        isActive = false;
        cardList = new ArrayList<Card>();
    }

    public Project(String name){
        Name = name;
        isActive = false;
        cardList = new ArrayList<Card>();
    }

    public Project(String name, int id){
        Name = name;
        ID = id;
        isActive = false;
        cardList = new ArrayList<Card>();
    }

    public void setProjectName(String projectName) {
        this.Name = projectName;
    }
    public String getProjectName(){
        return Name;
    }
    public void setId(int id){
        ID = id;
    }
    public void setIsActive(boolean active){
        isActive = active;
    }
    public void addCard(String question, String answer){
        Card newCard = new Card(question, answer);
        cardList.add(newCard);
    }
    public ArrayList<Card> getCardList(){
        return cardList;
    }
    public Card getCard(int i){
        return cardList.get(i);
    }

    public int getListLength(){
        return cardList.size();
    }
}
