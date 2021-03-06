package ru.zintur.mobilebase.activity;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.utils.Utils;

public class AbstractDetailsActivity extends AppCompatActivity{


   final private static String TAG = "details_activity";
    // HashMap of all EditText elements (Form FIELDS)


//   View view;
   SparseArray<EditText> fields = new SparseArray<>();
   LinearLayout bottomBar;


    public void initActionBar() {
         if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
         }
  }

   public void initFields() {
      // Set longClickListener or all EditText in Layout
     LinearLayout customerForm = (LinearLayout) findViewById(R.id.fragment_customer_details);

     Utils.findAllEditTexts(customerForm, fields);
      for(int i = 0; i < fields.size(); i++) {
         int key = fields.keyAt(i);
         fields.get(key).setOnLongClickListener(longClickListener);
      }
   }


    public void bottomBarSwitchMode() {
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            View childView = bottomBar.getChildAt(i);
            if (childView instanceof Button) {
                if (childView.getVisibility() == View.GONE) {
                    childView.setVisibility(View.VISIBLE);
                } else {
                    childView.setVisibility(View.GONE);
                }
            }
        }
    }


    public void setEditMode(boolean mode, View view) {
        Utils.hideSystemSoftKeyboard(this, view);

        bottomBarSwitchMode();
        for (int i = 0; i < fields.size(); i++) {
            int key = fields.keyAt(i);
            fields.get(key).setFocusableInTouchMode(mode);
            fields.get(key).clearFocus();
        }
    }




   private void showPopupMenu(View v) {

      PopupMenu popupMenu = new PopupMenu(this, v);
      popupMenu.inflate(R.menu.menu_popup);
      // ! HACK ! - Show item icon in popup menu
       Utils.setPopupMenuForceIconShow(popupMenu);

       final EditText field = (EditText) v.findViewById(v.getId());

       popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               if (item.getItemId() == R.id.menu_popup_item_copy) {

                   ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                   ClipData clip = ClipData.newPlainText(TAG, field.getText().toString());
                   clipboard.setPrimaryClip(clip);
               }
               return false;
           }
       });

      popupMenu.show();
   }



   View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {

         showPopupMenu(v);
         return false;
      }
   };


   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      onBackPressed();
      return true;
   }

}
