package com.bbkk.android.bbkkclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

public class ViewFlipperAction extends AppCompatActivity implements View.OnTouchListener {

  Context context;
  //전체화면개수
  int countIndexes;
  //현재화면인덱스
  int currentIndex;
  //터치시작 x좌표
  float downX;
  //터치끝 x좌표
  float upX;

  ViewFlipper flipper;

  ViewFlipperCallback indexCallback;

  //인터페이스 - 탭 클릭시 이미지 변경하기 위한 인터페이스
  //여러 액티비티가 fragment를 호출하여도 동일한 인터페이스를 구현하도록 한다
  public static interface ViewFlipperCallback{
    public void onFlipperActionCallback(int position);
  }

  public ViewFlipperAction(Context context, ViewFlipper flipper){
    this.context = context;
    this.flipper = flipper;

    if(context instanceof ViewFlipperCallback){
      indexCallback = (ViewFlipperCallback)context;
    }

    currentIndex = 0;
    downX = 0;
    upX = 0;
    countIndexes = flipper.getChildCount();

    //인덱스 업데이트
    indexCallback.onFlipperActionCallback(currentIndex);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    //터치시작
    if(event.getAction()==MotionEvent.ACTION_DOWN){
      downX = event.getX();
    }
    //터치종료
    else if(event.getAction()==MotionEvent.ACTION_UP){
      upX = event.getX();

      //왼쪽 -> 오른쪽
      if(upX < downX){
        //애니메이션
        flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));

        //인덱스체크 - 마지막화면이면 동작없음
        if(currentIndex < (countIndexes-1)){
          flipper.showNext();

          currentIndex++;
          //인덱스 업데이트
          indexCallback.onFlipperActionCallback(currentIndex);
        }
      }
      //오른쪽 -> 왼쪽
      else if(upX > downX){
        //애니메이션 설정
        flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));

        //인덱스체크 - 첫번째화면이면 동작없음
        if(currentIndex > 0){
          flipper.showPrevious();

          currentIndex--;
          //인덱스 업데이트
          indexCallback.onFlipperActionCallback(currentIndex);
        }
      }
    }
    return true;
  }
}
