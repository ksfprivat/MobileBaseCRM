package ru.zintur.mobilebase.utils;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


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


    // Wrapper for findAllEditText
    public static SparseArray<EditText> getFields(ViewGroup viewGroup) {
        SparseArray<EditText> fields = new SparseArray<>();
        findAllEditTexts(viewGroup, fields);
        return fields;
    }


    // ! HACK ! Use Java Reflection for force standard popup menu icons show
    public static void setPopupMenuForceIconShow(PopupMenu popupMenu) {
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menuPopupHelper = field.get(popupMenu);
            Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
            Method method = cls.getDeclaredMethod("setForceShowIcon", boolean.class);
            method.setAccessible(true);
            method.invoke(menuPopupHelper, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideSystemSoftKeyboard(Context ctx, View view) {
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
