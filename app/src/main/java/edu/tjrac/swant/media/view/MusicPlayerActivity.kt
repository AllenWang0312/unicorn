package edu.tjrac.swant.media.view

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.PopupWindow
import android.widget.SeekBar
import com.chad.library.adapter.base.entity.SectionEntity
import com.yckj.baselib.common.base.BaseActivity
import com.yckj.baselib.util.T
import com.yckj.baselib.util.TimeUtils
import edu.tjrac.swant.media.LrcLoadCallBack
import edu.tjrac.swant.media.MusicPlayService
import edu.tjrac.swant.media.adapter.PlayListExpandAdapter
import edu.tjrac.swant.qqmusic.lrchelper.MusicInfo
import edu.tjrac.swant.unicorn.R
import kotlinx.android.synthetic.main.activity_music_player.*
import java.io.File
import java.util.*

class MusicPlayerActivity : BaseActivity(), View.OnClickListener {

    internal var TAG = "MusicPlayerActivity"
    internal var index: Int = 0
    internal var playList = ArrayList<MusicInfo>()

    //    @BindView(R.id.iv_back) ImageView iv_back;
    //    @BindView(R.id.tv_title) TextView iv_title;
    //    @BindView(R.id.tv_summary) TextView iv_summary;
    //    @BindView(R.id.iv_share) ImageView mIvShare;
    //    @BindView(R.id.sb_voice) SeekBar mSbVoice;
    //    @BindView(R.id.iv_change_state) StateImageView mIvChangeState;
    //
    //    @BindView(R.id.v_lrc) LRCView v_lrc;
    //    @BindView(R.id.sb_progress) SeekBar sb_progress;
    //    @BindView(R.id.iv_pre) ImageView iv_pre;
    //    @BindView(R.id.cb_play) CheckBox cb_play;
    //    @BindView(R.id.iv_next) ImageView iv_next;
    //    @BindView(R.id.iv_playlist) ImageView iv_playlist;
    //    @BindView(R.id.tv_progress) TextView tv_progress;
    //    @BindView(R.id.tv_duration) TextView tv_duration;


    internal var sBinder: MusicPlayService.MusicPlayerBinder?=null
    internal var pop: PopupWindow? = null
    internal var isPlaying: Boolean = false
    internal var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            v_lrc.scrollTo(sBinder!!.progress.toLong() - offset)
            val progress = (sBinder!!.progress.toFloat() / sBinder!!.length * 100).toInt()
            sb_progress.setProgress(progress)
            tv_progress.setText(TimeUtils.getTimeWithFormat(progress.toLong(), "mm:ss"))
            tv_duration.setText(TimeUtils.getTimeWithFormat(sBinder!!.length.toLong(), "mm:ss"))

            Log.i("progress", sBinder!!.progress.toString() + "_" + sBinder!!.length + "_" + sb_progress.getMax())
        }
    }
    internal var mTimer = Timer()

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            sBinder = service as MusicPlayService.MusicPlayerBinder
            playList = sBinder!!.playlist
            sBinder!!.setLrcCallback(object : LrcLoadCallBack {
                override fun onSuccess() {
                    v_lrc.setLrcInfo(sBinder!!.lrcInfo)
                }

                override fun onFaild(message: String) {
                    T.show(mContext, message)
                }
            })
            sBinder!!.setMusicCallback { info ->
                tv_progress.setText(TimeUtils.getTimeWithFormat(info.progress, "mm:ss"))
                tv_duration.setText(TimeUtils.getTimeWithFormat(info.duration, "mm:ss"))
            }

            mTimer.schedule(object : TimerTask() {
                override fun run() {
                    if (sBinder!!.length != 0) {
                        if (v_lrc.getDuration() == 0L) {
                            v_lrc.setDuration(sBinder!!.length.toLong())
                        }
                        if (sb_progress.getMax() === 0) {
                            sb_progress.setMax(sBinder!!.length)
                        }
                        //                      float present=sBinder.getProgress()/sBinder.getLength();
                        mHandler.sendEmptyMessage(0)
                    }
                }
            }, 500, 1000)
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    internal var offset = 3000


    override fun onResume() {
        //        String name = getIntent().getStringExtra("name");
        //        String url=getIntent().getStringExtra("url");
        //        String lrcStr=getIntent().getStringExtra("lrc");
        playList = intent.getParcelableArrayListExtra<MusicInfo>("list")
        index = intent.getIntExtra("index", 0)
        bindService(Intent(this, MusicPlayService::class.java)
                .putExtra("list", playList)
                .putExtra("index", index), connection, Context.BIND_AUTO_CREATE)

        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout)

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
        iv_playlist.setOnClickListener(View.OnClickListener { pop!!.showAsDropDown(iv_playlist) })

        sb_progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val position = progress.toFloat() / 100 * sBinder!!.length
                sBinder!!.scrollTo(progress.toInt())
                v_lrc.scrollTo(progress.toLong())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

                Log.i("onStop", seekBar.progress.toString() + "")
                sBinder!!.scrollTo((seekBar.progress / 100f * sBinder!!.length).toInt()
                )
            }
        })
        iv_pre.setOnClickListener(this)
        iv_next.setOnClickListener(this)
        cb_play.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sBinder!!.play()
            } else {
                sBinder!!.stop()
            }
        })
    }

    private fun getData(local_files: HashMap<String, ArrayList<String>>): List<SectionEntity<*>> {
        val entitys = ArrayList<SectionEntity<*>>()
        val dirs = local_files.keys
        for (dir in dirs) {
            entitys.add(PlayListExpandAdapter.GroupData(dir))
            val items = local_files[dir]
            for (str in items!!) {
                entitys.add(PlayListExpandAdapter.ChildData(str))
            }
        }
        return entitys
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_next -> sendBroadcast(Intent(MusicPlayService.PLAY_NEXT_ACTION))
            R.id.iv_pre -> sendBroadcast(Intent(MusicPlayService.PLAY_PRE_ACTION))
        }//                sBinder.playNext();
        //                sBinder.playNext();
    }

    val layout: Int
        get() = R.layout.activity_music_player

    companion object {
        var res_type = ".mp3"

        fun start(context: Activity, path: String) {
            val file = File(path)
            val bros = file.parentFile.listFiles()
            var index = 0
            val infos = ArrayList<MusicInfo>()

            for (i in bros.indices) {
                if (file.name == bros[i].name) {
                    index = i
                }
                infos.add(MusicInfo(bros[i].absolutePath))
            }
            context.startActivity(Intent(context, MusicPlayerActivity::class.java)
                    .putExtra("list", infos)
                    .putExtra("index", index)
            )
        }

        fun start(context: Activity, infos: ArrayList<MusicInfo>) {
            context.startActivity(Intent(context, MusicPlayerActivity::class.java)
                    .putExtra("list", infos)
            )
        }

        fun start(context: Activity, infos: ArrayList<MusicInfo>, index: Int) {
            context.startActivity(Intent(context, MusicPlayerActivity::class.java)
                    .putExtra("list", infos)
                    .putExtra("index", index)
            )
        }


        fun testStart(context: Context) {
            val infos = ArrayList<MusicInfo>()
            val info = MusicInfo(
                    "http://yc-cihang-test.oss-cn-shanghai.aliyuncs.com/test/%E9%BB%84%E9%BE%84%20-%20%E7%97%92.mp3",
                    "http://yc-cihang-test.oss-cn-shanghai.aliyuncs.com/test/yang1.lrc"
            )
            infos.add(info)
            context.startActivity(Intent(context, MusicPlayerActivity::class.java)
                    .putExtra("list", infos))
        }
    }
}
