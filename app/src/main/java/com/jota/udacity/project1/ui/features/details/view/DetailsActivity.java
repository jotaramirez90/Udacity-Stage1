package com.jota.udacity.project1.ui.features.details.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.jota.udacity.project1.R;
import com.jota.udacity.project1.app.BaseActivity;
import com.jota.udacity.project1.model.MovieModel;
import com.jota.udacity.project1.ui.features.BasePresenter;
import com.jota.udacity.project1.ui.features.details.presenter.DetailsPresenter;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends BaseActivity {

  public static final String PARAM_MOVIE = "movieParam";

  private DetailsPresenter mDetailsPresenter;
  private ImageView mPosterImageView;
  private TextView mSynopsisTextView;
  private TextView mDateTextView;
  private TextView mRatingTextView;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, DetailsActivity.class);
  }

  @Override protected BasePresenter bindPresenter() {
    mDetailsPresenter = new DetailsPresenter();
    return mDetailsPresenter;
  }

  @Override protected int bindLayout() {
    return R.layout.activity_details;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    MovieModel movieModel = getIntent().getParcelableExtra(PARAM_MOVIE);

    mPosterImageView = (ImageView) findViewById(R.id.iv_poster);
    mDateTextView = (TextView) findViewById(R.id.tv_date);
    mRatingTextView = (TextView) findViewById(R.id.tv_rating);
    mSynopsisTextView = (TextView) findViewById(R.id.tv_synopsis);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(movieModel.getTitle());
    Picasso.with(this).load(movieModel.getPoster()).into(mPosterImageView);
    mDateTextView.setText(movieModel.getDate());
    mRatingTextView.setText(movieModel.getRating());
    mSynopsisTextView.setText(movieModel.getSynopsis());
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }
}
