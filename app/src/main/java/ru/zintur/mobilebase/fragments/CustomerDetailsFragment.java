package ru.zintur.mobilebase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.zintur.mobilebase.R;

public class CustomerDetailsFragment extends AbstractFragment{

    private static final int LAYOUT = R.layout.fragment_customer_details;
    private static final int TITLE = R.string.customer_details_tab_item_customers;

    public CustomerDetailsFragment() {
        // Default constructor
    }

    public static CustomerDetailsFragment getInstance(Context ctx) {
        Bundle args = new Bundle();
        CustomerDetailsFragment fragment = new CustomerDetailsFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(LAYOUT, container, false);



      return view;
    }






}
