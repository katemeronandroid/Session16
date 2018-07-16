package com.emarkova.session16;

import android.content.Context;

public class DBManager {
    private DBHelper helper;

    public DBManager(Context context) {
        this.helper = new DBHelper(context);
    }

    public void addRow(String str) {
        helper.insertTable(helper.getWritableDatabase(), str);
    }

    public String getRow(String result) {
        return helper.selectRow(helper.getReadableDatabase(), result);
    }

}
