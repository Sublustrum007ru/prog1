package util;

import controller.SiteSettings;

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

    public static boolean isNotEmptySiteSettings(SiteSettings siteSettings){
        boolean result = false;
        if(!siteSettings.getSiteURL().equals("")){
            if(!siteSettings.getBaseURL().equals("")){
                if(!siteSettings.getCategorySelector().equals("")){
                    if (!siteSettings.getProductSelector().equals("")){
                        if(!siteSettings.getTitleSelector().equals("")){
                            if (!siteSettings.getPriceSelector().equals("")){
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
} 
