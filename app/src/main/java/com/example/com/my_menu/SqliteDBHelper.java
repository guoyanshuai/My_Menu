package com.example.com.my_menu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.com.my_menu.TableInfo.student.COLUMN_NAME;
import static com.example.com.my_menu.TableInfo.student.COLUMN_SEX;
import static com.example.com.my_menu.TableInfo.student.TABLE_NAME;

/**
 * Created by Administrator on 2016/7/17.
 */
public class SqliteDBHelper extends SQLiteOpenHelper {
    public SqliteDBHelper(Context context) {
        super(context, "stu.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table "+ TABLE_NAME+
                    "("+_ID+" integer primary key autoincrement," +
                    COLUMN_NAME+" char(20)," +
                    COLUMN_SEX+" char(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("数据库要更新啦！！！");
    }
}
