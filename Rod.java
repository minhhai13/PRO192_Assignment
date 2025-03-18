
import java.util.*;

public class Rod extends Item {

    private double length;

    public Rod() {
    }

    public Rod(String id, String name, String category, String material, double inPrice, double price, double length, List<String> AuthorIds, int yearOfRelease, List<String> awards, List<String> images, String desc) {
        super(id, name, category, material, inPrice, price, AuthorIds, yearOfRelease, awards, images, desc);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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
                + "'\n\tlength=" + length
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
                System.out.print("Enter  inprice: ");
                inPrice = Integer.parseInt(sc.nextLine().trim());
                if (inPrice <= 0) {
                    System.out.println("Inprice must be greater than 0! Please enter a valid inprice.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for inprice! Please enter a valid integer.");
            }
        }

        // Input price 
        while (true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(sc.nextLine().trim());
                if (price <= 0) {
                    System.out.println("Price must be greater than 0! Please enter a valid price.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for price! Please enter a valid number.");
            }
        }
        
        // Input length
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

        // Input list of author IDs
        while (true) {
            try {
                System.out.print("Enter number of factories: ");
                int count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    System.out.println("Number cannot be negative! Please try again.");
                    continue;
                }

                getAuthorIds().clear();
                for (int i = 0; i < count; i++) {
                    String authorId;
                    while (true) {
                        System.out.print("Enter fatory " + (i + 1) + ": ");
                        authorId = sc.nextLine().trim();
                        if (authorId.isEmpty()) {
                            System.out.println("Factory name cannot be empty! Please enter the factory name:");
                        } else {
                            break;
                        }
                    }
                    getAuthorIds().add(authorId);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Number of factories must be an integer.");
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

            // Input description
            System.out.print("Enter product description: ");
            desc = sc.nextLine().trim();
        }
    }

    @Override
    public void output() {
        System.out.println(this);
    }

}
