package com.t_negi.www.blueduck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.t_negi.www.blueduck.utils.DateUtils;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class TweetAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Tweet> tweetList;
    private TweetAdapterListener mListener;

    public TweetAdapter(Context context, List<Tweet> tweetList, TweetAdapterListener listener) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tweetList = tweetList;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return tweetList.size();
    }

    @Override
    public Object getItem(int position) {
        return tweetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tweetList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {

            // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.tweet_row, parent, false);
            viewHolder.iconImageView = (ImageView)view.findViewById(R.id.imageView);
            viewHolder.userNameTextView = (TextView)view.findViewById(R.id.user_name);
            viewHolder.screenNameTextView = (TextView)view.findViewById(R.id.screen_name);
            viewHolder.tweetTimeTextView = (TextView)view.findViewById(R.id.tweet_time);
            viewHolder.tweetTextTextView = (TextView)view.findViewById(R.id.tweet_text);
            viewHolder.favoriteCountTextView = (TextView)view.findViewById(R.id.favorite_count);
            viewHolder.reTweetCountTextView = (TextView)view.findViewById(R.id.rt_count);
            viewHolder.replyButton = (Button) view.findViewById(R.id.reply_button);

            // リプライボタンイベントのリスナーセット
            viewHolder.replyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // ボタンが押されたら、独自リスナーのメソッドを呼ぶ。
                    // 引数にはリプライ対象のtweetを渡す
//                mListener.onClickReplyButton(tweet);
                }
            });

            view.setTag(viewHolder);

        } else {

            // レイアウトが存在する場合は再利用する
            viewHolder = (ViewHolder) view.getTag();

        }

        Tweet tweet = tweetList.get(position);

        GlideApp.with(context).load(tweet.user.profileImageUrl).circleCrop().into(viewHolder.iconImageView);
        viewHolder.userNameTextView.setText(tweet.user.name);
        viewHolder.screenNameTextView.setText(tweet.user.screenName);
        viewHolder.tweetTimeTextView.setText("・" + DateUtils.getDateStringFromTwitterDate(tweet.createdAt));
        viewHolder.tweetTextTextView.setText(tweet.text);
        viewHolder.favoriteCountTextView.setText(String.valueOf(tweet.favoriteCount));
        viewHolder.reTweetCountTextView.setText(String.valueOf(tweet.retweetCount));

        return view;
    }

    // ツイート情報のViewHolderクラス
    private class ViewHolder {
        ImageView iconImageView;
        TextView userNameTextView;
        TextView screenNameTextView;
        TextView tweetTimeTextView;
        TextView tweetTextTextView;
        TextView favoriteCountTextView;
        TextView reTweetCountTextView;
        Button replyButton;
    }
}

