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
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.Contact;

public class ContactListAdapter extends BaseAdapter implements Filterable {

    Context ctx;
    LayoutInflater ltInflater;
    List<Contact> items;
    List<Contact> filteredItems;
    ValueFilter valueFilter;


    public ContactListAdapter(Context context, List<Contact> contacts) {
        ctx = context;
        items = contacts;
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
            view = ltInflater.inflate(R.layout.list_item_contact, parent, false);
        }

        Contact contact = (Contact) getItem(position);
        DataSource.getCustomersById(contact.getCustomer()).getTitleShort();

        if (contact.getName().isEmpty())
            ((TextView) view.findViewById(R.id.txtName)).setText(
                    DataSource.getCustomersById(contact.getCustomer()).getTitleShort());
        else
            ((TextView) view.findViewById(R.id.txtName)).setText(contact.getName());

        ((TextView) view.findViewById(R.id.txtCompany)).setText(DataSource.getCustomersById(contact.getCustomer()).getTitleShort());
        ((TextView) view.findViewById(R.id.txtStatus)).setText(contact.getStatus());

        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Contact> filterList = new ArrayList<>();
                for (int i = 0; i < filteredItems.size(); i++) {
                    if ( (filteredItems.get(i).getName().toUpperCase() )
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
            items = (ArrayList<Contact>) results.values;
            notifyDataSetChanged();
        }
    }


}
