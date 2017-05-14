package com.enenim.movies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.enenim.movies.ExpandableListAdapterReview;
import com.enenim.movies.R;
import com.enenim.movies.config.Config;
import com.enenim.movies.model.Movie;
import com.enenim.movies.model.Review;
import com.enenim.movies.model.ReviewResponse;
import com.enenim.movies.rest.ApiClient;
import com.enenim.movies.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {
    private Movie movie;
    private ExpandableListAdapterReview listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ProgressBar pbLoadingIndicator;

    public ReviewFragment() {
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
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pbLoadingIndicator = (ProgressBar) getActivity().findViewById(R.id.pb_loading_indicator_review);

        // get the listview
        expListView = (ExpandableListView)getActivity().findViewById(R.id.expandable_list_review);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ReviewResponse> call;

        call = apiService.getReviews(movie.getMovieId(), Config.API_KEY);

        pbLoadingIndicator.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ReviewResponse>() {
             @Override
             public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                 //mLoadingIndicator.setVisibility(View.INVISIBLE);
                 List<Review>reviews = response.body().getResults();

                 /*if(reviews != null){
                     if(reviews.size() < 1){
                         TextView tv =  (TextView) getActivity().findViewById(R.id.review_status);
                         tv.setText(getString(R.string.review_status));
                         tv.setVisibility(View.VISIBLE);
                     }
                 }*/

                 if (reviews != null) {
                     prepareListData(reviews);

                     listAdapter = new ExpandableListAdapterReview(getActivity(), listDataHeader, listDataChild);

                     // setting list adapter
                     expListView.setAdapter(listAdapter);


                     pbLoadingIndicator.setVisibility(View.GONE);

                     // Listview Group click listener
                     expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                         @Override
                         public boolean onGroupClick(ExpandableListView parent, View v,
                                                     int groupPosition, long id) {
                             //TODO
                             return false;
                         }
                     });

                     // Listview Group expanded listener
                     expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                         @Override
                         public void onGroupExpand(int groupPosition) {
                             //TODO
                         }
                     });

                     // Listview Group collasped listener
                     expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                         @Override
                         public void onGroupCollapse(int groupPosition) {
                             //TODO
                         }
                     });

                     // Listview on child click listener
                     expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                         @Override
                         public boolean onChildClick(ExpandableListView parent, View v,
                                                     int groupPosition, int childPosition, long id) {
                             //TODO
                             return false;
                         }
                     });
                 } else {
                     //showErrorMessage();
                 }
             }

             @Override
             public void onFailure(Call<ReviewResponse> call, Throwable t) {

             }
         });
    }

    private void prepareListData(List<Review> reviews) {
        int index = 0;
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for(Review review:reviews) {
            listDataHeader.add(review.getAuthor());
            List<String> child = new ArrayList<String>();
            child.add(review.getContent());

            listDataChild.put(listDataHeader.get(index++), child); // Header, Child data
        }
    }
}
