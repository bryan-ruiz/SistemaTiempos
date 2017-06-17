/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistematiempos;

/**
 *
 * @author Bryan
 */
public class SpanishLanguage {
    
    private String passwordError, notNumberError;
    private static SpanishLanguage instance = null;
    
    private SpanishLanguage() {
        this.passwordError = "Contraseña no valida.";
        this.notNumberError = "No utilice simbolos";
    }
    
    public static SpanishLanguage getInstance() {
        if (instance == null) {
            instance = new SpanishLanguage();
        }
        return instance;
    }
}
