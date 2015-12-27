package com.example.lihf.listviewtestnestmei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class QianTaoListviewActivity extends Activity {
	private ListView listView;
	private ArrayList<HashMap<String, Object>> parentList, childList;
	private ParentAdapter parentAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qiantao);
		init();
	}

	private void init() {
		listView = (ListView) findViewById(R.id.qiantao_lv);
		getList();
	}

	private void getList() {

		parentList = new ArrayList<HashMap<String, Object>>();
		for (int i = 1; i < 10; i++) {
			childList = new ArrayList<HashMap<String, Object>>();
			for (int j = i < 7 ? i : 7; j < 9; j++) {
				HashMap<String, Object> ParentMap = new HashMap<String, Object>();
				ParentMap.put("parent_title", "children" + i);
				ParentMap.put("parent_address", "8." + i);
				ParentMap.put("parent_distance", "1" + i);
				ParentMap.put("parent_distance1", "距离123" + i);
				childList.add(ParentMap);
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("child_item_title", "parent" + i);

			map.put("child_item_price_old", "4." + i + "颗星");
			map.put("child_item_price_score", "(100人评价)");
			map.put("parent_lv", childList);
			parentList.add(map);
		}

		parentAdapter = new ParentAdapter(parentList,
				QianTaoListviewActivity.this);
		listView.setAdapter(parentAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Intent intent = new Intent(QianTaoListviewActivity.this,
						Activity_1.class);
				Toast.makeText(QianTaoListviewActivity.this,
						"进入" + arg2 + "listview", Toast.LENGTH_LONG).show();
				startActivity(intent);

			}
		});
	}

}
