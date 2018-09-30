package com.bbkk.android.bbkkclient.view.write;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.presenter.WritePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.erikagtierrez.multiple_media_picker.Gallery;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.BaseSliderView;
import com.glide.slider.library.SliderTypes.TextSliderView;
import com.glide.slider.library.Tricks.ViewPagerEx;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Url;

import static com.bbkk.android.bbkkclient.presenter.SeasonPresenter.USER_SEASON;
import static com.bbkk.android.bbkkclient.view.splash.SplashActivity.USER_DATA;

public class WriteActivity extends AppCompatActivity implements WriteContract.View,
  BaseSliderView.OnSliderClickListener,
  ViewPagerEx.OnPageChangeListener {
  private static final String IMAGE_PICKER_TITLE = "title";
  private static final String IMAGE_PICKER_VALUE = "이미지 선택";
  private static final int OPEN_MEDIA_PICKER = 1;
  private static final String MAX_IMAGE_PICK_TITLE = "maxSelection";
  private static final int MAX_IMAGE_PICK_VALUE = 5;
  private ArrayList<String> selectImages;
  private WriteContract.Presenter presenter;
  @BindView(R.id.tv_header_cancel)
  public TextView tvCancelBtn;
  @BindView(R.id.tv_header_next)
  public TextView tvNextBtn;
  @BindView(R.id.sl_write_images)
  public SliderLayout slImageSlider;
  @BindView(R.id.et_write_title)
  public EditText etTitle;
  @BindView(R.id.et_write_local_name)
  public EditText etLocalName;
  @BindView(R.id.et_write_local_addr)
  public EditText etLocalAddr;
  @BindView(R.id.et_write_content)
  public EditText etContent;
  private SharedPreferences userData;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_write);
    ButterKnife.bind(this);
    this.userData = getSharedPreferences(USER_DATA, MODE_PRIVATE);
    presenter = new WritePresenter(this);
  }

  @Override
  public void initView() {
    this.listenCancel();
    this.listenSubmit();
    this.setPermission();
  }

  @Override
  public void finishActivity() {
    Log.e("이미지", "빵야빵야빵야");
    finish();
  }

  private void listenSubmit() {
    tvNextBtn.setOnClickListener((__) -> {
      boolean hasData = checkData();
      String currentImages = convertImages(selectImages);

      if (hasData) {
        Log.e("이미지", this.selectImages.get(0));
        presenter.requestPostFeed(
          etTitle.getText().toString(),
          etLocalName.getText().toString(),
          userData.getString(USER_SEASON, "봄"),
          etContent.getText().toString(),
          etLocalAddr.getText().toString(),
          currentImages
        );
      } else {
        Toast.makeText(this, "빈 정보가 있습니다.", Toast.LENGTH_SHORT).show();
      }
    });
  }

  private String convertImages(ArrayList<String> selectImages) {
//    ArrayList<Uri> items = new ArrayList<>();
//    for (String item: selectImages) {
//      items.add(Uri.parse(item));
//    }
    String currentItems = "";
    for(String item: selectImages) {
      currentItems += encodeImage(item) +",";
    }
    currentItems = currentItems.substring(0, currentItems.length()-1);
    return currentItems;
  }

  private String encodeImage(String path)
  {
    File imagefile = new File(path);
    FileInputStream fis = null;
    try{
      fis = new FileInputStream(imagefile);
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
    Bitmap bm = BitmapFactory.decodeStream(fis);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
    byte[] b = baos.toByteArray();
    String encImage = Base64.encodeToString(b, Base64.DEFAULT);
    //Base64.de
    return encImage;

  }

  private boolean checkData() {
    boolean isReady = true;
    if (
        checkNull(etTitle.getText().toString())&&
        checkNull(etLocalName.getText().toString())&&
        checkNull(etLocalAddr.getText().toString())&&
        checkNull(etContent.getText().toString())
      ) {
      isReady = true;
    } else {
      isReady = false;
    }
    return isReady;
  }


  private boolean checkNull(String data) {
    if(TextUtils.isEmpty(data)) {
      return false;
    } else {
      return true;
    }
  }

  private void listenCancel() {
    tvCancelBtn.setOnClickListener((__) -> {
      finish();
    });
  }

  private void setPermission() {
    PermissionListener permissionlistener = new PermissionListener() {
      @Override
      public void onPermissionGranted() {
        initImagePicker();
      }

      @Override
      public void onPermissionDenied(List<String> deniedPermissions) {
        finish();
        Toast.makeText(WriteActivity.this, "권한이 없어 내용 등록이 불가능합니다.", Toast.LENGTH_SHORT).show();
      }
    };

    new TedPermission().with(getApplicationContext())
      .setPermissionListener(permissionlistener)
      .setDeniedMessage("권한 설정 동의를 안하신다면, 나중에 이곳에서 설정해 주세요. [설정] > [권한]")
      .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
      .check();
  }

  private void initImagePicker() {
    Intent intent= new Intent(this, Gallery.class);
    intent.putExtra(IMAGE_PICKER_TITLE, IMAGE_PICKER_VALUE);
    intent.putExtra("mode", 2);
    intent.putExtra(MAX_IMAGE_PICK_TITLE, MAX_IMAGE_PICK_VALUE);
    startActivityForResult(intent, OPEN_MEDIA_PICKER);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == OPEN_MEDIA_PICKER) {
      if (resultCode == RESULT_OK && data != null) {
        ArrayList<String> selectionResult = data.getStringArrayListExtra("result");
        renderWriteImageView(selectionResult);
      } else {
        finish();
      }
    }
  }

  private void renderWriteImageView(ArrayList<String> selectionResult) {
    selectImages = selectionResult;
    RequestOptions requestOptions = new RequestOptions();
    requestOptions.centerCrop();

    for(int index = 0; index < selectImages.size(); index++ ) {
      TextSliderView sliderView = new TextSliderView(this);

      sliderView.image(selectImages.get(index))
        .setRequestOption(requestOptions)
        .setBackgroundColor(Color.WHITE)
        .setProgressBarVisible(true)
        .setOnSliderClickListener(this);

      slImageSlider.addSlider(sliderView);
    }

    slImageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
    slImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    slImageSlider.setDuration(4000);
    slImageSlider.addOnPageChangeListener(this);
  }

  @Override
  public void onSliderClick(BaseSliderView baseSliderView) {
  }

  @Override
  public void onPageScrolled(int i, float v, int i1) {
  }

  @Override
  public void onPageSelected(int i) {
  }

  @Override
  public void onPageScrollStateChanged(int i) {
  }
}
