package com.jordao.promoeasy.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordao.promoeasy.HomeActivity;
import com.jordao.promoeasy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public HomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //((HomeActivity) activity).onSectionAttached(
        //        getArguments().getInt(ARG_SECTION_NUMBER));
    }
}