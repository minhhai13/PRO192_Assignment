package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MinhHai
 */
public class Item {

    private String id;
    private String name;
    private String category;
    private String material;
    private double inPrice;
    private double price;
    private int yearOfRelease;
    private List<String> awards;
    private List<String> images;
    private String desc;

    public Item() {
        awards = new ArrayList<>();
        images = new ArrayList<>();
    }

    public Item(String id, String name, String category, String material, double inPrice, double price, int yearOfRelease, List<String> awards, List<String> images, String desc) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.material = material;
        this.inPrice = inPrice;
        this.price = price;
        this.yearOfRelease = yearOfRelease;
        this.awards = awards;
        this.images = images;
        this.desc = desc;
    }

    public void input(Scanner sc) {
        // Input product ID
        System.out.print("Enter product ID: ");
        id = sc.nextLine().trim();
        while (id.isEmpty()) {
            System.out.print("ID cannot be empty! Please enter the product ID: ");
            id = sc.nextLine().trim();
        }

        // Input product name
        System.out.print("Enter product name: ");
        name = sc.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty! Please enter the product name: ");
            name = sc.nextLine().trim();
        }

        // Input product category
        System.out.print("Enter product category: ");
        category = sc.nextLine().trim();
        while (category.isEmpty()) {
            System.out.print("Category cannot be empty! Please enter the product category: ");
            category = sc.nextLine().trim();
        }

        // Input material
        System.out.print("Enter material: ");
        material = sc.nextLine().trim();
        while (material.isEmpty()) {
            System.out.print("Material cannot be empty! Please enter the material: ");
            material = sc.nextLine().trim();
        }

        // Input cost price with exception handling
        while (true) {
            try {
                System.out.print("Enter cost price: ");
                inPrice = Integer.parseInt(sc.nextLine().trim());
                if (inPrice <= 0) {
                    System.out.println("Cost price must be greater than 0! Please enter a valid cost price.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for cost price! Please enter a valid integer.");
            }
        }

        // Input sale price with exception handling
        while (true) {
            try {
                System.out.print("Enter sale price: ");
                price = Double.parseDouble(sc.nextLine().trim());
                if (price <= 0) {
                    System.out.println("Sale price must be greater than 0! Please enter a valid sale price.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for sale price! Please enter a valid number.");
            }
        }

        // Input year of release with exception handling and validity check
        while (true) {
            try {
                System.out.print("Enter year of release: ");
                yearOfRelease = Integer.parseInt(sc.nextLine().trim());
                int currentYear = java.time.Year.now().getValue();
                if (yearOfRelease < 1900 || yearOfRelease > currentYear) {
                    System.out.println("Year of release must be between 1900 and " + currentYear + ". Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for year of release! Please enter a valid integer.");
            }
        }

        // Input list of awards with exception handling
        while (true) {
            try {
                System.out.print("Enter number of awards: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }
                awards.clear();
                for (int i = 0; i < count; i++) {
                    String award;
                    while (true) {
                        System.out.print("Enter award " + (i + 1) + ": ");
                        award = sc.nextLine().trim();
                        if (award.isEmpty()) {
                            System.out.println("Award cannot be empty! Please enter the award.");
                        } else {
                            break;
                        }
                    }
                    awards.add(award);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of awards must be an integer.");
            }
        }

        // Input list of image links with exception handling
        while (true) {
            try {
                System.out.print("Enter number of image links: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }
                images.clear();
                for (int i = 0; i < count; i++) {
                    String imageLink;
                    while (true) {
                        System.out.print("Enter image link " + (i + 1) + ": ");
                        imageLink = sc.nextLine().trim();
                        if (imageLink.isEmpty()) {
                            System.out.println("Image link cannot be empty! Please enter the image link.");
                        } else {
                            break;
                        }
                    }
                    images.add(imageLink);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of image links must be an integer.");
            }
        }

        // Input product description
        System.out.print("Enter product description: ");
        desc = sc.nextLine().trim();
    }

    public void output() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "\n\tid='" + id
                + "'\n\tname='" + name
                + "'\n\tcategory='" + category
                + "'\n\tmaterial='" + material
                + "'\n\tinPrice=" + inPrice
                + "'\n\tprice=" + price
                + "'\n\tyearOfRelease=" + yearOfRelease
                + "'\n\tawards=" + awards
                + "'\n\timages=" + images
                + "'\n\tdesc='" + desc;
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

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
