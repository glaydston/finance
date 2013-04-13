package com.android.finance.fragment;


import com.android.finance.MainActivity;
import com.android.finance.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Glaydston Veloso
 * @email glaydston.oliveira@gmail.com
 * @since 2013
 * @version 1.0
 */
public class FinanceMenuFragment extends ListFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
			
		String[] options = getResources().getStringArray(R.array.menu_options);		
		FinanceListAdapter adapter = new FinanceListAdapter(getActivity());		
		for (String string : options) {
			adapter.add(new FinanceMenuItem(string, R.drawable.indicator));
		}		
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {		
		Fragment fragment = new FinanceFragment();
		
		MainActivity ma = (MainActivity) getActivity();
		if (fragment != null)
			ma.switchContent(fragment, position);
	}
	
		
	private class FinanceMenuItem {
		public String tag;
		public int iconRes;
		public FinanceMenuItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}
	
	public class FinanceListAdapter extends ArrayAdapter<FinanceMenuItem> {

		public FinanceListAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}


}
