package com.t_negi.www.blueduck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.core.TwitterCore;

public class MainActivity extends AppCompatActivity {

    /** ログイン処理リクエストコード */
    static final int LOGIN_REQUEST_CODE = 11;
    /** タイムライン取得処理リクエストコード */
    static final int TIMELINE_REQUEST_CODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (TwitterCore.getInstance().getSessionManager().getActiveSession() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);

        } else {
            Intent intent = new Intent(this, TimelineActivity.class);
            startActivityForResult(intent, TIMELINE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Intent intent = new Intent(this, TimelineActivity.class);
                startActivityForResult(intent, TIMELINE_REQUEST_CODE);

            }
        } else{
            // ログイン画面,タイムライン画面からキャンセルで戻ってきたらアプリ終了
            finish();
        }
    }
}
