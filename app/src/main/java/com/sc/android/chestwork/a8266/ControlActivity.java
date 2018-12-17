package com.sc.android.chestwork.a8266;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.android.chestwork.R;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener {

    static TextView tv_temp;
    public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            switch (msg.what) {
                case 1:
                    tv_temp.setText("18B20当前温度：" + data + "摄氏度");
            }
        }
    };
    static Context context = null;
    TcpClientConnector connector;
    Button btn_begin;
    Button btn_send;
    Button btn_led_on;
    Button btn_led_off;
    Button btn_M_on;
    Button btn_M_off;
    Button btn_bef_on;
    Button btn_bef_off;
    Button btn_dz_on;
    Button btn_dz_off;
    Button btn_temp;
    EditText edit_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);

        btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_led_on = (Button) findViewById(R.id.btn_led_on);
        btn_led_off = (Button) findViewById(R.id.btn_led_off);
        btn_M_on = (Button) findViewById(R.id.btn_M_on);
        btn_M_off = (Button) findViewById(R.id.btn_M_off);
        btn_bef_on = (Button) findViewById(R.id.btn_bef_on);
        btn_bef_off = (Button) findViewById(R.id.btn_bef_off);
        btn_dz_on = (Button) findViewById(R.id.btn_dz_on);
        btn_dz_off = (Button) findViewById(R.id.btn_dz_off);
        btn_temp = (Button) findViewById(R.id.btn_temp);

        edit_text = (EditText) findViewById(R.id.edit_text);

        tv_temp = (TextView) findViewById(R.id.tv_temp);

        btn_begin.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_led_on.setOnClickListener(this);
        btn_led_off.setOnClickListener(this);
        btn_M_on.setOnClickListener(this);
        btn_M_off.setOnClickListener(this);
        btn_bef_on.setOnClickListener(this);
        btn_bef_off.setOnClickListener(this);
        btn_dz_on.setOnClickListener(this);
        btn_dz_off.setOnClickListener(this);
        btn_temp.setOnClickListener(this);
        context = this;

        connector = TcpClientConnector.getInstance();

        Toast.makeText(ControlActivity.context, "先连接8266" + "\n" + "再按开始" + "\n", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String string = null;
        switch (view.getId()) {
            case R.id.btn_begin:
                btn_begin.setEnabled(false);
                btn_send.setEnabled(true);
                btn_led_on.setEnabled(true);
                btn_led_off.setEnabled(true);
                btn_M_on.setEnabled(true);
                btn_M_off.setEnabled(true);
                btn_bef_on.setEnabled(true);
                btn_bef_off.setEnabled(true);
                btn_dz_on.setEnabled(true);
                btn_dz_off.setEnabled(true);
                btn_temp.setEnabled(true);
                edit_text.setEnabled(true);
                connector.creatConnect("192.168.4.1", 333);
                break;
            case R.id.btn_send:
                string = edit_text.getText().toString();
                break;
            case R.id.btn_led_on:
                string = "1";
                break;
            case R.id.btn_led_off:
                string = "2";
                break;
            case R.id.btn_M_on:
                string = "3";
                break;
            case R.id.btn_M_off:
                string = "4";
                break;
            case R.id.btn_bef_on:
                string = "5";
                break;
            case R.id.btn_bef_off:
                string = "6";
                break;
            case R.id.btn_dz_on:
                string = "7";
                break;
            case R.id.btn_dz_off:
                string = "8";
                break;
            case R.id.btn_temp:
                string = "9";
                break;
            default:
                break;
        }
        try {
            if (string != null) {
                connector.send(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}