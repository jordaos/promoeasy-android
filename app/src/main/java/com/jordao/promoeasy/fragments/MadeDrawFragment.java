package com.jordao.promoeasy.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.jordao.promoeasy.HomeActivity;
import com.jordao.promoeasy.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MadeDrawFragment extends Fragment {
    private CheckBox like_photo;
    private CheckBox follow_profiles;
    private CheckBox tag_friends;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public MadeDrawFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_made_draw, container, false);

        like_photo = (CheckBox) rootView.findViewById(R.id.checkbox_like_photo);
        follow_profiles = (CheckBox) rootView.findViewById(R.id.checkbox_follow_profiles);
        tag_friends = (CheckBox) rootView.findViewById(R.id.checkbox_tag_friends);

        setListeners();

        return rootView;
    }

    private void setListeners(){
        like_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked())
                    Toast.makeText(getActivity().getApplicationContext(), "Like photo checked", Toast.LENGTH_SHORT);
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Like photo unchecked", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}