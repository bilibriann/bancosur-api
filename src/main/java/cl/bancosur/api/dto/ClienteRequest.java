package cl.bancosur.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Datos que el cliente de la API envía para crear/actualizar un Cliente.
// Se separa de la entidad para no exponer campos como id o fechaCreacion.
public record ClienteRequest(

        @NotBlank(message = "El RUT es obligatorio")
        @Pattern(regexp = "^\\d{7,8}-[\\dkK]$", message = "El RUT debe tener el formato 12345678-9")
        String rut,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no tiene un formato válido")
        @Size(max = 120, message = "El email no puede superar 120 caracteres")
        String email
) {}
