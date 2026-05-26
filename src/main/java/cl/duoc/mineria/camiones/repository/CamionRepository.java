package cl.duoc.mineria.camiones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mineria.camiones.model.Camion;

// Indica que esta interfaz accede a base de datos
@Repository

public interface CamionRepository extends JpaRepository<Camion, Long> {

    // Busca un camión por código
    Optional<Camion> findByCodigo(String codigo);

    // Busca camiones por estado operativo
    List<Camion> findByEstadoOperativo(String estadoOperativo);

    // Busca camiones por sector
    List<Camion> findBySector(String sector);
}
