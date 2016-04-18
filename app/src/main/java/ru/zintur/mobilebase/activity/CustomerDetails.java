package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

        initActionBar();

        EditText etTitleShort = (EditText) findViewById(R.id.etTitleShort);
        EditText etTitleFull = (EditText) findViewById(R.id.etTitleFull);
        EditText etRegion = (EditText) findViewById(R.id.etRegion);
        EditText etCity = (EditText) findViewById(R.id.etCity);
        EditText etAddress = (EditText) findViewById(R.id.etAddress);

        Customer customer = DataSource.getCustomersById(customerId);

        setTitle(customer.getTitleShort());
        etTitleShort.setText(customer.getTitleShort());
        etTitleFull.setText(customer.getTitleFull());
        etCity.setText(customer.getCity());
        etAddress.setText(customer.getStreet());
        etRegion.setText(customer.getDistrict());


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void initActionBar() {
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);


 }
    }


}
