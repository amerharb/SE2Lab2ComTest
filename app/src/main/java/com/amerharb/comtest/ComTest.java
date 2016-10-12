package com.amerharb.comtest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.*;
import android.view.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;


public class ComTest extends Activity {

    private final int PORT = 8000;
    private final String SERVER = "192.168.10.118";
    private final int PERMISSIONS_REQUEST_INTERNET = 3001;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button yes = (Button) findViewById(R.id.yes);
        final Button no = (Button) findViewById(R.id.no);

        try {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.INTERNET)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.INTERNET)) {

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.INTERNET},
                            PERMISSIONS_REQUEST_INTERNET);

                }
            }
            Exception ex = new Exception();

            //YES button
            yes.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(ComTest.this, R.string.Yes, Toast.LENGTH_SHORT).show();
                    try {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
//                                    Log.i("host ip address", InetAddress.getLocalHost().getHostAddress());
                                    Socket socket = new Socket(SERVER, PORT);
                                    final OutputStream os;
                                    final DataOutputStream out;
                                    final PrintStream ps;

                                    os = socket.getOutputStream();
                                    out = new DataOutputStream(os);
                                    ps = new PrintStream(out);

                                    ps.println(R.string.Yes);
                                    ps.flush();
                                    out.flush();

                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).run();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            //NO button
            no.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(ComTest.this, "NO", Toast.LENGTH_SHORT).show();
                }
            });
            //final PrintStream ps = new PrintStream(new DataOutputStream(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null;
        } catch (Exception ex) {
            return false;
        }
    }
}

