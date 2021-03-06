package ru.zintur.mobilebase.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ru.zintur.mobilebase.activity.CustomerDetailsActivity;
import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.activity.MainActivity;
import ru.zintur.mobilebase.schema.Customer;
import ru.zintur.mobilebase.schema.DataSource;

public class CustomerFragment extends AbstractFragment{

    private static final int LAYOUT = R.layout.fragment_customer;
    private static final int TITLE = R.string.tab_item_customers;

    public CustomerFragment() {
        // Default constructor
    }

    public static CustomerFragment getInstance(Context ctx) {
        Bundle args = new Bundle();
        CustomerFragment fragment = new CustomerFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(LAYOUT, container, false);

        ListView lvCustomers = (ListView) view.findViewById(R.id.lvCustomers);
        lvCustomers.setAdapter(MainActivity.customerListAdapter);

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Customer customer = ((Customer) MainActivity.customerListAdapter.getItem(position));

                Intent intent = new Intent(container.getContext(), CustomerDetailsActivity.class);

                intent.putExtra("customerId", customer.getId());
                startActivity(intent);
            }
        });


      return view;
    }






}
