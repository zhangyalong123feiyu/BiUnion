package com.bibinet.biunion.project.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bibinet.biunion.R;
import com.bibinet.biunion.project.ui.activity.CoustomAskActivity;
import com.bibinet.biunion.project.ui.activity.ExpertsAnswerActivity;
import com.bibinet.biunion.project.ui.activity.TenderHelpActivity;
import com.bibinet.biunion.project.ui.activity.WriteTenderBook;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Ask extends Fragment {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_imageright)
    ImageView titleImageright;
    @BindView(R.id.title_imageleft)
    ImageView titleImageleft;
    @BindView(R.id.customService)
    LinearLayout customService;
    @BindView(R.id.experts)
    LinearLayout experts;
    @BindView(R.id.hotelSure)
    LinearLayout hotelSure;
    @BindView(R.id.carSure)
    LinearLayout carSure;
    @BindView(R.id.tenderBook)
    LinearLayout tenderBook;
    @BindView(R.id.tenderHelp)
    LinearLayout tenderHelp;
    Unbinder unbinder;
    private View view;

    public Fragment_Ask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ask, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        title.setText("比比驿站");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.customService, R.id.experts, R.id.hotelSure, R.id.carSure, R.id.tenderBook, R.id.tenderHelp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.customService:
                startActivity(new Intent(getActivity(), CoustomAskActivity.class));
                break;
            case R.id.experts:
                startActivity(new Intent(getActivity(), ExpertsAnswerActivity.class));
                break;
            case R.id.hotelSure:
                break;
            case R.id.carSure:
                break;
            case R.id.tenderBook:
                startActivity(new Intent(getActivity(), WriteTenderBook.class));
                break;
            case R.id.tenderHelp:
                startActivity(new Intent(getActivity(), TenderHelpActivity.class));
                break;
        }
    }
}
