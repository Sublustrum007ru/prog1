package controller.finds;

import controller.ParsingSites;

public class FindVolume {
    String volume;
    private ParsingSites parsingSites;

    public void setParsingSites(ParsingSites parsingSites){
        this.parsingSites = parsingSites;
    }

    public String findVolume(String product){
        String result = "1 шт";
        String[] searchVolume = product.split(" ");
        for (int i = 0; i < searchVolume.length; i++) {
            if(searchVolume[i].equals("ml") || searchVolume[i].equals("ml.") || searchVolume[i].equals("мл") || searchVolume[i].equals("мл.")){
                result =  searchVolume[i - 1] + " " + searchVolume[i];
            }
        }
        return result;
    }

}
