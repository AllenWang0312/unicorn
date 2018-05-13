package edu.tjrac.swant.douban.doubane;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import edu.tjrac.swant.zhihu.zhihu.SubjectsBean;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/17 0017 下午 1:33
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public interface MovieData extends MultiItemEntity {

    SubjectsBean getMovieData();
}
