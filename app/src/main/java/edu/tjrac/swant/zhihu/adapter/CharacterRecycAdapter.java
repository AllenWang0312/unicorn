package edu.tjrac.swant.zhihu.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.unicorn.R;
import edu.tjrac.swant.zhihu.zhihu.Character;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 2:13
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class CharacterRecycAdapter extends BaseQuickAdapter<Character, BaseViewHolder> {
    public CharacterRecycAdapter(@Nullable List<Character> data) {
        super(R.layout.item_doubane_character, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Character item) {
        helper.setText(R.id.name, item.getName());
//        helper.setText(R.id.role, item.getName());
        Glide.with(mContext).load(item.getAvatars().getSmall()).into((ImageView) helper.getView(R.id.icon));

    }
}
