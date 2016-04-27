package ru.zintur.mobilebase.fragments.details;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.fragments.AbstractFragment;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.domains.Customer;
import ru.zintur.mobilebase.utils.Utils;

public class CustomerDetailsFragment extends AbstractFragment {

    private static final int LAYOUT = R.layout.fragment_customer_details;
    private static final int TITLE = R.string.customer_details_tab_item_customers;
    private static final String TAG = "customerDetails";

    private static Long _customerId;

    SparseArray<EditText> fields = new SparseArray<>();

    public CustomerDetailsFragment() {
        // Default constructor
    }

    public static CustomerDetailsFragment getInstance(Context ctx, Long customerId) {
        _customerId = customerId;

        Bundle args = new Bundle();
        CustomerDetailsFragment fragment = new CustomerDetailsFragment();

        fragment.setArguments(args);
        fragment.setTitle(ctx.getString(TITLE));

        return fragment;
    }


    public void initFields(View view, int layoutId) {
        // Set longClickListener or all EditText in Layout
        LinearLayout customerForm = (LinearLayout) view.findViewById(layoutId);

        Utils.findAllEditTexts(customerForm, fields);
        for(int i = 0; i < fields.size(); i++) {
            int key = fields.keyAt(i);
            fields.get(key).setOnLongClickListener(fieldLongClickListener);
        }
    }

    private void  fillFields(View view) {

        Customer customer = DataSource.getCustomersById(_customerId);

        ((EditText) view.findViewById(R.id.etTitleShort)).setText(customer.getTitleShort());
        ((EditText) view.findViewById(R.id.etTitleFull)).setText(customer.getTitleFull());
        ((EditText) view.findViewById(R.id.etRegion)).setText(customer.getDistrict());
        ((EditText) view.findViewById(R.id.etCity)).setText(customer.getCity());
        ((EditText) view.findViewById(R.id.etAddress)).setText(customer.getStreet());
    }

    private void initBottomBar(View view) {
        // Setup bottom bar button onClick event listener
        LinearLayout bottomBar = ((LinearLayout) view.findViewById(R.id.fragment_customer_bottom_bar));
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            View childView = bottomBar.getChildAt(i);
            if (childView instanceof Button) {
                childView.setOnClickListener(bottomBarClickListener);
            }
        }
    }

    private void showPopupMenu(View v) {

        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.menu_popup);
        // ! HACK ! - Show item icon in popup menu
        Utils.setPopupMenuForceIconShow(popupMenu);

        final EditText field = (EditText) v.findViewById(v.getId());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_popup_item_copy) {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText(TAG, field.getText().toString());
                    clipboard.setPrimaryClip(clip);
                }
                return false;
            }
        });

        popupMenu.show();
    }

    View.OnLongClickListener fieldLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            showPopupMenu(v);
            return false;
        }
    };

    View.OnClickListener bottomBarClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
           Log.d(TAG, "ON_BUTTON_CLICK");

            switch (v.getId()) {
                case R.id.fragment_customer_btnEdit:
                    Log.d(TAG, "ON_BUTTON_CLICK_EDIT");
                    break;
                case R.id.fragment_customer_btnMap:
                    Log.d(TAG, "ON_BUTTON_CLICK_MAP");
                    break;
                case R.id.fragment_customer_btnDelete:
                    Log.d(TAG, "ON_BUTTON_CLICK_DELETE");
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(LAYOUT, container, false);

        initFields(view, R.id.fragment_customer_details);
        initBottomBar(view);
        fillFields(view);

        return view;
    }


}
