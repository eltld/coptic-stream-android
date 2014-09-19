package com.copticstream.copticstream;

import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.app.FragmentTransaction;
import android.app.ActionBar;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();

		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());// TabsPagerAdapter
																		// is a
																		// class
																		// i
																		// created
																		// to
																		// return
																		// View

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setIcon(R.drawable.video_file)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // when
																					// the
																					// page
																					// start
																					// scrolling
					@Override
					public void onPageSelected(int position) {// When Scroll Completed 
						// TODO Auto-generated method stub
						actionBar.setSelectedNavigationItem(position);
						actionBar
								.setTitle(GetActionBarTitleByPagePosition(position));
					}

					@Override
					public void onPageScrolled(int position, float arg1,
							int arg2) { // When the page completely Changed
										// "Changed Complete"

						
					}

					@Override
					public void onPageScrollStateChanged(int position) {
						// TODO Auto-generated method stub

					}
				});

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {// When tab
																// clicked also
																// get called on
																// pages load
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {// When a Tab
																	// is
																	// unselected
		// TODO Auto-generated method stub
		// Use tab.getposition
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public String GetActionBarTitleByPagePosition(int position) {

		switch (position) {
		case 0:
			return "Video";
		case 1:
			return "Audio";
		case 2:
			return "News ";
		default:
			break;
		}

		return "";

	}

}
