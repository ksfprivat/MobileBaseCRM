package ru.zintur.mobilebase.schema.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaseImporter extends SQLiteOpenHelper {

    private final static String LOG_TAG = "dbImporter";

    private final static String DB_PATH = "/data/data/ru.zintur.mobilebase/databases/";

    private static String DB_NAME = "base";

    public SQLiteDatabase db;

    private final Context ctx;


    public BaseImporter(Context context) {
        super(context, DB_NAME , null, 1);
        this.ctx = context;

    }

    public static void importDatabase(Context ctx) {
        BaseImporter baseImporter = new BaseImporter(ctx);
        Log.d(LOG_TAG, "Import database to device...");
        try {
            baseImporter.createDataBase();
            Log.d(LOG_TAG, "Done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        baseImporter.openDataBase();
        baseImporter.db.close();
    }


    public static boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // базы данных не существует
        }
        return checkDB != null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void createDataBase() throws IOException {

        //вызывая этот метод создаем пустую базу, позже она будет перезаписана
        this.getReadableDatabase();

        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    private void copyDataBase() throws IOException {

            //Открываем локальную БД как входящий поток
            InputStream myInput = ctx.getAssets().open(DB_NAME);

            //Путь ко вновь созданной БД
            String outFileName = DB_PATH + DB_NAME;

            //Открываем пустую базу данных как исходящий поток
            OutputStream myOutput = new FileOutputStream(outFileName);

            //перемещаем байты из входящего файла в исходящий
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //закрываем потоки
            myOutput.flush();
            myOutput.close();
            myInput.close();

    }



    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(db != null)
            db.close();

        super.close();
    }
}
