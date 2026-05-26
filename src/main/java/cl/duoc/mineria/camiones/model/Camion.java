package cl.duoc.mineria.camiones.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

// Nombre de la tabla PostgreSQL
@Table(name = "camiones")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Ordena atributos en Postman
@JsonPropertyOrder({
    "id",
    "codigo",
    "modelo",
    "capacidadCarga",
    "estadoOperativo",
    "sector",
    "fechaRegistro"
})

public class Camion {

    // Llave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    // Código interno del camión
    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    // Modelo del camión minero
    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    // Capacidad de carga en toneladas
    @Column(name = "capacidad_carga", nullable = false)
    private Double capacidadCarga;

    // Estado operativo del camión
    @Column(name = "estado_operativo", nullable = false, length = 50)
    private String estadoOperativo;

    // Sector donde opera
    @Column(name = "sector", nullable = false, length = 100)
    private String sector;

    // Fecha de registro automática
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
}