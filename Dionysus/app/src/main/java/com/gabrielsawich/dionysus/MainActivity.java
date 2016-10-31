package com.gabrielsawich.dionysus;

import android.bluetooth.*;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Set;


public class MainActivity extends ActionBarActivity {

    Set<BluetoothDevice> paired;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Menu mainMenu = new MenuBuilder(this);
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth unavailable", Toast.LENGTH_LONG);
        }
        else {
            paired = adapter.getBondedDevices();
            onPrepareOptionsMenu(mainMenu);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (paired.size() > 0) {
            for (BluetoothDevice bd : paired) {
                menu.add(bd.getName());
            }
        }
        return true;
    }
}
