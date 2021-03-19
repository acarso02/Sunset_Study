package com.example.sunsetstudy;

import java.util.ArrayList;
import java.util.List;

public class Project {
    public List<Card> cardList = new ArrayList<Card>();
    public int listLength;
    public String projectName;


    public Project(String name){
        projectName = name;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectName(){
        return projectName;
    }
    public void addCard(String question, String answer){
        Card newCard = new Card(question, answer);
        cardList.add(newCard);
        listLength++;
    }


}
