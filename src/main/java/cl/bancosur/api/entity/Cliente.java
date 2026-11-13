package cl.bancosur.api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity                      // esta clase es una tabla
@Table(name = "clientes")    // nombre de la tabla en la BD
public class Cliente {

    @Id                                                   // clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // autoincremental
    private Long id;

    @Column(nullable = false, unique = true, length = 12) // NOT NULL + UNIQUE
    private String rut;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false)
    private Boolean activo = true;

    @CreationTimestamp                 // fecha automática al insertar
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    public Cliente() {}                // JPA exige constructor vacío

    public Long getId() { return id; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}