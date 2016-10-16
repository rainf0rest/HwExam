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
    private String sorry1 = "金额不足，请先投币";
    private String sorry2 = "商品已售罄，请选择其他商品";
    private int cuslave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question3);

        proData = (TextView) findViewById(R.id.proInfo);

        changebtn = (Button) findViewById(R.id.changebtn);
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getChange()) {
                    Message msg = new Message();
                    msg.what = 0x256;
                    myHandler.sendMessage(msg);
                }
                else {
                    proData.setText("零钱不够，找零失败");
                    proData.append("\n已投币：" + robot.getMoney());
                }
            }
        });

        proA = (Button) findViewById(R.id.proAbtn);
        proB = (Button) findViewById(R.id.proBbtn);
        proC = (Button) findViewById(R.id.proCbtn);

        proA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lave = (robot.getMoney() - robot.getItems()[0].getPrice());
                if( lave >= 0) {
                    if(robot.getItems()[0].getNumber() > 0) {
                        robot.setMoney(lave);
                        robot.getItems()[0].decNumber();
                        getInfo();
                    }
                    else {
                        proData.setText(sorry2);
                        proData.append("\n已投币：" + robot.getMoney());
                    }

                }
                else {
                    proData.setText(sorry1);
                    proData.append("\n已投币：" + robot.getMoney());
                }
            }
        });

        proB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lave = (robot.getMoney() - robot.getItems()[1].getPrice());
                if( lave >= 0) {
                    if(robot.getItems()[1].getNumber() > 0) {
                        robot.setMoney(lave);
                        robot.getItems()[1].decNumber();
                        getInfo();
                    }
                    else {
                        proData.setText(sorry2);
                        proData.append("\n已投币：" + robot.getMoney());
                    }

                }
                else {
                    proData.setText(sorry1);
                    proData.append("\n已投币：" + robot.getMoney());
                }
            }
        });

        proC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lave = (robot.getMoney() - robot.getItems()[2].getPrice());
                if( lave >= 0) {
                    if(robot.getItems()[2].getNumber() > 0) {
                        robot.setMoney(lave);
                        robot.getItems()[2].decNumber();
                        getInfo();
                    }
                    else {
                        proData.setText(sorry2);
                        proData.append("\n已投币：" + robot.getMoney());
                    }

                }
                else {
                    proData.setText(sorry1);
                    proData.append("\n已投币：" + robot.getMoney());
                }
            }
        });

        rma1 = (Button) findViewById(R.id.rmb1btn);
        rma2 = (Button) findViewById(R.id.rmb2btn);
        rma5 = (Button) findViewById(R.id.rmb5btn);

        rma1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robot.moneyVary(1);
                robot.getRmbs()[0].incNumber();
                getInfo();
            }
        });

        rma2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robot.moneyVary(2);
                robot.getRmbs()[1].incNumber();
                getInfo();
            }
        });

        rma5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robot.moneyVary(5);
                robot.getRmbs()[2].incNumber();
                getInfo();
            }
        });


        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x256) {
                    getInfo();
                    proData.append("\n退币成功！");
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
        proData.append("货币"+robot.getRmbs()[2].getName()+"  剩余:"+robot.getRmbs()[2].getNumber()+"\n");
        proData.append("已投币：" + robot.getMoney());
        //proData.append("退币成功，谢谢惠顾");
    }
    //贪心算法
    boolean getChange() {
        cuslave = robot.getMoney();
        while(cuslave >= 5 && robot.getRmbs()[2].getNumber() > 0) {
            cuslave = cuslave - 5;
            robot.getRmbs()[2].decNumber();
        }
        while(cuslave >= 2 && robot.getRmbs()[1].getNumber() > 0) {
            cuslave = cuslave - 2;
            robot.getRmbs()[1].decNumber();
        }
        while(cuslave >= 1 && robot.getRmbs()[1].getNumber() > 0) {
            cuslave = cuslave - 1;
            robot.getRmbs()[0].decNumber();
        }
        robot.setMoney(cuslave);
        if(cuslave == 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
