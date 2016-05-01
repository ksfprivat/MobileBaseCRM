package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Contact;

public class ContactDetailsActivity extends AbstractDetailsActivity {

    private static final String LOG_TAG = "CONTACT_DETAILS";

    Long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent intent = getIntent();
        contactId = intent.getLongExtra("contactId", 0);


        if (DataSource.getContactById(contactId).getName().isEmpty())
            setTitle(DataSource.getCustomersById(DataSource.getContactById(contactId).getCustomer()).getTitleShort());
         else
            setTitle(DataSource.getContactById(contactId).getName());


        initActionBar();
        initFields();
        fillFields();
   }



    private void fillFields() {
        Contact contact = DataSource.getContactById(contactId);

        ((EditText) findViewById(R.id.etContactName)).setText(contact.getName());
        ((EditText) findViewById(R.id.etContactStatus)).setText(contact.getStatus());
        ((EditText) findViewById(R.id.etContactPhone)).setText(contact.getPhone());
        ((EditText) findViewById(R.id.etContactMobile)).setText(contact.getMobile());
        ((EditText) findViewById(R.id.etContactEmail)).setText(contact.getEmail());
    }
}
