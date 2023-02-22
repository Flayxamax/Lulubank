/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ildex
 */
public class Validadores {

    /**
     *
     * Valida una contraseña según una expresión regular.
     *
     * La contraseña debe contener al menos 8 caracteres y un máximo de 20,
     *
     * al menos una letra minúscula, una letra mayúscula, un número y un
     * carácter especial.
     *
     * @param s la contraseña a validar
     *
     * @return true si la contraseña es válida, false en caso contrario.
     */
    public boolean validaContrasena(String s) {
        CharSequence cadena = s;
        String recadena = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,20}$";

        Pattern pattern = Pattern.compile(recadena);

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Valida un nombre según una expresión regular.
     *
     * El nombre debe contener al menos 3 caracteres y un máximo de 50,
     *
     * sólo puede contener letras mayúsculas y minúsculas y espacios en blanco.
     *
     * @param s el nombre a validar
     *
     * @return true si el nombre es válido, false en caso contrario.
     */
    public boolean validaNombre(String s) {
        CharSequence cadena = s;
        String recadena = "^[a-zA-Z ]{3,50}$";

        Pattern pattern = Pattern.compile(recadena);

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Valida un apellido según una expresión regular.
     *
     * El apellido debe contener al menos 3 caracteres y un máximo de 30,
     *
     * sólo puede contener letras mayúsculas y minúsculas.
     *
     * @param s el apellido a validar
     *
     * @return true si el apellido es válido, false en caso contrario.
     */
    public boolean validaApellido(String s) {
        CharSequence cadena = s;
        String recadena = "^[a-zA-Z]{3,30}$";

        Pattern pattern = Pattern.compile(recadena);

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * Valida cadenas que representen direcciones de correos electronicos
     *
     * @param s Cadena
     * @return true si la validación es correcta, false en caso contrario
     */
    public boolean validaEmail(String s) {
        CharSequence cadena = s;
        String recadena = "\\w+@[a-z]+\\.[a-z]{1,4}";

        Pattern pattern = Pattern.compile(recadena);

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     *
     * Verifica si una cadena es vacía o no.
     *
     * @param s la cadena a verificar
     *
     * @return true si la cadena no es vacía, false en caso contrario
     */
    public boolean esVacia(String s) {
        CharSequence cadena = s;
        String recadena = "^.+$";

        Pattern pattern = Pattern.compile(recadena);

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
