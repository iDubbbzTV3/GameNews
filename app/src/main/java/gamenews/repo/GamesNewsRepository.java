package gamenews.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import gamenews.room.GamesNewsDataBase;
import gamenews.room.dao.NoticiasDao;
import gamenews.room.dao.PlayerDao;
import gamenews.room.dao.UserDao;
import gamenews.room.model.News;
import gamenews.room.model.Player;
import gamenews.room.model.User;

public class GamesNewsRepository {
    private final LiveData<List<News>> mAllNews;
    private final LiveData<List<News>> mAllFavoriteNews;
    private Application application;
    private static NoticiasDao newDao;
    private static PlayerDao playerDao;
    private static UserDao userDao;

    public GamesNewsRepository(Application application) {
        this.application = application;
        GamesNewsDataBase db = GamesNewsDataBase.getDatabase(application);
        newDao = db.newDao();
        playerDao = db.playerDao();
        userDao = db.userDao();
        mAllNews = newDao.getAllNews();
        mAllFavoriteNews = newDao.getFavoritesNews();
    }


    public LiveData<List<News>> getAllNews() {
        return mAllNews;
    }

    public LiveData<List<News>> getNewsByCategory(String category) {
        return newDao.getNewsByCategory(category);
    }

    public LiveData<List<String>> getNewsCategories() {
        return newDao.getNewsCategory();
    }

    public LiveData<List<String>> getNewsImageByCategory(String category) {
        return newDao.getNewsImageByCategory(category);
    }

    public LiveData<List<Player>> getPlayersByCategory(String category) {
        return playerDao.getPlayersByCategory(category);
    }

    public LiveData<List<News>> getFavoriteNews() {
        return mAllFavoriteNews;
    }


    public News getNewById(String id) {
        News n = null;
        AsyncTask<String, Void, News> task = new AsyncTask<String, Void, News>() {
            @Override
            protected News doInBackground(String... strings) {
                News n = null;
                n = newDao.getNewByID(strings[0]);
                return n;
            }
        };
        try {
            n = task.execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return n;
    }

    public User getCurrentUserInfo() {
        return userDao.getUser();
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
