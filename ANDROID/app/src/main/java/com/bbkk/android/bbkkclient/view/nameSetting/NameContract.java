package com.bbkk.android.bbkkclient.view.nameSetting;

import com.bbkk.android.bbkkclient.model.NameModel;

import retrofit2.Callback;

public interface NameContract {

  interface View {
    void initView();

    void startTendencyActivity();
    void renderChangeName(String currentName);
  }

  interface Presenter {
    void submitAction();
    void changeNameAction();
  }
}
