package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MinhHai
 */
public class Rod extends Item {

    private double length;
    private List<String> authorIds;

    public Rod() {
        super();
        authorIds = new ArrayList<>();

    }

    public Rod(String id, String name, String category, String material, double inPrice, double price,
            List<String> authorIds, int yearOfRelease, List<String> awards, List<String> images, String desc, double length) {
        super(id, name, category, material, inPrice, price, yearOfRelease, awards, images, desc);
        this.authorIds = authorIds;
        this.length = length;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        // Input list of author/factory IDs with exception handling
        while (true) {
            try {
                System.out.print("Enter number of factories: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count <= 0) {
                    System.out.println("Number must be positve! Please try again.");
                    continue;
                }
                authorIds.clear();
                for (int i = 0; i < count; i++) {
                    while (true) {
                        System.out.print("Enter factory " + (i + 1) + " name : ");
                        String fName = sc.nextLine();
                        while (fName.isEmpty()) {
                            System.out.print("Factory name cannot be empty! Please enter the factory name: ");
                            fName = sc.nextLine().trim();
                        }
                        authorIds.add(fName);
                        break;
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of factories must be an integer.");
            }
        }
        while (true) {
            try {
                System.out.print("Enter rod length (m): ");
                length = Double.parseDouble(sc.nextLine().trim());
                if (length <= 0) {
                    System.out.println("Length must be greater than 0! Please enter a valid length.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for length! Please enter a valid number.");
            }
        }

    }

    @Override
    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Rod information: "
                + super.toString()
                + "'\n\tauthorIds=" + authorIds
                + "'\n\tlength=" + length + ".";
    }

    public List<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<String> authorIds) {
        this.authorIds = authorIds;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
