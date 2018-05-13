package edu.tjrac.swant.download;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by wpc on 2018/2/3 0003.
 */

public class DownloadDirInfoAdapter extends BaseSectionQuickAdapter<SectionEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DownloadDirInfoAdapter(int layoutResId, int sectionHeadResId, List<SectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionEntity item) {

    }

    @Override
    protected void convert(BaseViewHolder helper, SectionEntity item) {

    }

    class DirEntry extends SectionEntity {
        String path;

        public DirEntry(Object o) {
            super(o);
        }
    }

    class ItemEntry extends SectionEntity {
        String path;

        public ItemEntry(Object o) {
            super(o);
        }
    }
}
