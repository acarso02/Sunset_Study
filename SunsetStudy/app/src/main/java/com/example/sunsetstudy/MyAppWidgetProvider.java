package com.example.sunsetstudy;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class MyAppWidgetProvider extends AppWidgetProvider {

    ArrayList<Project> cardList;
    String ACTION_TEXT_CHANGED = "TEXT_CHANGED";
    static Project currentProject;
    static int i = 0;
    static boolean isAnswer = false;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.widget_relative_layout, getPendingSelfIntent(context, "TEXT_CHANGED"));
            updateAppWidget(context, appWidgetManager, appWidgetId);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction().equals(ACTION_TEXT_CHANGED)){

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MyAppWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            onUpdate(context, appWidgetManager, appWidgetIds);
        }

        if(intent.getAction().equals("UPDATE_ACTIVE_PROJECT")){
            Gson gson = new Gson();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MyAppWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

            //get project from intent
            currentProject = gson.fromJson(intent.getExtras().getString("project"), Project.class);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId){

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        //check if on answer and if there is next question
        if(currentProject != null && currentProject.getListLength() != 0){
            if(isAnswer) {
                i++;
                if(i>=currentProject.getListLength()){
                    //restart question set
                    i=0;
                }
                views.setTextViewText(R.id.widget_content, currentProject.getCard(i).getQuestion());
            }
            else{
                views.setTextViewText(R.id.widget_content, currentProject.getCard(i).getAnswer());
                isAnswer = true;
            }
            views.setTextViewText(R.id.qa_number, Integer.toString(i+1) + "/" + Integer.toString(currentProject.getListLength()));
            views.setTextViewText(R.id.widget_project_name, currentProject.Name);
        }
        else{
            views.setTextViewText(R.id.widget_content, "This is an empty project.");
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        //get active project from main activity

    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

}