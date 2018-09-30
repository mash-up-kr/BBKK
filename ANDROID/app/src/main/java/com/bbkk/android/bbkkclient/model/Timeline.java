package com.bbkk.android.bbkkclient.model;

public class Timeline {
  public String name;
  public String image;
  public Timeline(String image, String name) {
    this.image = image;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}

