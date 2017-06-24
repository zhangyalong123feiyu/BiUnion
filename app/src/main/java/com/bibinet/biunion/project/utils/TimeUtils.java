package com.bibinet.biunion.project.utils;

import java.text.SimpleDateFormat;

/**
 * Created by 吴昊 on 2017-6-17.
 */

public class TimeUtils {

    public static String getTimeFormat(String templet, long time){
        SimpleDateFormat format = new SimpleDateFormat(templet);
        return format.format(time);
    }
}
