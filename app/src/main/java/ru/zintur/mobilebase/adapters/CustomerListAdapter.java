package ru.zintur.mobilebase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.domains.Customer;


public class CustomerListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater ltInflater;
    List<Customer> items;


    public CustomerListAdapter(Context context, List<Customer> customers) {
        ctx = context;
        items = customers;
        ltInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = ltInflater.inflate(R.layout.list_item_customer, parent, false);
        }

        Customer customer = (Customer) getItem(position);
        ((TextView) view.findViewById(R.id.txtTitleShort)).setText(customer.getTitleShort());
        ((TextView) view.findViewById(R.id.txtAddress)).setText(customer.getCity());

        return view;
    }
}
