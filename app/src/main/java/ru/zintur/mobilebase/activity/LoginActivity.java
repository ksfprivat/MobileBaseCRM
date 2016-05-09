package ru.zintur.mobilebase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.zintur.mobilebase.R;
import ru.zintur.mobilebase.dialogs.MessageDialog;
import ru.zintur.mobilebase.schema.Config;
import ru.zintur.mobilebase.schema.DataSource;
import ru.zintur.mobilebase.schema.User;
import ru.zintur.mobilebase.schema.utils.BaseImporter;
import ru.zintur.mobilebase.schema.utils.DateUtil;
import ru.zintur.mobilebase.schema.utils.Securty;

public class LoginActivity extends AppCompatActivity {


    private static final String STATE_ON= "LOGON";

    private Config config;

    private LinearLayout ltLoginFailed;
    private EditText etUser;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);

        initDataBase();
        config = DataSource.getConfig(getString(R.string.db_version));

        ltLoginFailed = (LinearLayout) findViewById(R.id.ltLoginFailed);
        etUser = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);



        if (config.getState().equals(STATE_ON) && Login(config.getUser(), config.getPassword()) && checkUpTime()) {

            if (checkEvolution()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                MessageDialog.showDialog(this, getString(R.string.txtUpdates),
                                getString(R.string.txtCheckEvolutionFailed));
            }

        } else {
            DataSource.clearConfig(config);
            DataSource.saveConfig(config);
        }
    }

    public void onBtnLoginClick(View view) {

        if ( Login(etUser.getText().toString(), etPassword.getText().toString()) ) {
            ltLoginFailed.setVisibility(View.GONE);

            if (((CheckBox) findViewById(R.id.chkStayInSystem)).isChecked()) {
                config.setUser(etUser.getText().toString());
                config.setPassword(etPassword.getText().toString());
                config.setState(STATE_ON);
                config.setUptime(DateUtil.getCurrentDate());
            } else {
                DataSource.clearConfig(config);
            }

            if (checkEvolution()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                DataSource.saveConfig(config);
            } else {
                MessageDialog.showDialog(this, getString(R.string.txtUpdates),
                        getString(R.string.txtCheckEvolutionFailed));
            }
        } else {

            ltLoginFailed.setVisibility(View.VISIBLE);
        }
    }

    private boolean Login(String user, String password) {

        boolean result = false;
        User login = DataSource.getUser(user);

        if (login != null) {
            if (login.getUser().equals(user) && login.getPassword().equals(Securty.getMd5hash(password))) {
                result = true;
            }
        }

        return result;
    }


    private boolean checkUpTime() {
        return config.getUptime().equals(DateUtil.getCurrentDate());
    }


    private boolean checkEvolution() {
        int current = Integer.parseInt(config.getEvolution());
        int evolution = Integer.parseInt(DateUtil.getCurrentDate());
        return  (current > evolution);
    }


    private void initDataBase() {
        if (!BaseImporter.checkDataBase())
            BaseImporter.importDatabase(this);

        DataSource.openDatabase(this);
    }







}
