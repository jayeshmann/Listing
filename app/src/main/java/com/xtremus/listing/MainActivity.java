package com.xtremus.listing;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonArray;
import com.xtremus.listing.adapters.MainAdapter;
import com.xtremus.listing.model.GetListing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private Context context;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int All_PERM = 123;
    private static final String[] permissions = {
            Manifest.permission.INTERNET
    };


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<GetListing> getListingList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        permissionTask();


        recyclerView = findViewById(R.id.main_list);

        getListingList = new ArrayList<>();
        adapter = new MainAdapter(getApplicationContext(),getListingList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);


        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArray jsonArray = 

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,"https://www.tsassessors.in/nccwork/API/cadre_sign_up_login/testing.php?instt_id=1&ncc_year=1",
             new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {


                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.d(TAG,jsonObject.toString());
                        GetListing getListing =  new GetListing();
                        getListing.setEnrollmentNo(jsonObject.getString("enrollment_no"));
                        getListing.setCadetName(jsonObject.getString("cadet_name"));
                        getListing.setIsIsoUploaded(String.valueOf(jsonObject.getInt("is_iso_uploaded")));

                        getListingList.add(getListing);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }


                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @AfterPermissionGranted(All_PERM)
    private void permissionTask() {
        String[] perms = permissions;
        if (!EasyPermissions.hasPermissions(this, perms)) {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "You need to provide these permissions to continue!",
                    All_PERM, perms);

        } else {
            // Already have permission, do the thing
            // ...

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
        permissionTask();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms);
        permissionTask();
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            permissionTask();
        }
    }
}
