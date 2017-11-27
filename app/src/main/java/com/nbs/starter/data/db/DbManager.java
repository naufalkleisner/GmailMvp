package com.nbs.starter.data.db;

import com.nbs.starter.data.db.libs.DbOpenHelper;
import com.nbs.starter.data.db.model.DaoMaster;
import com.nbs.starter.data.db.model.DaoSession;
import com.nbs.starter.data.db.model.UserDb;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public class DbManager implements IDbManager {
    private final DaoSession mDaoSession;

    @Inject
    public DbManager(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

//    example implementation of GreenDAO database operation

    @Override
    public long insertUser(UserDb user) {
        return mDaoSession.getUserDbDao().insert(user);
    }

    @Override
    public List<UserDb> getAllUsers() {
        return mDaoSession.getUserDbDao().loadAll();
    }
}
