package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.adapters.ContactListAdapter;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Contact;
import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerDetailsActivity extends AbstractDetailsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();
        Long customerId = intent.getLongExtra("customerId", 0);
        Customer customer = DataSource.getCustomersById(customerId);

        initActionBar();
        initFields();
        fillFields(customer);
        inflateContactList(customer);

    }

    private void  fillFields(Customer customer) {

        EditText etTitleShort = (EditText) findViewById(R.id.etTitleShort);
        EditText etTitleFull = (EditText) findViewById(R.id.etTitleFull);
        EditText etRegion = (EditText) findViewById(R.id.etRegion);
        EditText etCity = (EditText) findViewById(R.id.etCity);
        EditText etAddress = (EditText) findViewById(R.id.etAddress);

        setTitle(customer.getTitleShort());
        etTitleShort.setText(customer.getTitleShort());
        etTitleFull.setText(customer.getTitleFull());
        etCity.setText(customer.getCity());
        etAddress.setText(customer.getStreet());
        etRegion.setText(customer.getDistrict());
    }


    private void inflateContactList(Customer customer) {

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout ltCustomerList = (LinearLayout) findViewById(R.id.layout_customer_contacts_list);

        View contactList = inflater.inflate(R.layout.fragment_contact, ltCustomerList, true);

        List<Contact> contacts = customer.getContacts();

        ContactListAdapter adapter = new ContactListAdapter(this, contacts);

        ListView lvContacts = (ListView) findViewById(R.id.lvContacts);

        lvContacts.setAdapter(adapter);


    }



}
