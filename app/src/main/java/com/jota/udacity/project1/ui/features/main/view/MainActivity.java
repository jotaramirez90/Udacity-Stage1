package com.jota.udacity.project1.ui.features.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.jota.udacity.project1.R;
import com.jota.udacity.project1.app.BaseActivity;
import com.jota.udacity.project1.app.navigator.Navigator;
import com.jota.udacity.project1.model.MovieModel;
import com.jota.udacity.project1.ui.common.GridItemDecoration;
import com.jota.udacity.project1.ui.features.BasePresenter;
import com.jota.udacity.project1.ui.features.main.adapter.MainAdapter;
import com.jota.udacity.project1.ui.features.main.presenter.MainPresenter;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainAdapter.OnMovieItemClickListener {

  private static final int NUM_COLUMNS = 2;

  private MainPresenter mMainPresenter;
  private ProgressBar mMainProgressBar;
  private RecyclerView mMainRecyclerView;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override protected BasePresenter bindPresenter() {
    mMainPresenter = new MainPresenter();
    return mMainPresenter;
  }

  @Override protected int bindLayout() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainProgressBar = (ProgressBar) findViewById(R.id.pb_main);
    mMainRecyclerView = (RecyclerView) findViewById(R.id.rv_main);

    mMainRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMNS));
    mMainRecyclerView.setHasFixedSize(true);
    GridItemDecoration itemDecoration = new GridItemDecoration(
        getResources().getDimensionPixelSize(R.dimen.grid_decoration_spacing), NUM_COLUMNS);
    mMainRecyclerView.addItemDecoration(itemDecoration);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    clearMovies();
    switch (item.getItemId()) {
      case R.id.action_popular:
        mMainPresenter.getPopularMovies();
        break;
      case R.id.action_top:
        mMainPresenter.getTopMovies();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  private void clearMovies() {
    mMainRecyclerView.setVisibility(View.GONE);
    mMainProgressBar.setVisibility(View.VISIBLE);
  }

  public void setMovies(ArrayList<MovieModel> movieModels) {
    mMainProgressBar.setVisibility(View.GONE);
    MainAdapter adapter = new MainAdapter(movieModels, this);
    mMainRecyclerView.setAdapter(adapter);
    mMainRecyclerView.setVisibility(View.VISIBLE);
  }

  @Override public void onItemClick(MovieModel movieModel) {
    Navigator.toDetails(this, movieModel);
  }
}
