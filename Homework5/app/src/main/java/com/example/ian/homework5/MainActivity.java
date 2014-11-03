package com.example.ian.homework5;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
    GridView mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGrid=(GridView) findViewById(R.id.gridView);
        mainGrid.setAdapter(new MainAdapter(this));
        mainGrid.setOnItemClickListener(this);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
        else if(id==R.id.action_add)
        {
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.action_search)
        {
            Fragment searchFragment = new Fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.search, searchFragment);
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    class MainAdapter extends BaseAdapter
    {
        String[] buttons;
        Context context;
        MainAdapter(Context context)
        {
            this.context = context;
            Resources res = context.getResources();
            buttons = res.getStringArray(R.array.grid_buttons);

        }

        @Override
        public int getCount() {
            return buttons.length;
        }

        @Override
        public Object getItem(int i) {
            return buttons[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        class ViewHolder
        {
            TextView myButton;
            ViewHolder(View v)
            {
                myButton = (TextView) v.findViewById(R.id.textView);
            }

        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View row = view;
            ViewHolder holder=null;
            if(row==null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) row.getTag();
            }

            holder.myButton.setText(buttons[i]);
            return row;
        }
    }
}
