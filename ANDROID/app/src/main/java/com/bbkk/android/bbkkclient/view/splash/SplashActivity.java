package com.bbkk.android.bbkkclient.view.splash;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.presenter.SplashPresenter;
import com.bbkk.android.bbkkclient.view.main.MainActivity;
import com.bbkk.android.bbkkclient.view.nameSetting.NameActivity;
import com.bbkk.android.bbkkclient.view.season.SeasonActivity;
import com.bbkk.android.bbkkclient.view.tendency.TendencyActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
  public static final String USER_DATA = "USER_DATA";
  SplashContract.Presenter presenter;
  @BindView(R.id.iv_splash_logo)
  ImageView ivSplashLogo;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    presenter = new SplashPresenter(this, userData);
    presenter.requestAction();
  }

  @Override
  public void renderView() {
    YoYo.with(Techniques.FadeOut)
      .delay(2500)
      .duration(500)
      .withListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }
        @Override
        public void onAnimationEnd(Animator animation) {
          presenter.activityManager();
        }
        @Override
        public void onAnimationCancel(Animator animation) {
        }
        @Override
        public void onAnimationRepeat(Animator animation) {
        }
      })
      .playOn(ivSplashLogo);
  }

  @Override
  public void startTendencyActivity() {
    startActivity(new Intent(this, TendencyActivity.class));
    finish();
  }

  @Override
  public void startNameActivity() {
    startActivity(new Intent(this, NameActivity.class));
    finish();
  }

  @Override
  public void startSeasonActivity() {
    startActivity(new Intent(this, SeasonActivity.class));
    finish();
  }

  @Override
  public void initView() {
      setContentView(R.layout.activity_splash);
      ButterKnife.bind(this);
  }
}
