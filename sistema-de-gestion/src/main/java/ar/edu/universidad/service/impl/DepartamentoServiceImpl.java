package ar.edu.universidad.service.impl;

import ar.edu.universidad.model.Departamento;
import ar.edu.universidad.repository.DepartamentoRepository;
import ar.edu.universidad.service.DepartamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));
    }

    @Override
    public Departamento buscarPorNombre(String nombre) {
        Departamento dep = departamentoRepository.findByNombre(nombre);
        if (dep == null)
            throw new RuntimeException("Departamento no encontrado: " + nombre);
        return dep;
    }

    @Override
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento actualizar(Long id, Departamento departamento) {
        if (!departamentoRepository.existsById(id)) {
            throw new RuntimeException("Departamento no encontrado con ID: " + id);
        }
        departamento.setId(id);
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RuntimeException("Departamento no encontrado con ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }
}
