package Controladores;

import java.util.List;

import Modelos.Juego;

public interface IJuegoDb {

    Juego elemento(int id);
    Juego elemento(String nombre);

    List<Juego> elementos();

    void agregar(Juego juego);
    void actualizar(int id, Juego juego);
    void eliminar(int id);

}
