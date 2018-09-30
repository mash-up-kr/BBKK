package com.bbkk.android.bbkkclient.presenter;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.api.BbkkApi;
import com.bbkk.android.bbkkclient.model.SeasonModel;
import com.bbkk.android.bbkkclient.model.request.TypeRequest;
import com.bbkk.android.bbkkclient.view.season.FallFragment;
import com.bbkk.android.bbkkclient.view.season.SeasonContract;
import com.bbkk.android.bbkkclient.view.season.SpringFragment;
import com.bbkk.android.bbkkclient.view.season.SummerFragment;
import com.bbkk.android.bbkkclient.view.season.WinterFragment;

import static com.bbkk.android.bbkkclient.presenter.TendencyPresenter.USER_TYPE;

public class SeasonPresenter implements SeasonContract.Presenter{
  public static final String USER_SEASON = "USER_SEASON";
  private SharedPreferences userData;
  private SeasonContract.View view;
  private SeasonModel model;
  private Fragment fragment = new Fragment();

  public SeasonPresenter(SeasonContract.View seasonView, SharedPreferences userData) {
    this.view = seasonView;
    this.model = new SeasonModel();
    this.userData = userData;
    seasonView.initView();
    this.getTypeImage();
  }

  private void getTypeImage() {
    String currentType = userData.getString(USER_TYPE, "");

    switch (currentType) {
      case "식도락형":
        view.renderTypeIamge(R.drawable.char_food);
        break;
      case "예술가형":
        view.renderTypeIamge(R.drawable.char_artist);
        break;
      case "관광객형":
        view.renderTypeIamge(R.drawable.char_travel);
        break;
      case "탐험가형":
        view.renderTypeIamge(R.drawable.char_adventure);
        break;
      case "알뜰형":
        view.renderTypeIamge(R.drawable.char_miser);
        break;
        default:
    }
  }

  @Override
  public Fragment seasonFragmentGetItem(int position, int max) {
    model.setPosition(position);
    model.setMax(max);
//    if(position<0 || max<=position)
//      return null;
    switch (position) {
      case 0:
        fragment = new SpringFragment();
        break;
      case 1:
        fragment = new SummerFragment();
        break;
      case 2:
        fragment = new FallFragment();
        break;
      case 3:
        fragment = new WinterFragment();
        break;
      default:

    }
    return fragment;
  }

  @Override
  public void requestGetSeason(int pos) {
    this.setSeason(pos);
    view.startMainActivity();
  }

  private void setSeason(int pos) {
    int seasonNum = pos;
    String currentSeason = "";

    switch (seasonNum) {
      case 0:
        currentSeason = "봄";
        break;
      case 1:
        currentSeason = "여름";
        break;
      case 2:
        currentSeason = "가을";
        break;
      case 3:
        currentSeason = "겨울";
        break;

      default:
    }

    SharedPreferences.Editor editor = userData.edit();
    editor.putString(USER_SEASON, currentSeason);
    editor.commit();

  }
}
