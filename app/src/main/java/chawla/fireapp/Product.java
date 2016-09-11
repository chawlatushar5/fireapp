package chawla.fireapp;

/**
 * Created by Tushar on 9/10/16.
 */
public class Product {
    private String id;
    private String name;
    private String price;
    private String description;

    //Constructor

    public Product(String description, String id, String name, String price) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //Setter, Getter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

