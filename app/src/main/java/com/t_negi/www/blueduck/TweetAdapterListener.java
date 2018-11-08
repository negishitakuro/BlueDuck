package com.t_negi.www.blueduck;

import com.twitter.sdk.android.core.models.Tweet;

public interface TweetAdapterListener {
    // リプライボタンが押されたことをActivityに知らせる
    public void onClickReplyButton(Tweet tweet);
}
