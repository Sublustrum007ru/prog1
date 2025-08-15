package controller;

public class Category {
    private String name;
    private String URL;

    public Category(String name, String URL){
        this.name = name;
        this.URL = URL;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setURL(String URL){
        this.URL = URL;
    }

    public String getURL(){
        return URL;
    }
}
