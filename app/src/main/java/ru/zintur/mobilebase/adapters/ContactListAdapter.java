package ru.zintur.mobilebase.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Contact;
import ru.zintur.mobilebase.schema.domains.Customer;

public class ContactListAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater ltInflater;
    List<Contact> items;


    public ContactListAdapter(Context context, List<Contact> contacts) {
        ctx = context;
        items = contacts;
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

        ((TextView) view.findViewById(R.id.txtStatus)).setText(contact.getStatus());
        ((TextView) view.findViewById(R.id.txtCompany)).setText(DataSource.getCustomersById(contact.getCustomer()).getTitleShort());

        return view;
    }
}
