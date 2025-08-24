package ar.edu.universidad.repository;

import ar.edu.universidad.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByFechaFinAfter(LocalDate fecha);
    List<Proyecto> findByNombreContainingIgnoreCase(String nombre);
}
