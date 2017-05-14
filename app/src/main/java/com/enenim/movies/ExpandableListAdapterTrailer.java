package com.enenim.movies;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.enenim.movies.model.Trailer;
import com.enenim.movies.util.CommonUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by enenim on 5/13/17.
 */

public class ExpandableListAdapterTrailer extends ExpandableListAdapter{
    private List<Trailer> trailers;

    public ExpandableListAdapterTrailer (Context context, List<String> listDataHeader,
                                        HashMap<String, List<String>> listChildData) {

        super(context, listDataHeader, listChildData);
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.get_context()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_trailer, null);
        }


        final Trailer trailer = getTrailers().get(groupPosition);
        final String childText = trailer.getName() + " [ " + trailer.getSize() + " ]";


        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        ImageButton playButton = (ImageButton) convertView
                .findViewById(R.id.image_button_play);

        playButton.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                CommonUtil.playYoutubeVideo(trailer.getKey(), get_context());
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.get_context()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_trailer, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
    }
}
