package com.enenim.movies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import com.enenim.movies.ExpandableListAdapterTrailer;
import com.enenim.movies.R;
import com.enenim.movies.config.Config;
import com.enenim.movies.model.Movie;
import com.enenim.movies.model.Trailer;
import com.enenim.movies.model.TrailerResponse;
import com.enenim.movies.rest.ApiClient;
import com.enenim.movies.rest.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerFragment extends Fragment {
    private Movie movie;
    private ExpandableListAdapterTrailer listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private ProgressBar pbLoadingIndicator;

    public TrailerFragment() {
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
        return inflater.inflate(R.layout.fragment_trailer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pbLoadingIndicator = (ProgressBar) getActivity().findViewById(R.id.pb_loading_indicator_trailer);

        // get the listview
        expListView = (ExpandableListView)getActivity().findViewById(R.id.expandable_list_trailer);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<TrailerResponse> call;

        call = apiService.getVideos(movie.getMovieId(), Config.API_KEY);

        pbLoadingIndicator.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                String type = "Trailer";
                List<Trailer>trailers = filter(type, response.body().getResults());

                if (!trailers.isEmpty()) {
                    prepareListData(trailers);

                    listAdapter = new ExpandableListAdapterTrailer(getActivity(), listDataHeader, listDataChild);
                    listAdapter.setTrailers(trailers);

                    // setting list adapter
                    expListView.setAdapter(listAdapter);

                    pbLoadingIndicator.setVisibility(View.GONE);

                    // Listview Group click listener
                    expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v,
                                                    int groupPosition, long id) {
                            return false;
                        }
                    });

                    // Listview Group expanded listener
                    expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                        @Override
                        public void onGroupExpand(int groupPosition) {

                        }
                    });

                    // Listview Group collasped listener
                    expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                        @Override
                        public void onGroupCollapse(int groupPosition) {

                        }
                    });

                    // Listview on child click listener
                    expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v,
                                                    int groupPosition, int childPosition, long id) {
                            return false;
                        }
                    });
                } else {
                    //showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {

            }
        });
    }

    private void prepareListData(List<Trailer> trailers) {
        int index = 0;
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for(Trailer trailer:trailers) {
            listDataHeader.add(trailer.getName());
            List<String> child = new ArrayList<String>();
            child.add(trailer.getKey());

            listDataChild.put(listDataHeader.get(index++), child); // Header, Child data
        }
    }

    public List<Trailer> filter(String type, List<Trailer> results){
        List<Trailer> filterList = new ArrayList<Trailer>();
        for(Trailer result : results) {
            if(type.equalsIgnoreCase(result.getType())){
                filterList.add(result);
            }
        }
        return filterList;
    }
}
