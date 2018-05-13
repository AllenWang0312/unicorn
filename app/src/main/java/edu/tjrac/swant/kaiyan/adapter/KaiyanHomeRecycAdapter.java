package edu.tjrac.swant.kaiyan.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yckj.baselib.util.StringUtils;

import java.util.List;

import edu.tjrac.swant.kaiyan.kaiyan.AuthorBean;
import edu.tjrac.swant.kaiyan.kaiyan.KaiYanHomeBean;
import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/22 0022 下午 1:45
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class KaiyanHomeRecycAdapter extends BaseMultiItemQuickAdapter<KaiYanHomeBean.ItemListBean, BaseViewHolder> {
    public KaiyanHomeRecycAdapter(@Nullable List<KaiYanHomeBean.ItemListBean> data) {
        super(data);
        addItemType(0, R.layout.item_kaiyan_home);
        addItemType(1, R.layout.item_kaiyan_home_collection);
        addItemType(2, R.layout.item_kaiyan_home_header);
        addItemType(3, R.layout.item_kaiyan_home_coll_with_cover);
        addItemType(4, R.layout.item_kaiyan_home_footer);
    }

    @Override
    protected void convert(BaseViewHolder helper, KaiYanHomeBean.ItemListBean item) {
        switch (item.getItemType()) {
            case 0: {
                AuthorBean author = item.getData().getAuthor();
                if (author != null) {
                    String authorName = author.getName();
                    if (!StringUtils.isEmpty(authorName)) {
                        helper.setText(R.id.tv_author_name, authorName);
                    }

//        helper.setText(R.id.tv_author_desc,item.getData().getAuthor().getDescription());
                    Glide.with(mContext).load(item.getData().getAuthor().getIcon()).into((ImageView) helper.getView(R.id.iv_author_icon));


                }
                helper.setText(R.id.title, item.getData().getTitle());
//        helper.setText(R.id.desc,item.getData().getDescription());
//                helper.setText(R.id.remark, item.getData().getRemark());

//                Glide.with(mContext).load(item.getData().getCover().getFeed()).into((ImageView) helper.getView(R.id.iv_cover));
            }
            break;
            case 1:
            case 3:
                helper.setText(R.id.title, item.getData().getHeader().getTitle());
                helper.setText(R.id.desc, item.getData().getHeader().getDescription());
                ColloctionAdapter adapter = new ColloctionAdapter(item.getData().getItemList());
                ViewPager vp = helper.getView(R.id.vp);
                vp.setAdapter(adapter);

                break;
            case 2:
            case 4:
                helper.setText(R.id.title, item.getData().getText());
                break;
        }
    }


    class ColloctionAdapter extends PagerAdapter {
        List<KaiYanHomeBean.ItemListBean.DataBean> itemList;

        public ColloctionAdapter(List<KaiYanHomeBean.ItemListBean.DataBean> itemList) {
            this.itemList = itemList;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            KaiYanHomeBean.ItemListBean.DataBean item = itemList.get(position);
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_kaiyan_collection, container);
            Glide.with(mContext).load(item.getCover().getFeed()).into((ImageView) v.findViewById(R.id.image));
            ((TextView) v.findViewById(R.id.title)).setText(item.getCategory());
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
