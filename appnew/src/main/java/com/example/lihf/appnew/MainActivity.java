package com.example.lihf.appnew;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends Activity {

    private ListView act_listview;
    private ParentAdapter parentAdapter;
    private ArrayList<HashMap<String, Object>> parentList;
    private ArrayList<HashMap<String, Object>> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        act_listview = (ListView) findViewById(R.id.act_listview);
        initData();
    }

    private void initData() {
        parentList = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 100; i++) {
            childList = new ArrayList<HashMap<String, Object>>();
            for (int j = 0; j < 5; j++) {
                HashMap<String, Object> childMap = new HashMap<String, Object>();
                childMap.put("children_country", "北京市 country" + j);
                childList.add(childMap);
            }
            HashMap<String,Object> parentMap = new HashMap<String, Object>();
            parentMap.put("parent_title","省份名"+i);
            parentMap.put("parent_country_list",childList);
            parentList.add(parentMap);
        }
        parentAdapter = new ParentAdapter(MainActivity.this,parentList);
        act_listview.setAdapter(parentAdapter);
        setListViewHeightBasedOnChildren(act_listview);

    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


}
