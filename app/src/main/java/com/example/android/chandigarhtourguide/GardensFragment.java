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

public class GardensFragment extends Fragment {
    private RecyclerView recyclerView;

    private String[] names = new String[]{"Rose Garden",
            "Rock Garden","Garden of Fragnance","Terrased Garden"};

    private int[] thumbnail = new int[]{
            R.drawable.rose_garden,R.drawable.rock_garden,R.drawable.garden_of_fragrance,R.drawable.terrased_garden,
    };
    private String[] likeCounts = new String[]{
            "115 likes","115 likes","115 likes","115 likes"
    };

    ArrayList<Place> places = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        for (int i=0;i<names.length;i++)
        {
            places.add(new Place(names[i], thumbnail[i], likeCounts[i]));
        }

        View rootView =  inflater.inflate(R.layout.content_main, container, false);
        getActivity().setTitle(R.string.gardens);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        final PlaceAdapter adapter = new PlaceAdapter(getActivity(), places);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        try {
            Glide.with(this).load(R.drawable.elante).into((ImageView) rootView.findViewById(R.id.thumbnail));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
