package com.bbkk.android.bbkkclient.presenter;

import com.bbkk.android.bbkkclient.view.detail.DetailActivity;
import com.bbkk.android.bbkkclient.view.detail.DetailContract;

public class DetailPresenter implements DetailContract.Presenter {
  DetailContract.View view;

  public DetailPresenter(DetailContract.View view) {
    this.view = view;
    view.initView();
  }
}
