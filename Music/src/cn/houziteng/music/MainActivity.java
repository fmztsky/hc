package cn.houziteng.music;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private enum TabOption {
		playlist, artist, album, song, cloudmusic, style, search, folder
	}

	private View mTabBar;
	private TabBarHolder mTabBarHolder;
	private View mTabBarMore;
	private RelativeLayout option1;
	private RelativeLayout option2;
	private RelativeLayout option3;
	private boolean isShowTabSelection;

	private View mCurrentSelectTabView;
	private TabOption mCurrentSelectTab;

	private TabOption[] mOrderOfMore = new TabOption[] { TabOption.cloudmusic,
			TabOption.style, TabOption.search, TabOption.folder };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
		initTabBar();
		super.onCreate(savedInstanceState);
	}

	private void initTabBar() {

		TabOnTouchListener tabOnTouchListener = new TabOnTouchListener(this);

		mTabBar = findViewById(R.id.tabBar);
		mTabBarHolder = new TabBarHolder();
		mTabBar.setTag(mTabBarHolder);

		mTabBarHolder.tabPlaylist = (RelativeLayout) mTabBar
				.findViewById(R.id.tabPlaylist);
		TabBarButtonHolder tabPlaylistButtonHolder = new TabBarButtonHolder();
		mTabBarHolder.tabPlaylist.setTag(tabPlaylistButtonHolder);
		tabPlaylistButtonHolder.icon = (ImageView) mTabBarHolder.tabPlaylist
				.getChildAt(0);
		tabPlaylistButtonHolder.text = (TextView) mTabBarHolder.tabPlaylist
				.getChildAt(1);

		mTabBarHolder.tabArtist = (RelativeLayout) mTabBar
				.findViewById(R.id.tabArtist);
		TabBarButtonHolder tabArtistButtonHolder = new TabBarButtonHolder();
		mTabBarHolder.tabArtist.setTag(tabArtistButtonHolder);
		tabArtistButtonHolder.icon = (ImageView) mTabBarHolder.tabArtist
				.getChildAt(0);
		tabArtistButtonHolder.text = (TextView) mTabBarHolder.tabArtist
				.getChildAt(1);

		mTabBarHolder.tabAlbum = (RelativeLayout) mTabBar
				.findViewById(R.id.tabAlbum);
		TabBarButtonHolder tabAlbumButtonHolder = new TabBarButtonHolder();
		mTabBarHolder.tabAlbum.setTag(tabAlbumButtonHolder);
		tabAlbumButtonHolder.icon = (ImageView) mTabBarHolder.tabAlbum
				.getChildAt(0);
		tabAlbumButtonHolder.text = (TextView) mTabBarHolder.tabAlbum
				.getChildAt(1);

		mTabBarHolder.tabSong = (RelativeLayout) mTabBar
				.findViewById(R.id.tabSong);
		TabBarButtonHolder tabSongButtonHolder = new TabBarButtonHolder();
		mTabBarHolder.tabSong.setTag(tabSongButtonHolder);
		tabSongButtonHolder.icon = (ImageView) mTabBarHolder.tabSong
				.getChildAt(0);
		tabSongButtonHolder.text = (TextView) mTabBarHolder.tabSong
				.getChildAt(1);

		mTabBarHolder.tabSelect = (RelativeLayout) mTabBar
				.findViewById(R.id.tabSelect);
		TabBarButtonHolder tabSelectButtonHolder = new TabBarButtonHolder();
		mTabBarHolder.tabSelect.setTag(tabSelectButtonHolder);
		tabSelectButtonHolder.icon = (ImageView) mTabBarHolder.tabSelect
				.getChildAt(0);
		tabSelectButtonHolder.text = (TextView) mTabBarHolder.tabSelect
				.getChildAt(1);

		selectTab(mTabBarHolder.tabSong);

		mTabBarHolder.tabPlaylist.setOnTouchListener(tabOnTouchListener);
		mTabBarHolder.tabArtist.setOnTouchListener(tabOnTouchListener);
		mTabBarHolder.tabAlbum.setOnTouchListener(tabOnTouchListener);
		mTabBarHolder.tabSong.setOnTouchListener(tabOnTouchListener);
		mTabBarHolder.tabSelect.setOnTouchListener(tabOnTouchListener);

		mTabBarMore = findViewById(R.id.tabMore);
		mTabBarMore.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					hideMoreSelection();
					break;
				default:
					break;
				}
				return false;
			}
		});
		option1 = (RelativeLayout) mTabBarMore.findViewById(R.id.option1);
		TabBarButtonHolder option1ButtonHolder = new TabBarButtonHolder();
		option1.setTag(option1ButtonHolder);
		option1ButtonHolder.icon = (ImageView) option1.getChildAt(0);
		option1ButtonHolder.text = (TextView) option1.getChildAt(1);

		option2 = (RelativeLayout) mTabBarMore.findViewById(R.id.option2);
		TabBarButtonHolder option2ButtonHolder = new TabBarButtonHolder();
		option2.setTag(option2ButtonHolder);
		option2ButtonHolder.icon = (ImageView) option2.getChildAt(0);
		option2ButtonHolder.text = (TextView) option2.getChildAt(1);

		option3 = (RelativeLayout) mTabBarMore.findViewById(R.id.option3);
		TabBarButtonHolder option3ButtonHolder = new TabBarButtonHolder();
		option3.setTag(option3ButtonHolder);
		option3ButtonHolder.icon = (ImageView) option3.getChildAt(0);
		option3ButtonHolder.text = (TextView) option3.getChildAt(1);

		option1.setOnTouchListener(tabOnTouchListener);
		option2.setOnTouchListener(tabOnTouchListener);
		option3.setOnTouchListener(tabOnTouchListener);
	}

	private void hideMoreSelection() {
		if (isShowTabSelection) {
			isShowTabSelection = false;
			mTabBarMore.setVisibility(View.INVISIBLE);
			if (!mTabBarHolder.tabSelect.equals(mCurrentSelectTabView)) {
				mTabBarHolder.tabSelect
						.setBackgroundResource(R.drawable.tabbar_bg_with_arrow);
			}
		}
	}

	private class TabBarHolder {
		RelativeLayout tabPlaylist;

		RelativeLayout tabArtist;

		RelativeLayout tabAlbum;

		RelativeLayout tabSong;

		RelativeLayout tabSelect;

	}

	private class TabBarButtonHolder {
		ImageView icon;
		TextView text;
	}

	private class TabOnTouchListener implements OnTouchListener {

		private GestureDetector gestureDetector;
		private View currentTouchTab;

		public TabOnTouchListener(Context context) {
			gestureDetector = new GestureDetector(context,
					new GestureDetector.SimpleOnGestureListener() {
						@Override
						public boolean onDown(MotionEvent e) {
							return true;
						}

						@Override
						public void onLongPress(MotionEvent e) {
							longPress();
						}

						@Override
						public boolean onSingleTapUp(MotionEvent e) {
							singleTapUp();
							return true;
						}
					});
		}

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			gestureDetector.onTouchEvent(event);
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				touchDown(view);
				break;

			case MotionEvent.ACTION_UP:
				touchUp(view);
				break;
			}
			return true;
		}

		private void touchDown(View view) {
			if (!view.equals(mTabBarHolder.tabSelect)
					&& !view.equals(mCurrentSelectTabView)
					&& !view.equals(option1) && !view.equals(option2)
					&& !view.equals(option3)) {
				view.setBackgroundResource(R.drawable.tabbar_bg_down3);
			}
			currentTouchTab = view;
		}

		private void touchUp(View view) {
			if (!view.equals(mTabBarHolder.tabSelect)
					&& !view.equals(mCurrentSelectTabView)
					&& !view.equals(option1) && !view.equals(option2)
					&& !view.equals(option3)) {
				view.setBackgroundResource(0);
			}
		}

		private void singleTapUp() {
			View selectTab = mTabBarHolder.tabSelect;
			if (currentTouchTab.equals(option1)) {
				changeSelectionView(option1);
			} else if (currentTouchTab.equals(option2)) {
				changeSelectionView(option2);
			} else if (currentTouchTab.equals(option3)) {
				changeSelectionView(option3);
			} else {
				selectTab = currentTouchTab;
			}
			hideMoreSelection();
			selectTab(selectTab);
		}

		private void longPress() {
			if (mTabBarHolder.tabSelect.equals(currentTouchTab)) {
				mTabBarHolder.tabSelect
						.setBackgroundResource(R.drawable.tabbar_bg_down2);
				mTabBarMore.setVisibility(View.VISIBLE);
				isShowTabSelection = true;
			}
		}
	}

	private void changeSelectionView(RelativeLayout option) {
		int icon = 0;
		int text = 0;
		TabOption selectOption = mOrderOfMore[3];
		switch (selectOption) {
		case cloudmusic:
			icon = R.drawable.tabbar_cloudmusic;
			text = R.string.tabbar_cloudmusic;
			break;
		case style:
			icon = R.drawable.tabbar_style;
			text = R.string.tabbar_style;
			break;
		case search:
			icon = R.drawable.tabbar_search;
			text = R.string.tabbar_search;
			break;
		case folder:
			icon = R.drawable.tabbar_folder;
			text = R.string.tabbar_folder;
			break;
		default:
			break;
		}
		TabBarButtonHolder barButtonHolder = (TabBarButtonHolder) option
				.getTag();
		barButtonHolder.icon.setImageResource(icon);
		barButtonHolder.text.setText(getText(text));

		int position = -1;

		if (option.equals(option1)) {
			position = 0;
		} else if (option.equals(option2)) {
			position = 1;
		} else if (option.equals(option3)) {
			position = 2;
		}

		switch (mOrderOfMore[position]) {
		case cloudmusic:
			mOrderOfMore[position] = mOrderOfMore[3];
			mOrderOfMore[3] = TabOption.cloudmusic;
			break;
		case style:
			mOrderOfMore[position] = mOrderOfMore[3];
			mOrderOfMore[3] = TabOption.style;
			break;
		case search:
			mOrderOfMore[position] = mOrderOfMore[3];
			mOrderOfMore[3] = TabOption.search;
			break;
		case folder:
			mOrderOfMore[position] = mOrderOfMore[3];
			mOrderOfMore[3] = TabOption.folder;
			break;
		default:
			break;
		}

	}

	private void cancelCurrentSelect() {
		if (mCurrentSelectTabView != null) {
			TabBarButtonHolder tabBarButtonHolder = (TabBarButtonHolder) mCurrentSelectTabView
					.getTag();
			tabBarButtonHolder.text.setTextColor(Color.rgb(0x87, 0x87, 0x87));
			int icon = 0;
			if (mTabBarHolder.tabSelect.equals(mCurrentSelectTabView)) {
				mCurrentSelectTabView
						.setBackgroundResource(R.drawable.tabbar_bg_with_arrow);
				TabOption selectOption = mOrderOfMore[3];
				switch (selectOption) {
				case cloudmusic:
					icon = R.drawable.tabbar_cloudmusic;
					break;
				case style:
					icon = R.drawable.tabbar_style;
					break;
				case search:
					icon = R.drawable.tabbar_search;
					break;
				case folder:
					icon = R.drawable.tabbar_folder;
					break;
				default:
					break;
				}
			} else {
				mCurrentSelectTabView.setBackgroundResource(0);
				if (mTabBarHolder.tabPlaylist.equals(mCurrentSelectTabView)) {
					icon = R.drawable.tabbar_playlist;
				} else if (mTabBarHolder.tabArtist
						.equals(mCurrentSelectTabView)) {
					icon = R.drawable.tabbar_artist;
				} else if (mTabBarHolder.tabAlbum.equals(mCurrentSelectTabView)) {
					icon = R.drawable.tabbar_album;
				} else if (mTabBarHolder.tabSong.equals(mCurrentSelectTabView)) {
					icon = R.drawable.tabbar_song;
				}
			}
			tabBarButtonHolder.icon.setImageResource(icon);
		}
	}

	private TabOption getTabOption(View tabButton) {
		TabOption tabOption = null;
		if (mTabBarHolder.tabPlaylist.equals(tabButton)) {
			tabOption = TabOption.playlist;
		} else if (mTabBarHolder.tabAlbum.equals(tabButton)) {
			tabOption = TabOption.album;
		} else if (mTabBarHolder.tabArtist.equals(tabButton)) {
			tabOption = TabOption.artist;
		} else if (mTabBarHolder.tabSong.equals(tabButton)) {
			tabOption = TabOption.song;
		} else if (mTabBarHolder.tabSelect.equals(tabButton)) {
			tabOption = mOrderOfMore[3];
		} else if (option1.equals(tabButton)) {
			tabOption = mOrderOfMore[0];
		} else if (option2.equals(tabButton)) {
			tabOption = mOrderOfMore[1];
		} else if (option3.equals(tabButton)) {
			tabOption = mOrderOfMore[2];
		}
		return tabOption;
	}

	private void selectTab(View tab) {
		TabOption tabOption = getTabOption(tab);
		if (mCurrentSelectTab != null && mCurrentSelectTab == tabOption
				&& mCurrentSelectTabView != null
				&& mCurrentSelectTabView.equals(tab)) {
			return;
		}
		cancelCurrentSelect();
		mCurrentSelectTab = tabOption;
		mCurrentSelectTabView = tab;
		TabBarButtonHolder tabBarButtonHolder = (TabBarButtonHolder) tab
				.getTag();
		tabBarButtonHolder.text.setTextColor(Color.rgb(255, 255, 255));
		int icon = 0;
		TabOption option = null;
		if (mTabBarHolder.tabSelect.equals(tab)) {
			tab.setBackgroundResource(R.drawable.tabbar_bg_down2);
			TabOption selectOption = mOrderOfMore[3];
			switch (selectOption) {
			case cloudmusic:
				icon = R.drawable.tabbar_cloudmusic_down;
				option = TabOption.cloudmusic;
				tabBarButtonHolder.text
						.setText(getText(R.string.tabbar_cloudmusic));
				break;
			case style:
				icon = R.drawable.tabbar_style_down;
				option = TabOption.style;
				tabBarButtonHolder.text.setText(getText(R.string.tabbar_style));
				break;
			case search:
				icon = R.drawable.tabbar_search_down;
				option = TabOption.search;
				tabBarButtonHolder.text
						.setText(getText(R.string.tabbar_search));
				break;
			case folder:
				icon = R.drawable.tabbar_folder_down;
				option = TabOption.folder;
				tabBarButtonHolder.text
						.setText(getText(R.string.tabbar_folder));
				break;
			default:
				break;
			}
		} else {
			tab.setBackgroundResource(R.drawable.tabbar_bg_down);
			if (mTabBarHolder.tabPlaylist.equals(tab)) {
				icon = R.drawable.tabbar_playlist_down;
				option = TabOption.playlist;
			} else if (mTabBarHolder.tabArtist.equals(tab)) {
				icon = R.drawable.tabbar_artist_down;
				option = TabOption.artist;
			} else if (mTabBarHolder.tabAlbum.equals(tab)) {
				icon = R.drawable.tabbar_album_down;
				option = TabOption.album;
			} else if (mTabBarHolder.tabSong.equals(tab)) {
				icon = R.drawable.tabbar_song_down;
				option = TabOption.song;
			}
		}
		tabBarButtonHolder.icon.setImageResource(icon);
		setContentPage(option);
	}

	private void setContentPage(TabOption option) {
		mCurrentSelectTab = option;
		switch (option) {
		case playlist:

			break;
		case artist:

			break;
		case album:

			break;
		case song:

			break;
		case cloudmusic:

			break;
		case style:

			break;
		case search:

			break;
		case folder:

			break;
		}
	}

}
