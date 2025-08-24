package ar.edu.universidad.service;

import ar.edu.universidad.model.Proyecto;
import java.time.LocalDate;
import java.util.List;

public interface ProyectoService {
    Proyecto guardar(Proyecto proyecto);

    Proyecto buscarPorId(Long id);

    List<Proyecto> buscarProyectosActivos(LocalDate fecha);

    List<Proyecto> buscarPorNombre(String nombre);

    List<Proyecto> obtenerTodos();

    Proyecto actualizar(Long id, Proyecto proyecto);

    void eliminar(Long id);
}
