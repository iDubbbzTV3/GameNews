package gamenews.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import gamenews.repo.GamesNewsPoblationRepository;
import gamenews.repo.GamesNewsRepository;
import gamenews.room.model.News;
import gamenews.room.model.Player;

public class GamesNewsViewModel extends AndroidViewModel {
    private final GamesNewsRepository mRepository;
    private final GamesNewsPoblationRepository gamesNewsPoblationRepository;

    public GamesNewsViewModel(@NonNull Application application) {
            super(application);
            mRepository = new GamesNewsRepository(application);
        gamesNewsPoblationRepository = new GamesNewsPoblationRepository(application);
    }

    public boolean addFavorite(String token, String user, String n_new) {
        return gamesNewsPoblationRepository.addFavorite(token, user, n_new);
    }

    public boolean removeFavorite(String token, String user, String n_new) {
        return gamesNewsPoblationRepository.removeFavorite(token, user, n_new);
    }

    public LiveData<List<News>> getAllNews(){
        return mRepository.getAllNews();
    }

    public LiveData<List<News>> getAllNewsByCategory(String category) {
        return mRepository.getNewsByCategory(category);
    }

    public LiveData<List<String>> getNewsCategories() {
        return mRepository.getNewsCategories();
    }
    public LiveData<List<News>> getFavoriteNews() {
        return mRepository.getFavoriteNews();
    }


    public LiveData<List<String>> getAllNewsImageByCategory(String category) {
        return mRepository.getNewsImageByCategory(category);
    }

    public LiveData<List<Player>> getPlayersByCategory(String category) {
        return mRepository.getPlayersByCategory(category);
    }

    public int updateNews(String token) {
        return gamesNewsPoblationRepository.updateNews(token);
    }

    public boolean updateUserInfo(String token) {
        return gamesNewsPoblationRepository.updateUserInfo(token);
    }

    public boolean updateUserInfoNoAsync(String token) {
        return gamesNewsPoblationRepository.updateUserInfoNoAsync(token);
    }

    public int updateNewsByCategory(String token, String category) {
        return gamesNewsPoblationRepository.updateNewsByCategory(token, category);
    }

    public int updateTopPlayersByCategory(String token, String category) {
        return gamesNewsPoblationRepository.updatePlayersByCategory(token, category);
    }

    public boolean updatePassword(String token, String iduser, String new_password) {
        return gamesNewsPoblationRepository.updatePassword(token, iduser, new_password);
    }

}
