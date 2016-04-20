package ru.zintur.mobilebase.utils;


import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


// UI utilities

public class Utils {


    // Find all EdiText on Layout (with all child) and put him in SparseArray
    public static void findAllEditTexts(ViewGroup viewGroup, SparseArray<EditText> fields) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup)
                findAllEditTexts((ViewGroup) view, fields);
            else if (view instanceof EditText) {
                EditText edittext = (EditText) view;
                fields.put(edittext.getId(), edittext);
            }
        }
    }



}
