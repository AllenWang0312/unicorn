package edu.tjrac.swant.media.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.yckj.baselib.common.base.BaseActivity;
import com.yckj.baselib.common.views.StateImageView;
import com.yckj.baselib.util.T;
import com.yckj.baselib.util.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tjrac.swant.media.LrcLoadCallBack;
import edu.tjrac.swant.media.MusicPlayService;
import edu.tjrac.swant.media.MusicResCallBack;
import edu.tjrac.swant.media.adapter.PlayListExpandAdapter;
import edu.tjrac.swant.qqmusic.lrchelper.LRCView;
import edu.tjrac.swant.qqmusic.lrchelper.MusicInfo;
import edu.tjrac.swant.unicorn.R;

public class MusicPlayerActivity extends BaseActivity implements View.OnClickListener {

    String TAG = "MusicPlayerActivity";
    public static String res_type = ".mp3";
    int index;
    ArrayList<MusicInfo> playList = new ArrayList<>();

    @BindView(R.id.iv_back) ImageView mIvBack;
    @BindView(R.id.tv_title) TextView mTvTitle;
    @BindView(R.id.tv_summary) TextView mTvSummary;
    @BindView(R.id.iv_share) ImageView mIvShare;
    @BindView(R.id.sb_voice) SeekBar mSbVoice;
    @BindView(R.id.v_lrc) LRCView mVLrc;
    @BindView(R.id.sb_progress) SeekBar mSbProgress;
    @BindView(R.id.iv_change_state) StateImageView mIvChangeState;
    @BindView(R.id.iv_pre) ImageView mIvPre;
    @BindView(R.id.cb_play) CheckBox mCbPlay;
    @BindView(R.id.iv_next) ImageView mIvNext;
    @BindView(R.id.iv_playlist) ImageView mIvPlaylist;
    @BindView(R.id.tv_progress) TextView tvProgress;
    @BindView(R.id.tv_duration) TextView tvDuration;


    MusicPlayService.MusicPlayerBinder sBinder;
    PopupWindow pop;
    boolean isPlaying;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mVLrc.scrollTo((long ) sBinder.getProgress() - offset);
            int progress = (int) ((float) (sBinder.getProgress()) / sBinder.getLength() * 100);
            mSbProgress.setProgress(progress);
            tvProgress.setText(TimeUtils.getTimeWithFormat((long) progress, "mm:ss"));
            tvDuration.setText(TimeUtils.getTimeWithFormat((long) sBinder.getLength(), "mm:ss"));

