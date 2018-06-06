package com.example.monte.gamenews;

import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.Noticias.Noticias;
import com.example.monte.gamenews.Usuarios.DeleteUser;
import com.example.monte.gamenews.Usuarios.GPubFavUsuario;
import com.example.monte.gamenews.Usuarios.Login;
import com.example.monte.gamenews.Usuarios.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {

    @POST("/login")
    @FormUrlEncoded
    Call<Login> Login(@Field("user") String user, @Field("password")String password);

    @POST("/users")
    @FormUrlEncoded
    Call<Usuario> registrarUsuario(@Header("Authorization") String token, @Field ("user") String user,
                                   @Field("password") String password, @Field ("avatar") String avatar);

    @PUT("/users/{id}")
    @FormUrlEncoded
    Call<Usuario> actualizarUsuario(@Header("Authorization") String token, @Field ("password")
                                    String password, @Path("id") String id);

    @GET("/users/{id}")
    @FormUrlEncoded
    Call<Usuario> datosUsuario(@Header("Authorization") String token, @Path ("id") String id);

    @DELETE("/users/{id}")
    @FormUrlEncoded
    Call<DeleteUser> removerUsuario(@Header("Authorization") String token, @Path ("id") String id);

    @POST("/users/{id}/fav")
    @FormUrlEncoded
    Call<GPubFavUsuario> agregarNoticiaFav(@Header("Authorization") String token, @Field("new") String newnew,
                                           @Path ("id") String id);

    @DELETE("/users/{id}/fav")
    @FormUrlEncoded
    Call<DeleteUser> removerNoticiasFav(@Header("Authorization") String token, @Field("new") String newnew,
                                            @Path ("id") String id);

    @GET("/users/detail")
    @FormUrlEncoded
    Call<Usuario> detallesUsuario(@Header("Authorization") String token);

    //NOTICIAS

    @GET("/news")
    @FormUrlEncoded
    Call<List<Noticias>> listaNoticias(@Header("Authorization") String token);

    @GET("/news/type/list")
    @FormUrlEncoded
    Call<List<Noticias>> listaTipoNoticias(@Header("Authorization") String token);

    @GET("/news/type/{id}")
    @FormUrlEncoded
    Call<List<Noticias>> listaNoticiasCat(@Header("Authorization") String token, @Path("id") String game);

    @POST("/news")
    @FormUrlEncoded
    Call<Noticias> crearNoticia(@Header("Authorization") String token, @Field("title") String title,
                                @Field("description") String description, @Path("coverImage") String coverImage,
                                @Path("body") String body, @Path("game") String game);

    @GET("/news/{id}")
    @FormUrlEncoded
    Call<Noticias> infoNoticia(@Header("Authorization") String token, @Path("id") String id);

    //JUGADORES

    @GET("/players")
    @FormUrlEncoded
    Call<List<Player>> listaPlayers(@Header("Authorization") String token);

    @GET("/players/type/list")
    @FormUrlEncoded
    Call<List<Player>> juegoDePlayers(@Header("Authorization") String token);

    @GET("/players/type/{id}")
    @FormUrlEncoded
    Call<List<Player>> listaPlayerPorJuego(@Header("Authorization") String token, @Path("id") String id);

    @POST("/players")
    @FormUrlEncoded
    Call<Player> creadorJugador(@Header("Authorization") String token, @Field("Nombre1") String name,
                                @Field("Biografia1") String biografia, @Field("Avatar") String avatar,
                                @Field("Game") String game);
}
