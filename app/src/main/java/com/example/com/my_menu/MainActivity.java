package com.example.com.my_menu;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.com.my_menu.TableInfo.student.*;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ADMIN";
    private List<ListInfo> mlist_info;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlist_info = new ArrayList<ListInfo>();
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: {
                insert();
                return true;
            }
            case R.id.item2: {
                delete();
                return true;
            }
            default:
                return false;
        }
    }

    public void insert() {
        String path2 = "content://com.example.com.visitedDB/insert";
        Uri uri = Uri.parse(path2);
        ContentResolver resolver = MainActivity.this.getContentResolver();
        ContentValues values = new ContentValues();

        for (int i = 0; i <= 4; i++) {
            int a = (int) (Math.random() * 100 + 1);
            values.put("name", "张三" + a);
            values.put("sex", "男");
            resolver.insert(uri, values);
        }

        mlist_info.clear();
        query();
    }

    public void delete() {
        String path2 = "content://com.example.com.visitedDB/delete";
        Uri uri = Uri.parse(path2);
        ContentResolver resolver = MainActivity.this.getContentResolver();
        int y = resolver.delete(uri, null, null);
        if (y == 1) {
            Toast.makeText(MainActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
        }
        mlist_info.clear();
        query();
    }

    public void query() {
        String path1 = "content://com.example.com.visitedDB/query";
        Uri uri = Uri.parse(path1);
        ContentResolver resolver = MainActivity.this.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        Log.i(TAG, "query: " + "我收到数据啦");
        while (cursor.moveToNext()) {
            ListInfo listInfo = new ListInfo();
//            listInfo.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
//            listInfo.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
//            listInfo.setSex(cursor.getString(cursor.getColumnIndex(COLUMN_SEX)));
            listInfo.id = cursor.getInt(cursor.getColumnIndex(_ID));
            listInfo.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            listInfo.sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX));
            mlist_info.add(listInfo);
            listInfo = null;
        }
        cursor.close();
        lv.setAdapter(new MyAdapter());
    }

    static class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mlist_info.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            View v2 = View.inflate(MainActivity.this, R.layout.items, null);
//            //View v2 = LAYOUT_INFLATER_SERVICE(MainActivity.this, R.layout.items, null);
//            TextView tv1 = (TextView) v2.findViewById(R.id.tv1);
//            TextView tv2 = (TextView) v2.findViewById(R.id.tv2);
//            TextView tv3 = (TextView) v2.findViewById(R.id.tv3);
//            tv1.setText(String.valueOf(mlist_info.get(i).getId()));
//            tv2.setText(mlist_info.get(i).getName());
//            tv3.setText(mlist_info.get(i).getSex());
//            return v2;
//        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                Log.i(TAG, "getView: "+i);
                view = View.inflate(MainActivity.this, R.layout.items, null);
                holder = new ViewHolder();
                holder.tv1 = (TextView) view.findViewById(R.id.tv1);
                holder.tv2 = (TextView) view.findViewById(R.id.tv2);
                holder.tv3 = (TextView) view.findViewById(R.id.tv3);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tv1.setText(String.valueOf(mlist_info.get(i).getId()));
            holder.tv2.setText(mlist_info.get(i).getName());
            holder.tv3.setText(mlist_info.get(i).getSex());
            return view;
        }
    }
}
