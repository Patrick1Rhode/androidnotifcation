package com.example.notications;


import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onReceive(Context context, Intent intent) {
        //getting the remote input bundle from intent
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);




        //if there is some input
        if (remoteInput != null) {
            //getting the input value
            CharSequence name = remoteInput.getCharSequence(MainActivity.NOTIFICATION_REPLY);

            try {
                MainActivity.getInstace().updateTheTextView(name.toString());
            } catch (Exception e) {

            }



            //updating the notification with the input value
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_menu_info_details)
                    .setContentTitle(name);
            NotificationManager notificationManager = (NotificationManager) context.
                    getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MainActivity.NOTIFICATION_ID, mBuilder.build());
        }

        //if help button is clicked
        if (intent.getIntExtra(MainActivity.KEY_INTENT_REPLY, -1) == MainActivity.REQUEST_CODE_REPLY) {

            CharSequence name = "";

            if (remoteInput != null) {

                //getting the input value
                name = remoteInput.getCharSequence(MainActivity.NOTIFICATION_REPLY);
            }
            Toast.makeText(context, "Your Reply "+name, Toast.LENGTH_LONG).show();
        }

//        //if more button is clicked
//        if (intent.getIntExtra(MainActivity.KEY_INTENT_MORE, -1) == MainActivity.REQUEST_CODE_MORE) {
//            Toast.makeText(context, "You Clicked More", Toast.LENGTH_LONG).show();
//        }
    }
}
