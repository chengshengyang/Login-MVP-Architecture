package com.github.mvp.data.source;

import com.github.mvp.data.User;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public interface UserDataSource {
    /**
     * 这里可以定义一些回调接口
     */
    public interface OnLoginListener {

        void loginSuccess(User user);

        void loginFailed();
    }

    void saveUserInfo(User user);
}
