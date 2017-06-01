package com.bibinet.biunion.project.builder;

import org.xutils.common.Callback;

/**
 * Created by bibinet on 2017-5-31.
 */

public class MyCacheCallBack implements Callback.CacheCallback<String>{
    @Override
    public boolean onCache(String s) {
        return false;
    }

    @Override
    public void onSuccess(String s) {

    }

    @Override
    public void onError(Throwable throwable, boolean b) {

    }

    @Override
    public void onCancelled(CancelledException e) {

    }

    @Override
    public void onFinished() {

    }
}
