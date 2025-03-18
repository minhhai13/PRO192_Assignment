
import java.util.*;

public class TeaPot extends Item {

    private double volume;

    public TeaPot() {
    }

    public TeaPot(String id, String name, String category, String material, double inPrice, double price, double volume, List<String> authorIds, int yearOfRelease, List<String> awards, List<String> images, String desc) {
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
        return this.getClass().getName()
                + "\n\tid='" + id
                + "'\n\tname='" + name
                + "'\n\tcategory='" + category
                + "'\n\tmaterial='" + material
                + "'\n\tinPrice=" + inPrice
                + "'\n\tprice=" + price
                + "'\n\tvolume=" + volume
                + "'\n\tauthorIds=" + authorIds
                + "'\n\tyearOfRelease=" + yearOfRelease
                + "'\n\tawards=" + awards
                + "'\n\timages=" + images
                + "'\n\tdesc='" + desc;
    }

    @Override
    public void input() {

        Scanner sc = new Scanner(System.in);

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

        // Input inprice
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

        // Input price
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

        // Input volume
        while (true) {
            try {
                System.out.print("Enter volume (in liters): ");
                volume = Double.parseDouble(sc.nextLine().trim());
                if (volume <= 0) {
                    System.out.println("Volume must be greater than 0! Please enter a valid volume.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for volume! Please enter a valid number.");
            }
        }

        // Input list of author IDs
        while (true) {
            try {
                System.out.print("Enter number of authors: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }
                if (count > 8) {
                    System.out.println("Maximum 8 authors allowed! Please enter a smaller number.");
                    continue;
                }
                getAuthorIds().clear();
                for (int i = 0; i < count; i++) {
                    String authorId;
                    while (true) {
                        System.out.print("Enter Author ID " + (i + 1) + ": ");
                        authorId = sc.nextLine().trim();
                        if (authorId.isEmpty()) {
                            System.out.println("Author ID cannot be empty! Please enter the Author ID.");
                        } else {
                            break;
                        }
                    }
                    getAuthorIds().add(authorId);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of authors must be an integer.");
            }
        }
        // Input year of release
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

        // Input list of awards
        while (true) {
            try {
                System.out.print("Enter number of awards: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }

                getAwards().clear();
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
                    getAwards().add(award);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of awards must be an integer.");
            }
        }

        // Input list of image links
        while (true) {
            try {
                System.out.print("Enter number of image links: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }

                getImages().clear();
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
                    getImages().add(imageLink);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of image links must be an integer.");
            }

            // Input product description
            System.out.print("Enter product description: ");
            desc = sc.nextLine().trim();
        }
    }

    @Override
    public void output() {
        System.out.println(this);
    }

}
