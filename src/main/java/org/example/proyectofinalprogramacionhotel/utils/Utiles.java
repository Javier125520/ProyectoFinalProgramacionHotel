package org.example.proyectofinalprogramacionhotel.utils;

import java.util.regex.Pattern;

public class Utiles {

    public static boolean validarDNI(String dni) {
        // Expresión regular para un DNI válido (8 dígitos seguidos de una letra mayúscula)
        String regex = "^[0-9]{8}[A-Z]$";
        return Pattern.matches(regex, dni);
    }

    public static boolean validarTelefono(String telefono) {
        // Expresión regular para un número de teléfono válido (9 dígitos)
        String regex = "^[0-9]{9}$";
        return Pattern.matches(regex, telefono);
    }

    public static boolean validarGmail(String email) {
        // Expresión regular para un correo electrónico válido
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    public static boolean validarContrasena(String contrasena) {
        // Expresión regular para una contraseña válida (mínimo 8 caracteres, al menos una letra y un número)
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$";
        return Pattern.matches(regex, contrasena);
    }

    public static boolean validarNombre(String nombre) {
        // Expresión regular para un nombre válido (solo letras y espacios)
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return Pattern.matches(regex, nombre);
    }

    public static boolean validarNumeroPersonas(String numero) {
        // Expresión regular para un número de personas válido (número entero positivo)
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, numero);
    }

    public static boolean validarNumeroHabitacion(String numeroHabitacion) {
        // Expresión regular para un número de habitación válido (número entero positivo)
        String regex = "^[1-9][0-9]*$";
        return Pattern.matches(regex, numeroHabitacion);
    }

    public static boolean validarPrecioNoche(String precioNoche) {
        // Expresión regular para un precio por noche válido (número decimal positivo)
        String regex = "^[0-9]+(\\.[0-9]{1,2})?$";
        return Pattern.matches(regex, precioNoche);
    }

    public static boolean validarCodigoGerente(String codigoGerente) {
        // Expresión regular para un código de gerente válido (número entero positivo)
        String regex = "^[A-Za-z0-9]+$";
        return Pattern.matches(regex, codigoGerente);
    }
}
