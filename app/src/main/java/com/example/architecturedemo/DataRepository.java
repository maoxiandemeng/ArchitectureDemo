package com.example.architecturedemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by liujing on 2017/11/17.
 */

public class DataRepository {
    private static DataRepository INSTANCE = null;
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    static DataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<User> getGirlList(int index) {
        User user = new User();
        user.setName(index+"-----------hahahaha");
        userLiveData.setValue(user);
        return userLiveData;
    }

    public User getUser(int index) {
        User user = new User();
        user.setName(index+"-----------hahahaha");
        return user;
    }

}
