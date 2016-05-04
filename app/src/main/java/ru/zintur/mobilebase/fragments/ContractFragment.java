package ru.zintur.mobilebase.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.dialogs.MessageDialog;
import ru.zintur.mobilebase.schema.domains.Customer;

public class ContractFragment extends AbstractFragment{

    private static final int LAYOUT = R.layout.fragment_contract;
    private static final int TITLE = R.string.tab_item_contracts;




    public ContractFragment() {
        // Default constructor
    }

    public static ContractFragment getInstance(Context ctx) {
        Bundle args = new Bundle();
        ContractFragment fragment = new ContractFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        ListView lvContracts = (ListView) view.findViewById(R.id.lvContracts);



        return view;
    }

}
