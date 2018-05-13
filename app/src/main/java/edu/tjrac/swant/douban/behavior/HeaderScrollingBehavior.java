package edu.tjrac.swant.douban.behavior;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/4/2 0002 上午 8:53
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
//
//public class HeaderScrollingBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
//
//    private boolean isExpanded = false;
//    private boolean isScrolling = false;
//    private WeakReference<View> dependentView;
//    private Scroller scroller;
//    private Handler handler;
//
//    public HeaderScrollingBehavior(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        scroller = new Scroller(context);
//        handler = new Handler();
//    }
//
//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
//        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
//            dependentView = new WeakReference<View>(dependency);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
//            child.layout((0, 0, parent.getWidth(), (int) (parent.getHeight() - getDependentViewCollapsedHeight())))
//            return true;
//        }
//        return super.onLayoutChild(parent, child, layoutDirection);
//    }
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
//        Resources resources=getDependentView().getResources();
//        final float progress = 1.f -Math.abs(dependency.getTranslationY()/(dependency.gethe))
//
//        return super.onDependentViewChanged(parent, child, dependency);
//    }
//
//    public Object getDependentView() {
//        return dependentView;
//    }
//}
