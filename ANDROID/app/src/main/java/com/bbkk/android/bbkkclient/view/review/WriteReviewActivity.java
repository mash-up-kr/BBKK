package com.bbkk.android.bbkkclient.view.review;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.model.request.ReviewWriteRequest;
import com.bbkk.android.bbkkclient.model.response.ReviewWriteResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bbkk.android.bbkkclient.presenter.NamePresenter.USER_NAME;
import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class WriteReviewActivity extends AppCompatActivity {

  @BindView(R.id.tv_back_button)
  public TextView btnBack;
  @BindView(R.id.tv_review_write_check)
  public TextView btnWriteCheck;
  @BindView(R.id.tv_write_review_name)
  public TextView tvWriterName;
  @BindView(R.id.et_write_review)
  public EditText etWriteReview;
  SharedPreferences userData;
  private int feedId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write_review);
    ButterKnife.bind(this);
    feedId = this.getIntent().getIntExtra("FEED_ID", 1);
    closeActivity();
    this.actionWrite();
    this.userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    tvWriterName.setText(this.userData.getString(USER_NAME,"코딩하는 오징어"));
  }

  private void actionWrite() {
    btnWriteCheck.setOnClickListener((__) -> {
      String content = etWriteReview.getText().toString();
      if (! TextUtils.isEmpty(content)) {
        BbkkApi.getApi().postFeedReview(
          new ReviewWriteRequest(1, feedId, content)
        ).enqueue(new Callback<ReviewWriteResponse>() {
          @Override
          public void onResponse(Call<ReviewWriteResponse> call, Response<ReviewWriteResponse> response) {
            ReviewWriteResponse reviewWriteResponse = response.body();
            finish();
          }

          @Override
          public void onFailure(Call<ReviewWriteResponse> call, Throwable t) {

          }
        });
      } else {
        Toast.makeText(this, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show();
      }

    });
  }

  private void closeActivity() {
    btnBack.setOnClickListener((__) -> finish());
  }


}
