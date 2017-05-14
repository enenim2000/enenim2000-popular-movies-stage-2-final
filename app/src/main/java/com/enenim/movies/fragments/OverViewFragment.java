package com.enenim.movies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enenim.movies.R;
import com.enenim.movies.config.Config;
import com.enenim.movies.model.Movie;

public class OverViewFragment extends Fragment {
    private Movie movie;

    public OverViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        movie = getArguments().getParcelable(Config.MOVIE_KEY);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_over_view, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();

        if (bundle != null) {
            movie = bundle.getParcelable(Config.MOVIE_KEY);
            String overView = movie.getOverview();
            setText(overView);
        }
    }

    public void setText(String url) {
        TextView view = (TextView) getView().findViewById(R.id.tv_overview);
        view.setText(url);
    }
}
