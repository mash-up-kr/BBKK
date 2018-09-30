package com.bbkk.android.bbkkclient.view.season;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.presenter.SeasonPresenter;
import com.bbkk.android.bbkkclient.view.main.MainActivity;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pm10.library.CircleIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class SeasonActivity extends AppCompatActivity implements SeasonContract.View {
  private int MAX_PAGE=4;
  @BindView(R.id.vp_season_layout)
  public ViewPager vpSeason;
  @BindView(R.id.tv_season_button)
  public TextView tvSeasonBtn;
  @BindView(R.id.tv_season_result)
  public TextView tvSeasonResult;
  @BindView(R.id.iv_type_char)
  public ImageView ivTypeChar;

  private SeasonContract.Presenter presenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_season);
    ButterKnife.bind(this);
    SharedPreferences userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    presenter = new SeasonPresenter(this, userData);

  }

  @Override
  public void initView() {
    vpSeason.setAdapter(new adapter(getSupportFragmentManager()));
    this.renderSeasonResult();
    this.submitAction();
  }

  private void renderSeasonResult() {
    vpSeason.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        renderLabel(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }

      private void renderLabel(int pos) {
        int currentNum = pos;
        String currentName = "";
        switch (currentNum) {
          case 0:
            currentName = "봄";
            break;
          case 1:
            currentName = "여름";
            break;
          case 2:
            currentName = "가을";
            break;
          case 3:
            currentName = "겨울";
            break;
          default:
        }
        renderChangeLabel(currentName);
      }


    });
  }

  private void renderChangeLabel(String currentName) {
    YoYo.with(Techniques.FadeOut)
      .duration(200)
      .withListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }
        @Override
        public void onAnimationEnd(Animator animation) {
          tvSeasonResult.setText(currentName);
          showName();
        }
        @Override
        public void onAnimationCancel(Animator animation) {
        }
        @Override
        public void onAnimationRepeat(Animator animation) {
        }
      })
      .playOn(tvSeasonResult);
  }

  private void showName() {
    YoYo.with(Techniques.FadeIn)
      .duration(200)
      .playOn(tvSeasonResult);
  }

  private void submitAction() {
    tvSeasonBtn.setOnClickListener((__) -> {
      presenter.requestGetSeason(vpSeason.getCurrentItem());
    });
  }

  @Override
  public void startMainActivity() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  @Override
  public void renderTypeIamge(int char_type) {
    Glide.with(getApplicationContext())
      .load(char_type)
      .into(ivTypeChar);
  }

  private class adapter extends FragmentPagerAdapter {

    public adapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("NUM", String.valueOf(position));
      CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
      circleIndicator.setupWithViewPager(vpSeason);
      return presenter.seasonFragmentGetItem(position,MAX_PAGE);
    }

    @Override
    public int getCount() {
      return MAX_PAGE;
    }
  }
}

