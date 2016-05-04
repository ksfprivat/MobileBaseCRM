package ru.zintur.mobilebase.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.adapters.ContactListAdapter;
import ru.zintur.mobilebase.adapters.CustomerListAdapter;
import ru.zintur.mobilebase.adapters.TabsFragmentAdapter;
import ru.zintur.mobilebase.utils.MessageDialog;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.utils.BaseImporter;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    public  static CustomerListAdapter customerListAdapter;
    public  static ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataBase();
        initToolbar();
        intNavigationBar();
        initTabs();
        customerListAdapter = new CustomerListAdapter(this, DataSource.getCustomers());
        contactListAdapter = new ContactListAdapter(this, DataSource.getContacts());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Init search manager
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(searchViewQueryTextListener);

        // !HACK! - Custom icon of Clear button in SearchView
        ImageView searchClose = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.ic_clear_white_24dp);

        return true;
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
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

// !!! - HACK procedure - set custom navigation icon for toggle button in toolbar
        setCustomNavigationIcon(toggle, R.drawable.ic_menu_white_24dp);
    }


    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
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
                    MessageDialog.underConstructionMsg(MainActivity.this);
                    break;
                case (R.id.navigator_settings):
                    MessageDialog.underConstructionMsg(MainActivity.this);
                    break;
                case (R.id.navigator_update):
                    MessageDialog.showDialog(MainActivity.this, getString(R.string.txtUpdates),
                            String.format("%s: %s\n%s",
                                    getString(R.string.txtDbVersion),
                                    DataSource.getVersion(),
                                    getString(R.string.txtUpdatesNotFound))
                    );
                    break;

                case (R.id.navigator_about):
                    MessageDialog.aboutDialog(MainActivity.this);

                    break;


            }
            return true;
        }
    };

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


    SearchView.OnQueryTextListener searchViewQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    customerListAdapter.getFilter().filter(newText);
                    break;
                case 1:
                    contactListAdapter.getFilter().filter(newText);
                    break;
                case 2:
                    break;
            }
            return false;
        }
    };
}
