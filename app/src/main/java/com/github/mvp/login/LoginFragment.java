package com.github.mvp.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.mvp.R;
import com.github.mvp.main.MainActivity;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    @BindView(R.id.login_progress)
    ProgressBar mLoginProgress;

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.login_form)
    ScrollView mLoginForm;

    /**
     * MVP中的View持有Presenter的实例，并通过View接口LoginContract.View.setPresenter()传入。
     */
    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getUserEmail() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showLoginProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginForm.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginProgress.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                            mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void toMainAct() {
        Toast.makeText(getContext(), "Sign in Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getContext(), "Sign in Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean setEmailError(String error) {
        mEmailView.setError(error);
        if (!TextUtils.isEmpty(error)) {
            mEmailView.requestFocus();
        }
        return  true;
    }

    @Override
    public boolean setPasswordError(String error) {
        mPasswordView.setError(error);
        if (!TextUtils.isEmpty(error)) {
            mPasswordView.requestFocus();
        }
        return true;
    }

    @Override
    public void resetEditView() {
        mEmailView.setText("");
        mPasswordView.setText("");
    }

    @OnClick({R.id.email_sign_in_button, R.id.email_reset_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                mPresenter.login();
                break;

            case R.id.email_reset_button:
                mPresenter.reset();
                break;
        }
    }
}
