package com.bbkk.android.bbkkclient.presenter;

import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.model.request.FeedRequest;
import com.bbkk.android.bbkkclient.model.response.FeedResponse;
import com.bbkk.android.bbkkclient.view.write.WriteActivity;
import com.bbkk.android.bbkkclient.view.write.WriteContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritePresenter  implements WriteContract.Presenter {
  WriteContract.View view;

  public WritePresenter(WriteActivity view) {
    this.view = view;
    this.view.initView();
  }

  @Override
  public void requestPostFeed(String name, String localName, String localAddr, String content,String position, String selectImages) {
    BbkkApi.getApi().postFeed(new FeedRequest(
      "1",
      name,
      localName,
      localAddr,
      content,
      position,
      selectImages
    )).enqueue(new Callback<FeedResponse>() {
      @Override
      public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
        FeedResponse feedResponse = response.body();
        view.finishActivity();

      }

      @Override
      public void onFailure(Call<FeedResponse> call, Throwable t) {

      }
    });

  }
}
