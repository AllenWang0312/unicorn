package edu.tjrac.swant.douban.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yckj.baselib.common.base.BaseActivity;
import com.yckj.baselib.common.base.BaseWebViewActivity;
import com.yckj.baselib.util.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.douban.doubane.DoubanMovieDetialBean;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.adapter.CharacterRecycAdapter;
import edu.tjrac.swant.zhihu.zhihu.Character;
import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DoubanMovieDetialActivity extends BaseActivity {

    SubjectsBean bean;
    @BindView(R.id.bg) ImageView bg;
    @BindView(R.id.iv) ImageView mIv;
    @BindView(R.id.ratingbar) AppCompatRatingBar mRatingbar;
    @BindView(R.id.rating) TextView mRating;
    @BindView(R.id.tv_name) TextView mTvName;
    @BindView(R.id.disc) TextView mDisc;
    @BindView(R.id.original_name) TextView mOriginalName;
    @BindView(R.id.tv_online_time) TextView mTvOnlineTime;
    @BindView(R.id.tv_movie_range) TextView mTvMovieRange;
    @BindView(R.id.discrabtion) TextView mDiscrabtion;

    @BindView(R.id.artists) RecyclerView mArtists;
    @BindView(R.id.photos) RecyclerView mPhotos;

    @BindView(R.id.comments) LinearLayout mComments;
    @BindView(R.id.all_comments) TextView mAllComments;
    @BindView(R.id.rating_count) TextView mRatingCount;
    @BindView(R.id.wish_count) TextView mWishCount;
    @BindView(R.id.collect_count) TextView mCollectCount;

    ArrayList<Character> characters = new ArrayList<>();
    CharacterRecycAdapter characterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_movie_detial);
        ButterKnife.bind(this);

        bean = getIntent().getParcelableExtra("subjects");
        if (bean != null) {
            Glide.with(mContext).load(bean.getImages()).into(mIv);
            Glide.with(mContext).load(bean.getImages())
//                    .apply(new RequestOptions().transform(new BlurTransformation(14,3) ))
//                    .bitmapTransform(new BlurTransformation(this, 14, 3))
                    .into(bg);
            mTvName.setText(bean.getTitle());
            mDisc.setText(bean.getYear() + "/" + StringUtils.join(bean.getGenres(), "/"));
            if (bean.getTitle().equals(bean.getOriginal_title())) {
                mOriginalName.setVisibility(View.GONE);
            } else {
                mOriginalName.setText("原名: " + bean.getOriginal_title());
            }
            mTvOnlineTime.setText("上映时间: " + bean.getYear());
            if (bean.getRating() != null) {
                mRating.setText(bean.getRating().getAverage() + "");
                mRatingbar.setMax(100);
                mRatingbar.setProgress(Float.floatToIntBits(bean.getRating().getAverage() * 10));

            }

        }
        characterAdapter = new CharacterRecycAdapter(characters);
        mArtists.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, true));
        mArtists.setAdapter(characterAdapter);
        characterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                CharacterDetialActivity.start(mContext, characters.get(position)));
                BaseWebViewActivity.start(mContext,characters.get(position).getName(),"https:/movie.douban.com/celebrity/" + characters.get(position).getId() + "/mobile");
            }
        });
        Net.getInstance().getDouBanService().getMovieDetialInfo(bean.getId())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanMovieDetialBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanMovieDetialBean bean) {

                        characters.addAll(bean.getDirectors());
                        characters.addAll(bean.getCasts());
//                        characters.addAll(bean.ge)
                        characterAdapter.notifyDataSetChanged();
                        mDiscrabtion.setText(bean.getSummary());
                        mRatingCount.setText(bean.getRatings_count() + "人");

                        mWishCount.setText(bean.getWish_count() + "人想看");
                        mCollectCount.setText(bean.getCollect_count() + "人看过");
//                        Glide.with(mContext).load(bean.getImages().getLarge()).into(mIv);
                        mAllComments.setText("全部短评" + bean.getReviews_count() + "个");
                    }
                });

    }


    public static void start(Context context, SubjectsBean bean) {
        Intent intent = new Intent(context, DoubanMovieDetialActivity.class);
        intent.putExtra("subjects", bean);
        context.startActivity(intent);
    }

    public static void startWithSharedView(Activity context, View view, SubjectsBean bean){
        Intent intent = new Intent(context, DoubanMovieDetialActivity.class);
        intent.putExtra("subjects", bean);
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(context, view, "movie_icon");
        context.startActivity(intent,optionsCompat.toBundle());
    }
}
