package com.bibinet.biunion.project.utils.cityselectutil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;


import com.bibinet.biunion.project.bean.cityselectbean.CityBean;
import com.github.promeg.pinyinhelper.Pinyin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author zp on 2016/1/26.
 */
public class DBManager {
    private static final String ASSETS_NAME = "china_cities.db";
    private static final String DB_NAME = "china_cities.db";
    private static final String TABLE_NAME = "city";
    private static final String NAME = "name";
    private static final String PINYIN = "pinyin";
    private static final int BUFFER_SIZE = 1024;
    private static String DB_PATH;
    private static Context mContext;
    private static DBManager instance;

    public static DBManager getInstance(Context context){
        mContext=context;
        if(instance==null){
            synchronized (DBManager.class){
                if(instance==null){
                    instance=new DBManager();
                }
            }
        }
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + context.getPackageName() + File.separator + "databases" + File.separator;
        return instance;
    }



    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void copyDBFile() {
        File dir = new File(DB_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(ASSETS_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取所有城市
     *
     * @return
     */
    public List<CityBean> getAllCities() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        List<CityBean> result = new ArrayList<>();
        CityBean city;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<name.length();i++){
                sb.append(Pinyin.toPinyin(name.charAt(i)).charAt(0));
            }

            city = new CityBean();
            city.setCityName(name);
            city.setCityPinyin(pinyin);
            city.setHeadTag(sb.toString());
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }

    /**
     * 通过名字或者拼音搜索
     *
     * @param keyword
     * @return
     */
    public List<CityBean> searchCity(final String keyword) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where name like \"%" + keyword
                + "%\" or pinyin like \"%" + keyword + "%\"", null);
        List<CityBean> result = new ArrayList<>();
        CityBean city;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String pinyin = cursor.getString(cursor.getColumnIndex(PINYIN));
            city = new CityBean();
            city.setCityName(name);
            city.setCityPinyin(pinyin);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }

    /**
     * a-z排序
     */
    private class CityComparator implements Comparator<CityBean> {
        @Override
        public int compare(CityBean lhs, CityBean rhs) {
            String a = lhs.getCityPinyin().substring(0, 1);
            String b = rhs.getCityPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }
}
