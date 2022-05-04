package com.example.sunsetstudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public static final String PROJECTS_TABLE = "PROJECTS_TABLE";
    public static final String COLUMN_PROJECT_NAME = "PROJECT_NAME";
    public static final String COLUMN_PROJECT_DATA = "PROJECT_DATA";
    public static final String COLUMN_ACTIVE_PROJECT = "ACITVE_PROJECT";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Projects.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PROJECTS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PROJECT_NAME + " TEXT, " + COLUMN_PROJECT_DATA + " TEXT, " + COLUMN_ACTIVE_PROJECT + " BOOL ) ";

        db.execSQL(createTableStatement);
    }

    //called if database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Gson gson = new Gson();
        String jsonProject = gson.toJson(project);

        cv.put(COLUMN_PROJECT_NAME, project.Name);
        cv.put(COLUMN_PROJECT_DATA, jsonProject);
        cv.put(COLUMN_ACTIVE_PROJECT, project.isActive);

        long insert = db.insert(PROJECTS_TABLE, null, cv);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<Project> getAll(){
        ArrayList<Project> returnList = new ArrayList<>();
        Gson gson = new Gson();

        //get data from database
        String queryString = "SELECT * FROM " + PROJECTS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            //loop through result and create a new Project, place in return list
            do{
                int id = cursor.getInt(0);
                String projectName = cursor.getString(1);
                String projectData = cursor.getString(2);
                boolean active = cursor.getInt(3) == 1;

                Project project = gson.fromJson(projectData, Project.class);
                project.setId(id);
                project.setIsActive(active);
                returnList.add(project);

            } while(cursor.moveToNext());
        }

        //close cursor and db
        cursor.close();
        db.close();
        return returnList;
    }


    public boolean deleteOne(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String queryString = "DELETE FROM " + PROJECTS_TABLE + " WHERE " + COLUMN_ID + " = " + project.ID;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean update(Project project){
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        String projectData = gson.toJson(project);

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PROJECT_DATA, projectData);

        db.update(PROJECTS_TABLE, cv, "ID=" + project.ID, null);

/*
        String queryString = "UPDATE " + PROJECTS_TABLE + " SET " + COLUMN_PROJECT_DATA + " = " +
                projectData + " WHERE " + COLUMN_ID + " = " + project.ID;

        db.execSQL(queryString);
*/
        //Log.d("TAG", cursor.toString());

        db.close();
        return true;
    }

    public Project getSelected(){
        Gson gson = new Gson();

        //get data from database
        String queryString = "SELECT * FROM " + PROJECTS_TABLE + " WHERE " + COLUMN_ACTIVE_PROJECT + " = 1" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            try{
                int id = cursor.getInt(0);
                String projectName = cursor.getString(1);
                String projectData = cursor.getString(2);
                boolean active = cursor.getInt(3) == 1;

                Project project = gson.fromJson(projectData, Project.class);
                project.setId(id);
                project.setIsActive(active);
                //close cursor and db
                cursor.close();
                db.close();
                return project;
            }
            catch (Exception e ){
                //do nothing
            }
        }
        //handle no active project found
        //close cursor and db
        cursor.close();
        db.close();
        return new Project();
    }
}
