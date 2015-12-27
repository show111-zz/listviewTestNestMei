package com.example.lihf.listviewtestnestmei;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentAdapter extends BaseAdapter implements ListAdapter {
	private ArrayList<HashMap<String, Object>> list;
	private Context context;
	private LayoutInflater inflater;

	public ParentAdapter(ArrayList<HashMap<String, Object>> list,
			Context context) {
		super();
		this.list = list;
		this.context = context;
		this.inflater = LayoutInflater.from(context);
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ChildListViewItem childListViewItem = null;
		if (convertView == null) {
			childListViewItem = new ChildListViewItem();
			convertView = inflater.inflate(R.layout.parentitem, parent, false);
			childListViewItem.child_item_img = (ImageView) convertView.findViewById(R.id.child_item_img);
			childListViewItem.child_item_title = (TextView) convertView.findViewById(R.id.child_item_title);
			childListViewItem.parent_lv = (ChildLiistView) convertView.findViewById(R.id.parent_lv);
			childListViewItem.child_item_price_old = (TextView) convertView.findViewById(R.id.child_item_price_old);
			childListViewItem.child_item_price_score = (TextView) convertView.findViewById(R.id.child_item_price_score);
			convertView.setTag(childListViewItem);
		} else {
			childListViewItem = (ChildListViewItem) convertView.getTag();
		}
		childListViewItem.child_item_title.setText((CharSequence) list.get(position).get("child_item_title"));
		childListViewItem.child_item_price_old.setText((CharSequence) list.get(position).get("child_item_price_old"));
		childListViewItem.child_item_price_score.setText((CharSequence) list.get(position).get("child_item_price_score"));
		final Button bt_jz = (Button) convertView.findViewById(R.id.bt_jz);

		final ChildAdapter daAdapter = new ChildAdapter(context);
		int z = ((ArrayList<HashMap<String, Object>>) list.get(position).get("parent_lv")).size();
		if (z <= 2) {
			bt_jz.setVisibility(View.GONE);
			daAdapter.addAll((ArrayList<HashMap<String, Object>>) list.get(position).get("parent_lv"));
			childListViewItem.parent_lv.setAdapter(daAdapter);
		} else {
			bt_jz.setVisibility(View.VISIBLE);
			bt_jz.setText("查看剩余" + (z - 2) + "的数据");
			ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < 2; i++) {
				list1.add(((ArrayList<HashMap<String, Object>>) list.get(position).get("parent_lv")).get(i));
			}
			daAdapter.addAll(list1);
			childListViewItem.parent_lv.setAdapter(daAdapter);

			// childListViewItem.parent_lv.setAdapter(new ParentAdapter(
			// (ArrayList<HashMap<String, Object>>) list.get(position)
			// .get("parent_lv"), context));

			bt_jz.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					bt_jz.setVisibility(View.GONE);
					daAdapter.addAll((ArrayList<HashMap<String, Object>>) list.get(position).get("parent_lv"));

				}
			});

		}
		childListViewItem.parent_lv
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Intent intent = new Intent(context, Activity_2.class);
						Toast.makeText(context, "进入" + (arg2+1) + "层listview",
								Toast.LENGTH_LONG).show();
						context.startActivity(intent);

					}
				});
		return convertView;
	}

	public class ChildListViewItem {
		TextView child_item_title, child_item_price_old,
				child_item_price_score;
		ImageView child_item_img;
		ChildLiistView parent_lv;
	}

}
