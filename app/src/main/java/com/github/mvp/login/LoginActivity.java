package com.github.mvp.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mvp.R;
import com.github.mvp.data.source.UserRepository;
import com.github.mvp.data.source.local.UserLocalDataSource;
import com.github.mvp.data.source.remote.UserRemoteDataSource;

/**
 * A login screen that offers login via mEmailView/mPasswordView.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        // Create the view
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance("LOGIN_FRAGMENT");
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, loginFragment);
        transaction.commit();

        // Create the presenter
        new LoginPresenter(getApplicationContext(),
                UserRepository.getInstance(
                        UserLocalDataSource.getInstance(getApplicationContext()),
                        UserRemoteDataSource.getInstance()),
                loginFragment);
    }
}

