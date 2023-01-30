package com.example.namazlog.DataLayer;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.namazlog.Models.Namaz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private class Param
    {
        public static final String DB_NAME = "namaz_log";
        public static final String TABLE_NAME = "namaz";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_DATE = "date";
        private static final String COLUMN_NAMAZ = "namaz_number";
        private static final String COLUMN_ADA = "ada";
        private static final String COLUMN_JAMAT = "jamat";
        private static final String COLUMN_RAQAT = "raqat";


    }
    public DBHelper(@Nullable Context context) {
        super(context, Param.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + Param.TABLE_NAME + "("
                + Param.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Param.COLUMN_DATE + " TEXT,"
                + Param.COLUMN_NAMAZ + " INTEGER,"
                + Param.COLUMN_ADA + " INTEGER,"
                + Param.COLUMN_JAMAT + " INTEGER,"
                + Param.COLUMN_RAQAT + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + Param.TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertNamaz(Namaz namaz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Param.COLUMN_DATE, namaz.date);
        values.put(Param.COLUMN_NAMAZ, namaz.namaz_num);
        values.put(Param.COLUMN_ADA, namaz.ada);
        values.put(Param.COLUMN_JAMAT, namaz.jama);
        values.put(Param.COLUMN_RAQAT, namaz.raqat);

        db.insert(Param.TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Namaz> selectAllNamaz() {
        List<Namaz> namaz = new ArrayList<>();

        String sql = "SELECT * FROM " + Param.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Param.COLUMN_ID));
                String date = cursor.getString(cursor.getColumnIndex(Param.COLUMN_DATE));
                int namaznum = cursor.getInt(cursor.getColumnIndex(Param.COLUMN_NAMAZ));
                int ada = cursor.getInt(cursor.getColumnIndex(Param.COLUMN_ADA));
                int jamat = cursor.getInt(cursor.getColumnIndex(Param.COLUMN_JAMAT));
                int raqat = cursor.getInt(cursor.getColumnIndex(Param.COLUMN_RAQAT));
                namaz.add(new Namaz(id, date, namaznum, ada, jamat, raqat));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return namaz;
    }
}
