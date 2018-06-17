package gamenews.inter;

import android.content.Context;

import gamenews.fragment.GamesNewsAPIService;

public class GamesNewsAPIUtils {


        private GamesNewsAPIUtils() {}

        public static final String BASE_URL = "http://gamenewsuca.herokuapp.com/";
    public static GamesNewsAPIService getAPIService(Context context) {
        return RetrofitClient.getClient(BASE_URL, context).create(GamesNewsAPIService.class);
        }
}
