package com.example.lihf.appnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lihf on 15/12/27.
 */
public class ChildrenAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<HashMap<String, Object>> childrenList;

    public ChildrenAdapter(Context context) {
        super();
        this.context = context;
    }

    public void addAll(ArrayList<HashMap<String, Object>> childrenList) {
        this.childrenList = childrenList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return childrenList.size();
    }

    @Override
    public Object getItem(int position) {
        return childrenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChildrenItemHolder holder;
        if (convertView == null) {
            holder = new ChildrenItemHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.children_layout, null, false);
            holder.children_country_tv = (TextView) convertView.findViewById(R.id.children_country_tv);
            convertView.setTag(holder);
        } else {
            holder = (ChildrenItemHolder) convertView.getTag();
        }

        holder.children_country_tv.setText((String) childrenList.get(position).get("children_country"));

        return convertView;
    }


    public class ChildrenItemHolder {
        TextView children_country_tv;
    }


}
