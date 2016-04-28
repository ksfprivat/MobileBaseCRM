package ru.zintur.mobilebase.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.adapters.CustomerTabsFragmentAdapter;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerDetailsActivity extends AbstractDetailsActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Intent intent = getIntent();
        Long customerId = intent.getLongExtra("customerId", 0);

        setTitle(DataSource.getCustomersById(customerId).getTitleShort());

        initActionBar();
        initTabs(customerId);
    }


    private void initTabs(Long customerId) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        CustomerTabsFragmentAdapter adapter = new CustomerTabsFragmentAdapter(this, customerId, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
