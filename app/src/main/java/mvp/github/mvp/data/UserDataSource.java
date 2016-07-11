package mvp.github.mvp.data;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public interface UserDataSource {

    public interface OnLoginListener {

        void loginSuccess(User user);

        void loginFailed();
    }

    void saveUserInfo(User user);
}
