package com.jzhu.io.baselibrary.injection.component;

import android.content.Context;
import com.jzhu.io.baselibrary.BaseActivity;
import com.jzhu.io.baselibrary.injection.module.ApplicationModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
}
