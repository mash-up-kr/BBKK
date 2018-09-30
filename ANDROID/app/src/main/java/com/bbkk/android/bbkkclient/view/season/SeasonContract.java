package com.bbkk.android.bbkkclient.view.season;

import android.support.v4.app.Fragment;

public interface SeasonContract {
  interface View {
    void initView();
    void startMainActivity();
    void renderTypeIamge(int char_type);
  }
  interface Presenter {
    Fragment seasonFragmentGetItem(int position, int max);
    void requestGetSeason(int pos);
  }
}
