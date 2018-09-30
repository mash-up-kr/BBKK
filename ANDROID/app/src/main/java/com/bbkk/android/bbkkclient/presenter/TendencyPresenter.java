package com.bbkk.android.bbkkclient.presenter;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.model.TendencyModel;
import com.bbkk.android.bbkkclient.model.request.TypeRequest;
import com.bbkk.android.bbkkclient.model.response.TypeResponse;
import com.bbkk.android.bbkkclient.view.tendency.TendencyContract;
import com.bbkk.android.bbkkclient.view.tendency.TendencyFragment_traveler;
import com.bbkk.android.bbkkclient.view.tendency.TendencyFragment_foodFighter;
import com.bbkk.android.bbkkclient.view.tendency.TendencyFragment_explorer;
import com.bbkk.android.bbkkclient.view.tendency.TendencyFragment_niggard;
import com.bbkk.android.bbkkclient.view.tendency.TendencyFragment_artist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bbkk.android.bbkkclient.presenter.NamePresenter.USER_NAME;

public class TendencyPresenter implements TendencyContract.Presenter{
  private TendencyContract.View view;
  private TendencyModel tendencyModel;
  private SharedPreferences userData;
  public static final String USER_TYPE = "USER_TYPE";
  private Fragment fragment = new Fragment();

  public TendencyPresenter(TendencyContract.View tendencyView, SharedPreferences userData) {
    this.view = tendencyView;
    this.tendencyModel = new TendencyModel();
    this.userData = userData;
    this.view.initView();
    this.getUserName();
  }

  private void getUserName() {
    String currentName = userData.getString(USER_NAME, "");
    view.renderName(currentName);
  }

  @Override
  public Fragment tendencyFragmentGetItem(int position, int max) {
    tendencyModel.setPosition(position);
    tendencyModel.setMax(max);
    if(position<0 || max<=position)
      return null;
    switch (position){
      case 0:
        fragment = new TendencyFragment_foodFighter();
        break;
      case 1:
        fragment = new TendencyFragment_artist();
        break;
      case 2:
        fragment = new TendencyFragment_traveler();
        break;
      case 3:
        fragment = new TendencyFragment_explorer();
        break;
      case 4:
        fragment = new TendencyFragment_niggard();
        break;
      default:

    }
    return fragment;
  }

  @Override
  public void requestPostType(int currentItem) {
    int currentNum = currentItem;
    String currentLabel = renderLabel(currentNum);
    String currentName = userData.getString(USER_NAME, "");
    BbkkApi.getApi().postTypeRegister(
      new TypeRequest(currentName, currentLabel)
    ).enqueue(new Callback<TypeResponse>() {
      @Override
      public void onResponse(Call<TypeResponse> call, Response<TypeResponse> response) {
        TypeResponse typeResponse = response.body();
        if (typeResponse.code == 200) {
          setType(currentLabel);
          view.startSeasonActivity();
        }
      }

      @Override
      public void onFailure(Call<TypeResponse> call, Throwable t) {

      }
    });
  }

  private void setType(String currentLabel) {
    SharedPreferences.Editor editor = userData.edit();
    editor.putString(USER_TYPE, currentLabel);
    editor.commit();
  }

  private String renderLabel(int pos) {
    int currentNum = pos;
    String currentName = "";
    switch (currentNum) {
      case 0:
        currentName = "식도락형";
        break;
      case 1:
        currentName = "예술가형";
        break;
      case 2:
        currentName = "관광객형";
        break;
      case 3:
        currentName = "탐험가형";
        break;
      case 4:
        currentName = "알뜰형";
        break;
      default:
    }
    return currentName;
  }
}
