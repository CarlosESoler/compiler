package org.study.model;

import java.util.HashMap;

public class ParseLexicalClass {

    public String parseLexemeClass(int id) {
        if(id == 2) {
            return "identificador";
        }
        if(id == 3) {
            return "constante_inteiro";
        }
        if(id == 4) {
            return "constante_float";
        }
        if(id == 5) {
            return "constante_caracter";
        }
        if(id == 6) {
            return "constante_string";
        }
        if(id >= 7 && id <= 23) {
            return "palavra_reservada";
        }
        if(id >= 24 && id <= 39) {
            return "simbolo";
        }
        return null;
    }
}
