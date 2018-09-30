package com.bbkk.android.bbkkclient.presenter;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.api.BbkkApiDefinition;
import com.bbkk.android.bbkkclient.model.response.NameResponse;
import com.bbkk.android.bbkkclient.view.nameSetting.NameContract;
import com.bbkk.android.bbkkclient.model.NameModel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NamePresenter implements NameContract.Presenter {
  public static final String USER_NAME = "USER_NAME";
  NameContract.View view;
  private NameResponse nameResponse;
  private String currentName;
  private SharedPreferences userData;

  public NamePresenter(NameContract.View nameView, SharedPreferences userData) {
    this.view = nameView;
    this.userData = userData;
    view.initView();
    this.changeNameAction();
  }


  @Override
  public void submitAction() {
    this.setName(currentName);
    view.startTendencyActivity();
  }

  @Override
  public void changeNameAction() {
    BbkkApi.getApi().getRandomName()
      .enqueue(new Callback<NameResponse>() {
        @Override
        public void onResponse(Call<NameResponse> call, Response<NameResponse> response) {
          nameResponse = response.body();
          currentName = nameResponse.getResult().nickname;
          view.renderChangeName(currentName);
        }

        @Override
        public void onFailure(Call<NameResponse> call, Throwable t) {

        }
      });
  }

  private void setName(String currentName) {
    SharedPreferences.Editor editor = userData.edit();
    editor.putString(USER_NAME, currentName);
    editor.commit();
  }
}
