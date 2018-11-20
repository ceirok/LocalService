package com.example.ceiro.localservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public LocalService localService;
    public boolean connected = false;
    public TextView status;
    public Button connectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.status);
        connectBtn = findViewById(R.id.connect);
    }

    public void showDate(View view) {
        if(connected) {
            String currentDate = localService.getDate();
            Toast.makeText(this, currentDate, Toast.LENGTH_SHORT).show();
        }
    }

    public void showTime(View view) {
        if(connected) {
            String currentTime = localService.getTime();
            Toast.makeText(this, currentTime, Toast.LENGTH_SHORT).show();
        }
    }

    public void connect(View view) {
        if(connected) {
            unbindService(mConnection);
            connected = false;
            connectBtn.setText(R.string.Connect);
            status.setText(R.string.Disconnected);
        } else {
            Intent mIntent = new Intent(this, LocalService.class);
            bindService(mIntent, mConnection, BIND_AUTO_CREATE);
            connected = true;
            status.setText(R.string.Connected);
            connectBtn.setText(R.string.Disconnect);
        }
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "Service is disconnected", Toast.LENGTH_SHORT).show();
            connected = false;
            localService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "Service is connected", Toast.LENGTH_SHORT).show();
            connected = true;
            LocalService.LocalBinder mLocalBinder = (LocalService.LocalBinder)service;
            localService = mLocalBinder.getServerInstance();
        }
    };
}
