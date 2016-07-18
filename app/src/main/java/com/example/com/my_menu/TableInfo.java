package com.example.com.my_menu;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/7/17.
 */

public class TableInfo {

    public class student implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SEX = "sex";
    }

}
