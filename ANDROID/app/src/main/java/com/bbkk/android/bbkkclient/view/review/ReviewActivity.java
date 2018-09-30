package com.bbkk.android.bbkkclient.view.review;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.adapter.ReviewAdapter;
import com.bbkk.android.bbkkclient.model.ReviewModel;
import com.bbkk.android.bbkkclient.model.response.ReviewResponse;
import com.bbkk.android.bbkkclient.presenter.ReviewPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity implements ReviewContract.View {
  private ReviewContract.Presenter presenter;

  @BindView(R.id.fb_write_review)
  public FloatingActionButton fbWriteReview;
  @BindView(R.id.tv_back_button)
  public TextView tvBackBtn;
//  @BindView(R.id.tv_review_delete)
//  public TextView tvDeleteBtn;
//  @BindView(R.id.tv_review_best_logo)
//  public TextView tvBestLogo;
  @BindView(R.id.drawer_layout)
  public DrawerLayout drawer;
  @BindView(R.id.iv_menu_button)
  public ImageView btnOpenMenu;
  @BindView(R.id.nv_header_main)
  public NavigationView nvHeaderMain;
  private View headerView;
  private ImageView ivCloseMenu;
  @BindView(R.id.rv_review)
  public RecyclerView rvReview;
  private RecyclerView.Adapter reviewAdapter;
  private int feedId;
  public String FEED_ID = "FEED_ID";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_review);
    ButterKnife.bind(this);
    presenter = new ReviewPresenter(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.requestGetReview(feedId);
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
    feedId = this.getIntent().getIntExtra("FEED_ID", 1);
    headerView = nvHeaderMain.getHeaderView(0);
    ivCloseMenu = headerView.findViewById(R.id.iv_close_button);
    this.drawerManager();
    this.writeReview();
  }

  @Override
  public void renderReview(ArrayList<ReviewResponse.Result.Comment> reviews) {
    ArrayList<ReviewResponse.Result.Comment> currentReviews = reviews;
    rvReview.setLayoutManager(new LinearLayoutManager(this));
    reviewAdapter = new ReviewAdapter(currentReviews);
    rvReview.setAdapter(reviewAdapter);
  }

  private void drawerManager() {
    this.closeActivity();
    this.openHeaderMenu();
    this.closeHeaderMenu();
  }

  private void closeActivity() {
    tvBackBtn.setOnClickListener((__) -> {
      finish();
    });
  }
  private void writeReview() {
    fbWriteReview.setOnClickListener((__) -> {
      Intent intent = new Intent(this, WriteReviewActivity.class);
      intent.putExtra(FEED_ID, feedId);
      startActivity(intent);
    });
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
}
