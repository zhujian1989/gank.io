package com.jzhu.io.gank.injection.module;

import com.jzhu.io.data.repository.GankIoRepository;
import com.jzhu.io.data.repository.impl.GankIoRepositoryImpl;
import com.jzhu.io.data.service.GankIoService;
import com.jzhu.io.data.service.impl.GankIoServiceImp;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jzhu on 2016/11/22.
 */
@Module
public class GankIoModule {

    @Provides
    GankIoRepository provideGankIoRepository(GankIoRepositoryImpl service){
        return service;
    }

    @Provides
    GankIoService provideGankIoService(GankIoServiceImp repository){
        return repository;
    }

}
