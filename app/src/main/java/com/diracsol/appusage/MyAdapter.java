package com.diracsol.appusage;

import android.app.usage.UsageStats;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nebuchadnezzar on 04/01/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<UsageStats> mDataset;
    private Context mContext;

    public MyAdapter(Context ctx){
        mContext = ctx;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view) {
            super(view);
            mTextView = view.findViewById(R.id.tv_usage_dtl);
        }
    }



    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.usage_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        String packageName = mDataset.get(position).getPackageName();
        String[] parts = packageName.split("\\.");
        Log.d(MyAdapter.class.getSimpleName(), packageName);
        Log.d(MyAdapter.class.getSimpleName(), Arrays.toString(parts));
        int len = parts.length;
        String last = len > 0 ? parts[len - 1] : packageName ;
        long totalTimeInForeground = mDataset.get(position).getTotalTimeInForeground();
        String txt = last + " - " + Long.toString(totalTimeInForeground) + "ms";
        holder.mTextView.setText(txt);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    void setDataset(List<UsageStats> stats){
        mDataset = stats;
        notifyDataSetChanged();
    }
}
