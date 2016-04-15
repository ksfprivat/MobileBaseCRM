package ru.zintur.mobilebase;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import ru.zintur.mobilebase.adapters.TabsFragmentAdapter;
import ru.zintur.mobilebase.schema.utils.BaseImporter;
import ru.zintur.mobilebase.schema.DataSource;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DB_NAME = "base";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataBase();
        initToolbar();
        intNavigationBar();
        initTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //initCustomerFragment();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataSource.closeDatabase();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_title);
        toolbar.setSubtitle(R.string.app_subtitle);
        setSupportActionBar(toolbar);
    }

    private void initDataBase() {
        if (!BaseImporter.checkDataBase())
            BaseImporter.importDatabase(this);

        DataSource.openDatabase(this);
    }

    private void intNavigationBar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigator);
        navigationView.setNavigationItemSelectedListener(this);

// !!! - HACK procedure - set custom navigation icon for toggle button in toolbar
        setCustomNavigationIcon(toggle, R.drawable.ic_menu_white_36dp);
    }


    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case (R.id.navigator_customer_item):
                viewPager.setCurrentItem(0);
                break;

            case (R.id.navigator_contact_item):
                viewPager.setCurrentItem(1);
                break;

            case (R.id.navigator_contract_item):
                viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }

    // !!! - HACK procedure - set custom navigation icon for toggle button in toolbar
    private void setCustomNavigationIcon(ActionBarDrawerToggle toggle, int icon) {
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

}
