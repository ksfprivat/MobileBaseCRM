package ru.zintur.mobilebase.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.zintur.mobilebase.R;

public class ContactFragment extends AbstractFragment{

    private static final int LAYOUT = R.layout.fragment_contact;
    private static final int TITLE = R.string.tab_item_contacts;

    public ContactFragment() {
        // Default constructor
    }

    public static ContactFragment getInstance(Context ctx) {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       return inflater.inflate(LAYOUT, container, false);
    }

}
