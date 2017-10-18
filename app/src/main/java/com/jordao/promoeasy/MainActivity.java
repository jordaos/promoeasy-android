package com.jordao.promoeasy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jordao.promoeasy.contract.MainContract;
import com.jordao.promoeasy.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainContract.Presenter presenter;

    private static final String TAG = "AnonymousAuth";

    // UI properties
    private Button toAuthenticate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        if (!getIntent().getBooleanExtra("EXIT_APP", false)){
            presenter.isLoggedIn();
        }

        initViews();
    }

    private void initViews(){
        toAuthenticate = (Button) findViewById(R.id.toAuthenticate);
        toAuthenticate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.authenticate();
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToWebAuthenticate() {
        Intent intent = new Intent(MainActivity.this, WebAuthenticateActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onResume() {
        if(getIntent().getBooleanExtra("EXIT_APP", false)){
            finish();
        }
        super.onResume();
    }
}
