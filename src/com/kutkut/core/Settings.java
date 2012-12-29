package com.kutkut.core;

/**
 * Store settings of the chatbot.
 * 
 * @author Ashutosh Kumar Singh [me@AKSingh.net]
 * @version 1.0 2012/12/24
 */

public class Settings {

    public static int getTheme() {
        return THEME;
    }
            
    public static void setTheme(int inputTheme) {
        THEME = inputTheme;
    }
    
private static int THEME = 1;
}
