package com.jordao.promoeasy.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.jordao.promoeasy.contract.InstagramFunctionsContract;
import com.jordao.promoeasy.model.entity.User;
import com.jordao.promoeasy.model.instagram.ApplicationData;
import com.jordao.promoeasy.model.instagram.Utils;
import com.jordao.promoeasy.presenter.WebAuthenticatePresenter;
import com.jordao.promoeasy.repository.ImageSaver;
import com.jordao.promoeasy.repository.UserRepository;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Thiago Locatelli <thiago.locatelli@gmail.com>
 * @author Lorensius W. L T <lorenz@londatiga.net>
 *
 */
public class InstagramApp implements InstagramFunctionsContract {

    private UserRepository repository;
    private OAuthAuthenticationListener mListener;
    private String mAuthUrl;
    private String mTokenUrl;
    private String mAccessToken;
    private Context mCtx;

    private String mClientId;
    private String mClientSecret;

    static int WHAT_FINALIZE = 0;
    static int WHAT_ERROR = 1;
    private static int WHAT_FETCH_INFO = 2;

    /**
     * Callback url, as set in 'Manage OAuth Costumers' page
     * (https://developer.github.com/)
     */

    public static String mCallbackUrl = "";
    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKEN_URL = "https://api.instagram.com/oauth/access_token";
    private static final String API_URL = "https://api.instagram.com/v1";

    private static final String TAG = "InstaLog";

    public InstagramApp(Context context, String clientId, String clientSecret,
                        String callbackUrl) {
        mClientId = clientId;
        mClientSecret = clientSecret;
        mCallbackUrl = callbackUrl;
        mCtx = context;

        repository = new UserRepository(mCtx);
        initVars();
    }

    private void initVars(){
        mClientId = ApplicationData.CLIENT_ID;
        mClientSecret = ApplicationData.CLIENT_SECRET;
        mTokenUrl = TOKEN_URL + "?client_id=" + mClientId +
                "&client_secret=" + mClientSecret +
                "&redirect_uri=" + mCallbackUrl +
                "&grant_type=authorization_code";
        mAuthUrl = AUTH_URL +
                "?client_id=" + mClientId +
                "&redirect_uri=" + mCallbackUrl +
                "&response_type=code&display=touch&scope=likes+comments+relationships";

        mAccessToken = repository.getAccessToken();
    }

    public WebAuthenticatePresenter.OAuthWebAuthenticateListener getWebViewListner(){
        WebAuthenticatePresenter.OAuthWebAuthenticateListener listener = new WebAuthenticatePresenter.OAuthWebAuthenticateListener() {
            @Override
            public void onComplete(String code) {
                storeAccessToken(code);
            }

            @Override
            public void onError(String error) {
                mListener.onFail("Authorization failed");
            }
        };
        return listener;
    }

    public String getAuthUrl(){
        return mAuthUrl;
    }

    private void storeAccessToken(final String code) {
        //mProgress.setMessage("Getting access token ...");
        //mProgress.show();

        new Thread() {
            @Override
            public void run() {
                Log.i(TAG, "Getting access token");
                int what = WHAT_FETCH_INFO;
                try {
                    URL url = new URL(TOKEN_URL);
                    // URL url = new URL(mTokenUrl + "&code=" + code);
                    Log.i(TAG, "Opening Token URL " + url.toString());
                    HttpURLConnection urlConnection = (HttpURLConnection) url
                            .openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);

                    OutputStreamWriter writer = new OutputStreamWriter(
                            urlConnection.getOutputStream());
                    writer.write("client_id=" + mClientId + "&client_secret="
                            + mClientSecret + "&grant_type=authorization_code"
                            + "&redirect_uri=" + mCallbackUrl + "&code=" + code);
                    writer.flush();
                    String response = Utils.streamToString(urlConnection
                            .getInputStream());
                    Log.i(TAG, "response " + response);
                    JSONObject jsonObj = (JSONObject) new JSONTokener(response)
                            .nextValue();

                    mAccessToken = jsonObj.getString("access_token");
                    Log.i(TAG, "Got access token: " + mAccessToken);

                    String id = jsonObj.getJSONObject("user").getString("id");
                    String username = jsonObj.getJSONObject("user").getString("username");
                    String name = jsonObj.getJSONObject("user").getString("full_name");
                    String profilePictureURL = jsonObj.getJSONObject("user").getString("profile_picture");
                    boolean is_business = jsonObj.getJSONObject("user").getBoolean("is_business");

                    Bitmap image = InstagramApp.this.saveProfileImage(id, profilePictureURL);
                    User user = new User(id, name, username, mAccessToken, image, is_business);
                    repository.storeUser(user);

                } catch (Exception ex) {
                    what = WHAT_ERROR;
                    ex.printStackTrace();
                }

                mHandler.sendMessage(mHandler.obtainMessage(what, 1, 0));
            }
        }.start();
    }

    public Bitmap saveProfileImage(String clientId, String URL) {
        new ImageSaver(mCtx)
                .setFileName(clientId + ".jpg")
                .setDirectoryName("images")
                .download(URL);

        return new ImageSaver(mCtx).
                setFileName(clientId + ".jpg").
                setDirectoryName("images").
                load();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == WHAT_ERROR) {
                //mProgress.dismiss();
                if (msg.arg1 == 1) {
                    mListener.onFail("Failed to get access token");
                } else if (msg.arg1 == 2) {
                    mListener.onFail("Failed to get user information");
                }
            } else if (msg.what == WHAT_FETCH_INFO) {
                // fetchUserName();
                //mProgress.dismiss();
                mListener.onSuccess();
            }
        }
    };

    public void setListener(OAuthAuthenticationListener listener) {
        mListener = listener;
    }

    public User getUser() {
        return repository.getUser();
    }

    public void resetUser() {
        if (mAccessToken != null) {
            repository.resetUser();
            mAccessToken = null;
        }
    }

    @Override
    public void comentOnPost(String postId) {

    }

    @Override
    public void likePost(String postId) {

    }

    @Override
    public void followProfile(String profileId) {

    }

    @Override
    public ArrayList<User> getFollowingList() {
        return null;
    }

    public interface OAuthAuthenticationListener {
        public abstract void onSuccess();

        public abstract void onFail(String error);
    }


}