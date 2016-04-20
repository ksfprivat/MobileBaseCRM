package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Customer;
import ru.zintur.mobilebase.utils.Utils;

public class CustomerDetails extends AppCompatActivity {

    // HashMap of all EditText elements
    SparseArray<EditText> fields = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();
        Long customerId = intent.getLongExtra("customerId", 0);
        Customer customer = DataSource.getCustomersById(customerId);

        initActionBar();
        initFields(customer);
    }


    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            String str = ((EditText) v.findViewById(v.getId())).getText().toString();
            Toast.makeText(CustomerDetails.this, str, Toast.LENGTH_SHORT).show();
            showPopupMenu(v);
            return false;
        }
    };

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_context);
        popupMenu.show();
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

    private void  initFields(Customer customer) {

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

        // Set longClickListener or all EditText in Layout
        LinearLayout customerForm = (LinearLayout) findViewById(R.id.form_customer_details);
        Utils.findAllEditTexts(customerForm, fields);
        for(int i = 0; i < fields.size(); i++) {
            int key = fields.keyAt(i);
            fields.get(key).setOnLongClickListener(longClickListener);
        }
    }

}
