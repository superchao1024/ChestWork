package com.sc.android.chestwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sc.android.chestwork.db.CloseBean;
import com.sc.android.chestwork.db.MyApplication;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

public class TakeActivity extends AppCompatActivity {
    private ListView lv_query;
    private List<CloseBean> list;
    private TextView closeid_query1;
    private TextView closekind_query1;
    private TextView closenum_query1;
    private TextView closeseason_query1;
    private ImageView iv_img1;
    private Button query_bt_delete1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take);


        lv_query = findViewById(R.id.lv_query);
        //获取查询的数据
        list = MyApplication.getDaoSession().getCloseBeanDao().loadAll();
      //  adapter = new MyAdapter();
        myAdapter = new MyAdapter();
        lv_query.setAdapter(myAdapter);


        //        iv_img.setOnListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Intent intent = new Intent(TakeActivity.this,DeleteActivity.class);
        //                startActivity(intent);
        //            }
        //        });
    }


    /**
     * ListView的Adapter
     */
    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            //return dao.getTotalCount();
            return list.size();
        }

        @Override
        public CloseBean getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(TakeActivity.this, R.layout.query_take, null);
            //获取实体对象
            iv_img1 = (ImageView) view.findViewById(R.id.iv_img1);
            closeid_query1 = (TextView) view.findViewById(R.id.closeid_query1);
            closekind_query1 = (TextView) view.findViewById(R.id.closekind_query1);
            closeseason_query1 = (TextView) view.findViewById(R.id.closeseason_query1);
            query_bt_delete1 = (Button) view.findViewById(R.id.query_delete1);
            //减
            closenum_query1 = (TextView) view.findViewById(R.id.closenum_query1);

            closeid_query1.setText(list.get(position).getId());
            closekind_query1.setText(list.get(position).getKind());
            closeseason_query1.setText(list.get(position).getSeason());
            closenum_query1.setText(list.get(position).getNum());


            query_bt_delete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getDaoSession().getCloseBeanDao().deleteByKey(list.get(position).getId());//根据接口删除服务器数据

                    List<CloseBean> list1 = MyApplication.getDaoSession().getCloseBeanDao().loadAll();

                    list.clear();
                    list.addAll(list1);
                    myAdapter.notifyDataSetChanged();//更新适配器
                    //lv_query.setAdapter(myAdapter);
                    TastyToast.makeText(getApplicationContext(), "删除成功 !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
            });


            //            closenum_delete.addTextChangedListener(null); //清除上个item的监听，防止oom
            //            closenum_delete.addTextChangedListener(new TextWatcher() {
            //                @Override
            //                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            //
            //                @Override
            //                public void onTextChanged(CharSequence s, int start, int before, int count) {
            //
            //                }
            //                @Override
            //                public void afterTextChanged(Editable s) {
            //                    String a = closenum_delete.getText().toString().trim();
            //                    list.get(position).setNum(a);
            //                    MyApplication.getDaoSession().getCloseBeanDao().update(list.get(position));
            //
            //
            //
            //
            //
            //                }
            //            });
            //  Toast.makeText(QueryActivity.this, list.get(position).getIamgePath(), Toast.LENGTH_SHORT).show();
            //            Uri urtqi = Uri.fromFile(new File(list.get(position).getIamgePath()+".jpg"));
            //            iv_img.setImageURI(uri);
            //String closenum_ = closenum_delete.getText().toString().trim();


            Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getIamgePath());
            iv_img1.setImageBitmap(bitmap);


            return view;
        }
    }


}

