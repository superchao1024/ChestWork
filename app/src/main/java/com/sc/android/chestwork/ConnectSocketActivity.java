package com.sc.android.chestwork;

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

import com.sc.android.chestwork.a8266.TcpClientConnector;

public class ConnectSocketActivity extends AppCompatActivity implements View.OnClickListener {


    static TextView tv_receiveMessage;
    public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            switch (msg.what) {
                case 1:
                    tv_receiveMessage.setText(data);
            }
        }
    };
    static Context context = null;
    TcpClientConnector connector;

    EditText et_sendMessage;
    Button set_connect_socket;
    Button bt_sendMessage;
    Button bt_receiveMessage;
    Button bt_1;
    //   private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_socket);

        et_sendMessage = (EditText) findViewById(R.id.et_sendMessage);
        set_connect_socket = (Button) findViewById(R.id.set_connect_socket);
        bt_sendMessage = (Button) findViewById(R.id.bt_sendMessage);
        bt_receiveMessage = (Button) findViewById(R.id.bt_receiveMessage);
        tv_receiveMessage = (TextView) findViewById(R.id.tv_receiveMessage);
        //  bt1 = findViewById(R.id.bt_1);

        et_sendMessage.setOnClickListener(this);
        set_connect_socket.setOnClickListener(this);
        bt_sendMessage.setOnClickListener(this);
        bt_receiveMessage.setOnClickListener(this);
        tv_receiveMessage.setOnClickListener(this);
        //  bt1.setOnClickListener(this);
        context = this;

        connector = TcpClientConnector.getInstance();

        Toast.makeText(this, "先连接8266" + "\n" + "再按开始" + "\n", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String string = null;
        switch (view.getId()) {
            case R.id.set_connect_socket:
                set_connect_socket.setEnabled(false);
                bt_receiveMessage.setEnabled(true);
                bt_sendMessage.setEnabled(true);
                connector.creatConnect("192.168.4.1", 333);
                break;
            case R.id.bt_sendMessage:
                string = et_sendMessage.getText().toString();
                break;
            //            case R.id.bt_1:
            //                string = "1";
            //                break;

            //            case R.id.btn_led_off:
            //                string = "2";
            //                break;
            //            case R.id.btn_M_on:
            //                string = "3";
            //                break;
            //            case R.id.btn_M_off:
            //                string = "4";
            //                break;
            //            case R.id.btn_bef_on:
            //                string = "5";
            //                break;
            //            case R.id.btn_bef_off:
            //                string = "6";
            //                break;
            //            case R.id.btn_dz_on:
            //                string = "7";
            //                break;
            //            case R.id.btn_dz_off:
            //                string = "8";
            //                break;
            //            case R.id.bt_receiveMessage:
            //                string = "9";
            //                break;
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