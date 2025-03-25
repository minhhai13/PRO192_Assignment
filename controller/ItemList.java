package controller;

import utils.Utils;
import model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ItemList extends ArrayList<Item> {

    // Static variable to store the current instance
    public static ItemList currentInstance;

    public ItemList() {
        super();
        this.addAll(RandomItemGenerator.generate(50));
        currentInstance = this;
    }

    public static ItemList getCurrentInstance() {
        return currentInstance;
    }

    // Add new item
    public void addItem(Item item) {
        this.add(item);
    }

    // Sort by price (descending)
    public void sortItemsByPrice() {
        this.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
    }

    // Sort by name ascending (A-Z)
    public void sortItemsByName() {
        this.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
    }

    // Search by AuthorIDs or FactoryName
    public void searchByAuthor() {
        System.out.println("\nWhich product type do you want to search for?\n"
                + "1. Tea Pot (Search by Artisan ID)\n"
                + "2. Rod (Search by Factory Name)");
        try {
            int productType = Utils.inputProductType("Input choice (1 or 2): ");
            switch (productType) {
                case 1:
                    String artisanId = Utils.inputNonEmptyString("Enter Artisan ID: ");
                    System.out.println("Search results for Artisan ID " + artisanId + ":");
                    System.out.println(String.format(
                            "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %6s | %-27s | %-8s | %-50s",
                            "ID", "Name", "Category", "Material", "In Price", "Price", "Year", "Author/Factory", "Vol/Len", "Description"
                    ));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Item item : this) {
                        if (item instanceof TeaPot) {
                            TeaPot teapot = (TeaPot) item;
                            for (String auth : teapot.getAuthorIds()) {
                                if (auth.equalsIgnoreCase(artisanId)) {
                                    System.out.println((TeaPot) item);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    String factoryName = Utils.inputNonEmptyString("Enter Factory Name: ");
                    System.out.println("Search results for Factory " + factoryName + ":");
                    System.out.println(String.format(
                            "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %6s | %-27s | %-8s | %-50s",
                            "ID", "Name", "Category", "Material", "In Price", "Price", "Year", "Author/Factory", "Vol/Len", "Description"
                    ));
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    for (Item item : this) {
                        if (item instanceof Rod) {
                            Rod rod = (Rod) item;
                            for (String factory : rod.getAuthorIds()) {
                                if (factory.equalsIgnoreCase(factoryName)) {
                                    System.out.println((Rod) item);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter 1 or 2 to select a product type!");
        }
    }

    // Update item
    public void updateItemById(String id, Scanner sc) {
        boolean found = false;
        for (Item item : this) {
            if (item.getId().equalsIgnoreCase(id)) {
                System.out.println(String.format(
                        "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %6s | %-27s | %-8s | %-50s",
                        "ID", "Name", "Category", "Material", "In Price", "Price", "Year", "Author/Factory", "Vol/Len", "Description"
                ));
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                if (item instanceof TeaPot) {
                    System.out.println(((TeaPot) item));
                } else if (item instanceof Rod) {
                    System.out.println(((Rod) item));
                }

                System.out.println("Enter new information:");
                item.input();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found with ID: " + id);
        }
    }

    /**
     * Saves the current list of items to a file in CSV format. Each line
     * represents an item with 13 fields: type, id, name, category, material,
     * inPrice, price, authorIds, yearOfRelease, awards, images, desc, and a
     * specific field (volume for TeaPot, length for Rod).
     */
    public void saveDataToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Item item : this) {
                // Xác định loại của item
                String type = item instanceof TeaPot ? "TeaPot" : "Rod";

                // Chuyển đổi các danh sách thành chuỗi, phân cách bằng "|"
                String authorIdsStr = String.join("|", item.getAuthorIds());
                String awardsStr = String.join("|", item.getAwards());
                String imagesStr = String.join("|", item.getImages());

                // Lấy trường đặc thù (volume cho TeaPot, length cho Rod)
                double specificFieldValue = item instanceof TeaPot ? ((TeaPot) item).getVolume() : ((Rod) item).getLength();
                String specificField = String.valueOf(specificFieldValue);

                // Ghi dòng dữ liệu với các trường phân cách bằng tab
                writer.println(type + "\t" + item.getId() + "\t" + item.getName() + "\t" + item.getCategory() + "\t" + item.getMaterial() + "\t"
                        + (int) item.getInPrice() + "\t" + (int) item.getPrice() + "\t" + authorIdsStr + "\t" + item.getYearOfRelease() + "\t"
                        + awardsStr + "\t" + imagesStr + "\t" + item.getDesc() + "\t" + specificField);
            }
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    /**
     * Loads items from a file into the current list, replacing existing items.
     * Expects a CSV file with 13 fields per line, matching the format used in
     * saveDataToFile.
     */
    public void loadDataFromFile(String filename) {
        this.clear(); // Xóa danh sách hiện tại trước khi tải
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\t");
                if (fields.length != 13) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }

                // Trích xuất các trường từ dòng
                String type = fields[0];
                String id = fields[1];
                String name = fields[2];
                String category = fields[3];
                String material = fields[4];
                int inPrice = Integer.parseInt(fields[5]);
                int price = Integer.parseInt(fields[6]);
                List<String> authorIds = fields[7].isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(fields[7].split("\\|")));
                int yearOfRelease = Integer.parseInt(fields[8]);
                List<String> awards = fields[9].isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(fields[9].split("\\|")));
                List<String> images = fields[10].isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(fields[10].split("\\|")));
                String desc = fields[11];
                double specificField = Double.parseDouble(fields[12]);

                // Tạo đối tượng dựa trên loại
                if (type.equals("TeaPot")) {
                    TeaPot teaPot = new TeaPot(id, name, category, material, inPrice, price,
                            specificField, authorIds, yearOfRelease, awards, images, desc);
                    this.add(teaPot);
                } else if (type.equals("Rod")) {
                    Rod rod = new Rod(id, name, category, material, inPrice, price,
                            specificField, authorIds, yearOfRelease, awards, images, desc);
                    this.add(rod);
                } else {
                    System.out.println("Unknown type: " + type);
                }
            }
            System.out.println("Data loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }
    }
}
