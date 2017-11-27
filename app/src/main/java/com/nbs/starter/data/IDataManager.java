package com.nbs.starter.data;

import com.nbs.starter.data.api.libs.ApiClient;
import com.nbs.starter.data.db.IDbManager;
import com.nbs.starter.data.preference.IPreferenceManager;

/**
 * Created by ghiyatshanif on 2/28/17.
 */

public interface IDataManager extends IPreferenceManager, ApiClient, IDbManager {

//    sample functions
    void saveUserSession(String accessToken, String username);
}
