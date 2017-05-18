package com.bibinet.biunion.project.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 *
 */
public class SharedPresUtils {

	private static SharedPresUtils sharedPresUtils = new SharedPresUtils();

	private static SharedPreferences pres = null;
	private static final String FILE_NAME = "logininfo";
	private static final int MODE = Context.MODE_PRIVATE;

	private SharedPresUtils() {
	}

	public static SharedPresUtils getsSharedPresUtils(Context context) {
		if (pres == null)
			pres = context.getSharedPreferences(FILE_NAME, MODE);
		return sharedPresUtils;
	}

	public String getString(String key, String defValue) {
		return pres.getString(key, defValue);
	}

	public boolean getBoolean(String key, Boolean defValue) {
		return pres.getBoolean(key, defValue);
	}

	public Map loadUserInfo() {
		Map map = new HashMap();

		map.put("rememberflag",pres.getBoolean("rememberflag", false));
		map.put("autoflag",pres.getBoolean("autoflag", false));
		map.put("username",pres.getString("username", ""));
		map.put("userpwd",pres.getString("userpwd", ""));
		
		return map;
	}

	public void putString(String key, String value) {
		Editor editor = pres.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void putBoolean(String key, Boolean value) {
		Editor editor = pres.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 
	 * �洢�û��ĵ�¼��Ϣ
	 * 
	 * @param rememberflag �Ƿ��ס����
	 * @param autoflag �Ƿ��Զ���¼
	 * @param username ��¼��
	 * @param userpwd ����
	 */
	public void saveUserInfo(boolean rememberflag, boolean autoflag, String username, String userpwd) {
		Editor editor = pres.edit();
		editor.putBoolean("rememberflag", rememberflag);
		editor.putBoolean("autoflag", autoflag);
		editor.putString("username", username);
		editor.putString("userpwd", userpwd);
		editor.commit();
	}

}
