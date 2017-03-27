package com.jzhu.io.baselibrary.injection.module;

import android.content.Context;
import com.jzhu.io.baselibrary.BaseApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {
    private final BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }


}

