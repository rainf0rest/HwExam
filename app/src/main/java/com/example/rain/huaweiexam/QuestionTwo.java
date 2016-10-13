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

/**
 * Created by rain on 2016/10/13.
 */
public class QuestionTwo extends Activity{

    private Button button, reset;
    private TextView answer;
    private EditText input;
    private int num;
    Handler workhandler;
    char ans[] = new char[100];
    private int ex[] = new int[] {
            1,3,9,27,81
    };
    private int k = 0;

    class TwoThread extends Thread {
        public Handler myHandler;

        public void run() {
            Looper.prepare();
            myHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(msg.what == 0x156) {
                        int low = getlId(num);
                        if(num > getSum(low)) {
                            putNum(ex[low+1]);
                            compute(low, ex[low + 1] - num);
                        }
                        else {
                            putNum(ex[low]);
                            compute(low - 1, ex[low] - num);
                        }
                    }
                    Message smsg = new Message();
                    smsg.what = 0x178;
                    workhandler.sendMessage(smsg);
                }
            };
            Looper.loop();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2);

        button = (Button) findViewById(R.id.que2btn);
        answer = (TextView) findViewById(R.id.que2text);
        input = (EditText) findViewById(R.id.que2Edit);
        reset = (Button) findViewById(R.id.que2Reset);

        final TwoThread myThread = new TwoThread();
        myThread.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char temp[] = new char[100];
                ans = temp;
                num = Integer.parseInt(input.getText().toString());
                Message msg = new Message();
                msg.what = 0x156;
                myThread.myHandler.sendMessage(msg);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText(null);
                input.setText(null);
                char temp[] = new char[100];
                ans = temp;
            }
        });

        workhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x178) {
                    answer.setText(String.valueOf(ans));
                }
            }
        };

    }

    private int geth(int n) {
        for(int i = 0; i < 5; i++) {
            if(n < ex[i]) {
                return ex[i];
            }
        }
        return ex[5];
    }

    private int getl(int n) {
        for(int i = 4; i >=0; i++) {
            if(n >= ex[i]) {
                return ex[i];
            }
        }
        return ex[0];
    }

    private int gethId(int n) {
        for(int i = 0; i < 5; i++) {
            if(n < ex[i]) {
                return i;
            }
        }
        return 5;
    }

    private int getlId(int n) {
        for(int i = 4; i >=0; i--) {
            if(n >= ex[i]) {
                return i;
            }
        }
        return 0;
    }

    private int getSum(int i) {
        int sum = 0;
        while(i >= 0) {
            sum = sum + ex[i];
            i--;
        }
        return sum;
    }

    private int getOverId(int i) {
        int low = getlId(i);
        if(i > getSum(i)) {
            return low+1;
        }
        else
            return low;
    }

    private void putNum(int num) {
        switch (num) {
            case 1:
                ans[k] = '1';
                k++;
                break;
            case 3:
                ans[k] = '3';
                k++;
                break;
            case 9:
                ans[k] = '9';
                k++;
                break;
            case 27:
                ans[k] = '2';
                k++;
                ans[k] = '7';
                k++;
                break;
            case 81:
                ans[k] = '8';
                k++;
                ans[k] = '1';
                k++;
                break;
            default:
                break;
        }
    }

    private void compute(int i, int num) {
        if(num == 0) {

        }
        else if(num < 0){
            if(num + ex[i] - getSum(i-1) > 0 ) {
                compute(i-1, num);
            }else {
                ans[k] = '+';
                k++;
                putNum(ex[i]);
                int n = num + ex[i];
                compute(i-1, n);
            }

        }
        else {
            if(num - ex[i] + getSum(i-1) < 0) {
                compute(i-1, num);
            }
            else {
                ans[k] = '-';
                k++;
                putNum(ex[i]);
                int n = num - ex[i];
                compute(i - 1, n);
            }
        }
    }

}
