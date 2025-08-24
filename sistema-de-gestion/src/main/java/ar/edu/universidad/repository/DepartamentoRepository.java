package ar.edu.universidad.repository;

import ar.edu.universidad.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Departamento findByNombre(String nombre);
}
