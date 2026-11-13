package cl.bancosur.api.repository;

import cl.bancosur.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ya trae findAll, findById, save, deleteById, etc.
// Spring genera el SQL de los métodos a partir de su nombre.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByRut(String rut);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
