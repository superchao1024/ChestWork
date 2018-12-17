package com.sc.android.chestwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 跳转页面
 */
public class AddCloseCameraActivity extends AppCompatActivity implements View.OnClickListener {

    private Button takePhoto;
    private Button connect_socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_close_camera);

        takePhoto = (Button) findViewById(R.id.take_photo);
        connect_socket = (Button) findViewById(R.id.connect_socket);
        takePhoto.setOnClickListener(this);
        connect_socket.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                // openCamera(this);

                Intent intent = new Intent(AddCloseCameraActivity.this, ShowAddCloseActivity.class);
                startActivity(intent);

                break;
            case R.id.connect_socket:

                Intent intent1 = new Intent(AddCloseCameraActivity.this, ConnectSocketActivity.class);
                startActivity(intent1);
        }
    }


}