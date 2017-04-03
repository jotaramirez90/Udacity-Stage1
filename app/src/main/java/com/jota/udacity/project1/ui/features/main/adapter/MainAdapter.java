package com.jota.udacity.project1.ui.features.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jota.udacity.project1.R;
import com.jota.udacity.project1.model.MovieModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

  private ArrayList<MovieModel> mMovieModels;
  private OnMovieItemClickListener mOnMovieItemClickListener;

  public MainAdapter(ArrayList<MovieModel> movieModels,
      OnMovieItemClickListener onMovieItemClickListener) {
    mMovieModels = movieModels;
    mOnMovieItemClickListener = onMovieItemClickListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    int layoutIdForListItem = R.layout.item_movie;
    LayoutInflater inflater = LayoutInflater.from(context);
    boolean shouldAttachToParentImmediately = false;
    View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
    return new ViewHolder(view, mOnMovieItemClickListener);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bind(holder.itemView.getContext(), mMovieModels.get(position));
  }

  @Override public int getItemCount() {
    return mMovieModels.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private OnMovieItemClickListener listener;

    public ViewHolder(View itemView, OnMovieItemClickListener listener) {
      super(itemView);
      this.listener = listener;
      imageView = (ImageView) itemView.findViewById(R.id.iv_poster);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      int position = getAdapterPosition();
      if (position >= 0) {
        listener.onItemClick(mMovieModels.get(position));
      }
    }

    public void bind(Context context, MovieModel movieModel) {
      Picasso.with(context).load(movieModel.getPoster()).into(imageView);
    }
  }

  public interface OnMovieItemClickListener {
    void onItemClick(MovieModel movieModel);
  }
}
