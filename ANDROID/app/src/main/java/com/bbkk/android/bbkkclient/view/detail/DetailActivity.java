package com.bbkk.android.bbkkclient.view.detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;
import com.bbkk.android.bbkkclient.presenter.DetailPresenter;
import com.bbkk.android.bbkkclient.view.review.ReviewActivity;
import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bbkk.android.bbkkclient.view.main.MainActivity.popularDataLists;

public class DetailActivity extends AppCompatActivity implements DetailContract.View,
  BaseSliderView.OnSliderClickListener,
  ViewPagerEx.OnPageChangeListener {
  private DetailContract.Presenter presenter;
  private Boolean hasLike = false;
  private int recyclerViewId;
  private int feedId;
  private String FEED_ID = "FEED_ID";
  private CardFeedsResponse.Result.PopularData data;

  @BindView(R.id.tv_back_button)
  public TextView tvBackBtn;
  @BindView(R.id.drawer_layout)
  public DrawerLayout drawer;
  @BindView(R.id.iv_menu_button)
  public ImageView btnOpenMenu;
  @BindView(R.id.nv_header_main)
  public NavigationView nvHeaderMain;
  private View headerView;
  private ImageView ivCloseMenu;
  @BindView(R.id.iv_like_button)
  public ImageView ivLikeBtn;
  @BindView(R.id.cl_review_button)
  public ConstraintLayout clReviewBtn;

  @BindView(R.id.tv_timeline_title)
  public TextView tvTitle;
  @BindView(R.id.tv_timeline_subtitle)
  public TextView tvSubTitle;
  @BindView(R.id.tv_timeline_local_content)
  public TextView tvLocalContent;
  @BindView(R.id.tv_content_name)
  public TextView tvName;
  @BindView(R.id.tv_content_content)
  public TextView tvContent;
  @BindView(R.id.tv_review_counter)
  public TextView tvReviewCounter;
  @BindView(R.id.sl_timeline_images)
  public SliderLayout slTimelineImages;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    this.presenter = new DetailPresenter(this);
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
    this.recyclerViewId = this.getIntent().getIntExtra("FEED_ID", 1);
    this.feedId = (popularDataLists.size() - recyclerViewId);
    this.data = popularDataLists.get(this.feedId);
    headerView = nvHeaderMain.getHeaderView(0);
    ivCloseMenu = headerView.findViewById(R.id.iv_close_button);
    this.drawerManager();
    this.detailManager();
    this.reviewBtnListener();
    this.renderContentView(this.data);
  }

  private void renderContentView(CardFeedsResponse.Result.PopularData data) {
    CardFeedsResponse.Result.PopularData currentData = data;
    String[] feedImages = data.imageUrl.split(",");
    tvTitle.setText(currentData.title);
    tvLocalContent.setText(currentData.localContent);
    tvName.setText(currentData.traveler.nickName);
    tvContent.setText(currentData.subtitle);
//    tvReviewCounter.setText(currentData.honeyCount+"");

    RequestOptions requestOptions = new RequestOptions();
    requestOptions.centerCrop();

    for(int index = 0; index < feedImages.length; index++) {
      TextSliderView sliderView = new TextSliderView(this);
      sliderView.image(feedImages[index])
        .setRequestOption(requestOptions)
        .setBackgroundColor(Color.WHITE)
        .setProgressBarVisible(true)
        .setOnSliderClickListener(this);

      slTimelineImages.addSlider(sliderView);
    }

    slTimelineImages.setPresetTransformer(SliderLayout.Transformer.Default);
    slTimelineImages.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    slTimelineImages.setDuration(4000);
    slTimelineImages.addOnPageChangeListener(this);
  }

  private void reviewBtnListener() {
    clReviewBtn.setOnClickListener((__) -> {
      Intent intent = new Intent(this, ReviewActivity.class);
      intent.putExtra(FEED_ID, feedId);
      startActivity(intent);
    });
  }

  private void detailManager() {
    this.renderLike(hasLike);
    this.likeListener();
  }

  private void renderLike(Boolean hasLike) {
    Boolean currentHasLike = hasLike;
    if (currentHasLike) {
      ivLikeBtn.setBackgroundResource(R.color.red1);
    } else {
      ivLikeBtn.setBackgroundResource(R.color.gray1);
    }
    this.hasLike = !currentHasLike;
//    TODO: hasLike 값을 서버에 전송한다.
  }

  private void likeListener() {
    ivLikeBtn.setOnClickListener((__) -> {
      renderLike(hasLike);
    });
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

  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {

  }

  @Override
  public void onPageScrolled(int i, float v, int i1) {

  }

  @Override
  public void onPageSelected(int i) {

  }

  @Override
  public void onPageScrollStateChanged(int i) {

  }
}
