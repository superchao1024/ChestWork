package com.sc.android.chestwork;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sc.android.chestwork.db.CloseBean;
import com.sc.android.chestwork.db.MyApplication;

import java.util.List;

public class QueryActivity extends Activity {
    private ListView lv_query;
    private List<CloseBean> list;
    private TextView closeid_query;
    private TextView closekind_query;
    private TextView closenum_query;
    private TextView closeseason_query;
    private Button query_bt_delete;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        lv_query = findViewById(R.id.lv_query);

        //获取查询的数据
        list = MyApplication.getDaoSession().getCloseBeanDao().loadAll();

        myAdapter = new MyAdapter();
        lv_query.setAdapter(myAdapter);

    }


    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            //return dao.getTotalCount();
            return list.size();
        }

        @Override
        public CloseBean getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(QueryActivity.this, R.layout.query_item, null);
            //获取实体对象
            ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
            closeid_query = (TextView) view.findViewById(R.id.closeid_query);
            closekind_query = (TextView) view.findViewById(R.id.closekind_query);
            closeseason_query = (TextView) view.findViewById(R.id.closeseason_query);
            closenum_query = (TextView) view.findViewById(R.id.closenum_query);
            //  query_bt_delete = (Button) view.findViewById(R.id.query_delete);

            closeid_query.setText(list.get(position).getId());
            closekind_query.setText(list.get(position).getKind());
            closeseason_query.setText(list.get(position).getSeason());
            closenum_query.setText(list.get(position).getNum());
            //  Toast.makeText(QueryActivity.this, list.get(position).getIamgePath(), Toast.LENGTH_SHORT).show();
            //            Uri uri = Uri.fromFile(new File(list.get(position).getIamgePath()+".jpg"));
            //            iv_img.setImageURI(uri);

            Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getIamgePath());
            iv_img.setImageBitmap(bitmap);


            //
            //            CloseBean info = new CloseBean();
            //            closeid_query.setText(info.getCloseId());
            return view;
        }
    }
}
