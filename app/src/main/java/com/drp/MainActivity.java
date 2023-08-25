package com.drp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView calculateButton, contributeButton, logoutButton;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        calculateButton = findViewById(R.id.calculateButtonMainId);
        contributeButton = findViewById(R.id.contributeId);
        logoutButton = findViewById(R.id.logoutButtonId);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent workIntent = new Intent(MainActivity.this,DrpWork.class);
                workIntent.putExtra("key", "calculate");
                startActivity(workIntent);
            }
        });

        contributeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent workIntent = new Intent(MainActivity.this,DrpWork.class);
                workIntent.putExtra("key", "nothing");
                startActivity(workIntent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();

                SendToLoginActivity();

            }
        });








    }

    private void SendToLoginActivity() {

        Intent mainactivityIntent = new Intent(MainActivity.this,LoginActivity.class);
//        mainactivityIntent.putExtra("key", email );
        mainactivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainactivityIntent);
        finish();
    }
}