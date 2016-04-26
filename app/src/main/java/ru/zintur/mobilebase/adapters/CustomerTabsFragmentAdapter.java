package ru.zintur.mobilebase.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import ru.zintur.mobilebase.fragments.AbstractFragment;
import ru.zintur.mobilebase.fragments.ContactFragment;
import ru.zintur.mobilebase.fragments.ContractFragment;
import ru.zintur.mobilebase.fragments.CustomerDetailsFragment;
import ru.zintur.mobilebase.fragments.CustomerFragment;
import ru.zintur.mobilebase.schema.domains.Customer;

public class CustomerTabsFragmentAdapter extends FragmentPagerAdapter {

   private static SparseArray<AbstractFragment> tabs;

    public CustomerTabsFragmentAdapter(Context ctx, Long customerId, FragmentManager fm) {
        super(fm);
        tabs = new SparseArray<>();
        tabs.put(0, CustomerDetailsFragment.getInstance(ctx, customerId));
        tabs.put(1, ContactFragment.getInstance(ctx, customerId));
        tabs.put(2, ContractFragment.getInstance(ctx));
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
