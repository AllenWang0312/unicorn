package edu.tjrac.swant.netimage.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.netimage.adapter.DetialAdapter;

public class MzituAblumDetialActivity extends AppCompatActivity {

    int id;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.rv) RecyclerView mRv;

    ArrayList<String> detials=new ArrayList<>();
    DetialAdapter mDetialAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id", 0);

        setContentView(R.layout.activity_mzitu_ablum_detial);
        ButterKnife.bind(this);

        mRv.setLayoutManager(new GridLayoutManager(MzituAblumDetialActivity.this,2));
        mDetialAdapter=new DetialAdapter(detials);
        mRv.setAdapter(mDetialAdapter);
        initData();
    }

    private void initData() {
//        App.getUnicornApi().getMzituAblumDetial(id).enqueue(new Callback<MzituAblumDetial>() {
//            @Override
//            public void onResponse(Call<MzituAblumDetial> call, Response<MzituAblumDetial> response) {
//                detials.addAll(response.body().getData());
//                mDetialAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<MzituAblumDetial> call, Throwable t) {
//
//            }
//        });
    }

    public static void start(Context context, int id) {
        context.startActivity(new Intent(context, MzituAblumDetialActivity.class)
                .putExtra("id", id));
    }
}
