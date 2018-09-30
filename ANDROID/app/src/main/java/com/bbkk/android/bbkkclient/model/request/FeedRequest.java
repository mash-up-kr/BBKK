package com.bbkk.android.bbkkclient.model.request;

public class FeedRequest {
  public String traveler_id;
  public String title;
  public String category;
  public String season;
  public String content;
  public String position;
  public String images;

  public FeedRequest(String traveler_id, String title, String category, String season, String content, String position, String images) {
    this.traveler_id = traveler_id;
    this.title = title;
    this.category = category;
    this.season = changeEnumSeason(season);
    this.content = content;
    this.position = position;
    this.images = images;
  }

  private String changeEnumSeason(String season) {
    String currentSeason = "AUTUMN";

    switch (season) {
      case "봄":
        currentSeason = "SPRING";
        break;
      case "여름":
        currentSeason = "SUMMER";
        break;
      case "가을":
        currentSeason = "AUTUMN";
        break;
      case "겨울":
        currentSeason = "WINTER";
        break;
    }
    return currentSeason;
  }
}
