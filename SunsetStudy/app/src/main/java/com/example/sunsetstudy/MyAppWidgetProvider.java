package com.example.sunsetstudy;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MyAppWidgetProvider extends android.appwidget.AppWidgetProvider {
    public static String WIDGET_BUTTON = "MY_PACKAGE_NAME.WIDGET_BUTTON";
    private int i = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        for(int appWidgetId : appWidgetIds){
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            if(i > MainActivity.projectList.size()) {
                i = 0;
            }
            views.setTextViewText(R.id.textView, getQuestion(0, i));
            views.setOnClickPendingIntent(R.id.button, getPendingSelfIntent(context,
                    WIDGET_BUTTON));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private PendingIntent getPendingSelfIntent(Context context, String action) {
        // An explicit intent directed at the current class (the "self").
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    private void onUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance
                (context);

        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(),getClass().getName()
                );
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                thisAppWidgetComponentName);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private String getQuestion(int projectPosition, int cardPosition){

        return MainActivity.projectList.get(projectPosition).getCard(cardPosition).getQuestion();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (WIDGET_BUTTON.equals(intent.getAction())) {
            i++;
            i++;
            onUpdate(context);
        }
    }
}
