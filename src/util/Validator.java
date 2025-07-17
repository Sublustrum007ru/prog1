package util; 
public class Validator{ 
    public static boolean isNumeric(String args){ 
        boolean result = false; 
        try{ 
            Integer.parseInt(args); 
            result = true; 
        }catch(NumberFormatException e){ 
            System.out.println(e.getMessage() + " cannot be converted to integer"); 
        } 
        return result; 
    } 
} 
