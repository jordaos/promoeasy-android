package com.jordao.promoeasy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jordao.promoeasy.contract.WebAuthenticateContract;
import com.jordao.promoeasy.presenter.WebAuthenticatePresenter;

public class WebAuthenticateActivity extends Activity implements WebAuthenticateContract.View{

    WebAuthenticateContract.Presenter presenter;

    // UI properties
    private WebView webView;
    private RelativeLayout viewLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_authenticate);

        initViews();

        presenter = new WebAuthenticatePresenter(this);

        webView.loadUrl(presenter.getUrlAuth());
    }

    private void initViews(){
        viewLoading = (RelativeLayout) findViewById(R.id.viewLoading);

        webView = (WebView) findViewById(R.id.webView);
        webView.clearCache(true);
        webView.clearHistory();
        webView.clearFormData();
        webView.clearMatches();
        webView.setWebViewClient(new myWebClient());
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setSavePassword(false);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setJavaScriptEnabled(true);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            viewLoading.setVisibility(View.VISIBLE);
            viewLoading.bringToFront();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            viewLoading.setVisibility(View.GONE);
            //findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //Handle the error
            super.onReceivedError(view, errorCode, description, failingUrl);
            Toast.makeText(getApplicationContext(), "Erro na conexão com o servidor", Toast.LENGTH_LONG).show();
            Log.d("Connection error", errorCode + ": " + description);
            finish();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            viewLoading.setVisibility(View.VISIBLE);
            return presenter.toCallBackUrl(url);
        }
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(WebAuthenticateActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(WebAuthenticateActivity.this, MainActivity.class);
        startActivity(intent);
    }

}