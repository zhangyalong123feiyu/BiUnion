package com.bibinet.biunion.project.ui.fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.bibinet.biunion.project.application.Constants;
import com.bibinet.biunion.project.utils.LoactionUtils;

/**
 * Created by bibinet on 2017-6-20.
 */

public class BaseFragment extends Fragment{

    public boolean isHasPermisson(String permisson){
      return   ContextCompat.checkSelfPermission(getActivity(),permisson)==PackageManager.PERMISSION_GRANTED;
    }
    public void reQuestPermisson(String permisson,int reQustCode){
          requestPermissions(new String[]{permisson},reQustCode);

    }
}
