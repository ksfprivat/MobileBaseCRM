package ru.genearator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Generator {

    private static Schema schema;

    private static Entity customer;
    private static Entity contact;

    public static void main(String args[]) throws Exception {

        System.out.println("Base Mobile. Schema generator...");

        schema = new Schema(1, "ru.zintur.mobilebase.schema");
        schema.setDefaultJavaPackageDao("ru.zintur.mobilebase.schema.dao");
        addCustomer();
        addContact();
        addConfig();
        addUsers();

        new DaoGenerator().generateAll(schema, "../MobileBaseCRM/app/src/main/java");
    }


    private static void addUsers() {
        customer = schema.addEntity("User");
        customer.setSkipTableCreation(true);
        customer.addIdProperty();
        customer.addStringProperty("user").columnName("user");
        customer.addStringProperty("password").columnName("password");
    }

    private static void addConfig() {
        customer = schema.addEntity("Config");
        customer.setSkipTableCreation(true);
        customer.addIdProperty();
        customer.addStringProperty("state").columnName("state");
        customer.addStringProperty("user").columnName("user");
        customer.addStringProperty("password").columnName("password");
        customer.addStringProperty("version").columnName("version");
        customer.addStringProperty("uptime").columnName("uptime");
        customer.addStringProperty("evolution").columnName("evolution");
    }


    private static void addCustomer() {
        customer = schema.addEntity("Customer");
        customer.setSkipTableCreation(true);
        customer.addIdProperty();
        customer.addStringProperty("TitleShort").notNull().columnName("TitleShort");
        customer.addStringProperty("TitleFull").columnName("TitleFull");
        customer.addStringProperty("Depart").columnName("Depart");
        customer.addStringProperty("Post").columnName("Post");
        customer.addStringProperty("District").columnName("District");
        customer.addStringProperty("City").columnName("City");
        customer.addStringProperty("Street").columnName("Street");
        customer.addStringProperty("Building").columnName("Building");
        customer.addStringProperty("Notes").columnName("Notes");
        customer.addStringProperty("INN").columnName("INN");
    }


    private static void addContact() {
        contact = schema.addEntity("Contact");
        contact.setSkipTableCreation(true);
        contact.addIdProperty();
        contact.addStringProperty("Name").columnName("Name");
        contact.addStringProperty("Status").columnName("Status");
        contact.addStringProperty("Phone").columnName("Phone");
        contact.addStringProperty("Mobile").columnName("Mobile");
        contact.addStringProperty("Email").columnName("Email");
        contact.addStringProperty("Fax").columnName("Fax");
        contact.addStringProperty("PhoneEx").columnName("PhoneEx");

        Property customerId = contact.addLongProperty("customer").notNull().getProperty();
        ToMany customerToContacts = customer.addToMany(contact, customerId);
        customerToContacts.setName("contacts");

    }


}