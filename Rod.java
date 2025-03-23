package model;

import controller.Utils;
import java.util.*;

public class Rod extends Item {

    private double length;

    public Rod() {
    }

    public Rod(String id, String name, String category, String material, double inPrice,
            double price, double length, List<String> AuthorIds, int yearOfRelease,
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

    public String toTableRow() {
        return String.format(
                "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %6d | %-27s | %-8s | %-50s",
                id,
                Utils.limitLength(name, 22),
                Utils.limitLength(category, 17),
                Utils.limitLength(material, 17),
                Utils.formatCurrency(inPrice),
                Utils.formatCurrency(price),
                yearOfRelease,
                Utils.limitLength(String.join(", ", authorIds), 27),
                String.format("%.1fm", length),
                Utils.limitLength(desc, 50)
        );
    }

    @Override
    public void input() {

        name = Utils.inputNonEmptyString("Enter product name: ");
        category = Utils.inputNonEmptyString("Enter product category: ");
        material = Utils.inputNonEmptyString("Enter material: ");
        inPrice = Utils.inputPositiveInteger("Enter cost price: ");
        price = Utils.inputPositiveDouble("Enter sale price: ");
        length = Utils.inputPositiveDouble("Enter length (m): ");
        yearOfRelease = Utils.inputYear("Enter year of release: ");

        // Input author IDs
        authorIds = Utils.inputStringList("author IDs");

        // Input awards
        awards = Utils.inputStringList("awards");

        // Input image links
        images = Utils.inputStringList("image links");

        // Input product description
        desc = Utils.inputNonEmptyString("Enter product description: ");
    }

    @Override
    public void output() {
        System.out.println(this);
    }

}
