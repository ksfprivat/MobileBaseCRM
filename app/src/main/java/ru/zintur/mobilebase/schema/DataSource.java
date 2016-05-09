package ru.zintur.mobilebase.schema;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ru.zintur.mobilebase.schema.dao.ConfigDao;
import ru.zintur.mobilebase.schema.dao.ContactDao;
import ru.zintur.mobilebase.schema.dao.CustomerDao;
import ru.zintur.mobilebase.schema.dao.DaoMaster;
import ru.zintur.mobilebase.schema.dao.DaoSession;
import ru.zintur.mobilebase.schema.dao.UserDao;

public class DataSource {

    private static final String DB_NAME = "base";

    private static SQLiteDatabase db;
    private static String version;
    private static DaoSession session;



    public static void openDatabase(Context ctx) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(ctx, DB_NAME, null);
        db = helper.getWritableDatabase();
        //!! TEMPORARY DUMMY !!
        version = "2015.06.05";
    }


    public static String getVersion() {
        // Dummy
        return version;
    }

    // if newSession == true return new session else return opened session
    private static DaoSession getSession(boolean newSession) {
        if (newSession) {
            return new DaoMaster(db).newSession();
        } else {
            if (session == null) {
                session = new DaoMaster(db).newSession();
            }
            return session;
        }
    }

    public static DaoSession getNewDaoSession() {
        return getSession(true);
    }

    public static DaoSession getDaoSession() {
        return getSession(false);
    }

    public static void closeDatabase() {
        db.close();
    }

    public static List<Customer> getCustomers() {
       return getDaoSession().getCustomerDao().
               queryBuilder().
               orderAsc(CustomerDao.Properties.TitleShort).
               list();
    }

    public static Customer getCustomersById(Long id) {
        return getDaoSession().getCustomerDao().queryBuilder().where(CustomerDao.Properties.Id.eq(id)).unique();

    }

    public static List<Contact> getContacts() {
        return getDaoSession().getContactDao().queryBuilder().
                where(ContactDao.Properties.Name.notEq("")).
                orderAsc(ContactDao.Properties.Name).
                list();
    }


    public static Contact getContactById(Long id) {
        return getDaoSession().getContactDao().queryBuilder().where(ContactDao.Properties.Id.eq(id)).unique();
    }

    public static List<Contact> getContactsByCustomerId(Long customerId) {
        return getDaoSession().getContactDao().queryBuilder().
                where(ContactDao.Properties.Customer.eq(customerId)).
                orderAsc(ContactDao.Properties.Name).list();
    }

    public static String getCustomerLocation(Long customerId) {
        Customer customer = getCustomersById(customerId);
        return String.format("%s %s %s", customer.getDistrict(), customer.getCity(), customer.getStreet());
    }

    public static User getUser(String user) {
        return getDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.User.eq(user)).unique();
    }

    public static Config getConfig(String version) {
        return getDaoSession().getConfigDao().
                queryBuilder().
                where(ConfigDao.Properties.Version.eq(version)).unique();
    }

    public static void saveConfig(Config config) {
        if (config != null) {
            getDaoSession().getConfigDao().update(config);
        }
    }

    public static void clearConfig(Config config) {
        config.setUser("");
        config.setPassword("");
        config.setState("");
    }


}
