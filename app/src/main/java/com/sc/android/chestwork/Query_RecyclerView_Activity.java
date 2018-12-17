package com.sc.android.chestwork;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Query_RecyclerView_Activity extends Activity {

    private TextView tv_recyclerView_query_id;
    private TextView tv_recyclerView_query_kind;
    private TextView tv_recyclerView_query_season;
    private TextView tv_recyclerView_query_num;
    private ImageView iv_recyclerView_query_imagePath;
    private String id;
    private String kind;
    private String season;
    private String num;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query__recycler_view_);

        tv_recyclerView_query_id = findViewById(R.id.tv_recyclerView_Query_id);
        tv_recyclerView_query_kind = findViewById(R.id.tv_recyclerView_Query_kind);
        tv_recyclerView_query_season = findViewById(R.id.tv_recyclerView_Query_season);
        tv_recyclerView_query_num = findViewById(R.id.tv_recyclerView_Query_num);
        iv_recyclerView_query_imagePath = findViewById(R.id.iv_recyclerView_Query_imagePath);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        kind = intent.getStringExtra("kind");
        season = intent.getStringExtra("season");
        num = intent.getStringExtra("num");
        imagePath = intent.getStringExtra("imagePath");

        tv_recyclerView_query_id.setText(id);
        tv_recyclerView_query_kind.setText(kind);
        tv_recyclerView_query_season.setText(season);
        tv_recyclerView_query_num.setText(num);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        iv_recyclerView_query_imagePath.setImageBitmap(bitmap);

    }

    public void back_recyclerview(View view) {
        finish();
    }
}
