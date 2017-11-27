package com.nbs.starter.data.db;

import com.nbs.starter.data.db.model.UserDb;

import java.util.List;

/**
 * Created by ghiyatshanif on 2/23/17.
 */

public interface IDbManager {
    long insertUser(UserDb user);

    List<UserDb> getAllUsers();

}
