package com.sc.android.chestwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.sc.android.chestwork.db.CloseBean;
import com.sc.android.chestwork.db.MyApplication;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    private List<CloseBean> list;
    private TextView tv_delete_id;
    private TextView tv_delete_kind;
    private TextView tv_delete_season;
    private EditText et_delete_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        Intent intent = getIntent();//获取传来的intent对象
        String id = intent.getStringExtra("id");//获取键值对的键名
        String kind = intent.getStringExtra("kind");
        String season = intent.getStringExtra("season");
        String num = intent.getStringExtra("num");
       // Toast.makeText(this, position, Toast.LENGTH_SHORT).show();


        tv_delete_id = findViewById(R.id.tv_delete_id);
        tv_delete_kind = findViewById(R.id.tv_delete_kind);
        tv_delete_season = findViewById(R.id.tv_delete_season);
        et_delete_num = findViewById(R.id.et_delete_num);

        list = MyApplication.getDaoSession().getCloseBeanDao().loadAll();

        tv_delete_id.setText(id);
        tv_delete_kind.setText(kind);
        tv_delete_season.setText(season);
        et_delete_num.setText(num);

    }
}
