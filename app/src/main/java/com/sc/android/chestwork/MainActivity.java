package com.sc.android.chestwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.sc.android.chestwork.Fragment.BaseFragment;
import com.sc.android.chestwork.Fragment.HomeFragment;
import com.sc.android.chestwork.Fragment.TypeFragment;
import com.sc.android.chestwork.Fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    /**
     * 装多个fragment的一个实例集合
     */
    private ArrayList<BaseFragment> fragments;

    private int position = 0;
    /**
     * 上次显示的fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity2_main);
        //ButtonKnife和当前Activity绑定
        ButterKnife.bind(this);
        /**
         * 初始化fragment
         */
        initFragment();
        //设置RadioGroup监听
        initListener();


    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:   //主页
                        position = 0;
                        break;
                    case R.id.rb_type://天气
                        position = 1;
                        break;
                    case R.id.rb_community:  //用户信息
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置去取不同的fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数：上次显示的fragment
                 * 第二个参数：当前正要显示的Fragment
                 */
                switchFragment(tempFragment, baseFragment);


            }
        });
        rgMain.check(R.id.rb_home);


    }

    /**
     * 添加的时候要按照顺序
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换fragment
     *
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {                 // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加fragment
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


}
