package gamenews.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gamenews.R;
import gamenews.fragment.GeneralFrag;
import gamenews.fragment.ImagenesFrag;
import gamenews.fragment.PlayerFRag;


public class Secciones extends FragmentPagerAdapter {
    private Context mContext;
    private String token;
    private String category;

    public Secciones(FragmentManager fm, String token, String category, Context context) {
        super(fm);
        this.token = token;
        this.category = category;
        this.mContext = context;
    }

    public Secciones(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GeneralFrag.newInstance(2, token, category);
            case 1:
                return PlayerFRag.newInstance(1, token, category);
            case 2:
                return ImagenesFrag.newInstance(3, token, category);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.generals);
            case 1:
                return mContext.getString(R.string.top_players);
            case 2:
                return mContext.getString(R.string.images);
            default:
                return null;
        }
    }
}
