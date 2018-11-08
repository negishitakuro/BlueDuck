package com.t_negi.www.blueduck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterCore;

public class MainActivity extends AppCompatActivity {

    /** ログイン処理リクエストコード */
    static final int LOGIN_REQUEST_CODE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (TwitterCore.getInstance().getSessionManager().getActiveSession() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);

        } else {
            Toast toast = Toast.makeText(MainActivity.this, "ログイン中", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(this, TimelineActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
