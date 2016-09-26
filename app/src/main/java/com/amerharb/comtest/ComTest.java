package com.amerharb.comtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class ComTest extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button yes = (Button) findViewById(R.id.yes);
        final Button no = (Button) findViewById(R.id.no);
        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ComTest.this, "YES", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ComTest.this, "NO", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

