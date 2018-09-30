package com.bbkk.android.bbkkclient.presenter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.model.MainModel;
import com.bbkk.android.bbkkclient.model.Timeline;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;
import com.bbkk.android.bbkkclient.view.main.MainContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

  private MainContract.View view;
  private ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists;

  public MainPresenter(MainContract.View mainView) {
    this.view = mainView;
    view.initView();
  }

  @Override
  public void requestContentList() {
//    TODO: 서버에 요청한다.
    getRequestFeeds();

  }

  private void getRequestFeeds() {

    BbkkApi.getApi().getCardFeeds()
      .enqueue(new Callback<CardFeedsResponse>() {
        @Override
        public void onResponse(Call<CardFeedsResponse> call, Response<CardFeedsResponse> response) {
          CardFeedsResponse cardFeedsResponse = response.body();
          popularDataLists = cardFeedsResponse.result.popularData;
          view.renderTimeLine(popularDataLists);
          view.renderMainCounter(popularDataLists.size());
        }

        @Override
        public void onFailure(Call<CardFeedsResponse> call, Throwable t) {

        }
      });
  }
}
