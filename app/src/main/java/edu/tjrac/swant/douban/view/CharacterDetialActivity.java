package edu.tjrac.swant.douban.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.unicorn.Net;
import edu.tjrac.swant.zhihu.zhihu.Character;
import edu.tjrac.swant.douban.doubane.DoubanCelebrityBean;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CharacterDetialActivity extends AppCompatActivity {

    private Character info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detial);

        info = getIntent().getParcelableExtra("info");

        Net.getInstance().getDouBanService()
                .getCelebrityInfo(info.getId())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanCelebrityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoubanCelebrityBean bean) {

                    }
                });
    }

    public static void start(Context context, Character character) {
        Intent i = new Intent(context, CharacterDetialActivity.class);
        i.putExtra("info", character);
        context.startActivity(i);
    }
}
