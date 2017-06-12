package com.ronicode.petagramserverendpoint.correo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Roni on 10/06/2017.
 */

public class EnviarCorreo extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Session session;

    private String email;
    private String tema;
    private String mensaje;

    private ProgressDialog progressDialog;

    public EnviarCorreo(Context context, String email, String tema, String mensaje) {
        this.context = context;
        this.email = email;
        this.tema = tema;
        this.mensaje = mensaje;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.dismiss();

        Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(Configuracion.EMAIL, Configuracion.PASSWORD);
            }

        });

        try {
            MimeMessage mm = new MimeMessage(session);

            mm.setFrom(new InternetAddress(Configuracion.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress());
            mm.setSubject(tema);
            mm.setText(mensaje);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
