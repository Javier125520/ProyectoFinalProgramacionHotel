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
}
