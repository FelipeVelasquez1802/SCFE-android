package com.diegoasencio.scfe.objects;

public class Result {

    private String article;
    private double count;
    private double price;
    private double total;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return (getCount() * getPrice());
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
