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

        EditText etTitleShort = (EditText) findViewById(R.id.etTitleShort);
        EditText etTitleFull = (EditText) findViewById(R.id.etTitleFull);
        EditText etRegion = (EditText) findViewById(R.id.etRegion);
        EditText etCity = (EditText) findViewById(R.id.etCity);
        EditText etAddress = (EditText) findViewById(R.id.etAddress);


        etTitleShort.setText(DataSource.getCustomersById(customerId).getTitleShort());
        etTitleFull.setText(DataSource.getCustomersById(customerId).getTitleFull());
        etCity.setText(DataSource.getCustomersById(customerId).getCity()+DataSource.getCustomersById(customerId).getBuilding());

        etRegion.setText("dfdf");

    }
}
