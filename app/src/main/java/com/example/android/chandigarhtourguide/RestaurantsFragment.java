package com.example.android.chandigarhtourguide;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.android.chandigarhtourguide.Utils.dpToPx;

public class RestaurantsFragment extends Fragment {
    private RecyclerView recyclerView;
    private String likes;

    private String[] names;
    private int[] thumbnail;
    private String[] likeCounts ;


    ArrayList<Place> places = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.content_main, container, false);
        likes = getActivity().getResources().getString(R.string.likes);

        names = new String[]{getString(R.string.elante),
                getString(R.string.rock_garden),getString(R.string.sukhna),getString(R.string.chandi_mandir),getString(R.string.pu),getString(R.string.paara_club)};

        int[] thumbnail = new int[]{
                R.drawable.elante,R.drawable.rock_garden,R.drawable.sukhna,R.drawable.chandi_mandir,R.drawable.pu,R.drawable.club_paara
        };
        likeCounts = new String[]{
                likes,likes,likes,likes,likes,likes
        };
        for (int i=0;i<names.length;i++)
        {
            places.add(new Place(names[i], thumbnail[i], likeCounts[i]));
        }

        getActivity().setTitle(R.string.resto);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        final PlaceAdapter adapter = new PlaceAdapter(getActivity(), places);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(getActivity().getApplicationContext(),10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
            Glide.with(this).load(R.drawable.elante).into((ImageView) rootView.findViewById(R.id.thumbnail));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }


}
