package model;

import utils.Utils;
import java.util.*;

public class Rod extends Item {

    // Unique attributes
    private double length;

    public Rod() {
    }

    public Rod(String id, String name, String category, String material, int inPrice,
            int price, double length, List<String> AuthorIds, int yearOfRelease,
            List<String> awards, List<String> images, String desc) {

        super(id, name, category, material, inPrice, price, AuthorIds, yearOfRelease, awards, images, desc);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // Returns a formatted string representation of the Rod object
    @Override
    public String toString() {
        return String.format(
                "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %-8s | %-27s | %6s | %-27s | %-27s | %-50s",
                id,
                Utils.limitLength(name, 22),
                Utils.limitLength(category, 17),
                Utils.limitLength(material, 17),
                String.format("%,15d", inPrice),
                String.format("%,15d", price),
                String.format("%.1fm", length),
                Utils.limitLength(String.join(", ", authorIds), 27),
                yearOfRelease,
                Utils.limitLength(String.join(", ", awards), 27),
                Utils.limitLength(String.join(", ", images), 27),
                Utils.limitLength(desc, 50)
        );
    }

    // Input method to receive values for the Rod properties
    @Override
    public void input() {
        id = Utils.inputUniqueId("Enter product id: ").toUpperCase();
        name = Utils.inputNonEmptyString("Enter product name: ");
        category = Utils.inputNonEmptyString("Enter product category: ");
        material = Utils.inputNonEmptyString("Enter material: ");
        inPrice = Utils.inputPositiveInteger("Enter cost price: ");
        price = Utils.inputPositiveInteger("Enter sale price: ");
        length = Utils.inputPositiveDouble("Enter length (m): ");
        yearOfRelease = Utils.inputYear("Enter year of release: ");
        authorIds = Utils.inputStringList("author IDs");
        awards = Utils.inputStringList("awards");
        images = Utils.inputStringList("image links");
        desc = Utils.inputNonEmptyString("Enter product description: ");
    }

    // Output method to display the Rod details
    @Override
    public void output() {
        System.out.println(this);
    }

}
