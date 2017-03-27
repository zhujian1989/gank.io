package com.jzhu.io.gank.injection.component;

import com.jzhu.io.baselibrary.injection.PerActivity;
import com.jzhu.io.baselibrary.injection.component.ActivityComponent;
import com.jzhu.io.baselibrary.injection.component.ApplicationComponent;
import com.jzhu.io.baselibrary.injection.module.ActivityModule;
import com.jzhu.io.gank.injection.module.GankIoModule;
import com.jzhu.io.gank.ui.activity.SplashActivity;
import com.jzhu.io.gank.ui.fragment.AndroidFragment;
import com.jzhu.io.gank.ui.fragment.FLFragment;
import com.jzhu.io.gank.ui.fragment.IosFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = { ActivityModule.class,
                                                                 GankIoModule.class})
public interface GankIoComponent extends ActivityComponent {
    void inject(FLFragment baseActivity);
    void inject(AndroidFragment baseActivity);
    void inject(IosFragment baseActivity);
    void inject(SplashActivity baseActivity);

}
