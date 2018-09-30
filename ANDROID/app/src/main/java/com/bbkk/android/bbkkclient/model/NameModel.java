package com.bbkk.android.bbkkclient.model;

public class NameModel {

  public Integer code;
  public String msg;
  public Result result;

  public class Result {
    public String nickname;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return result +"ID" ;
  }
}
