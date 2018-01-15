package com.cyan.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.cyan.multiselectionlayout.MultiSelectionLayout;
import com.cyan.testapp.adapters.AdapterItem;
import com.cyan.testapp.adapters.DividerDcoration;
import com.cyan.testapp.models.ItemModel;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView m_recycler;
    MultiSelectionLayout m_selection_layout;
    List<ItemModel> m_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_selection_layout = findViewById(R.id.multi_layout);
        m_recycler = findViewById(R.id.list_items);

        m_recycler.setLayoutManager(new LinearLayoutManager(this));
        m_recycler.addItemDecoration(new DividerDcoration(this, 112));

        m_items = new LinkedList<>();

        for(int i=0; i<20; i++){
            m_items.add(new ItemModel(i, "Item " + String.valueOf(i)));
        }

        AdapterItem m_adapter = new AdapterItem();
        m_adapter.set_toolbar(m_selection_layout);
        m_adapter.set_items(m_items);

        m_recycler.setAdapter(m_adapter);
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
}
