package com.wpl.worklog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpl.worklog.R;

import java.util.List;

/**
 * Created by valentine on 2017/4/28.
 */

public class InnerListAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;
    private LayoutInflater inflater;

    public InnerListAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_addressee_list, parent, false);
        } else {
            view = convertView;
        }
        bindView(position, view);
        return view;
    }

    private void bindView(int position, View view) {
        String s = list.get(position);
        TextView textView = (TextView) view.findViewById(R.id.addresseeList_tv);
        textView.setText(s);
    }
}
