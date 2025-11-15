package com.example.lab9_cau2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "employees.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "Employee";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (id TEXT PRIMARY KEY, name TEXT, age INTEGER)";
        db.execSQL(sql);

        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-101','Nguyễn Đại Nhân',30)");
        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-102','Trần Đại Nghĩa',28)");
        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-103','Hoàng Đại Lễ',35)");
        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-104','Phạm Đại Trí',31)");
        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-105','Trương Đại Tín',27)");
        db.execSQL("INSERT INTO " + TABLE + " VALUES('NV-106','Hồ Đại Đức',33)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, age FROM " + TABLE + " ORDER BY name", null);
        if (c != null) {
            while (c.moveToNext()) {
                String id = c.getString(0);
                String name = c.getString(1);
                int age = c.getInt(2);
                list.add(new Employee(id, name, age));
            }
            c.close();
        }
        return list;
    }

    public Employee getEmployee(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, age FROM " + TABLE + " WHERE id = ?", new String[]{id});
        Employee emp = null;
        if (c != null) {
            if (c.moveToFirst()) {
                emp = new Employee(c.getString(0), c.getString(1), c.getInt(2));
            }
            c.close();
        }
        return emp;
    }

    public boolean deleteEmployee(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(TABLE, "id = ?", new String[]{id});
        return rows > 0;
    }
}

