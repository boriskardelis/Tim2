package com.mbm.ftn.mbm.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mbm.ftn.mbm.R;
import com.mbm.ftn.mbm.adapters.ProfileAdapter;
import com.mbm.ftn.mbm.dao.ProfileDao;
import com.mbm.ftn.mbm.models.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makso on 6/17/2017.
 */

public class ProfilesActivity extends BaseActivity  {

    ProfileDao profileDao = null;

    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;
    private List<Profile> profileList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setLogo(R.mipmap.ic_sos);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        profileDao = new ProfileDao(this);
        profileList = profileDao.findAll();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_profile);
        profileAdapter = new ProfileAdapter(profileList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(profileAdapter);


        registerReceiver(broadcast_reciever, new IntentFilter("finish_activity"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_profiles, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, AddProfileActivity.class);
                startActivity(intent);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    BroadcastReceiver broadcast_reciever = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent intent) {
            String action = intent.getAction();
            if (action.equals("finish_activity")) {
                finish();
                // DO WHATEVER YOU WANT.
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcast_reciever);
        super.onDestroy();
    }

}