package com.enenim.movies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.enenim.movies.config.Config;
import com.enenim.movies.fragments.OverViewFragment;
import com.enenim.movies.fragments.ReviewFragment;
import com.enenim.movies.fragments.TrailerFragment;
import com.enenim.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enenim on 5/1/17.
 */

public class DetailTabActivity extends AppCompatActivity {
    private Intent intent;
    private Movie movie;
    private int appLabel = -1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private TextView textViewTitle;

    private TextView textViewRating;

    private ImageView imageViewFavourite;

    private TextView textviewReleaseDate;

    private ProgressBar pbLoadingIndicator;

    private int[] tabIcons = {
            R.drawable.icon_overview,
            R.drawable.icon_trailer,
            R.drawable.icon_review
    };

    public void onFavouriteClick(View view) {
        if(movie.exist()){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailTabActivity.this);

            alertDialog.setTitle("Confirm Action...");
            alertDialog.setMessage("Are you sure you want to remove this movie from favourite?");
            alertDialog.setIcon(R.drawable.delete_icon);
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    movie.delete();
                    imageViewFavourite.setImageResource(R.drawable.favourite_icon);
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
            imageViewFavourite.setImageResource(R.drawable.favourite_icon_active);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tab);
        ButterKnife.bind(this);

        //pbLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        //View view = LayoutInflater.from(this).inflate(R.layout.toolbar_custom_view,null);

        movie = null;

        if(savedInstanceState != null){
            super.onRestoreInstanceState(savedInstanceState);
            if(Config.MOVIE_KEY!=null && !Config.MOVIE_KEY.isEmpty())
                movie = savedInstanceState.getParcelable(Config.MOVIE_KEY);

            if(Config.APP_LABEL_KEY!=null && !Config.APP_LABEL_KEY.isEmpty())
                appLabel = savedInstanceState.getInt(Config.APP_LABEL_KEY);
        }else {
            intent = getIntent();
            if(intent.hasExtra(Config.MOVIE_KEY)){
                movie = (Movie)intent.getParcelableExtra(Config.MOVIE_KEY);
            }

            if(intent.hasExtra(Config.APP_LABEL_KEY)){
                appLabel = intent.getIntExtra(Config.APP_LABEL_KEY, R.string.popular_movie_label);
            }
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.drawable.favourite_icon);

        ImageView moviePoster = new ImageView(this);

       Picasso.with(this)
                .load(Config.IMAGE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.test)
                .error(R.drawable.test)
                .into(moviePoster);

        toolbar.setBackground(moviePoster.getDrawable());

        setSupportActionBar(toolbar);

        LayoutInflater mInflater=LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);

        textViewTitle = (TextView) mCustomView.findViewById(R.id.textview_title);
        textViewRating = (TextView) mCustomView.findViewById(R.id.textview_rating);
        textviewReleaseDate = (TextView) mCustomView.findViewById(R.id.textview_release_date);
        imageViewFavourite = (ImageButton)mCustomView.findViewById(R.id.image_button_favourite);
        textViewTitle.setText(movie.getTitle());
        textViewRating.setText(movie.getVoteAverage()+"");
        textviewReleaseDate.setText(movie.getReleaseDate());
        if(movie.exist()){
            imageViewFavourite.setImageResource(R.drawable.favourite_icon_active);
        }

        toolbar.addView(mCustomView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Config.MOVIE_KEY, movie);

        Fragment overViewFrag = new OverViewFragment();
        overViewFrag.setArguments(bundle);

        Fragment trailerFrag = new TrailerFragment();
        trailerFrag.setArguments(bundle);

        Fragment reviewFrag = new ReviewFragment();
        reviewFrag.setArguments(bundle);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(overViewFrag, getString(R.string.fragment_overview_label));
        adapter.addFragment(trailerFrag, getString(R.string.fragment_trailer_label));
        adapter.addFragment(reviewFrag, getString(R.string.fragment_review_label));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText(R.string.fragment_overview_label);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[0], 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText(R.string.fragment_trailer_label);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[1], 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText(R.string.fragment_review_label);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[2], 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(Config.APP_LABEL_KEY, appLabel);
        outState.putParcelable(Config.MOVIE_KEY, movie);
        super.onSaveInstanceState(outState);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
