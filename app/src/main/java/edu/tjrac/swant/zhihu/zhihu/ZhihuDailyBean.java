package edu.tjrac.swant.zhihu.zhihu;

import java.util.List;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/15 0015 下午 5:27
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class ZhihuDailyBean {


    /**
     * date : 20180315
     * stories : [{"images":["https://pic1.zhimg.com/v2-1971734a6cc9ca59ddb4c6793729ce4c.jpg"],"type":0,"id":9673801,"ga_prefix":"031517","title":"工作学习中，有哪些更有效率的思维方式？"},{"images":["https://pic3.zhimg.com/v2-8527025e94b5972d8ab78a7e495874f6.jpg"],"type":0,"id":9674301,"ga_prefix":"031517","title":"315 这天蓝标员工称遭 HR 强行辞退，这么做真的没问题吗？"},{"images":["https://pic1.zhimg.com/v2-32e61cd748ed9be24156ddb0b4d6ddb8.jpg"],"type":0,"id":9674197,"ga_prefix":"031515","title":"大公司面试技术岗，面试官更看重什么？"},{"images":["https://pic1.zhimg.com/v2-0c75027928ffc51cf4e35415c4f38110.jpg"],"type":0,"id":9674125,"ga_prefix":"031514","title":"所有人都在怀念霍金，却少有人知道「渐冻」的过程有多痛苦"},{"images":["https://pic3.zhimg.com/v2-67bfe16b6f48c32eccac6becde9bead6.jpg"],"type":0,"id":9674065,"ga_prefix":"031512","title":"大误 · 一看你就是玩音乐的"},{"images":["https://pic2.zhimg.com/v2-37dab3daa2973be34085c29b5a524205.jpg"],"type":0,"id":9674164,"ga_prefix":"031510","title":"职场与家庭，女性是不是真的不能兼顾？"},{"images":["https://pic3.zhimg.com/v2-6a6b65212c177d22060d6118bc17a796.jpg"],"type":0,"id":9673878,"ga_prefix":"031509","title":"「药不能吃太好的，那样吃普通的就没效了\u2026\u2026」"},{"images":["https://pic4.zhimg.com/v2-d6ee482a25cf3afdebe302b66f8d291b.jpg"],"type":0,"id":9672944,"ga_prefix":"031508","title":"「姨妈疼」能用避孕药缓解，但你得先搞清楚一件事"},{"images":["https://pic4.zhimg.com/v2-c67adb0be2982c86a2e711eb44619bc3.jpg"],"type":0,"id":9674102,"ga_prefix":"031507","title":"城市青年避坑手册 · 高铁餐吃出虫，不是默默不吃就完事了"},{"images":["https://pic3.zhimg.com/v2-2a4c91b1e13a25095ca06d6a90c59096.jpg"],"type":0,"id":9674155,"ga_prefix":"031507","title":"这款教你在夜店泡妞的游戏，也许能成为相亲指南"},{"images":["https://pic4.zhimg.com/v2-77c491f3e8fd33e6aeb3ae721c555537.jpg"],"type":0,"id":9673602,"ga_prefix":"031507","title":"30 年过去，中国第一批丁克们怎么样了？"},{"images":["https://pic4.zhimg.com/v2-da9410a0a0d4638a3a84d90c40ffd377.jpg"],"type":0,"id":9673382,"ga_prefix":"031506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-fba12be3ef2eca799ed23d2446225e7e.jpg","type":0,"id":9674301,"ga_prefix":"031517","title":"315 这天蓝标员工称遭 HR 强行辞退，这么做真的没问题吗？"},{"image":"https://pic2.zhimg.com/v2-03381e024e5626dd33fcf2ddb7579c45.jpg","type":0,"id":9673801,"ga_prefix":"031517","title":"工作学习中，有哪些更有效率的思维方式？"},{"image":"https://pic4.zhimg.com/v2-526ae7288f1067520dfb4d6c735e41ff.jpg","type":0,"id":9674125,"ga_prefix":"031514","title":"所有人都在怀念霍金，却少有人知道「渐冻」的过程有多痛苦"},{"image":"https://pic4.zhimg.com/v2-1da56b4d1f15accb19cb634cb4248b0b.jpg","type":0,"id":9674197,"ga_prefix":"031515","title":"大公司面试技术岗，面试官更看重什么？"},{"image":"https://pic1.zhimg.com/v2-e5a3b9c08b70f17aaf82975d091b3c64.jpg","type":0,"id":9674164,"ga_prefix":"031510","title":"职场与家庭，女性是不是真的不能兼顾？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<StoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {

        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<StoriesBean> getTop_stories() {

        return top_stories;
    }

    public void setTop_stories(List<StoriesBean> top_stories) {
        this.top_stories = top_stories;
    }


}
