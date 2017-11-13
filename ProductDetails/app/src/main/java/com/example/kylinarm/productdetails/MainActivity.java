package com.example.kylinarm.productdetails;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kylinarm.productdetails.ui.OneFragment;
import com.example.kylinarm.productdetails.ui.TwoFragment;
import com.example.kylinarm.productdetails.widget.KylinScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kylinARM on 2017/11/13.
 */

public class MainActivity extends AppCompatActivity implements TabChangeLinstener{

    @InjectView(R.id.ll_scroll_content)
    LinearLayout upContent;
    @InjectView(R.id.tl_tab)
    TabLayout cTab;
    @InjectView(R.id.fl_scroll_content)
    FrameLayout frameContent;

    private KylinScrollView kylinScrollView;
    private TabLayout tTab;
    private View contentView;

    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private Fragment[] fragments = new Fragment[2];
    private String[] tags = new String[2];
    private String[] titles = new String[]{"One","Two"};
    private FragmentManager fragmentManager;
    private TabGroup tabGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initTab();
        initFragment();
        initScroll();
        initUpView();
        showFragment(0);
    }

    private void initView(){
        kylinScrollView = (KylinScrollView) findViewById(R.id.pull_scroll);
        tTab = (TabLayout) findViewById(R.id.top_tab);
        contentView = LayoutInflater.from(this).inflate(R.layout.layout_ks_content,null);
        kylinScrollView.addView(contentView);
        ButterKnife.inject(this,contentView);
    }

    private void initTab(){
        tabGroup = new TabGroup(this);
        tabGroup.addTabLayout(cTab);
        tabGroup.addTabLayout(tTab);
        tabGroup.addTitiles(titles);
        tabGroup.tabGroupListener();
        tabGroup.setTabChangeLinstener(this);
    }

    private void initFragment(){
        fragmentManager = getSupportFragmentManager();
        oneFragment = OneFragment.newInstance();
        twoFragment = TwoFragment.newInstance();
        fragments[0] = oneFragment;
        fragments[1] = twoFragment;
        tags[0] = "fragments0";
        tags[1] = "fragments1";
    }

    private void initScroll(){
        kylinScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
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
        kylinScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<NestedScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<NestedScrollView> refreshView) {
                Toast.makeText(MainActivity.this,"下拉刷新",Toast.LENGTH_SHORT).show();
                // todo 下拉的操作，恢复拉动的操作在刷新之后做
                if (kylinScrollView.isRefreshing()){
                    kylinScrollView.onRefreshComplete();
                }
            }
        });
    }

    private void initUpView(){
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
        textView.setBackgroundResource(R.color.colorAccent);
        textView.setGravity(Gravity.CENTER);
        textView.setText("测试");

        upContent.addView(textView);
    }

    /**
     *  展示fragment
     */
    public void showFragment(int position){
        for (int i = 0; i < fragments.length; i++) {
            if (i == position){
                if (fragmentManager.findFragmentByTag(tags[i]) == null){
                    fragmentManager.beginTransaction().add(R.id.fl_scroll_content, fragments[i], tags[i]).commit();
                }else {
                    fragmentManager.beginTransaction().attach(fragments[i]).commit();
                }
            }else {
                if (fragmentManager.findFragmentByTag(tags[i]) != null){
                    fragmentManager.beginTransaction().detach(fragments[i]).commit();
                }
            }
        }
    }

    @Override
    public void tabChange(int position) {
        showFragment(position);
    }

}
