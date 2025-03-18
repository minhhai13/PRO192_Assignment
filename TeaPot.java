package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MinhHai
 */
public class TeaPot extends Item {

    private int volume;
    private List<Integer> authorIds;

    public TeaPot() {
        super();
        authorIds = new ArrayList<>();
    }

    public TeaPot(String id, String name, String category, String material, double inPrice, double price,
            List<Integer> authorIds, int yearOfRelease, List<String> awards, List<String> images, String desc, int volume) {
        super(id, name, category, material, inPrice, price, yearOfRelease, awards, images, desc);
        this.authorIds = authorIds;
        this.volume = volume;
    }

    @Override
    public void input(Scanner sc) {
        super.input(sc);
        // Input list of author/factory IDs with exception handling
        while (true) {
            try {
                System.out.print("Enter number of artisans: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count <= 0) {
                    System.out.println("Number must be positve! Please try again.");
                    continue;
                }
                authorIds.clear();
                for (int i = 0; i < count; i++) {
                    while (true) {
                        try {
                            System.out.print("Enter artisan " + (i + 1) + " ID: ");
                            authorIds.add(Integer.valueOf(sc.nextLine().trim()));
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("ID must be a valid integer! Please try again.");
                        }
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of artisans must be an integer.");
            }
        }
        while (true) {
            try {
                System.out.print("Enter teapod volume (ml): ");
                volume = Integer.parseInt(sc.nextLine().trim());
                if (volume <= 0) {
                    System.out.println("Volume must be greater than 0! Please enter a valid volume.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for volume! Please enter a valid integer.");
            }
        }

    }

    @Override
    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "TeaPot information: "
                + super.toString()
                + "'\n\tauthorIds=" + authorIds
                + "'\n\tvolume=" + volume + ".";
    }

    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
