package com.example.rain.huaweiexam;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rain.huaweiexam.R;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private int[] imageRes= new int[] {
            R.id.start,
            R.id.no1,
            R.id.no2,
            R.id.no3
    };
    private List imageViewList = new ArrayList<ImageView>();
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        for(int i = 0; i < imageRes.length; i++) {
            ImageView imageView = (ImageView) findViewById(imageRes[i]);
            imageView.setOnClickListener(this);
            imageViewList.add(imageView);
        }

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.start:
                if(flag) {
                    startAnimation();
                    flag = false;
                }
                else {
                    stopAnimation();
                    flag = true;
                }
                break;
            case R.id.no1:
                //Toast.makeText(StartActivity.this, "no1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartActivity.this, QuestionOne.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(StartActivity.this, "!!!***", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void stopAnimation() {

        for(int i = 1; i < imageRes.length; i ++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 200*i, 0);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(i), "alpha", 1f, 0f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animator, animator1);
            animatorSet.setDuration(500);
            animatorSet.setInterpolator(new AnticipateInterpolator());

            animatorSet.start();
        }
    }

    private void startAnimation() {
        for(int i = 1; i < imageRes.length; i ++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i), "translationY", 0f, 150*i);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(i), "alpha", 0f, 1f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animator, animator1);
            animatorSet.setDuration(300);
            animatorSet.setStartDelay(i*100);
            animatorSet.setInterpolator(new AnticipateInterpolator());

            animatorSet.start();
        }

    }
}