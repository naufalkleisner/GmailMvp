package com.nbs.starter.data;

import com.nbs.starter.data.api.libs.ApiClient;
import com.nbs.starter.data.api.model.Message;
import com.nbs.starter.data.db.IDbManager;
import com.nbs.starter.data.db.model.UserDb;
import com.nbs.starter.data.preference.IPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by ghiyatshanif on 2/20/17.
 * purpose : Class that handles all database request, delegate all method to each corresponding helpers
 */

public class DataManager implements IDataManager {
    private final ApiClient mApiManager;
    private final IPreferenceManager mPreferenceManager;
    private final IDbManager mDbManager;


    @Inject
    public DataManager(ApiClient mApiManager, IPreferenceManager mPreferenceManager, IDbManager mDbManager) {
        this.mApiManager = mApiManager;
        this.mPreferenceManager = mPreferenceManager;
        this.mDbManager = mDbManager;
    }

    @Override
    public void saveUserSession(String accessToken, String username) {

    }


    ///////////////////////////////////////////////////////////////////////////
    // API DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public Call<ArrayList<Message>> getInbox() {
        return mApiManager.getInbox();
    }
//    @Override
//    public Call<List<Message>> getInbox() {
//        return mApiManager.getInbox();
//    }

//    @Override
//    public Call<SampleResponse> loadAnotherSample() {
//        return mApiManager.loadAnotherSample();
//    }

    ///////////////////////////////////////////////////////////////////////////
    // END OF API DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // PREFERENCE DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void setAccessToken(String accessToken) {
        mPreferenceManager.setAccessToken(accessToken);
    }

    @Override
    public String getAccessToken() {
        return mPreferenceManager.getAccessToken();
    }

    @Override
    public void setUsername(String username) {
        mPreferenceManager.setUsername(username);
    }

    @Override
    public String getUsername() {
        return mPreferenceManager.getUsername();
    }


    ///////////////////////////////////////////////////////////////////////////
    // END OF PREFERENCE DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////
    // DB DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public long insertUser(UserDb user) {
        return mDbManager.insertUser(user);
    }

    @Override
    public List<UserDb> getAllUsers() {
        return mDbManager.getAllUsers();
    }


    ///////////////////////////////////////////////////////////////////////////
    // END OF DB DELEGATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
}
