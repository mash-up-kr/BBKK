package com.bbkk.android.bbkkclient.view.tendency;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bumptech.glide.Glide;

public class TendencyFragment_artist extends android.support.v4.app.Fragment {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  @SuppressLint("ResourceAsColor")
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ConstraintLayout ccTypeLayout = (ConstraintLayout) inflater.inflate(R.layout.item_tendency_viewpager,container,false);
    TextView tvTypeContent = ccTypeLayout.findViewById(R.id.tv_type_content);
    ImageView ivTypeChar = ccTypeLayout.findViewById(R.id.iv_type_char);

    tvTypeContent.setText(R.string.type_artist);
    Glide.with(getActivity())
      .load(R.drawable.char_artist)
      .into(ivTypeChar);


    return ccTypeLayout;
  }
}
