package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    Drawable originalDrawable;

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
            EditText field = (EditText) v.findViewById(v.getId());

        field.selectAll();
            field.setFocusable(true);
            String str = ((EditText) v.findViewById(v.getId())).getText().toString();
            Toast.makeText(CustomerDetails.this, str, Toast.LENGTH_SHORT).show();



            showPopupMenu(v);
            return false;
        }
    };

    private void showPopupMenu(final View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.menu_context);
        EditText field = (EditText) v.findViewById(v.getId());
        field.selectAll();
        field.setFocusable(true);
        // ! HACK ! - Customize standard style popup menu - force show icon
        Utils.setPopupMenuForceIconShow(popupMenu);
        popupMenu.setOnMenuItemClickListener(popupMenuItemClickListener);


        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {

            @Override
            public void onDismiss(PopupMenu menu) {

                EditText field = (EditText) v.findViewById(v.getId());


            }
        });

        popupMenu.show();
    }


    PopupMenu.OnMenuItemClickListener popupMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.menu_context_copy) {
                Toast.makeText(CustomerDetails.this, "Copy", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    };

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

        originalDrawable = etTitleShort.getBackground();

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
