package ru.zintur.mobilebase.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.adapters.CustomerTabsFragmentAdapter;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerActivity extends AbstractDetailsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Intent intent = getIntent();
        Long customerId = intent.getLongExtra("customerId", 0);
        Customer customer = DataSource.getCustomersById(customerId);

        initActionBar();
        initTabs();
    }


    private void initTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        CustomerTabsFragmentAdapter adapter = new CustomerTabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
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

}
