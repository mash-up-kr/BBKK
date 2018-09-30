package com.bbkk.android.bbkkclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbkk.android.bbkkclient.R;
import com.bbkk.android.bbkkclient.model.Timeline;
import com.bbkk.android.bbkkclient.model.response.CardFeedsResponse;
import com.bbkk.android.bbkkclient.view.detail.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

  private ArrayList<CardFeedsResponse.Result.PopularData> items;
  private RecyclerViewClickListener recyclerViewClickListener;
  private Context context;
  private View view;

  private final View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//    TODO: 터치에 나오는 getAdapterPosition() 값을 가지고 switch 문을 이용해 Intent를 구분지어 준다)
      final Intent intent;
      intent = new Intent(context, DetailActivity.class);
      context.startActivity(intent);
    }
  };

  public interface RecyclerViewClickListener {
    void onClick(View view, CardFeedsResponse.Result.PopularData item);
  }

  public TimeLineAdapter(
    ArrayList<CardFeedsResponse.Result.PopularData> data,
    RecyclerViewClickListener recyclerViewClickListener) {
    this.items = data;
    this.recyclerViewClickListener = recyclerViewClickListener;
  }

  @NonNull
  @Override
  public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.item_timeline, parent, false);
    view.setOnClickListener(onClickListener);
    return new TimeLineViewHolder(this.view, this.recyclerViewClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
    String[] feedImages = items.get(position).imageUrl.split(",");
    CardFeedsResponse.Result.PopularData item = items.get(position);
    Glide.with(view)
      .load(feedImages[0])
      .into(holder.ivImage);

    holder.tvHoneyCount.setText(item.honeyCount+"");
    holder.tvTitle.setText(item.title);
    holder.tvSubTitle.setText(item.category);
    holder.tvLocalContent.setText(item.localContent);
    holder.item = item;
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public class TimeLineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    CardFeedsResponse.Result.PopularData item;
    ImageView ivImage;
    TextView tvHoneyCount;
    TextView tvTitle;
    TextView tvSubTitle;
    TextView tvLocalContent;
    RecyclerViewClickListener recyclerViewClickListener;

    public TimeLineViewHolder(
      View view,
      RecyclerViewClickListener recyclerViewClickListener
    ) {
      super(view);
      context = view.getContext();
      ivImage = view.findViewById(R.id.iv_timeline_image);
      tvHoneyCount = view.findViewById(R.id.tv_honey_count);
      tvTitle = view.findViewById(R.id.tv_timeline_title);
      tvSubTitle = view.findViewById(R.id.tv_timeline_subtitle);
      tvLocalContent = view.findViewById(R.id.tv_timeline_local_content);
      this.recyclerViewClickListener = recyclerViewClickListener;
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      this.recyclerViewClickListener.onClick(view, item);
    }
  }
}
