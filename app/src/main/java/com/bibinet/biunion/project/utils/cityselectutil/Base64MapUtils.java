package com.bibinet.biunion.project.utils.cityselectutil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by bibinet on 2017-6-14.
 */
public class Base64MapUtils {
    /**
     * @param string
     *            字符串
     * @return 转化成的位图
     */
    public static Bitmap stringToBitmap(String string) {
// 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
