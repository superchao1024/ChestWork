package com.sc.android.chestwork.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sc.android.chestwork.AddCloseCameraActivity;
import com.sc.android.chestwork.R;
import com.sc.android.chestwork.TakeActivity;

/**
 * 主页面的fragment
 */


public class HomeFragment extends BaseFragment {


    private ImageView addClothes;
    private ImageView takeClothes;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        // addCloses = (ImageView) view.findViewById(R.id.iv_home_add);
        addClothes = view.findViewById(R.id.iv_addClothes);
        takeClothes = view.findViewById(R.id.iv_takeClothes);

        //  control = (TextView) view.findViewById(R.id.iv_home_control);
        // 设置点击事件
        initListener();
        return view;


    }

    /**
     * 设置点击事件
     */
    private void initListener() {
        //添加衣物的监听
        addClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                getActivity().getSupportFragmentManager()
                //                        .beginTransaction()
                //                        .replace(R., new AddCloseCameraFragment(), null)
                //                        .addToBackStack(null)
                //                        .commit();
                Intent intent = new Intent(getActivity(), AddCloseCameraActivity.class);
                startActivity(intent);
            }
        });
        //        // 查询衣物的监听
        takeClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TakeActivity.class);
                startActivity(intent);
                Toast.makeText(mContext, "已经入衣物查询界面", Toast.LENGTH_SHORT).show();
            }
        });
        // 添加衣物的监听
        //        control.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                //                getActivity().getSupportFragmentManager()
        //                //                        .beginTransaction()
        //                //                        .replace(R., new AddCloseCameraFragment(), null)
        //                //                        .addToBackStack(null)
        //                //                        .commit();
        //                Intent intent = new Intent(getActivity(), ControlActivity.class);
        //                startActivity(intent);
        //            }
        //        });
    }


}
