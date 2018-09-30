package com.bbkk.android.bbkkclient.api;

public class BbkkApi {
  public static final String BASE_URL = "http://106.10.50.31:8080";
  private static String UUID_CODE = "";
  private static BbkkApiDefinition apiDefinition;

  public static BbkkApiDefinition initialize(String uuid_code) {
    UUID_CODE = uuid_code;
    return apiDefinition = RetrofitClient.getClient(BASE_URL, UUID_CODE)
      .create(BbkkApiDefinition.class);
  }

  public static BbkkApiDefinition getApi() {
    return BbkkApi.apiDefinition != null ? BbkkApi.apiDefinition : BbkkApi.initialize(UUID_CODE);
  }
}
