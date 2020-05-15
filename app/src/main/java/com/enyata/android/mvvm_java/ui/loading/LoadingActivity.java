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

        loadingBig = findViewById(R.id.loadingBigIcon);
        animation = AnimationUtils.loadAnimation(this, R.anim.loading_animation);

        loadingBig.startAnimation(animation);

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
