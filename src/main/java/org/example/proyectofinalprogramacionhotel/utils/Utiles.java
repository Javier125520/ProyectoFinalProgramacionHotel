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

    public static boolean validarFechaEntrada(String fechaEntrada) {
        // Expresión regular para una fecha de entrada válida (dd/mm/yyyy)
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";
        return Pattern.matches(regex, fechaEntrada);
    }

    public static boolean validarFechaSalida(String fechaSalida) {
        // Expresión regular para una fecha de salida válida (dd/mm/yyyy)
        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";
        return Pattern.matches(regex, fechaSalida);
    }
}
