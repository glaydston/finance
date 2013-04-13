package com.android.finance;

/**
 * @author Glaydston Veloso
 * @email glaydston.oliveira@gmail.com
 * @since 2013
 * @version 1.0
 */
import com.android.finance.dao.DAO;
import com.android.finance.fragment.FinanceFragment;
import com.android.finance.fragment.FinanceMenuFragment;
import com.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends BaseActivity {
	private Fragment content;
	private DAO dao;
	
	public MainActivity() {
		super(R.string.app_name);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		dao = new DAO(this);
		dao.open();
		
		// set the Above View
		if(savedInstanceState != null)
			content = getSupportFragmentManager().getFragment(savedInstanceState,"content");
		if(content == null)
			content = new FinanceFragment();
			
		// set the Above View
		setContentView(R.layout.main);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.main, content)
		.commit();
		
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new FinanceMenuFragment())
		.commit();		

		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
	}
	
		
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		dao.open();
		super.onResume();
	}

	/* (non-Javadoc)
	 * @see com.actionbarsherlock.app.SherlockFragmentActivity#onPause()
	 */
	@Override
	protected void onPause() {
		dao.close();
		super.onPause();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "content", content);
	}
	
	
	public void switchContent(Fragment fragment, int position) {
		content = fragment;
		
		switch (position) {
		case 0:
			setContentView(R.layout.main); 
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.main, fragment)
			.commit();
			break;
		case 1:
			setContentView(R.layout.shopping); 
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.shopping, fragment)
			.commit();
			break;
		case 2:
			setContentView(R.layout.add_category); 
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.category, fragment)
			.commit();
			break;

		default:
			setContentView(R.layout.content_frame); 
			getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, fragment)
			.commit();
			break;
		}	
			
		getSlidingMenu().showContent();
	}



}
