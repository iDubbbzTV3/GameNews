package com.example.monte.gamenews.Noticias;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.monte.gamenews.DataBase.AllViewModel;
import com.example.monte.gamenews.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NoticiasFrag extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count", TOKEN = "token";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private AllViewModel allViewModel;
    private String token;


    public NoticiasFrag() {
    }


    @SuppressWarnings("unused")
    public static NoticiasFrag newInstance(int columnCount,String token) {
        NoticiasFrag fragment = new NoticiasFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(TOKEN, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            token = getArguments().getString(TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_all_news, container, false);
        allViewModel =  ViewModelProviders.of(this).get(AllViewModel.class);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            final NoticiasAdapter mAdapter = new NoticiasAdapter(allViewModel.listaNoticias(token), mListener, context);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                GridLayoutManager mLayoutManager = new GridLayoutManager(context, mColumnCount);
                mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        switch(mAdapter.getItemViewType(position)){
                            case 1:
                                return 2;
                            default:
                                return 1;
                        }
                    }
                });
                recyclerView.setLayoutManager(mLayoutManager);
            }
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnListFragmentInteractionListener {

    }
}
