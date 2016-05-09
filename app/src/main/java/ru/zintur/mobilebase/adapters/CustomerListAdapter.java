package ru.zintur.mobilebase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.Customer;


public class CustomerListAdapter extends BaseAdapter implements Filterable {

    Context ctx;
    LayoutInflater ltInflater;
    List<Customer> items;
    List<Customer> filteredItems;
    ValueFilter valueFilter;


    public CustomerListAdapter(Context context, List<Customer> customers) {
        ctx = context;
        items = customers;
        filteredItems = items;
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

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

// Inner class implemented filter in items of ListView. Return filtered List of customer or original customer list (if constraint not find)
    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Customer> filterList = new ArrayList<>();
                for (int i = 0; i < filteredItems.size(); i++) {
                    if ( (filteredItems.get(i).getTitleShort().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        filterList.add(filteredItems.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filteredItems.size();
                results.values = filteredItems;
            }
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            items = (ArrayList<Customer>) results.values;
            notifyDataSetChanged();
        }
    }

}
