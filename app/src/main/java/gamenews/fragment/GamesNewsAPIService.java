package gamenews.fragment;

import java.util.List;

import gamenews.inter.pojo.MessageResultPOJO;
import gamenews.inter.pojo.NewNewPOJO;
import gamenews.inter.pojo.NewPOJO;
import gamenews.inter.pojo.PlayerPOJO;
import gamenews.inter.pojo.TokenPOJO;
import gamenews.inter.pojo.UserPOJO;
import gamenews.inter.pojo.UserWithFavsPOJO;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GamesNewsAPIService {

    @POST("/login")
    @FormUrlEncoded
    Call<TokenPOJO> logIn(@Field("user") String user,
                          @Field("password") String password);

    @GET("/users")
    Call<List<UserPOJO>> getAllUsers(@Header("Authorization") String authHeader);

    @POST("/users")
    @FormUrlEncoded
    Call<UserPOJO> addUser(@Header("Authorization") String authHeader, @Field("user") String user,
                           @Field("avatar") String avatar, @Field("password") String password);

    @PUT("/users/{id}")
    @FormUrlEncoded
    Call<UserPOJO> editUser(@Header("Authorization") String authHeader, @Path("id") String id, @Field("password") String password);

    @GET("/users/{id}")
    @FormUrlEncoded
    Call<UserWithFavsPOJO> getUserByID(@Header("Authorization") String authHeader, @Path("id") String id);

    @DELETE("/users/{id}")
    @FormUrlEncoded
    Call<UserPOJO> deleteUserByID(@Header("Authorization") String authHeader, @Path("id") String id);

    @GET("users/detail")
    Call<UserPOJO> getUserDetail(@Header("Authorization") String authHeader);

    @POST("/users/{id}/fav")
    @FormUrlEncoded
    Call<NewNewPOJO> addUserFav(@Header("Authorization") String authHeader, @Path("id") String id, @Field("new") String n_new);


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/users/{id}/fav", hasBody = true)
    Call<MessageResultPOJO> deleteUserFav(@Header("Authorization") String authHeader, @Path("id") String id, @Field("new") String n_new);

    @GET("/news")
    Call<List<NewPOJO>> getAllNews(@Header("Authorization") String authHeader);

    @GET("/news/type/list")
    Call<List<String>> getNewsCategory(@Header("Authorization") String authHeader);

    @GET("/news/type/{category}")
    Call<List<NewPOJO>> getNewsByCategory(@Header("Authorization") String authHeader, @Path("category") String category);

    @POST("/news")
    @FormUrlEncoded
    Call<NewPOJO> addNew(@Header("Authorization") String authHeader, @Path("title") String title, @Path("description")
            String description, @Path("coverImage") String coverImage, @Path("body") String body, @Path("game") String category);

    @GET("/news/{id}")
    Call<NewPOJO> getNewByID(@Header("Authorization") String authHeader, @Path("id") String id);

    @GET("/players")
    Call<List<PlayerPOJO>> getAllPlayers(@Header("Authorization") String authHeader);

    @GET("/players/type/list")
    Call<List<String>> getPlayersCategory(@Header("Authorization") String authHeader);

    @GET("/players/type/{game}")
    Call<List<PlayerPOJO>> getPlayersByCategory(@Header("Authorization") String authHeader, @Path("game") String category);

    @GET("/players/{id}")
    Call<PlayerPOJO> getPlayerByID(@Header("Authorization") String authHeader, @Path("id") String id);

}

