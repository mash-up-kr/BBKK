package com.bbkk.android.bbkkclient.model.request;

public class ReviewWriteRequest {
  int traveler_id;
  int playland_id;
  String content;

  public ReviewWriteRequest(int traveler_id, int playland_id, String content) {
    this.traveler_id = traveler_id;
    this.playland_id = playland_id;
    this.content = content;
  }
}
