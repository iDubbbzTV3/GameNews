package gamenews.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gamenews.R;
import gamenews.adapter.NewsR;
import gamenews.room.model.News;
import gamenews.viewmodel.GamesNewsViewModel;


public class GeneralFrag extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_COLUMN_COUNT = "column-count", TOKEN = "token", CATEGORY = "category";
    private static final String USER_ID = "userId";
    private int mColumnCount = 1;
    private NewsFragment.OnListFragmentInteractionListener mListener;
    private GamesNewsViewModel gamesNewsViewModel;
    private String token;
    private NewsR mAdapter;
    private String category;
    private SharedPreferences sp;
    private String user;
    private LiveData<List<News>> list;
    SwipeRefreshLayout mySwipeRefreshLayout;

    public GeneralFrag() {
    }


    @SuppressWarnings("unused")
    public static GeneralFrag newInstance(int columnCount, String token, String category) {
        GeneralFrag fragment = new GeneralFrag();
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
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        gamesNewsViewModel = ViewModelProviders.of(this).get(GamesNewsViewModel.class);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        mySwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mySwipeRefreshLayout.setOnRefreshListener(this);
        doInBackGroundTask task = new doInBackGroundTask();
        task.execute();
        list = gamesNewsViewModel.getAllNewsByCategory(category);
        sp = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);

        user = sp.getString(USER_ID, "");
        mAdapter = new NewsR(context) {
            @Override
            public void setAction(boolean isFavorite, String id_new) {
                if (!isFavorite) {
                    if (!gamesNewsViewModel.addFavorite(token, user, id_new)) {
                        Toast.makeText(getContext(), R.string.mensaje_error, Toast.LENGTH_SHORT).show();
                    }
                } else if (!gamesNewsViewModel.removeFavorite(token, user, id_new)) {
                    Toast.makeText(getContext(), R.string.mensaje_error, Toast.LENGTH_SHORT).show();
                }
            }
        };
        list.observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                List<News> new_list = new ArrayList<>();
                mAdapter.setList(new_list);
                mAdapter.notifyDataSetChanged();
                for (News n : news) {
                    new_list.add(n);
                    mAdapter.notifyItemInserted(new_list.size());
                }
            }
        });

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL);


            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(mLayoutManager);
        }
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.grid_layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(mAdapter);

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

    @Override
    public void onRefresh() {

        doInBackGroundTask task = new doInBackGroundTask();
        task.execute();
    }

    public interface OnListFragmentInteractionListener {
    }

    private class doInBackGroundTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {

            return gamesNewsViewModel.updateNewsByCategory(token, category);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mySwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(integer);
        }
    }
}
