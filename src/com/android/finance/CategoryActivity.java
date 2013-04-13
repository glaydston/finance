package com.android.finance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.finance.dao.DAO;
import com.android.finance.fragment.FinanceFragment;
import com.android.finance.fragment.FinanceMenuFragment;
import com.android.finance.model.Category;
import com.slidingmenu.lib.SlidingMenu;

public class CategoryActivity extends BaseActivity {
	private Fragment content;
	private DAO dao;
	
	public CategoryActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_category);
		// set the Above View
		if (savedInstanceState != null)
			content = getSupportFragmentManager().getFragment(
					savedInstanceState, "content");
		if (content == null)
			content = new FinanceFragment();

		// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, content).commit();

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new FinanceMenuFragment()).commit();

		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		// open Dao
		dao = new DAO(this);
		dao.open();
		
		final EditText description = (EditText) findViewById(R.id.add_description_edit);
		
		Button button = (Button) findViewById(R.id.add_category_button);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Category category = new Category();		
				category.setDescription(description.getEditableText().toString());
				String ret = dao.createCategory(category);
				Toast.makeText(CategoryActivity.this, ret, 600);
				finish();
			}
		});
		
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

}
