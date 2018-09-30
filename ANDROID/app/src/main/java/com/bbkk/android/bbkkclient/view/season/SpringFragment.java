package com.bbkk.android.bbkkclient.view.season;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;

public class SpringFragment extends android.support.v4.app.Fragment {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  @SuppressLint("ResourceAsColor")
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ConstraintLayout seasonLayout = (ConstraintLayout) inflater.inflate(R.layout.item_season_viewpager, container, false);
    ImageView seasonBackground = seasonLayout.findViewById(R.id.iv_season_background);
    TextView seasonTitle = seasonLayout.findViewById(R.id.tv_season_title);
    TextView seasonSubtitle = seasonLayout.findViewById(R.id.tv_season_subtitle);

    seasonBackground.setImageResource(R.drawable.season_spring);
    seasonTitle.setText(R.string.season_spring_title);
    seasonSubtitle.setText(R.string.season_spring_subTitle);
    return seasonLayout;
  }
}









