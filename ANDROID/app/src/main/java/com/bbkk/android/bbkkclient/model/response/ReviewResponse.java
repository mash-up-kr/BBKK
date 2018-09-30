package com.bbkk.android.bbkkclient.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewResponse {
  @SerializedName("code")
  public int code;
  @SerializedName("result")
  public Result result;

  public class Result {
    @SerializedName("comment")
    public ArrayList<Comment> comment;

    public class Comment {
      @SerializedName("id")
      public int id;
      @SerializedName("nickname")
      public String name;
      @SerializedName("content")
      public String content;
      @SerializedName("review_at")
      public String date;
    }
  }
}
