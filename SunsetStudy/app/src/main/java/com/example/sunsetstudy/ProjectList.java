package com.example.sunsetstudy;

import java.util.ArrayList;
import java.util.List;

public class ProjectList {
    public List<Project> projectsList = new ArrayList<Project>();

    /**
     * adds a project to projects list
     *
     * @param name name of project to add
     * @return 0 for success
     */
    public int addProject(String name){
        for (Project proj:projectsList){
            if(proj.projectName.equals(name)){
                return 1;   //Name in Use
            }
        }
        Project newProject = new Project(name);
        return 0;   //OK
    }

    /**
     * removes a project from projects list
     *
     * @param name name of project to remove
     * @return 0 for success
     */
    public int removeProject(String name){
        for (Project proj:projectsList){
            if(proj.projectName.equals(name)){
                projectsList.remove(proj);
                return 0;   //Name found successfully
            }
        }
        return 2; //Name not found
    }

}
