package toastsimulation;

/**
 * Utilities to do conversions with milliseconds and text input
 * 
 * By Emiliano, Victor and Jonny
 */
/** This method converts a String number input and converts to int  */
public class Utils {
    
    public static Integer stringToMillliseconds(String inputText){
        return 1000 * Integer.parseInt(inputText);
    }
}
