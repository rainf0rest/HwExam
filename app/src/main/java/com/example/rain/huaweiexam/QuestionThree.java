package com.example.rain.huaweiexam;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rain on 2016/10/13.
 */
public class QuestionThree extends Activity {

    private Button proA, proB, proC, rma1, rma2, rma5, changebtn;
    private TextView proData;

    private Robot robot = new Robot();
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question3);

        proData = (TextView) findViewById(R.id.proInfo);

        changebtn = (Button) findViewById(R.id.changebtn);
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getchange();
                Message msg = new Message();
                msg.what = 0x256;
                myHandler.sendMessage(msg);
            }
        });

        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x256) {
                    getInfo();
                }
            }
        };
    }

    private void getInfo() {
        proData.setText("");
        proData.append("商品"+robot.getItems()[0].getName()+"  剩余:"+robot.getItems()[0].getNumber()+"\n");
        proData.append("商品"+robot.getItems()[1].getName()+"  剩余:"+robot.getItems()[1].getNumber()+"\n");
        proData.append("商品"+robot.getItems()[2].getName()+"  剩余:"+robot.getItems()[2].getNumber()+"\n");
        proData.append("货币"+robot.getRmbs()[0].getName()+"  剩余:"+robot.getRmbs()[0].getNumber()+"\n");
        proData.append("货币"+robot.getRmbs()[1].getName()+"  剩余:"+robot.getRmbs()[1].getNumber()+"\n");
        proData.append("货币"+robot.getRmbs()[2].getName()+"  剩余:"+robot.getRmbs()[2].getNumber());
    }

}
