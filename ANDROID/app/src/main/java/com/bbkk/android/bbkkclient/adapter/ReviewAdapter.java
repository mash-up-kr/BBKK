package com.bbkk.android.bbkkclient.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.model.ReviewModel;
import com.bbkk.android.bbkkclient.model.response.ReviewResponse;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
  private ArrayList<ReviewResponse.Result.Comment> reviews;

  public ReviewAdapter(ArrayList<ReviewResponse.Result.Comment> data) {
    this.reviews = data;
  }

  @NonNull
  @Override
  public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_rv_review, parent, false);
    return new ReviewViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
    ReviewResponse.Result.Comment review = reviews.get(position);
    holder.tvReviewName.setText(review.name);
    holder.tvReviewContent.setText(review.content);
    String[] date = review.date.split("T");
    holder.tvReviewDate.setText(date[0]);
  }

  @Override
  public int getItemCount() {
    return this.reviews.size();
  }

  public class ReviewViewHolder extends RecyclerView.ViewHolder {
    TextView tvReviewName;
    TextView tvLikeCount;
    TextView tvReviewBest;
    TextView tvReviewContent;
    TextView tvReviewDate;

    public ReviewViewHolder(View view) {
      super(view);
      tvReviewName = view.findViewById(R.id.tv_review_name);
      tvLikeCount = view.findViewById(R.id.tv_like_count);
      tvReviewBest = view.findViewById(R.id.tv_review_best_logo);
      tvReviewContent = view.findViewById(R.id.tv_review_content);
      tvReviewDate = view.findViewById(R.id.tv_review_date);
    }
  }
}
