package com.example.monte.gamenews.DataBase;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.example.monte.gamenews.APIUtils;
import com.example.monte.gamenews.DataBase.DAO.NewsDAO;
import com.example.monte.gamenews.DataBase.DAO.PlayerDAO;
import com.example.monte.gamenews.DataBase.DAO.UserDAO;
import com.example.monte.gamenews.Jugadores.Player;
import com.example.monte.gamenews.Noticias.Noticias;
import com.example.monte.gamenews.Service;
import com.example.monte.gamenews.Usuarios.DeleteUser;
import com.example.monte.gamenews.Usuarios.GPubFavUsuario;
import com.example.monte.gamenews.Usuarios.Usuario;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RepositorioDB {
    private Service service;
    private Application application;
    private NewsDAO newsDAO;
    private PlayerDAO playerDAO;
    private UserDAO userDAO;

public RepositorioDB(Application application){
    this.application = application;
    DataBase db = DataBase.getDatabase(application);
    newsDAO = db.newsDAO();
    playerDAO = db.playerDAO();
    userDAO = db.userDAO();
    service = APIUtils.getAPIService();
}


    //USUARIO
    public Usuario registrarUsuario(String token, String user, String password, String avatar) {

        AsyncTask<String, Void, Usuario> task = new AsyncTask<String, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(String... strings) {
                Usuario list = null;
                if (isOnline()) {

                    try {
                        list = service.registrarUsuario(strings[0], strings[1], strings[2], strings[3]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, user, password, avatar).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario actualizarUsuario(String token, String password, String id) {

        AsyncTask<String, Void, Usuario> task = new AsyncTask<String, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(String... strings) {
                Usuario list = null;
                if (isOnline()) {

                    try {
                        list = service.actualizarUsuario(strings[0], strings[1], strings[2]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, password, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario datosUsuario(String token, String id) {

        AsyncTask<String, Void, Usuario> task = new AsyncTask<String, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(String... strings) {
                Usuario list = null;
                if (isOnline()) {

                    try {
                        list = service.datosUsuario(strings[0], strings[1]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeleteUser removerUsuario(String token, String id) {

        AsyncTask<String, Void, DeleteUser> task = new AsyncTask<String, Void, DeleteUser>() {
            @Override
            protected DeleteUser doInBackground(String... strings) {
                DeleteUser list = null;
                if (isOnline()) {

                    try {
                        list = service.removerUsuario(strings[0], strings[1]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GPubFavUsuario agregarNoticiasFav(String token, String newnew, String id) {

        AsyncTask<String, Void, GPubFavUsuario> task = new AsyncTask<String, Void, GPubFavUsuario>() {
            @Override
            protected GPubFavUsuario doInBackground(String... strings) {
                GPubFavUsuario list = null;
                if (isOnline()) {

                    try {
                        list = service.agregarNoticiaFav(strings[0], strings[1], strings[2]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, newnew, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeleteUser removerNoticiasFav(String token, String newnew, String id) {

        AsyncTask<String, Void, DeleteUser> task = new AsyncTask<String, Void, DeleteUser>() {
            @Override
            protected DeleteUser doInBackground(String... strings) {
                DeleteUser list = null;
                if (isOnline()) {

                    try {
                        list = service.removerNoticiasFav(strings[0], strings[1], strings[2]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, newnew, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Usuario detallesUsuario(String token) {

        AsyncTask<String, Void, Usuario> task = new AsyncTask<String, Void, Usuario>() {
            @Override
            protected Usuario doInBackground(String... strings) {
                Usuario list = null;
                if (isOnline()) {

                    try {
                        list = service.detallesUsuario(strings[0]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    //NOTICIAS
    public List<Noticias> listaNoticias(String token) {

        AsyncTask<String, Void, List<Noticias>> task = new AsyncTask<String, Void, List<Noticias>>() {
            @Override
            protected List<Noticias> doInBackground(String... strings) {
                List<Noticias> list = null;
                if (isOnline()) {

                    try {
                        list = service.listaNoticias(strings[0]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Noticias> listaTipoNoticias(String token){
        AsyncTask<String, Void, List<Noticias>> task = new AsyncTask<String, Void, List<Noticias>>() {
            @Override
            protected List<Noticias> doInBackground(String... strings) {
                List<Noticias> list = null;
                if (isOnline()) {

                    try {
                        list = service.listaTipoNoticias(strings[0]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<Noticias> listaNoticiasCat(String token, String game){
        AsyncTask<String, Void, List<Noticias>> task = new AsyncTask<String, Void, List<Noticias>>() {
            @Override
            protected List<Noticias> doInBackground(String... strings) {
                List<Noticias> list = null;
                if (isOnline()) {

                    try {
                        list = service.listaNoticiasCat(strings[0], strings[1]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, game).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Noticias infoNoticia(String token, String id){
        AsyncTask<String, Void, Noticias> task = new AsyncTask<String, Void, Noticias>() {
            @Override
            protected Noticias doInBackground(String... strings) {
                Noticias list = null;
                if (isOnline()) {

                    try {
                        list = service.infoNoticia(strings[0], strings[1]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token, id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }


    //JUGADORES

    public List<Player> listaPlayers(String token) {

        AsyncTask<String, Void, List<Player>> task = new AsyncTask<String, Void, List<Player>>() {
            @Override
            protected List<Player> doInBackground(String... strings) {
                List<Player> list = null;
                if (isOnline()) {

                    try {
                        list = service.listaPlayers(strings[0]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> juegoDePlayers(String token) {

        AsyncTask<String, Void, List<Player>> task = new AsyncTask<String, Void, List<Player>>() {
            @Override
            protected List<Player> doInBackground(String... strings) {
                List<Player> list = null;
                if (isOnline()) {

                    try {
                        list = service.juegoDePlayers(strings[0]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> listaPlayerPorJuego(String token, String id) {

        AsyncTask<String, Void, List<Player>> task = new AsyncTask<String, Void, List<Player>>() {
            @Override
            protected List<Player> doInBackground(String... strings) {
                List<Player> list = null;
                if (isOnline()) {

                    try {
                        list = service.listaPlayerPorJuego(strings[0], strings[1]).execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }

        };

        try {
            return task.execute(token).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
