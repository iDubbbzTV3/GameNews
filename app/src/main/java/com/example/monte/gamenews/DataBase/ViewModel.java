package com.example.monte.gamenews.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.Noticias.Noticias;
import com.example.monte.gamenews.Usuarios.DeleteUser;
import com.example.monte.gamenews.Usuarios.GPubFavUsuario;
import com.example.monte.gamenews.Usuarios.Usuario;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private final RepositorioDB repositorioDB;

    public ViewModel(@NonNull Application application){
        super(application);
        repositorioDB = new RepositorioDB(application);
    }

    public Usuario registrarUsuario(String token, String user, String password, String avatar){
        return repositorioDB.registrarUsuario(token, user, password, avatar);
    }

    public Usuario actualizarUsuario(String token, String password, String id){
        return repositorioDB.actualizarUsuario(token, password, id);
    }

    public Usuario datosUsuario(String token, String id){
        return repositorioDB.datosUsuario(token, id);
    }

    public DeleteUser removerUsuario(String token, String id){
        return repositorioDB.removerUsuario(token, id);
    }

    public GPubFavUsuario agregarNoticiasFav(String token, String newnew, String id){
        return repositorioDB.agregarNoticiasFav(token, newnew, id);
    }

    public DeleteUser removerNoticiasFav(String token, String newnew, String id){
        return repositorioDB.removerNoticiasFav(token, newnew, id);
    }

    public Usuario detallesUsuario(String token){
        return repositorioDB.detallesUsuario(token);
    }

    public List<Player> listaPlayers(String token){
        return repositorioDB.listaPlayers(token);
    }

    public List<Player> juegoDePlayers(String token){
        return repositorioDB.juegoDePlayers(token);
    }

    public List<Player> listaPlayerPorJuego(String token, String id){
        return repositorioDB.listaPlayerPorJuego(token, id);
    }

    public List<Noticias>listaNoticias(String token){
        return repositorioDB.listaNoticias(token);
    }

    public List<Noticias>listaTipoNoticias(String token){
        return repositorioDB.listaTipoNoticias(token);
    }

    public List<Noticias>listaNoticiasCat(String token, String game){
        return repositorioDB.listaNoticiasCat(token, game);
    }

    public Noticias infoNoticia(String token, String id){
        return repositorioDB.infoNoticia(token, id);
    }

}
