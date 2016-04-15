package ru.zintur.mobilebase.schema.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import ru.zintur.mobilebase.schema.domains.Contact;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT".
*/
public class ContactDao extends AbstractDao<Contact, Long> {

    public static final String TABLENAME = "CONTACT";

    /**
     * Properties of entity Contact.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "Name", false, "Name");
        public final static Property Status = new Property(2, String.class, "Status", false, "Status");
        public final static Property Phone = new Property(3, String.class, "Phone", false, "Phone");
        public final static Property Mobile = new Property(4, String.class, "Mobile", false, "Mobile");
        public final static Property Email = new Property(5, String.class, "Email", false, "Email");
        public final static Property Fax = new Property(6, String.class, "Fax", false, "Fax");
        public final static Property PhoneEx = new Property(7, String.class, "PhoneEx", false, "PhoneEx");
        public final static Property Customer = new Property(8, long.class, "customer", false, "CUSTOMER");
    };

    private Query<Contact> customer_ContactsQuery;

    public ContactDao(DaoConfig config) {
        super(config);
    }
    
    public ContactDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(2, Name);
        }
 
        String Status = entity.getStatus();
        if (Status != null) {
            stmt.bindString(3, Status);
        }
 
        String Phone = entity.getPhone();
        if (Phone != null) {
            stmt.bindString(4, Phone);
        }
 
        String Mobile = entity.getMobile();
        if (Mobile != null) {
            stmt.bindString(5, Mobile);
        }
 
        String Email = entity.getEmail();
        if (Email != null) {
            stmt.bindString(6, Email);
        }
 
        String Fax = entity.getFax();
        if (Fax != null) {
            stmt.bindString(7, Fax);
        }
 
        String PhoneEx = entity.getPhoneEx();
        if (PhoneEx != null) {
            stmt.bindString(8, PhoneEx);
        }
        stmt.bindLong(9, entity.getCustomer());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Contact readEntity(Cursor cursor, int offset) {
        Contact entity = new Contact( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Status
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Phone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Mobile
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Fax
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // PhoneEx
            cursor.getLong(offset + 8) // customer
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Contact entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStatus(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMobile(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEmail(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFax(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPhoneEx(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCustomer(cursor.getLong(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Contact entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Contact entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "contacts" to-many relationship of Customer. */
    public List<Contact> _queryCustomer_Contacts(long customer) {
        synchronized (this) {
            if (customer_ContactsQuery == null) {
                QueryBuilder<Contact> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Customer.eq(null));
                customer_ContactsQuery = queryBuilder.build();
            }
        }
        Query<Contact> query = customer_ContactsQuery.forCurrentThread();
        query.setParameter(0, customer);
        return query.list();
    }

}
