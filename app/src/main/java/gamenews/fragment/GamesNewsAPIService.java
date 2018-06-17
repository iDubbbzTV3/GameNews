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
    Call<List<UserPOJO>> conseguirUsuarios(@Header("Authorization") String authHeader);

    @POST("/users")
    @FormUrlEncoded
    Call<UserPOJO> agregarUsuario(@Header("Authorization") String authHeader, @Field("user") String user,
                           @Field("avatar") String avatar, @Field("password") String password);

    @PUT("/users/{id}")
    @FormUrlEncoded
    Call<UserPOJO> editarUsuario(@Header("Authorization") String authHeader, @Path("id") String id, @Field("password") String password);

    @GET("/users/{id}")
    @FormUrlEncoded
    Call<UserWithFavsPOJO> conseguirUsuario(@Header("Authorization") String authHeader, @Path("id") String id);

    @DELETE("/users/{id}")
    @FormUrlEncoded
    Call<UserPOJO> borrarUsuario(@Header("Authorization") String authHeader, @Path("id") String id);

    @GET("users/detail")
    Call<UserPOJO> conseguirdetallesUsuario(@Header("Authorization") String authHeader);

    @POST("/users/{id}/fav")
    @FormUrlEncoded
    Call<NewNewPOJO> agregarFavoritosUsuario(@Header("Authorization") String authHeader, @Path("id") String id, @Field("new") String n_new);


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/users/{id}/fav", hasBody = true)
    Call<MessageResultPOJO> borrarFavoritosUsuario(@Header("Authorization") String authHeader, @Path("id") String id, @Field("new") String n_new);

    @GET("/news")
    Call<List<NewPOJO>> conseguirNoticias(@Header("Authorization") String authHeader);

    @GET("/news/type/list")
    Call<List<String>> ConseguirNoticias(@Header("Authorization") String authHeader);

    @GET("/news/type/{category}")
    Call<List<NewPOJO>> conseguirNoticiasCat(@Header("Authorization") String authHeader, @Path("category") String category);

    @POST("/news")
    @FormUrlEncoded
    Call<NewPOJO> agregarNoticia(@Header("Authorization") String authHeader, @Path("title") String title, @Path("description")
            String description, @Path("coverImage") String coverImage, @Path("body") String body, @Path("game") String category);

    @GET("/news/{id}")
    Call<NewPOJO> conseguirNoticiaporId(@Header("Authorization") String authHeader, @Path("id") String id);

    @GET("/players")
    Call<List<PlayerPOJO>> conseguirTodosJugadores(@Header("Authorization") String authHeader);

    @GET("/players/type/list")
    Call<List<String>> conseguirJugadoresCat(@Header("Authorization") String authHeader);

    @GET("/players/type/{game}")
    Call<List<PlayerPOJO>> coseguirJugadoresporJuego(@Header("Authorization") String authHeader, @Path("game") String category);

    @GET("/players/{id}")
    Call<PlayerPOJO> conseguirJugadorporId(@Header("Authorization") String authHeader, @Path("id") String id);
}

