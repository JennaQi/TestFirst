package com.example.actionbarwithspinner;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ActionBar mActionBar = null;
	View contentView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (mActionBar == null) {
			mActionBar = getActionBar();
		}
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setDisplayShowCustomEnabled(true);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
	    
		
		final ArrayList<String> spinnerArray = new ArrayList<String>();
		spinnerArray.add("one");
		spinnerArray.add("two");
		spinnerArray.add("three");
		spinnerArray.add("four");
		spinnerArray.add("five");
		System.out.println("add");
		System.out.println("add in windows file");
	
		if (contentView == null) {
			contentView = getLayoutInflater().inflate(R.layout.title_view, null, false);
		}
		Spinner spinner = (Spinner)contentView.findViewById(R.id.spinner);
		final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
		
		NothingSelectedSpinnerAdapter adapter = new NothingSelectedSpinnerAdapter(spinnerArrayAdapter, this);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {               
	            Toast.makeText(getApplicationContext(), spinnerArray.get(position), Toast.LENGTH_SHORT).show();


	        }

	        public void onNothingSelected(AdapterView<?> parent) {
	            // TODO Auto-generated method stub

	        }
	    });
		spinner.setPadding(0, 0, 0, 0);
		RelativeLayout layout = (RelativeLayout)contentView.findViewById(R.id.content);
		TextView title = (TextView)contentView.findViewById(R.id.docName);
		title.setText("Spinner");
//		title.setText("Spinner");
//		layout.addView(title);
//		layout.addView(spinner);
	
		mActionBar.setCustomView(layout, new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class NothingSelectedSpinnerAdapter implements SpinnerAdapter {

	    protected SpinnerAdapter adapter;
	    protected Context context;
	    protected View contentView = null;


	    public NothingSelectedSpinnerAdapter(SpinnerAdapter spinnerAdapter, Context context) {
	        this.adapter = spinnerAdapter;
	        this.context = context;
	    }

	    @Override
	    public final View getView(int position, View convertView, ViewGroup parent) {
			
				convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.layout_name, parent, false);
			TextView layoutNameText = (TextView) convertView.findViewById(R.id.layoutName);
			layoutNameText.setText(String.valueOf(position));


			return layoutNameText;
	    }


	    @Override
	    public View getDropDownView(int position, View convertView, ViewGroup parent) {

	        // Could re-use the convertView if possible, use setTag...
	    	TextView text1 =  (TextView)adapter.getDropDownView(position, convertView, parent);
	    		convertView = MainActivity.this.getLayoutInflater().inflate(R.layout.spinner_item_text, parent, false);
	    	TextView itemNameText = (TextView) convertView.findViewById(R.id.itemName);
	    	itemNameText.setText(text1.getText());
	        return itemNameText;
	    }


	    @Override
	    public int getCount() {
	        int count = adapter.getCount();
	        return count ;
	    }

	    @Override
	    public Object getItem(int position) {
	        return adapter.getItem(position );
	    }

	    @Override
	    public int getItemViewType(int position) {
	        return 0;
	    }

	    @Override
	    public int getViewTypeCount() {
	        return adapter.getViewTypeCount();
	    }

	    @Override
	    public long getItemId(int position) {
	        return adapter.getItemId(position);
	    }

	    @Override
	    public boolean hasStableIds() {
	        return adapter.hasStableIds();
	    }

	    @Override
	    public boolean isEmpty() {
	        return adapter.isEmpty();
	    }

	    @Override
	    public void registerDataSetObserver(DataSetObserver observer) {
	        adapter.registerDataSetObserver(observer);
	    }

	    @Override
	    public void unregisterDataSetObserver(DataSetObserver observer) {
	        adapter.unregisterDataSetObserver(observer);
	    }


	}

}
