package com.bbkk.android.bbkkclient.view.tendency;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.presenter.TendencyPresenter;
import com.bbkk.android.bbkkclient.view.main.MainActivity;
import com.bbkk.android.bbkkclient.view.season.SeasonActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pm10.library.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class TendencyActivity extends AppCompatActivity implements TendencyContract.View {

  private List<Drawable> poisition = new ArrayList<>();
  private int MAX_PAGE=5;
  private static int CHECK_START=0;
  TendencyContract.Presenter presenter;


  @BindView(R.id.vp_type_layout)
  public ViewPager vpTypeLayout;
  @BindView(R.id.tv_tendency_button)
  public TextView tvTypeBtn;
  @BindView(R.id.tv_type_label)
  public TextView tvTypeLabel;
  @BindView(R.id.tv_user_name)
  public TextView tvUserName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tendency);
    ButterKnife.bind(this);
    presenter();
  }

  private void presenter() {
    SharedPreferences userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    presenter = new TendencyPresenter(this, userData);
    tvTypeBtn.setOnClickListener((__) -> {
//      TODO: 서버에 보내고 난 다음에 이렇게 한다.
      presenter.requestPostType(vpTypeLayout.getCurrentItem());
//      startSeasonActivity();
    });
  }

  @Override
  public void startSeasonActivity() {
    startActivity(new Intent(this, SeasonActivity.class));
    finish();
  }
  @Override
  public void initView() {
    vpTypeLayout.setAdapter(new adapter(getSupportFragmentManager()));
    this.stateTypeLayout();
  }
  private void stateTypeLayout() {
    vpTypeLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }
      private void renderLabel(int pos) {
        int currentNum = pos;
        String currentName = "";
        switch (currentNum) {
          case 0:
            currentName = "식도락형";
            break;
          case 1:
            currentName = "예술가형";
            break;
          case 2:
            currentName = "관광객형";
            break;
          case 3:
            currentName = "탐험가형";
            break;
          case 4:
            currentName = "알뜰형";
            break;
          default:
        }
        renderChangeName(currentName);
      }

      @Override
      public void onPageSelected(int position) {
        this.renderLabel(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  private void renderChangeName(String currentName) {
    YoYo.with(Techniques.FadeOut)
      .duration(200)
      .withListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
        }
        @Override
        public void onAnimationEnd(Animator animation) {
          tvTypeLabel.setText(currentName);
          showName();
        }
        @Override
        public void onAnimationCancel(Animator animation) {
        }
        @Override
        public void onAnimationRepeat(Animator animation) {
        }
      })
      .playOn(tvTypeLabel);
  }

  private void showName() {
    YoYo.with(Techniques.FadeIn)
      .duration(200)
      .playOn(tvTypeLabel);
  }

  @Override
  public void renderName(String currentName) {
    tvUserName.setText(currentName);
  }

  private class adapter extends FragmentPagerAdapter {
    public adapter(FragmentManager fm) {
      super(fm);
    }
    @Override
    public Fragment getItem(int position) {
      CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
      circleIndicator.setupWithViewPager(vpTypeLayout);
      return presenter.tendencyFragmentGetItem(position,MAX_PAGE);
    }
    @Override
    public int getCount() {
      return MAX_PAGE;
    }
  }
}
