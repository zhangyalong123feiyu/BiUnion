package com.bibinet.biunion.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bibinet on 2017-5-3.
 */

public class PhoneNumberUtils {
    /**
     * 验证手机号格式
     */
    public static boolean isMobileNumber(String mobilenumber) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        m = p.matcher(mobilenumber);
        b = m.matches();
        return b;
    }
}
