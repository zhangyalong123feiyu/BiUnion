package com.bibinet.biunion.project.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bibinet.biunion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Ask extends Fragment {


    private View view;

    public Fragment_Ask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_ask, container, false);
        return view;
    }

}
