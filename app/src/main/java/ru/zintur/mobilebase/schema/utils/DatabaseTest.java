package ru.zintur.mobilebase.schema.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import ru.zintur.mobilebase.schema.domains.Contact;
import ru.zintur.mobilebase.schema.domains.Customer;
import ru.zintur.mobilebase.schema.dao.CustomerDao;
import ru.zintur.mobilebase.schema.dao.DaoMaster;
import ru.zintur.mobilebase.schema.dao.DaoSession;


public class DatabaseTest {

   private final static String LOG_TAG = "dbTest";

   public static void runTest(Context ctx) {

        System.out.println("Database test");
        SQLiteDatabase db;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, "base", null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        CustomerDao customerDao = daoSession.getCustomerDao();

        List<Customer> customers = customerDao.loadAll();

        for (Customer customer : customers) {
            Log.i(LOG_TAG, customer.getTitleShort());

            List<Contact> contacts = customer.getContacts();

            for (Contact contact: contacts ) {
                Log.i(LOG_TAG, "\t\t+ "+contact.getName());
            }
        }
    }

}



