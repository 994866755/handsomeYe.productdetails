### 一.文章地址

http://www.jianshu.com/p/2683a519b3cf

### 二.注意事项

##### 1.pullrefresh的模块是上下拉模块
##### 2.定义一个tabGroup来管理所有tab的联动
##### 3.demo中第一个fragment是普通情况，第二个fragment是RecyclerView布局的情况，使用RecyclerView布局记得加.setNestedScrollingEnabled(false)
##### 4.判断其它tab的显示和隐藏要监听滚动

```
kylinScrollView.getScrollView().setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= cTab.getTop()+contentView.getTop()){
                    tTab.setVisibility(View.VISIBLE);
                }else {
                    tTab.setVisibility(View.GONE);
                }
            }
        });
```

##### 5.FragmentManager的操作都是基础
##### 6.自定义的PullToRefreshBase可以按自己逻辑写，我代码中把布局分成两部分来添加是为了增强扩展性
##### 7.记得在父NestedScrollView中加入

```
android:fillViewport="true"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
```
