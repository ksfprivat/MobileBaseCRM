package ru.zintur.mobilebase.fragments;


import android.support.v4.app.Fragment;

public class AbstractFragment extends Fragment {

    private String Title;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
