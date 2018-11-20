package com.example.ceiro.localservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocalService extends Service {
    String date;
    String time;
    IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public LocalService getServerInstance() {
            return LocalService.this;
        }
    }

    public String getDate() {
        this.date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        return this.date;
    }

    public String getTime() {
        this.time = new SimpleDateFormat("HH.mm.ss", Locale.getDefault()).format(new Date());
        return this.time;
    }
}
