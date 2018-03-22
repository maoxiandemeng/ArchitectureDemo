package com.example.architecturedemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    private UserProfileViewModel viewModel;
    private User user;
    private LiveData<User> userLiveData;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UserProfileViewModel.Factory factory = new UserProfileViewModel.Factory(this.getApplication(), DataRepository.getInstance());
        viewModel = ViewModelProviders.of(this, factory).get(UserProfileViewModel.class);
        viewModel.init("123");
        viewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                tv.setText(user.getName());
            }
        });
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        i++;
        viewModel.refreshData(i);
    }
}
