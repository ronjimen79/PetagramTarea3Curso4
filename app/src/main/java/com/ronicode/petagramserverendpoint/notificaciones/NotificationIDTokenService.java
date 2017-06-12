package com.ronicode.petagramserverendpoint.notificaciones;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Roni on 11/06/2017.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, "Enviar token: " + token);

    }
}
