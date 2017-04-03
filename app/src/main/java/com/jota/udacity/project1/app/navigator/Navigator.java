package com.jota.udacity.project1.app.navigator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jota.udacity.project1.model.MovieModel;
import com.jota.udacity.project1.ui.features.details.view.DetailsActivity;

import static com.jota.udacity.project1.ui.features.details.view.DetailsActivity.PARAM_MOVIE;

public class Navigator {

  public static void toDetails(Context context, MovieModel movieModel) {
    if (context != null) {
      Intent intentToLaunch = DetailsActivity.getCallingIntent(context);
      Bundle params = new Bundle();
      params.putParcelable(PARAM_MOVIE, movieModel);
      intentToLaunch.putExtras(params);
      context.startActivity(intentToLaunch);
    }
  }
}
