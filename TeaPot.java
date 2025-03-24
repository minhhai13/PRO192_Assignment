package model;

import utils.Utils;
import java.util.*;

public class TeaPot extends Item {

    private double volume;

    public TeaPot() {
    }

    public TeaPot(String id, String name, String category, String material, double inPrice,
            double price, double volume, List<String> authorIds, int yearOfRelease,
            List<String> awards, List<String> images, String desc) {

        super(id, name, category, material, inPrice, price, authorIds, yearOfRelease, awards, images, desc);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %-8s | %-27s | %6s | %-27s | %-27s | %-50s",
                id,
                Utils.limitLength(name, 22),
                Utils.limitLength(category, 17),
                Utils.limitLength(material, 17),
                Utils.formatCurrency(inPrice),
                Utils.formatCurrency(price),
                String.format("%.1fm", volume),
                Utils.limitLength(String.join(", ", authorIds), 27),
                yearOfRelease,              
                Utils.limitLength(String.join(", ", awards), 27),
                Utils.limitLength(String.join(", ", images), 27),
                Utils.limitLength(desc, 50)
        );
    }

    @Override
    public void input() {

        id = Utils.inputUniqueId("Enter product ID: ");
        name = Utils.inputNonEmptyString("Enter product name: ");
        category = Utils.inputNonEmptyString("Enter product category: ");
        material = Utils.inputNonEmptyString("Enter material: ");
        inPrice = Utils.inputPositiveInteger("Enter cost price: ");
        price = Utils.inputPositiveDouble("Enter sale price: ");
        volume = Utils.inputPositiveDouble("Enter volume (in militers): ");
        yearOfRelease = Utils.inputYear("Enter year of release: ");
        authorIds = Utils.inputStringList("author IDs");
        awards = Utils.inputStringList("awards");
        images = Utils.inputStringList("image links");
        desc = Utils.inputNonEmptyString("Enter product description: ");
    }

    @Override
    public void output() {
        System.out.println(this);
    }

}
