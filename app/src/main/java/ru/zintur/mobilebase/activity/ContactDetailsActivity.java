package ru.zintur.mobilebase.activity;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Contact;

public class ContactDetailsActivity extends AbstractDetailsActivity {

    private static final String LOG_TAG = "CONTACT_DETAILS";
    private static final int BOTTOM_BAR = R.id.activity_contact_bottom_bar;

    Long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Intent intent = getIntent();
        contactId = intent.getLongExtra("contactId", 0);
        bottomBar = (LinearLayout) findViewById(BOTTOM_BAR);

        if (DataSource.getContactById(contactId).getName().isEmpty())
            setTitle(DataSource.getCustomersById(DataSource.getContactById(contactId).getCustomer()).getTitleShort());
         else
            setTitle(DataSource.getContactById(contactId).getName());

        initActionBar();
        initFields();
        fillFields();
        initBottomBar();
   }


    private void fillFields() {
        Contact contact = DataSource.getContactById(contactId);

        ((EditText) findViewById(R.id.etContactName)).setText(contact.getName());
        ((EditText) findViewById(R.id.etContactStatus)).setText(contact.getStatus());
        ((EditText) findViewById(R.id.etContactPhone)).setText(contact.getPhone());
        ((EditText) findViewById(R.id.etContactMobile)).setText(contact.getMobile());
        ((EditText) findViewById(R.id.etContactEmail)).setText(contact.getEmail());
    }



    private void initBottomBar() {
        // Setup bottom bar button onClick event listener
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            View childView = bottomBar.getChildAt(i);
            if (childView instanceof Button) {
                childView.setOnClickListener(bottomBarClickListener);
            }
        }
    }

    private void addContact(Contact contact) {
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, contact.getName())
                .putExtra(ContactsContract.Intents.Insert.JOB_TITLE, contact.getStatus())
                .putExtra(ContactsContract.Intents.Insert.PHONE, contact.getMobile())
                .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, contact.getPhone())
                .putExtra(ContactsContract.Intents.Insert.EMAIL, contact.getEmail())
                .putExtra(ContactsContract.Intents.Insert.COMPANY, DataSource.getCustomersById(DataSource.getContactById(contactId).getCustomer()).getTitleShort()
                );

        startActivity(contactIntent);
    }

    View.OnClickListener bottomBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.activity_contact_btnEdit:
                    setEditMode(true, v);
                    break;
                case R.id.activity_contact_btnDelete:
                    Log.d(LOG_TAG, "CONTACT_DEL");
                    break;
                case R.id.activity_contact_btnAddToContacts:
                    Log.d(LOG_TAG, "CONTACT_ADD");
                    addContact(DataSource.getContactById(contactId));
                    break;
                case R.id.activity_contact_btnApply:
                    setEditMode(false, v);
                    applyEdit();
                    break;
                case R.id.activity_contact_btnCancel:
                    setEditMode(false, v);
                    cancelEdit();
                    break;
            }
        }
    };

    // Dummy function - implement this latter
    private void cancelEdit() {

    }

    // Dummy function - implement this latter
    private void applyEdit() {

    }


    public void onBtnCallToPhoneClick(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + DataSource.getContactById(contactId).getPhone()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactDetailsActivity.this, R.string.txtPhoneIntentFail, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnCallToMobileClick(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+DataSource.getContactById(contactId).getMobile()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactDetailsActivity.this, R.string.txtPhoneIntentFail, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnSendSmsClick(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+DataSource.getContactById(contactId).getMobile()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactDetailsActivity.this, R.string.txtPhoneIntentFail, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBtnSendEmailClick(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+DataSource.getContactById(contactId).getEmail()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactDetailsActivity.this, R.string.txtEmailIntentFail, Toast.LENGTH_SHORT).show();
        }
    }
}
