package com.bryanortiz.bluesoft.bluebank.root;

import com.bryanortiz.bluesoft.bluebank.mainmvp.MainModule;
import com.bryanortiz.bluesoft.bluebank.mainmvp.MainView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MainModule.class})
public interface ApplicationComponent {

    void inject(MainView target);
}
