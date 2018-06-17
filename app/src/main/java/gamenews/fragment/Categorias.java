package gamenews.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gamenews.R;
import gamenews.adapter.Secciones;

public class Categorias extends Fragment {
    private static final String TOKEN = "token";
    private static final String CATEGORY = "category";

    private String token;
    private String category;

    private OnFragmentInteractionListener mListener;
    private ViewPager viewPager;

    public Categorias() {
    }


    public static Categorias newInstance(String token, String category) {
        Categorias fragment = new Categorias();
        Bundle args = new Bundle();
        args.putString(TOKEN, token);
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(TOKEN);
            category = getArguments().getString(CATEGORY);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);
        viewPager = v.findViewById(R.id.viewPager);
        Secciones adapter = new Secciones(getChildFragmentManager(), token, category, getContext());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = v.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
