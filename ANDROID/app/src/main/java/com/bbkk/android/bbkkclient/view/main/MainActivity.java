package com.bbkk.android.bbkkclient.view.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.model.Timeline;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;
import com.bbkk.android.bbkkclient.presenter.MainPresenter;
import com.bbkk.android.bbkkclient.adapter.TimeLineAdapter;
import com.bbkk.android.bbkkclient.view.detail.DetailActivity;
import com.bbkk.android.bbkkclient.view.season.SeasonActivity;
import com.bbkk.android.bbkkclient.view.tendency.TendencyActivity;
import com.bbkk.android.bbkkclient.view.write.WriteActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbkk.android.bbkkclient.presenter.NamePresenter.USER_NAME;
import static com.bbkk.android.bbkkclient.presenter.SeasonPresenter.USER_SEASON;
import static com.bbkk.android.bbkkclient.presenter.TendencyPresenter.USER_TYPE;
import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class MainActivity extends AppCompatActivity implements MainContract.View {
  private MainContract.Presenter presenter;

  @BindView(R.id.tv_back_button)
  public TextView tvBackBtn;
  @BindView(R.id.tv_main_counter)
  public TextView tvMainCounter;
  @BindView(R.id.drawer_layout)
  public DrawerLayout drawer;
  @BindView(R.id.iv_menu_button)
  public ImageView btnOpenMenu;
  @BindView(R.id.nv_header_main)
  public NavigationView nvHeaderMain;
  @BindView(R.id.btn_start_write)
  public FloatingActionButton btnWrite;
  @BindView(R.id.rv_timeline)
  public RecyclerView rvTimeLineLayout;
  @BindView(R.id.tv_main_title)
  public TextView tvMainTitle;
  private RecyclerView.Adapter timeLineAdapter;
  private View headerView;
  private ImageView ivCloseMenu;
  private TextView tvHeaderName;
  private TextView tvHeaderType;
  private ImageView ivHeaderImage;
  private ImageView ivHeaderChar;
  private SharedPreferences userData;
  public static ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    ButterKnife.bind(this);
    this.presenter = new MainPresenter(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.requestContentList();
  }

  @Override
  public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public void initView() {
    headerView = nvHeaderMain.getHeaderView(0);
    ivCloseMenu = headerView.findViewById(R.id.iv_close_button);
    tvHeaderName = headerView.findViewById(R.id.tv_header_name);
    tvHeaderType = headerView.findViewById(R.id.tv_header_tendency);
    ivHeaderImage = headerView.findViewById(R.id.iv_header_image);
    ivHeaderChar = headerView.findViewById(R.id.iv_header_char);
    tvBackBtn.setVisibility(View.GONE);
    this.drawerManager();
    this.writeListener();
    tvMainTitle.setText("서울의 " + userData.getString(USER_SEASON, "봄"));
  }

  @Override
  public void renderTimeLine(ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists) {
    this.popularDataLists = popularDataLists;
    rvTimeLineLayout.setLayoutManager(new LinearLayoutManager(this));
    timeLineAdapter = new TimeLineAdapter(this.popularDataLists, this::handleClickTimelineEntry);
    rvTimeLineLayout.setAdapter(timeLineAdapter);
  }

  @Override
  public void renderMainCounter(int size) {
    tvMainCounter.setText(size + "개");
  }

  private void writeListener() {
    btnWrite.setOnClickListener((__) -> {
      startWriteActivity();
    });
  }

  private void startWriteActivity() {
      startActivity(new Intent(this, WriteActivity.class));
  }

  private void drawerManager() {
    this.openHeaderMenu();
    this.closeHeaderMenu();
    this.renderName();
    this.renderType();
    this.renderSeason();
  }

  private void renderSeason() {
    int currentBackgorund = R.drawable.bg_spring;
    String currentSeason = userData.getString(USER_SEASON, "봄");
    switch (currentSeason) {
      case "봄":
        currentBackgorund = R.drawable.bg_spring;
        break;
      case "여름":
        currentBackgorund = R.drawable.bg_summer;
        break;
      case "가을":
        currentBackgorund = R.drawable.bg_autumn;
        break;
      case "겨울":
        currentBackgorund = R.drawable.bg_winter;
        break;
      default:
    }
    Glide.with(getApplicationContext())
      .load(currentBackgorund)
      .into(ivHeaderImage);
  }

  private void renderType() {
    String currentType = userData.getString(USER_TYPE, "");
    tvHeaderType.setText(currentType);

    switch (currentType) {
      case "식도락형":
        this.renderTypeIamge(R.drawable.char_food);
        break;
      case "예술가형":
        this.renderTypeIamge(R.drawable.char_artist);
        break;
      case "관광객형":
        this.renderTypeIamge(R.drawable.char_travel);
        break;
      case "탐험가형":
        this.renderTypeIamge(R.drawable.char_adventure);
        break;
      case "알뜰형":
        this.renderTypeIamge(R.drawable.char_miser);
        break;
      default:
    }
  }

  private void renderTypeIamge(int type) {
    Glide.with(getApplicationContext())
      .load(type)
      .into(ivHeaderChar);
  }

  private void renderName() {
    String currentName = userData.getString(USER_NAME, "");
    tvHeaderName.setText(currentName);
  }

  private void closeHeaderMenu() {
    ivCloseMenu.setOnClickListener((__) -> {
      drawer.closeDrawer(GravityCompat.START);
    });
  }

  private void openHeaderMenu() {
    btnOpenMenu.setOnClickListener((__) -> {
      drawer.openDrawer(GravityCompat.START);
    });
  }

  private void handleClickTimelineEntry(View view, CardFeedsResponse.Result.PopularData item) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra("FEED_ID", item.feedId);
    this.startActivity(intent);
  }

  public void startTendencyActivity(View view) {
    startActivity(new Intent(this, TendencyActivity.class));
  }
  public void startSeasonActivity(View view) {
    startActivity(new Intent(this, SeasonActivity.class));
  }
  public void openBookmark(View view) {
//TODO:즐겨찾기 받아오기
  }
  public void openHighlight(View view) {
//TODO:하이라이트만 받아오기

  }
}
