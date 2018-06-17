package gamenews.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gamenews.R;
import gamenews.adapter.Images;
import gamenews.adapter.ImagesR;
import gamenews.viewmodel.GamesNewsViewModel;

public class ImagenesFrag extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count", TOKEN = "token", CATEGORY = "category";
    private int mColumnCount = 3;
    private String token;
    private String category;
    private GamesNewsViewModel gamesNewsViewModel;
    private ImagesR mAdapter;

    public ImagenesFrag() {
    }

    @SuppressWarnings("unused")
    public static ImagenesFrag newInstance(int columnCount, String token, String category) {
        ImagenesFrag fragment = new ImagenesFrag();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(TOKEN, token);
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            token = getArguments().getString(TOKEN);
            category = getArguments().getString(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images_list, container, false);
        gamesNewsViewModel = ViewModelProviders.of(this).get(GamesNewsViewModel.class);
       if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new Images(context, mColumnCount));
            }
            final LiveData<List<String>> list = gamesNewsViewModel.getAllNewsImageByCategory(category);
            mAdapter = new ImagesR(context);
            recyclerView.setAdapter(mAdapter);

            list.observe(this, new Observer<List<String>>() {
                @Override
                public void onChanged(@Nullable List<String> strings) {
                    mAdapter.setList(strings);
                    mAdapter.notifyDataSetChanged();
                }
            });
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
    }

    public interface OnListFragmentInteractionListener {
    }
}
