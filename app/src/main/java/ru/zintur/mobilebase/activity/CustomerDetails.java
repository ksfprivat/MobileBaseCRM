package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();
        Long customerId = intent.getLongExtra("customerId", 0);

        EditText etTitleShort = (EditText) findViewById(R.id.etShortTitle);
        EditText etTitleFull = (EditText) findViewById(R.id.etFullTitle);

        etTitleShort.setText(DataSource.getCustomersById(customerId).getTitleShort());
        etTitleFull.setText(DataSource.getCustomersById(customerId).getTitleFull());


    }
}
