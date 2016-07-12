package com.github.mvp.login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

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
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance("LOGIN_FRAGMENT");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, loginFragment);
            transaction.commit();
        }

        // Create the presenter
        new LoginPresenter(getApplicationContext(),
                UserRepository.getInstance(
                        UserLocalDataSource.getInstance(getApplicationContext()),
                        UserRemoteDataSource.getInstance()),
                loginFragment);
    }

}

