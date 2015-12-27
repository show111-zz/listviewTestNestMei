package com.example.lihf.appnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by lihf on 15/12/27.
 */
public class ParentAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<HashMap<String,Object>> parentList;

    public ParentAdapter(Context context, ArrayList<HashMap<String, Object>> parentList) {
        this.context = context;
        this.parentList = parentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return parentList.size();
    }

    @Override
    public Object getItem(int position) {
        return parentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ParentItemHolder holder;
        if(convertView == null){
            view = inflater.inflate(R.layout.parent_layout,parent,false);
            holder = new ParentItemHolder();
            holder.parent_city_tv = (TextView) view.findViewById(R.id.parent_city_tv);
            holder.parent_country_list = (CustomListview) view.findViewById(R.id.parent_country_list);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ParentItemHolder) view.getTag();
        }

        holder.parent_city_tv.setText((String)parentList.get(position).get("parent_title"));

        ChildrenAdapter childrenAdapter = new ChildrenAdapter(context);
        ArrayList<HashMap<String, Object>> listCountry = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 2; i++) {
            listCountry.add(((ArrayList<HashMap<String, Object>>) parentList.get(position).get("parent_country_list")).get(i));
        }
        childrenAdapter.addAll(listCountry);

        holder.parent_country_list.setAdapter(childrenAdapter);

        return view;
    }

    public class ParentItemHolder{
        TextView parent_city_tv;
        CustomListview parent_country_list;
    }

}
