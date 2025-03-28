package model;

import java.util.*;

public abstract class Item {

    // Common attributes
    protected String id;
    protected String name;
    protected String category;
    protected String material;
    protected int inPrice;
    protected int price;
    protected List<String> authorIds;
    protected int yearOfRelease;
    protected List<String> awards;
    protected List<String> images;
    protected String desc;

    // Initialize item
    public Item() {
        authorIds = new ArrayList<>();
        awards = new ArrayList<>();
        images = new ArrayList<>();
    }

    public Item(String id, String name, String category, String material, int inPrice, int price, List<String> authorIds, int yearOfRelease, List<String> awards, List<String> images, String desc) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.material = material;
        this.inPrice = inPrice;
        this.price = price;
        this.authorIds = authorIds;
        this.yearOfRelease = yearOfRelease;
        this.awards = awards;
        this.images = images;
        this.desc = desc;
    }

    // Abstract methods for input and output
    public abstract void input();

    public abstract void output();

    // Getter & Setter
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(int inPrice) {
        this.inPrice = inPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<String> authorIds) {
        this.authorIds = authorIds;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
