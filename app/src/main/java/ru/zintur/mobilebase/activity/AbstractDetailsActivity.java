package ru.zintur.mobilebase.activity;


import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.utils.Utils;

public class AbstractDetailsActivity extends AppCompatActivity{

    // HashMap of all EditText elements
   SparseArray<EditText> fields = new SparseArray<>();

   public void initActionBar() {
         if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
         }
  }

   public void initFields() {
      // Set longClickListener or all EditText in Layout
      LinearLayout customerForm = (LinearLayout) findViewById(R.id.form_customer_details);
      Utils.findAllEditTexts(customerForm, fields);
      for(int i = 0; i < fields.size(); i++) {
         int key = fields.keyAt(i);
         fields.get(key).setOnLongClickListener(longClickListener);
      }
   }


   private void showPopupMenu(View v) {
      PopupMenu popupMenu = new PopupMenu(this, v);
      popupMenu.inflate(R.menu.menu_context);
      // ! HACK ! - Show item icon in popup menu
       Utils.setPopupMenuForceIconShow(popupMenu);

       popupMenu.setOnMenuItemClickListener(popupMenuItemClickListener);

      popupMenu.show();
   }



    PopupMenu.OnMenuItemClickListener popupMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_popup_item_copy) {
            Toast.makeText(AbstractDetailsActivity.this, "HelpFull", Toast.LENGTH_SHORT).show();
        }
         return false;
        }
    };


   View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
         String str = ((EditText) v.findViewById(v.getId())).getText().toString();
          Toast.makeText(AbstractDetailsActivity.this, str, Toast.LENGTH_SHORT).show();
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
