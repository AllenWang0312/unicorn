package edu.tjrac.swant.media.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.File;
import java.util.List;

import edu.tjrac.swant.unicorn.R;

/**
 * Created by wpc on 2018/2/5 0005.
 */

public class PlayListExpandAdapter extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {


    public PlayListExpandAdapter(List<SectionEntity> data) {
        super(R.layout.item_play_list_group, R.layout.item_play_list_child, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity item) {
        GroupData groupData = (GroupData) item;
        helper.setText(R.id.tv_name, new File(groupData.dir).getName());
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionEntity item) {
        ChildData childData = (ChildData) item;
        helper.setText(R.id.tv_name, new File(childData.item).getName());
    }

    public static class GroupData extends SectionEntity {
        String dir;

        public GroupData(String dir) {
            super(dir);
            this.dir = dir;
        }
    }

    public static class ChildData extends SectionEntity {
        String item;

        public ChildData(String item) {
            super(item);
            this.item = item;
        }
    }
}
