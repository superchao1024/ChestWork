package com.sc.android.chestwork;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.sc.android.chestwork.db.CloseBean;
import com.sc.android.chestwork.db.MyApplication;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 添加衣服界面
 */
//implements View.OnFocusChangeListener

public class ShowAddCloseActivity extends AppCompatActivity {

    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int CROP_PHOTO = 2;
    public static File tempFile;

    private Uri imageUri;
    private EditText et_show_id;
    private EditText et_show_kind;
    private EditText et_show_num;
    private EditText et_show_season;
    private ImageView iv_show_pic;


    /*
     * 判断sdcard是否被挂载
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_add_closectivity);
        //ivshow = findViewById(R.id.iv_show);
        openCamera(this);
        iv_show_pic = (ImageView) findViewById(R.id.iv_show_pic);
        et_show_id = (EditText) findViewById(R.id.et_show_id);
        et_show_kind = (EditText) findViewById(R.id.et_show_kind);
        et_show_num = (EditText) findViewById(R.id.et_show_num);
        et_show_season = (EditText) findViewById(R.id.et_show_season);

        // et_show_id.setOnFocusChangeListener(this);  //对edit 进行焦点监听

        et_show_kind.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showListPopulWindow2();
                }
            }
        });
        et_show_season.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if (b) {
                    showListPopulWindow3();
                }
            }
        });
        TastyToast.makeText(getApplicationContext(), "请使用横屏拍摄 !", TastyToast.LENGTH_LONG, TastyToast.LENGTH_LONG);
        //Toast.makeText(this, "sasasasasa", Toast.LENGTH_SHORT).show();
    }

    private void showListPopulWindow3() {
        final String[] list = {"春季", "夏季", "秋季", "冬季"};//要填充的数据
        final ListPopupWindow listPopupWindow;
        listPopupWindow = new ListPopupWindow(ShowAddCloseActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));//用android内置布局，或设计自己的样式
        listPopupWindow.setAnchorView(et_show_season);//以哪个控件为基准，在该处以logId为基准
        listPopupWindow.setModal(true);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置项点击监听
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                et_show_season.setText(list[i]);//把选择的选项内容展示在EditText上
                listPopupWindow.dismiss();//如果已经选择了，隐藏起来
            }
        });
        listPopupWindow.show();//把ListPopWindow展示出来
    }

    private void showListPopulWindow2() {
        final String[] list = {"衣服", "裤子", "袜子", "帽子", "鞋子", "围巾"};//要填充的数据
        final ListPopupWindow listPopupWindow;
        listPopupWindow = new ListPopupWindow(ShowAddCloseActivity.this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));//用android内置布局，或设计自己的样式
        listPopupWindow.setAnchorView(et_show_kind);//以哪个控件为基准，在该处以logId为基准
        listPopupWindow.setModal(true);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置项点击监听
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                et_show_kind.setText(list[i]);//把选择的选项内容展示在EditText上
                listPopupWindow.dismiss();//如果已经选择了，隐藏起来
            }
        });
        listPopupWindow.show();//把ListPopWindow展示出来
    }


    public void addClose(View view) {
        addToDb();
    }

    /**
     * 添加进数据库
     */
    private void addToDb() {

        String id = et_show_id.getText().toString().trim();
        String kind = et_show_kind.getText().toString().trim();
        String num = et_show_num.getText().toString().trim();
        String season = et_show_season.getText().toString().trim();
        String imagePath = tempFile.toString();
        // imagePath = tempFile.toString();
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(kind) || TextUtils.isEmpty(season)) {
            Toast.makeText(this, "数据不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //保存数据到数据库
            MyApplication.getDaoSession().getCloseBeanDao().insert(new CloseBean(id, kind, num, season, imagePath));
            TastyToast.makeText(getApplicationContext(), "数据保存成功 !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            return;
            //
            //            CloseBean info = new CloseBean();
            //            info.setCloseId(id);
            //            info.setKind(kind);
            //            info.setColo
            // r(color);
            //            info.setSeason(season);
            //            boolean result = dao.add(info);
            //            if (result) {
            //                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            //
            //            } else {
            //                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            //            }
            //        }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_CAREMA:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    //裁剪框比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    //图片输出大小
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                                .openInputStream(imageUri));
                        iv_show_pic.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * 打开相机类
     *
     * @param activity
     */
    public void openCamera(Activity activity) {
        //获取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());


                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }


                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    //    @Override
    //    public void onFocusChange(View v, boolean hasFocus) {
    //        if (hasFocus) {
    //            showListPopulWindow(); //调用显示PopuWindow 函数
    //        }
    //    }
    //
    //    private void showListPopulWindow() {
    //        final String[] list = {"1", "2", "3","4","5","6","7","8","9","0"};//要填充的数据
    //        final ListPopupWindow listPopupWindow;
    //        listPopupWindow = new ListPopupWindow(this);
    //        listPopupWindow.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list));//用android内置布局，或设计自己的样式
    //        listPopupWindow.setAnchorView(et_show_id);//以哪个控件为基准，在该处以mEditText为基准
    //        listPopupWindow.setModal(true);
    //
    //        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {//设置项点击监听
    //            @Override
    //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    //                et_show_id.setText(list[i]);//把选择的选项内容展示在EditText上
    //                listPopupWindow.dismiss();//如果已经选择了，隐藏起来
    //            }
    //        });
    //        listPopupWindow.show();//把ListPopWindow展示出来
    //    }
}
