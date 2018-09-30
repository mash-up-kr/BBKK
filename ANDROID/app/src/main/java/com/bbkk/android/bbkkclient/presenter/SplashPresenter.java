package com.bbkk.android.bbkkclient.presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.view.splash.SplashContract;

import java.util.UUID;

import static com.bbkk.android.bbkkclient.presenter.NamePresenter.USER_NAME;
import static com.bbkk.android.bbkkclient.presenter.TendencyPresenter.USER_TYPE;

public class SplashPresenter implements SplashContract.Presenter {
  SplashContract.View view;
  private SharedPreferences userData;
  public static final String USER_UUID = "USER_UUID";

  public SplashPresenter(SplashContract.View view, SharedPreferences userData) {
    this.view = view;
    this.userData = userData;
    this.view.initView();
  }

  @Override
  public void activityManager() {
    if (checkHasName()) {
      if (checkHasType()) {
        view.startSeasonActivity();
      } else {
        view.startTendencyActivity();
      }
    } else {
      view.startNameActivity();
    }
  }

  private boolean checkHasType() {
    Boolean hasType = false;
    String currentType = userData.getString(USER_TYPE, "");
    if (!TextUtils.isEmpty(currentType)) {
      hasType = true;
    }

    return hasType;
  }

  private Boolean checkHasName() {
    Boolean hasName = false;

    String currentName = userData.getString(USER_NAME, "");
    if (!TextUtils.isEmpty(currentName)) {
      hasName = true;
    }

    return hasName;
  }
  @Override
  public void requestAction() {
    String uniqueID = this.getUUID();
    Log.e("UUID",uniqueID);
    BbkkApi.initialize(uniqueID);
    view.renderView();
  }

  private String getUUID() {
    String currentUinqueID = userData.getString(USER_UUID, "");
    if (TextUtils.isEmpty(currentUinqueID)) {
      currentUinqueID = UUID.randomUUID().toString();
      SharedPreferences.Editor editor = userData.edit();
      editor.putString(USER_UUID, currentUinqueID);
      editor.commit();
    }
    return currentUinqueID;
  }
}
