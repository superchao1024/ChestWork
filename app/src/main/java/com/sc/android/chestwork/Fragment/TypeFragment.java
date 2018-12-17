package com.sc.android.chestwork.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sc.android.chestwork.QueryActivity;
import com.sc.android.chestwork.Query_pic_Activity;
import com.sc.android.chestwork.R;

/**
 * 分类的fragment
 */

public class TypeFragment extends BaseFragment {

    private TextView showClose1;
    private TextView showclose2;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        showClose1 = view.findViewById(R.id.tv_type_close1);
        showclose2 = view.findViewById(R.id.tv_type_close2);
        // 设置点击事件
        initListener();
        return view;
    }

    /**
     * 设置点击事件
     */
    private void initListener() {
        // 添加衣物的监听
        showClose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QueryActivity.class);
                startActivity(intent);
            }
        });
        showclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Query_pic_Activity.class);
                startActivity(intent);
            }
        });

    }

}
