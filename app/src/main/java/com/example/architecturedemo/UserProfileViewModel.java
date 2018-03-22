package com.example.architecturedemo;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by liujing on 2017/11/17.
 */

public class UserProfileViewModel extends ViewModel {
    private String userId;
    private User user;
    private final MutableLiveData<Integer> index = new MutableLiveData<>();
    private LiveData<User> userLiveData;
    private DataRepository dataRepository;
    private int i;

    public UserProfileViewModel(Application application, DataRepository repository) {
        this.dataRepository = repository;
//        this.userLiveData = Transformations.switchMap(index, new Function<Integer, LiveData<User>>() {
//            @Override
//            public LiveData<User> apply(Integer input) {
//                return dataRepository.getGirlList(input);
//            }
//        });
        this.userLiveData = Transformations.map(index, new Function<Integer, User>() {
            @Override
            public User apply(Integer input) {
                return dataRepository.getUser(input);
            }
        });
    }

    public void init(String userId) {
        this.userId = userId;
    }

    public void refreshData(int i) {
        this.i = i;
        index.setValue(i);
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final DataRepository mGirlsDataRepository;

        public Factory(@NonNull Application application, DataRepository girlsDataRepository) {
            mApplication = application;
            mGirlsDataRepository = girlsDataRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new UserProfileViewModel(mApplication, mGirlsDataRepository);
        }
    }

}
