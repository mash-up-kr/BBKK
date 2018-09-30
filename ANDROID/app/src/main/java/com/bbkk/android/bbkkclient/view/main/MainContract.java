package com.bbkk.android.bbkkclient.view.main;

import android.support.v7.widget.RecyclerView;

import com.bbkk.android.bbkkclient.model.Timeline;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;

import java.util.ArrayList;

public interface MainContract {

  interface View {
    void initView();
    void renderTimeLine(ArrayList<CardFeedsResponse.Result.PopularData> popularDataLists);
    void renderMainCounter(int size);
  }

  interface Presenter {
    void requestContentList();
  }
}
