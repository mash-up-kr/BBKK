package com.bbkk.android.bbkkclient.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CardFeedsResponse {
  @SerializedName("code")
  public int code;
  @SerializedName("msg")
  public String  msg;
  @SerializedName("result")
  public Result result;

  public class Result {
    @SerializedName("data")
    public ArrayList<PopularData> popularData;

    public class PopularData {
      @SerializedName("id")
      public int feedId;
      @SerializedName("title")
      public String title;
      @SerializedName("season")
      public String season;
      @SerializedName("category")
      public String category;
      @SerializedName("content")
      public String subtitle;
      @SerializedName("position")
      public String localContent;
      @SerializedName("image_url")
      public String imageUrl;
      @SerializedName("traveler")
      public Traveler traveler;
      @SerializedName("review_cnt")
      public int honeyCount;
      @SerializedName("create_at")
      public String createDate;

      public class Traveler {
        @SerializedName("id")
        public Integer travelerId;
        @SerializedName("nickname")
        public String nickName;
        @SerializedName("property")
        public String property;
      }
    }
  }
}
