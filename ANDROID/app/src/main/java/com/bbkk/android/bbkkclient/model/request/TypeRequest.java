package com.bbkk.android.bbkkclient.model.request;

import com.bbkk.android.bbkkclient.R;

public class TypeRequest {
  public String nickname;
  public String property;

  public TypeRequest(String nickname, String property) {
    this.nickname = nickname;
    this.property = changeEnumProperty(property);
  }

  private String changeEnumProperty(String property) {
    String currentResult = property;

    switch(property) {
      case "식도락형":
        currentResult = "FOODFIGHTER";
        break;
      case "예술가형":
        currentResult = "ARTIST";
        break;
      case "관광객형":
        currentResult = "VISITOR";
        break;
      case "탐험가형":
        currentResult = "EXPLORER";
        break;
      case "알뜰형":
        currentResult = "BEGGER";
        break;
    }

    return currentResult;
  }
}
