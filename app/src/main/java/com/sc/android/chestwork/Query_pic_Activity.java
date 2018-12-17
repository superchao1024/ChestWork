package com.sc.android.chestwork;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sc.android.chestwork.Adapter.MyRecyclerViewAdapter;
import com.sc.android.chestwork.db.CloseBean;
import com.sc.android.chestwork.db.MyApplication;
import com.sc.android.chestwork.utils.DividerGridItemDecoration;

import java.util.List;

public class Query_pic_Activity extends Activity {

    private RecyclerView recyclerView;
    private List<CloseBean> list;
    private MyRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_pic_);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));


        //准备数据集合
        list = MyApplication.getDaoSession().getCloseBeanDao().loadAll();
        adapter = new MyRecyclerViewAdapter(Query_pic_Activity.this, list);
        recyclerView.setAdapter(adapter);

        //LayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(Query_pic_Activity.this, 3, GridLayoutManager.VERTICAL, false));
    }

      public void back_recyclerview(View view){
            finish();
           }
}
