package com.example.sunsetstudy;

import java.util.Date;

public class Card {
    private String Question;
    private String Answer;
    Date creationDate;

    public Card(String question, String answer){
        Question = question;
        Answer = answer;
        creationDate = java.util.Calendar.getInstance().getTime();
    }

    //getters
    public String getQuestion(){
        return Question;
    }
    public String getAnswer(){
        return Answer;
    }

    //setters
    public void setAnswer(String answer) {
        Answer = answer;
    }
    public void setQuestion(String question) {
        Question = question;
    }

}
