package com.enyata.android.mvvm_java.ui.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.enyata.android.mvvm_java.R;

public class LoadingActivity extends AppCompatActivity {
    boolean isShowing = true;
    ImageView loadingBig,loadingSmall;
    ImageSwitcher imageSwitcher;
    Animation animation;
    int[]loadingImage = {R.drawable.ic_loading_big_icon,R.drawable.ic_loading_small_icon};
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
//        animation = AnimationUtils.loadAnimation(this,R.anim.l)
//       animation.setRepeatCount(Animation.INFINITE);


        loadingBig = findViewById(R.id.loadingBigIcon);
        animation = AnimationUtils.loadAnimation(this, R.anim.loading_animation);

        loadingBig.startAnimation(animation);

//        loadingBig.animate().translationY(-200F).setDuration(500);
//            loadingBig    .animate().scaleY(0.4F).setDuration(500);
//        loadingBig.animate().scaleX(0.4F).setDuration(500);
//        animation = AnimationUtils.loadAnimation(this,);
//        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.);
//        rotation.setRepeatCount(Animation.INFINITE);
//        myView.startAnimation(rotation);

//        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(loadingBig, "scaleX", 0.7f);
//        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(loadingBig, "scaleY", 0.7f);
//        scaleDownX.setDuration(1500);
//        scaleDownY.setDuration(1500);
//
//        ObjectAnimator moveUpY = ObjectAnimator.ofFloat(loadingBig, "translationY", -100);
//        moveUpY.setDuration(1500);
//
//        AnimatorSet scaleDown = new AnimatorSet();
//        AnimatorSet moveUp = new AnimatorSet();
//
//        scaleDown.play(scaleDownX).with(scaleDownY);
//        moveUp.play(moveUpY);
//
//        scaleDown.start();
//        moveUp.start();
//        loadingSmall = findViewById(R.id.loadingSmallIcon);
//        loading();
//        imageSwitcher = findViewById(R.id.image_switcher);
//        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
//            @Override
//            public View makeView() {
////                ImageView imageView = new ImageView(LoadingActivity.this);
////                imageView.setImageResource(loadingImage[position]);
//                return  new ImageView(LoadingActivity.this);
//            }
//        });
//
//        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.);
//        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
//
//        imageSwitcher.setInAnimation(in);
//        imageSwitcher.setOutAnimation(out);
//
//
//        imageSwitcher.postDelayed(new Runnable() {
//            int i = 0;
//            public void run() {
//                imageSwitcher.setImageResource(
//                        i++ % 2 == 0 ?
//                                R.drawable.ic_loading_big_icon :
//                                R.drawable.ic_loading_small_icon);
//                imageSwitcher.postDelayed(this, 500);
//            }
//        }, 500);
    }

    public void loading(){
        if (isShowing){
            loadingBig.animate().alpha(0).setDuration(200);
            loadingSmall.animate().alpha(1).setDuration(200);

        }else {
            loadingBig.animate().alpha(1).setDuration(200);
            loadingSmall.animate().alpha(0).setDuration(200);
        }


    }
}
