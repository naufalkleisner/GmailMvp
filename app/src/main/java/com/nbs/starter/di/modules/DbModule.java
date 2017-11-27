package com.nbs.starter.di.modules;

import com.nbs.starter.data.db.DbManager;
import com.nbs.starter.data.db.IDbManager;
import com.nbs.starter.di.qualifiers.DatabaseInfo;
import com.nbs.starter.utils.AppConstants;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ghiyatshanif on 2/8/17.
 */

@Module
public class DbModule {

    @DatabaseInfo
    @Provides
    String provideDbName(){
        return AppConstants.DB_NAME;
    }

    @Provides
    IDbManager provideDbManager(DbManager dbManager){
        return dbManager;
    }
}
