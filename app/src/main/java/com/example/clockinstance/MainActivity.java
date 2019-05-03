package com.example.clockinstance;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button = null;
    TextView textView = null;
    EditText editText = null;
    String num = null;
    int num1 = 0;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1!=0){
                textView.setText("倒计时："+msg.arg1+"s");
            }else {
                textView.setText("完成");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        button.setOnClickListener(this);




    }

    public void init(){
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
    }



    public void getTime(){
        num = editText.getText().toString();
        num1 = Integer.parseInt(num);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                getTime();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=num1;i>=0;i--){
                            try{
                                Thread.sleep(1000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            message.arg1 = i;
                            message.obj = "倒计时：";
                            handler.sendMessage(message);
                        }


                    }
                }).start();
        }
    }
}
