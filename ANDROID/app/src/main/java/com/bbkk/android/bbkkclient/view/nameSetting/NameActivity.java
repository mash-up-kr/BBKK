package com.bbkk.android.bbkkclient.view.nameSetting;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.presenter.NamePresenter;
import com.bbkk.android.bbkkclient.view.tendency.TendencyActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class NameActivity extends Activity implements NameContract.View {

  @BindView(R.id.tv_name_result)
  public TextView tvNameResult;

  @BindView(R.id.iv_change_button)
  public ImageView ivChangeName;

  @BindView(R.id.btn_name_submit)
  public Button btnNameSubmit;

  NameContract.Presenter presenter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    presenter = new NamePresenter(this, userData);
  }


  public void initView() {
    setContentView(R.layout.activity_name);
    ButterKnife.bind(this);
    this.renderView();
  }

  @Override
  public void startTendencyActivity() {
    startActivity(new Intent(this, TendencyActivity.class));
    finish();
  }

  @Override
  public void renderChangeName(String currentName) {
    YoYo.with(Techniques.FadeOut)
      .duration(250)
      .withListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }
        @Override
        public void onAnimationEnd(Animator animation) {
          tvNameResult.setText(currentName);
          showName();
        }
        @Override
        public void onAnimationCancel(Animator animation) {
        }
        @Override
        public void onAnimationRepeat(Animator animation) {
        }
      })
    .playOn(tvNameResult);
  }

  private void showName() {
    YoYo.with(Techniques.FadeIn)
      .duration(1300)
      .playOn(tvNameResult);
  }

  private void renderView() {
    this.changeNameListener();
    this.submitButtonListener();
  }

  public void changeNameListener() {
    ivChangeName.setOnClickListener((__) -> {
      presenter.changeNameAction();
    });
  }

  private void submitButtonListener() {
    btnNameSubmit.setOnClickListener((__) -> {
      presenter.submitAction();
    });
  }
}
