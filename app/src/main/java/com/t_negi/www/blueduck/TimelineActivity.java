package com.t_negi.www.blueduck;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class TimelineActivity extends AppCompatActivity implements TweetAdapterListener {

    ListView listView;
    List<Tweet> tweetList = new ArrayList<>();
    TweetAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color., R.color.red, R.color.blue, R.color.yellow);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 引っ張って離した時に呼ばれます。
                Snackbar.make(listView, "pull!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                getHomeTimeline();
            }
        });

        listView = (ListView) findViewById(R.id.my_list_view);
        adapter = new TweetAdapter(this, tweetList, this);
        listView.setAdapter(adapter);

        getHomeTimeline();
    }

    private void getHomeTimeline() {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();

        StatusesService statusesService = twitterApiClient.getStatusesService();

        Call<List<Tweet>> call = statusesService.homeTimeline(20, null, null, false, false, false, false);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    tweetList.clear();
                }

                // ListViewのListに取得したツイートのリストを追加
                tweetList.addAll(result.data);
                // ListViewの表示を更新
                adapter.notifyDataSetChanged();

            }

            @Override
            public void failure(TwitterException exception) {
                Snackbar.make(listView, "get timeline error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast toast = Toast.makeText(TimelineActivity.this, "タイムライン取得エラー", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public void onClickReplyButton(Tweet tweet) {

    }

}
