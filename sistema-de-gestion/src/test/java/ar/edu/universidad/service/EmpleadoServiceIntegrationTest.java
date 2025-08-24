package ar.edu.universidad.service;

import ar.edu.universidad.model.Departamento;
import ar.edu.universidad.model.Empleado;
import ar.edu.universidad.repository.DepartamentoRepository;
import ar.edu.universidad.repository.EmpleadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
class EmpleadoServiceIntegrationTest {
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Test
    void cuandoGuardarEmpleado_entoncesSePersisteCorrectamente() {
        Departamento departamento = new Departamento();
        departamento.setNombre("IT");
        departamento.setDescripcion("Departamento de Tecnología");
        departamento = departamentoRepository.save(departamento);

        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setEmail("juan.perez@empresa.com");
        empleado.setFechaContratacion(LocalDate.now());
        empleado.setSalario(new BigDecimal("50000.00"));
        empleado.setDepartamento(departamento);

        Empleado empleadoGuardado = empleadoService.guardar(empleado);

        assertNotNull(empleadoGuardado.getId());
        assertEquals("juan.perez@empresa.com", empleadoGuardado.getEmail());
        assertTrue(empleadoRepository.existsById(empleadoGuardado.getId()));
    }

    @Test
    void cuandoBuscarPorEmailExistente_entoncesRetornaEmpleado() {
        Departamento departamento = new Departamento();
        departamento.setNombre("HR");
        departamento.setDescripcion("Recursos Humanos");
        departamento = departamentoRepository.save(departamento);

        Empleado empleado = new Empleado();
        empleado.setNombre("Test");
        empleado.setApellido("User");
        empleado.setEmail("test@empresa.com");
        empleado.setFechaContratacion(LocalDate.now());
        empleado.setSalario(new BigDecimal("40000.00"));
        empleado.setDepartamento(departamento);
        empleadoRepository.save(empleado);

        Empleado resultado = empleadoService.buscarPorId(empleado.getId());
        assertNotNull(resultado);
        assertEquals("test@empresa.com", resultado.getEmail());
    }
}
