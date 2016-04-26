package ru.zintur.mobilebase.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.adapters.ContactListAdapter;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Contact;

public class ContactFragment extends AbstractFragment{

    private static final int LAYOUT = R.layout.fragment_contact;
    private static final int TITLE = R.string.tab_item_contacts;

    private static Long _customerId;

    public ContactFragment() {
        // Default constructor
    }

    public static ContactFragment getInstance(Context ctx, Long customerId) {

        _customerId = customerId;

        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(LAYOUT, container, false);

        List<Contact> contacts;

        if (_customerId != null)
            contacts = DataSource.getContactsByCustomerId(_customerId);
        else
            contacts  = DataSource.getContacts();


        ContactListAdapter adapter = new ContactListAdapter(container.getContext(), contacts);

        ListView lvContacts = (ListView) view.findViewById(R.id.lvContacts);
        lvContacts.setAdapter(adapter);

       return view;
    }

}
