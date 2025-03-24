package controller;

import utils.Utils;
import model.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ItemList extends ArrayList<Item> {
    
    // Biến static để lưu trữ instance hiện tại
    private static ItemList currentInstance;

    public ItemList() {
        super();
        this.addAll(RandomItemGenerator.generate(50));
        currentInstance = this; // Gán instance hiện tại khi khởi tạo
    }

    // Getter để Utils truy cập
    public static ItemList getCurrentInstance() {
        return currentInstance;
    }

    // Add new item
    public void addItem(Item item) {
        this.add(item);
    }
    
    public boolean checkExists(String id) {
        return this.stream().anyMatch(item -> item.getId().equalsIgnoreCase(id));
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

    // Load data from file
    public void loadDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            this.clear(); // Xóa danh sách hiện tại
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue; // Bỏ qua dòng trống
                }
                String[] parts = line.split(",", -1); // -1 để giữ các field rỗng
                if (parts.length < 13) {
                    System.err.println(String.format("Line %d: Invalid format - insufficient fields: %s",
                            lineNumber, line));
                    continue;
                }

                String type = parts[0].trim();
                try {
                    String id = parts[1].trim();
                    String name = parts[2].trim();
                    String category = parts[3].trim();
                    String material = parts[4].trim();
                    double inPrice = Double.parseDouble(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());
                    int yearOfRelease = Integer.parseInt(parts[7].trim());

                    // Xử lý danh sách phân cách bởi semicolon
                    List<String> awards = parts[8].trim().isEmpty() ? new ArrayList<>()
                            : Arrays.stream(parts[8].split(";"))
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList());
                    List<String> images = parts[9].trim().isEmpty() ? new ArrayList<>()
                            : Arrays.stream(parts[9].split(";"))
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList());
                    String desc = parts[10].trim(); // Không cần xử lý thêm vì save đã thay dấu phẩy
                    double volumeOrLength = Double.parseDouble(parts[11].trim()); // Volume hoặc Length
                    List<String> authorIds = parts[12].trim().isEmpty() ? new ArrayList<>()
                            : Arrays.stream(parts[12].split(";"))
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList());

                    Item item;
                    if ("TeaPot".equals(type)) {
                        item = new TeaPot(id, name, category, material, inPrice, price,
                                volumeOrLength, authorIds, yearOfRelease, awards, images, desc);
                    } else if ("Rod".equals(type)) {
                        item = new Rod(id, name, category, material, inPrice, price,
                                volumeOrLength, authorIds, yearOfRelease, awards, images, desc);
                    } else {
                        System.err.println(String.format("Line %d: Unknown item type: %s",
                                lineNumber, type));
                        continue;
                    }
                    this.add(item);
                } catch (NumberFormatException e) {
                    System.err.println(String.format("Line %d: Number format error: %s - %s",
                            lineNumber, line, e.getMessage()));
                } catch (Exception e) {
                    System.err.println(String.format("Line %d: Error parsing line: %s - %s",
                            lineNumber, line, e.getMessage()));
                }
            }
            System.out.println("Data loaded successfully! Total items: " + this.size());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file " + filename + ": " + e.getMessage());
        }
    }

    // Save data to file
    public void saveDataToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : this) {
                if (item instanceof TeaPot) {
                    TeaPot tp = (TeaPot) item;
                    String desc = tp.getDesc().replace(",", ";"); // Thay dấu phẩy trong desc bằng chấm phẩy
                    String line = String.join(",",
                            "TeaPot",
                            tp.getId(),
                            tp.getName(),
                            tp.getCategory(),
                            tp.getMaterial(),
                            String.valueOf(tp.getInPrice()),
                            String.valueOf(tp.getPrice()),
                            String.valueOf(tp.getYearOfRelease()),
                            String.join(";", tp.getAwards()),
                            String.join(";", tp.getImages()),
                            desc, // Sử dụng desc đã thay thế
                            String.valueOf(tp.getVolume()),
                            tp.getAuthorIds().stream().map(String::valueOf).collect(Collectors.joining(";"))
                    );
                    writer.write(line);
                    writer.newLine();
                } else if (item instanceof Rod) {
                    Rod rod = (Rod) item;
                    String desc = rod.getDesc().replace(",", ";"); // Thay dấu phẩy trong desc bằng chấm phẩy
                    String line = String.join(",",
                            "Rod",
                            rod.getId(),
                            rod.getName(),
                            rod.getCategory(),
                            rod.getMaterial(),
                            String.valueOf(rod.getInPrice()),
                            String.valueOf(rod.getPrice()),
                            String.valueOf(rod.getYearOfRelease()),
                            String.join(";", rod.getAwards()),
                            String.join(";", rod.getImages()),
                            desc, // Sử dụng desc đã thay thế
                            String.valueOf(rod.getLength()),
                            String.join(";", rod.getAuthorIds())
                    );
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }



}
