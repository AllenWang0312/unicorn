package edu.tjrac.swant.todo.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yckj.baselib.common.adapter.ViewsPagerAdapter;
import com.yckj.baselib.util.ReflectUtils;
import com.yckj.baselib.wiget.UnscrollableViewpager;

import java.util.ArrayList;

import edu.tjrac.swant.qqmusic.PackageSearchResult;
import edu.tjrac.swant.todo.JDApi;
import edu.tjrac.swant.todo.adapter.PackageInfoStepsAdapter;
import edu.tjrac.swant.unicorn.Net;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/6/22.
 */
@SuppressLint("ValidFragment")
public class ClipboardInfoDialog extends DialogFragment implements View.OnClickListener {

    String clip_text;


    public ClipboardInfoDialog(String clip_text) {
        this.clip_text = clip_text;
    }

    UnscrollableViewpager mvp;

    ViewsPagerAdapter adapter;

    View options;
    EditText et_info;
    TextView tv_package;

    View secondView;

    //    快递包裹信息
    ArrayList<PackageSearchResult.ResultBeanXX.DataBean.ListBeanX> steps = new ArrayList<>();
    PackageInfoStepsAdapter packagesinfo_adapter;
    TextView status, exp_name, exp_phone, exp_site, number, type;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                mvp.setCurrentItem(0);
                dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setVisibility(View.GONE);
            }else
            if (msg.what == 1) {
                adapter.notifyDataSetChanged();
                mvp.setCurrentItem(1);
                dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setVisibility(View.VISIBLE);
            }

        }
    };
    AlertDialog dialog;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mvp = (UnscrollableViewpager) LayoutInflater.from(getActivity()).inflate(R.layout.unscrollable_viewpager_h300, null);
        options = LayoutInflater.from(getActivity()).inflate(R.layout.options_clipboard_dialg, null);
        et_info = options.findViewById(R.id.et_info);
        et_info.setText(clip_text);


        tv_package = options.findViewById(R.id.tv_package);
        tv_package.setOnClickListener(this);
        adapter = new ViewsPagerAdapter();
        adapter.addView(options, "options");

        mvp.setNoScroll(true);

        mvp.setAdapter(adapter);

       dialog = new AlertDialog.Builder(getActivity())
                .setTitle("处理剪切板")
                .setView(mvp)
               .setCancelable(false)
                .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dia, int which) {
                      mHandler.sendEmptyMessage(0);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardInfoDialog.this.dismiss();
                    }
                })
                .create();
        ReflectUtils.disableClose(dialog);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_package:
                secondView = LayoutInflater.from(getActivity()).inflate(edu.tjrac.swant.unicorn.R.layout.recyclerview, null);

                Net.getInstance().getJDApi().searchPackage(clip_text, JDApi.key)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<PackageSearchResult>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(PackageSearchResult packageSearchResult) {
                                RecyclerView recyclerView = (RecyclerView) secondView;
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                                PackageInfoStepsAdapter ad = new PackageInfoStepsAdapter(packageSearchResult.getResult().getData().getList());
                                recyclerView.setAdapter(ad);
                                ad.bindToRecyclerView(recyclerView);
                                View head = LayoutInflater.from(getActivity()).inflate(R.layout.head_package_info, null);
                                status = head.findViewById(R.id.status);
                                exp_name = head.findViewById(R.id.exp_name);
                                exp_phone = head.findViewById(R.id.exp_phone);
                                exp_site = head.findViewById(R.id.exp_site);
                                number = head.findViewById(R.id.number);
                                type = head.findViewById(R.id.type);

                                initHead(packageSearchResult.getResult().getData());

                                ad.addHeaderView(head);
                                adapter.addView(secondView, "second");
                                mHandler.sendEmptyMessage(1);
//                                        PackageResultActivity.start(mContext, new Gson().toJson(packageSearchResult));
                            }
                        });

                break;
        }

    }

    private void initHead(PackageSearchResult.ResultBeanXX.DataBean data) {
        String s = "";
        switch (data.getDeliverystatus()) {
            case "1":
                s = "在途中";
                break;
            case "2":
                s = "正在派件";
                break;
            case "3":
                s = "已签收";
                break;
            case "4":
                s = "派送失败";
                break;
        }
        status.setText(s);
        exp_name.setText(data.getExpName());
        ;
        exp_phone.setText(data.getExpPhone());
        exp_site.setText(data.getExpSite());

        number.setText(data.getNumber());
        type.setText(data.getType());
    }

}
