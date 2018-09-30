package com.bbkk.android.bbkkclient.view.write;

import android.graphics.Bitmap;

import java.util.ArrayList;

public interface WriteContract {
  interface View {
    void initView();

    void finishActivity();
  }
  interface Presenter {

    void requestPostFeed(String name, String localName, String localAddr, String content, String position, String selectImages);
  }
}
