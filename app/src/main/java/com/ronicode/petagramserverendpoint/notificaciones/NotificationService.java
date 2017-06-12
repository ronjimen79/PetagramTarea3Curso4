package com.ronicode.petagramserverendpoint.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ronicode.petagramserverendpoint.ListaMascota;
import com.ronicode.petagramserverendpoint.R;

/**
 * Created by Roni on 11/06/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "FIREBASE";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Log.d("NOTIFICACION", remoteMessage.toString());
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        enviarNotificacion(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }

    private void enviarNotificacion(String messageBody, String messageTitle){

        Intent intent = new Intent(this, ListaMascota.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notificacion)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification.build());

    }
}
