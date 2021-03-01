package com.project.verification.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceData {

    public static final String MyPREFERENCES = "MyPrefs" ;

    public static final String CRIMINAL_RECORD_TYPE = "";
    public static final String CRIMINAL_RECORD = "";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static String getCriminalRecordType(Context context) {
        return getSharedPreferences(context).getString(CRIMINAL_RECORD_TYPE, null);
    }

    public static void setCriminalRecordType(Context context,String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CRIMINAL_RECORD_TYPE, newValue);
        editor.commit();
    }

    public static String getCriminalRecord(Context context) {
        return getSharedPreferences(context).getString(CRIMINAL_RECORD, null);
    }

    public static void setCriminalRecord(Context context,String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CRIMINAL_RECORD, newValue);
        editor.commit();
    }
}
