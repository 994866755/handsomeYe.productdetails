package com.example.kylinarm.productdetails.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kylinarm.productdetails.R;
import com.example.kylinarm.productdetails.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kylinARM on 2017/11/13.
 */

public class TwoFragment extends Fragment{

    @InjectView(R.id.rv_content)
    RecyclerView rvContent;

    private View contentView;
    private TestAdapter adapter;
    private List<String> datalist;

    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_two,null);
        initView();
        setData();
        return contentView;
    }

    private void initView(){
        ButterKnife.inject(this,contentView);
        rvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContent.setNestedScrollingEnabled(false);
    }

    private void setData(){
        datalist = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datalist.add("tab2-"+i);
        }
        adapter = new TestAdapter(getActivity(),datalist);
        rvContent.setAdapter(adapter);
    }

}
