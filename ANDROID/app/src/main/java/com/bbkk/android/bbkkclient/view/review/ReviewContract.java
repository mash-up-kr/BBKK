package com.bbkk.android.bbkkclient.view.review;

import com.bbkk.android.bbkkclient.model.ReviewModel;
import com.bbkk.android.bbkkclient.model.response.ReviewResponse;

import java.util.ArrayList;

public interface ReviewContract {
  interface View {
    void initView();
    void renderReview(ArrayList<ReviewResponse.Result.Comment> reviews);
  }
  interface Presenter {
    void deleteReview();
    void requestGetReview(int feedId);
  }
}
