package ru.zintur.mobilebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        Intent intent = getIntent();

        String customerId = intent.getStringExtra("customerId");

        Log.d("TagX", String.valueOf(customerId));

    }
}
