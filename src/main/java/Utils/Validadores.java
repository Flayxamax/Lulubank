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
public class Validadores{

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
}
