package com.canteko.mecaround.app;

import android.app.Application;

import com.canteko.mecaround.models.AveriaDB;
import com.canteko.mecaround.models.TallerDB;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();

        setMaxIds();
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private <T extends RealmObject> AtomicLong getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicLong(results.max("id").intValue()) : new AtomicLong(1);
    }

    private void setMaxIds() {
        Realm realm = Realm.getDefaultInstance();
        AveriaDB.setMaxId(getIdByTable(realm, AveriaDB.class));
        TallerDB.setMaxId(getIdByTable(realm, TallerDB.class));
    }
}
