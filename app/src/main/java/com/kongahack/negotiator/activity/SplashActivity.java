package com.kongahack.negotiator.activity;

import android.content.Intent;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.kongahack.negotiator.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

/**
 * Created by nezspencer on 10/29/16.
 */

public class SplashActivity extends AwesomeSplash{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashActivity.this, ProductActivity.class));
        SplashActivity.this.finish();


    }

    @Override
    public void initSplash(ConfigSplash configSplash) {

        overridePendingTransition(
                R.anim.activity_open_translate, R.anim.activity_close_scale);

        //Customize Circular Reveal
        configSplash.setBackgroundColor(android.R.color.white); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(850); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_TOP); //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.smack_icon);
        configSplash.setAnimLogoSplashDuration(700); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

        configSplash.setTitleSplash("Mobile");
        configSplash.setTitleTextColor(android.R.color.white);
        configSplash.setTitleTextSize(40f); //float value
        configSplash.setAnimTitleDuration(600);
        configSplash.setAnimTitleTechnique(Techniques.Flash);




    }

    @Override
    protected void onPause() {
        super.onPause();
        //closing transition animations
        overridePendingTransition(
                R.anim.activity_open_scale,
                R.anim.activity_close_translate);
    }
}
