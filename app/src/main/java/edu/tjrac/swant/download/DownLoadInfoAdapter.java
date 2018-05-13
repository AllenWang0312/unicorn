package edu.tjrac.swant.download;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.download.bean.DownloadFileInfo;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/12 0012 上午 10:35
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class DownLoadInfoAdapter extends BaseQuickAdapter<DownloadFileInfo,BaseViewHolder> {
    public DownLoadInfoAdapter( @Nullable List<DownloadFileInfo> data) {
        super(R.layout.item_file_download_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadFileInfo item) {
        helper.setText(R.id.tv_name,item.getFileName());
        TextView size= helper.getView(R.id.tv_size);
        ProgressBar progress=helper.getView(R.id.progress);

        if(item.isDownloading){
            size.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);

            progress.setMax(100);
            progress.setProgress((int)(item.downloadSize/item.size));
        }else {
            size.setVisibility(View.VISIBLE);
            size.setText(item.getSize()+"");
        }

        helper.addOnClickListener(R.id.iv_delete);

    }
}
