package com.bbkk.android.bbkkclient.model.response;

public class NameResponse {
  public int code;
  public String msg;
  public Result result;

  public Result getResult() {
    return result;
  }

  public class Result {
    public String nickname;
  }
}
