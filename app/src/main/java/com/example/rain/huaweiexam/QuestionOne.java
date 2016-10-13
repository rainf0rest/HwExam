package com.example.rain.huaweiexam;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rain on 2016/10/13.
 */
public class QuestionOne extends Activity {

    private Button button;
    private TextView answer;
    private EditText input;
    private Handler workhandler;
    char ans[] = new char[1000];
    String outputString = null;
    String inputString;

    class OneThread extends Thread {
        public Handler myHandler;

        public void run() {
            Looper.prepare();
            myHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    boolean flag = false;
                    int k = 0;
                    if (msg.what == 0x112) {
                        char temp[] = inputString.toCharArray();
                        for(int i = 0; i < temp.length; i++) {
                            for(int j = 0; j < k; j++) {
                                if(ans[j] == temp[i]) {
                                    flag = true;
                                }
                            }
                            if(flag == false) {
                                ans[k] = temp[i];
                                k++;
                            }
                            flag = false;
                        }
                        Message smsg = new Message();
                        smsg.what = 0x111;
                        workhandler.sendMessage(smsg);
                    }
                }
            };
            Looper.loop();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        button = (Button) findViewById(R.id.que1btn);
        answer = (TextView) findViewById(R.id.que1text);
        input = (EditText) findViewById(R.id.que1Edit);

        final OneThread myThread = new OneThread();
        myThread.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString = input.getText().toString();
                Message msg = new Message();
                msg.what = 0x112;
                myThread.myHandler.sendMessage(msg);
            }
        });

        workhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x111) {
                    answer.setText(String.valueOf(ans));
                }
            }
        };

    }

}