            Log.i("progress", sBinder.getProgress() + "_" + sBinder.getLength() + "_" + mSbProgress.getMax());
        }
    };
    Timer mTimer = new Timer();

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sBinder = (MusicPlayService.MusicPlayerBinder) service;
            playList = sBinder.playlist;
            sBinder.setLrcCallback(new LrcLoadCallBack() {
                @Override
                public void onSuccess() {
                    mVLrc.setLrcInfo(sBinder.getLrcInfo());
                }

                @Override
                public void onFaild(String message) {
                    T.show(mContext, message);
                }
            });
            sBinder.setMusicCallback(new MusicResCallBack() {
                @Override
                public void onSuccess(MusicInfo info) {
                    tvProgress.setText(TimeUtils.getTimeWithFormat(info.getProgress(), "mm:ss"));
                    tvDuration.setText(TimeUtils.getTimeWithFormat(info.getDuration(), "mm:ss"));
                }
            });

            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (sBinder.getLength() != 0) {
                        if (mVLrc.getDuration() == 0) {
                            mVLrc.setDuration(sBinder.getLength());
                        }
                        if (mSbProgress.getMax() == 0) {
                            mSbProgress.setMax(sBinder.getLength());
                        }
//                      float present=sBinder.getProgress()/sBinder.getLength();
                        mHandler.sendEmptyMessage(0);
                    }
                }
            }, 500, 1000);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    int offset = 3000;


    @Override
    protected void onResume() {
//        String name = getIntent().getStringExtra("name");
//        String url=getIntent().getStringExtra("url");
//        String lrcStr=getIntent().getStringExtra("lrc");
        playList = getIntent().getParcelableArrayListExtra("list");
        index = getIntent().getIntExtra("index", 0);
        bindService(new Intent(this, MusicPlayService.class)
                        .putExtra("list", playList)
                        .putExtra("index", index)
                , connection, BIND_AUTO_CREATE);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        ButterKnife.bind(this);

//        local_files = MediaUtil.getMediaMaps(this, MediaUtil.MediaType.music);

//        list = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.recycler, null);
//        list.setLayoutManager(new LinearLayoutManager(this));
//        list.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//        pop = new PopupWindow(this);
//
//        pop.setContentView(list);
//        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setBackgroundDrawable(null);
//        pop.setFocusable(true);
//        pop.setOutsideTouchable(true);
////        playlist.setWindowLayoutType();
////        adapter = new PlayListExpandAdapter(getData(local_files));
//        adapter = new PlayListAdapter(playList);
//
//        list.setAdapter(adapter);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                sendBroadcast(new Intent(MusicPlayService.PLAY_ACTION).putExtra("index", position));
//            }
//        });
        mIvPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.showAsDropDown(mIvPlaylist);
            }
        });

        mSbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float position = ((float) progress / 100 * sBinder.getLength());
                sBinder.scrollTo((int) progress);
                mVLrc.scrollTo((long) progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                Log.i("onStop",seekBar.getProgress()+"");
                sBinder.scrollTo((int)
                        (seekBar.getProgress()/100f*sBinder.getLength())
                );
            }
        });
        mIvPre.setOnClickListener(this);
        mIvNext.setOnClickListener(this);
        mCbPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sBinder.play();
                } else {
                    sBinder.stop();
                }
            }
        });
    }

    private List<SectionEntity> getData(HashMap<String, ArrayList<String>> local_files) {
        List<SectionEntity> entitys = new ArrayList<>();
        Set<String> dirs = local_files.keySet();
        for (String dir : dirs) {
            entitys.add(new PlayListExpandAdapter.GroupData(dir));
            ArrayList<String> items = local_files.get(dir);
            for (String str : items) {
                entitys.add(new PlayListExpandAdapter.ChildData(str));
            }
        }
        return entitys;
    }

    public static void start(Context context, String path) {
        File file = new File(path);
        File[] bros = file.getParentFile().listFiles();
        int index = 0;
        ArrayList<MusicInfo> infos = new ArrayList<>();

        for (int i = 0; i < bros.length; i++) {
            if (file.getName().equals(bros[i].getName())) {
                index = i;
            }
            infos.add(new MusicInfo(bros[i].getAbsolutePath()));
        }
        context.startActivity(new Intent(context, MusicPlayerActivity.class)
                .putExtra("list", infos)
                .putExtra("index", index)
        );
    }

    public static void start(Context context, ArrayList<MusicInfo> infos) {
        context.startActivity(new Intent(context, MusicPlayerActivity.class)
                .putExtra("list", infos)
        );
    }

    public static void start(Context context, ArrayList<MusicInfo> infos, int index) {
        context.startActivity(new Intent(context, MusicPlayerActivity.class)
                .putExtra("list", infos)
                .putExtra("index", index)
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_next:
                sendBroadcast(new Intent(MusicPlayService.PLAY_NEXT_ACTION));
//                sBinder.playNext();
                break;
            case R.id.iv_pre:
                sendBroadcast(new Intent(MusicPlayService.PLAY_PRE_ACTION));
//                sBinder.playNext();
                break;
        }
    }


    public static void testStart(Context context) {
        ArrayList<MusicInfo> infos = new ArrayList<>();
        MusicInfo info = new MusicInfo(
                "http://yc-cihang-test.oss-cn-shanghai.aliyuncs.com/test/%E9%BB%84%E9%BE%84%20-%20%E7%97%92.mp3",
                "http://yc-cihang-test.oss-cn-shanghai.aliyuncs.com/test/yang1.lrc"
        );
        infos.add(info);
        context.startActivity(new Intent(context, MusicPlayerActivity.class)
                .putExtra("list", infos));
    }

    public int getLayout() {
        return R.layout.activity_music_player;
    }
}
