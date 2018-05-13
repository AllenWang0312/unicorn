package edu.tjrac.swant.unicorn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.yckj.baselib.common.base.BaseBackNoticeActivity;

import edu.tjrac.swant.douyin.module.main.DouYinActivity;
import edu.tjrac.swant.bluetooth.view.BluetoothActivity;
import edu.tjrac.swant.todo.view.ToDoMainActivity;
import edu.tjrac.swant.unicorn.view.MainActivity;
import edu.tjrac.swant.unicorn.view.ZhihuActivity;

/**
 * Created by wpc on 2018/3/2 0002.
 */

public abstract class SharedStartActivity extends BaseBackNoticeActivity {


    Dialog window;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    boolean show = false;

    @Override
    public void onBackPressed() {
        if (show) {
            show = false;
            super.onBackPressed();
        } else {

            if (window == null) {
                window = new AlertDialog.Builder(mContext).setItems(
                        new String[]{"unicorn", "bluetooth", "抖音", "知乎 x 豆瓣电影 x Gank x 开眼视频","ed"}, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mContext.startActivity(new Intent(mContext, MainActivity.class));
                                        break;
                                    case 1:
                                        mContext.startActivity(new Intent(mContext, BluetoothActivity.class));
                                        break;
                                    case 2:
                                        mContext.startActivity(new Intent(mContext, DouYinActivity.class));
                                        break;
                                    case 3:
                                        mContext.startActivity(new Intent(mContext, ZhihuActivity.class));
                                        break;
                                    case 4:
                                        mContext.startActivity(new Intent(mContext, ToDoMainActivity.class));
                                        break;
//                                    case 4:
//                                        mContext.startActivity(new Intent(mContext, KaiyanActivity.class));
//                                        break;
                                }
                            }
                        }
                ).create();
            }
            window.show();

            show = true;
        }

    }
}
