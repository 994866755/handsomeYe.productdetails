package com.example.kylinarm.productdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kylinARM on 2017/11/13.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestVH> {

    private Context context;
    private List<String> datalist;

    public TestAdapter(Context context, List<String> datalist){
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public TestVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestVH(LayoutInflater.from(context).inflate(R.layout.layout_test,parent,false));
    }

    @Override
    public void onBindViewHolder(TestVH holder, int position) {
        holder.setData(datalist.get(position));
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class TestVH extends RecyclerView.ViewHolder{

        @InjectView(R.id.tv_contengt)
        TextView tvContengt;

        public TestVH(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }

        public void setData(String data){
            tvContengt.setText(data);
        }

    }

}
