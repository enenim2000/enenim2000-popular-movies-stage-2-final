/*
package com.enenim.movies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enenim.movies.config.Config;
import com.enenim.movies.model.Movie;
import com.enenim.movies.util.CommonUtil;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.button;
import static android.R.attr.id;
import static com.enenim.movies.R.id.fab_favourite;

*/
/**
 * Created by enenim on 4/13/17.
 *//*


public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();
    private Movie movie;
    private  int id;
    private int appLabel = -1;

    @BindView(R.id.fab_favourite)
    ImageButton fab_favourite;

    @BindView(R.id.tv_overview)
    TextView tvOverview;

    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;

    @BindView(R.id.tv_movie_title)
    TextView tvTitle;

    @BindView(R.id.tv_rating)
    TextView tvRating;

    @BindView(R.id.im_movie_poster)
    ImageView tvMoviePoster;

    @BindView(R.id.user_rating_value)
    TextView tv_ExtraRating;

    @BindView(R.id.release_date_value)
    TextView tv_ExtraReleaseDate;

    @OnClick(R.id.fab_favourite) void onClickFabFavourite(){

        if(movie.exist()){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);

            alertDialog.setTitle("Confirm Action...");
            alertDialog.setMessage("Are you sure you want to remove this movie from favourite?");
            alertDialog.setIcon(R.drawable.delete_icon);
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    movie.delete();
                    fab_favourite.setImageResource(R.drawable.favourite_icon);
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();


        }else {
            movie.save();
            fab_favourite.setImageResource(R.drawable.favourite_icon_active);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        movie = null;

        ColorStateList csl = new ColorStateList(new int[][] { new int[0] }, new int[]{Color.parseColor("#455A64") });
        //fab_favourite.setBackgroundTintList(csl);

        if(savedInstanceState != null){
            super.onRestoreInstanceState(savedInstanceState);
            if(Config.MOVIE_KEY!=null && !Config.MOVIE_KEY.isEmpty())
                movie = savedInstanceState.getParcelable(Config.MOVIE_KEY);

            if(Config.APP_LABEL_KEY!=null && !Config.APP_LABEL_KEY.isEmpty())
                appLabel = savedInstanceState.getInt(Config.APP_LABEL_KEY);
        }else {
            if(intent.hasExtra(Config.MOVIE_KEY)){
                movie = (Movie)intent.getParcelableExtra(Config.MOVIE_KEY);
            }

            if(intent.hasExtra(Config.APP_LABEL_KEY)){
                appLabel = intent.getIntExtra(Config.APP_LABEL_KEY, R.string.popular_movie_label);
            }
        }


        if(movie.exist()){
            fab_favourite.setImageResource(R.drawable.favourite_icon_active);
        }else {
            fab_favourite.setImageResource(R.drawable.favourite_icon);
        }

        this.setTitle(appLabel);

        Log.d(TAG, movie.getPosterPath());

        String rating = movie.getVoteAverage() + "";

        tvOverview.setText(movie.getOverview());
        tvReleaseDate.setText(R.string.overview_label);
        tvTitle.setText(movie.getTitle());
        tvRating.setText(rating);

        tv_ExtraRating.setText(rating);
        tv_ExtraReleaseDate.setText(movie.getReleaseDate());

        String imageFullPath = Config.IMAGE_BASE_URL + movie.getPosterPath();

        Log.d(TAG, imageFullPath);

        Picasso.with(this)
                .load(imageFullPath)
                .placeholder(R.drawable.test)
                .error(R.drawable.test)
                .into(tvMoviePoster);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        id = menuItem.getItemId();
        if(id == R.id.review_page){
            Bundle bundle = new Bundle();
            bundle.putParcelable(Config.MOVIE_KEY, movie);
            Intent intent = new Intent(DetailActivity.this, ReviewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }

        return  super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(Config.APP_LABEL_KEY, appLabel);
        outState.putParcelable(Config.MOVIE_KEY, movie);
        super.onSaveInstanceState(outState);
    }
}
*/
