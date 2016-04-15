package ru.zintur.mobilebase.schema.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import ru.zintur.mobilebase.schema.domains.Customer;
import ru.zintur.mobilebase.schema.domains.Contact;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig customerDaoConfig;
    private final DaoConfig contactDaoConfig;

    private final CustomerDao customerDao;
    private final ContactDao contactDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        customerDaoConfig = daoConfigMap.get(CustomerDao.class).clone();
        customerDaoConfig.initIdentityScope(type);

        contactDaoConfig = daoConfigMap.get(ContactDao.class).clone();
        contactDaoConfig.initIdentityScope(type);

        customerDao = new CustomerDao(customerDaoConfig, this);
        contactDao = new ContactDao(contactDaoConfig, this);

        registerDao(Customer.class, customerDao);
        registerDao(Contact.class, contactDao);
    }
    
    public void clear() {
        customerDaoConfig.getIdentityScope().clear();
        contactDaoConfig.getIdentityScope().clear();
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

}
