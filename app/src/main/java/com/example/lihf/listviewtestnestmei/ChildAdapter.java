package com.example.lihf.listviewtestnestmei;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ChildAdapter extends BaseAdapter {
	private ArrayList<HashMap<String, Object>> list;
	private Context context;
	private LayoutInflater inflater;

	public ChildAdapter(Context context) {
		super();

		this.context = context;
		
	}
	public void addAll(ArrayList<HashMap<String, Object>> list) {
		this.list=list;
		notifyDataSetChanged();
	}

	public void clearAll() {
		this.list.clear();
		notifyDataSetChanged();
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
		ParentListItem parentListItem = null;
		if (convertView == null) {
			parentListItem = new ParentListItem();
		    inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.childitem, null, false);
			parentListItem.tv_1 = (TextView) convertView
					.findViewById(R.id.tv_1);
			parentListItem.tv_2 = (TextView) convertView
					.findViewById(R.id.tv_2);
			parentListItem.tv_3 = (TextView) convertView
					.findViewById(R.id.tv_3);
			parentListItem.tv_4 = (TextView) convertView
					.findViewById(R.id.tv_4);
			convertView.setTag(parentListItem);
		} else {
			parentListItem = (ParentListItem) convertView.getTag();
		}
		parentListItem.tv_1.setText((CharSequence) list.get(position).get(
				"parent_address"));
		parentListItem.tv_2.setText((CharSequence) list.get(position).get(
				"parent_title"));
		parentListItem.tv_3.setText((CharSequence) list.get(position).get(
				"parent_distance"));
		parentListItem.tv_3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
		parentListItem.tv_4.setText((CharSequence) list.get(position).get("parent_distance1"));
		
		return convertView;
	}
	
		public class ParentListItem {
		TextView tv_1, tv_2, tv_3, tv_4;
	}

}
