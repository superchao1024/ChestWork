package com.sc.android.chestwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sc.android.chestwork.Query_RecyclerView_Activity;
import com.sc.android.chestwork.R;
import com.sc.android.chestwork.db.CloseBean;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private List<CloseBean> list;

    public MyRecyclerViewAdapter(Context context, List<CloseBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull


    /**
     * 相当于getView方法
     */
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itenView = View.inflate(context, R.layout.recyclerview_, null);
        return new MyViewHolder(itenView);
    }

    /**
     * 相当于getView绑定数据部分的代码
     * 数据和View绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //根据位置得到对应的数据

        Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getIamgePath());
        holder.imageView01.setImageBitmap(bitmap);
        holder.imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Query_RecyclerView_Activity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("kind",list.get(position).getKind());
                intent.putExtra("season",list.get(position).getSeason());
                intent.putExtra("num",list.get(position).getNum());
                intent.putExtra("imagePath",list.get(position).getIamgePath());
               context.startActivity(intent);

            }
        });

    }



    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView01;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView01 = (ImageView) itemView.findViewById(R.id.imageView01);
        }
    }


}
