package controller;

public class Product {

    String title;
    String volume;
    String Price;

    public Product(String title, String volume, String price) {
        this.title = title;
        this.volume = volume;
        Price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Название=" + title +
                " | Объем=" + volume +
                " | Цена=" + Price +
                " ₽";
    }
}
