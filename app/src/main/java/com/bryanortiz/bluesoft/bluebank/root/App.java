package com.bryanortiz.bluesoft.bluebank.root;

import android.app.Application;

import com.bryanortiz.bluesoft.bluebank.mainmvp.MainModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainModule(new MainModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
