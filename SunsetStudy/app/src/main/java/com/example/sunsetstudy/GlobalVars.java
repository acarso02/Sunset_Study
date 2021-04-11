package com.example.sunsetstudy;

import android.app.Application;

import java.util.ArrayList;

public class GlobalVars extends Application {
    private static ArrayList<Project> ProjectList = new ArrayList<>();
    int count = 0;

    public void addProject(Project project){
        ProjectList.add(project);
    }

}
