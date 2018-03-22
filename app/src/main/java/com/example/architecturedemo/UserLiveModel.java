package com.example.architecturedemo;

import android.arch.lifecycle.LiveData;

/**
 * Created by liujing on 2017/11/20.
 */

public class UserLiveModel extends LiveData<User> {
    public UserLiveModel() {
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }
}
