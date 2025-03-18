package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author MinhHai
 */
public class ItemList extends ArrayList<Item> {

    public ItemList() {
        super();
        this.addAll(RandomItemGenerator.generate(50));
    }

    // Thêm 1 sản phẩm vào danh sách
    public void addItem(Item item) {
        this.add(item);
    }

    // Sắp xếp theo Price giảm dần
    public void sortItemsByPrice() {
        this.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
    }

    // Sắp xếp theo tên tăng dần (A-Z)
    public void sortItemsByName() {
        this.sort((a, b) -> a.getName().compareToIgnoreCase(b.getName()));
    }

    // Tìm kiếm sản phẩm theo tên nghệ nhân/nhà máy (kiểm tra nếu chuỗi tìm kiếm nằm trong bất kỳ phần tử nào của authorIds)
    public List<Item> searchByAuthor(String author) {
        List<Item> results = new ArrayList<>();
        for (Item item : this) {
            if (item instanceof TeaPot) {
                TeaPot teapot = (TeaPot) item;
                for (String auth : teapot.getAuthorIds()) {
                    if (auth.equalsIgnoreCase(author)) {
                        results.add(item);
                        break;
                    }
                }
            }
        }
        return results;
    }

    public List<Item> searchByFactory(String factoryName) {
        List<Item> results = new ArrayList<>();
        for (Item item : this) {
            if (item instanceof Rod) { // Chỉ Rod mới có authorIds là tên nhà máy
                Rod rod = (Rod) item;
                for (String factory : rod.getAuthorIds()) {
                    if (factory.equalsIgnoreCase(factoryName)) {
                        results.add(rod);
                        break;
                    }
                }
            }
        }
        return results;
    }

    // Cập nhật thông tin sản phẩm theo Id
    public void updateItemById(String id, Scanner sc) {
        boolean found = false;
        for (Item item : this) {
            if (item.getId().equalsIgnoreCase(id)) {
                System.out.println("Current product: " + item);
                System.out.println("Enter new information:");
                item.input(sc);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found with ID: " + id);
        }
    }

    // Nạp dữ liệu từ file
    public void loadDataFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            this.clear(); // Xóa danh sách hiện tại
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 13) {
                    System.out.println("Invalid line: " + line);
                    continue;
                }
                String type = parts[0];
                try {
                    if ("TeaPot".equals(type)) {
                        TeaPot tp = new TeaPot();
                        tp.setId(parts[1]);
                        tp.setName(parts[2]);
                        tp.setCategory(parts[3]);
                        tp.setMaterial(parts[4]);
                        tp.setInPrice(Double.parseDouble(parts[5]));
                        tp.setPrice(Double.parseDouble(parts[6]));
                        tp.setYearOfRelease(Integer.parseInt(parts[7]));
                        // Xử lý danh sách awards và images
                        tp.setAwards(Arrays.stream(parts[8].split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
                        tp.setImages(Arrays.stream(parts[9].split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
                        tp.setDesc(parts[10]);
                        tp.setVolume(Integer.parseInt(parts[11]));
                        // Xử lý danh sách authorIds (kiểu Integer)
                        tp.setAuthorIds(Arrays.stream(parts[12].split(";"))
                                    .filter(s -> !s.isEmpty())
                                    .collect(Collectors.toList()));
                        this.add(tp);
                    } else if ("Rod".equals(type)) {
                        Rod rod = new Rod();
                        rod.setId(parts[1]);
                        rod.setName(parts[2]);
                        rod.setCategory(parts[3]);
                        rod.setMaterial(parts[4]);
                        rod.setInPrice(Double.parseDouble(parts[5]));
                        rod.setPrice(Double.parseDouble(parts[6]));
                        rod.setYearOfRelease(Integer.parseInt(parts[7]));
                        // Xử lý danh sách awards và images
                        rod.setAwards(Arrays.stream(parts[8].split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
                        rod.setImages(Arrays.stream(parts[9].split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
                        rod.setDesc(parts[10]);
                        rod.setLength(Double.parseDouble(parts[11]));
                        // Xử lý danh sách authorIds (kiểu String)
                        rod.setAuthorIds(Arrays.stream(parts[12].split(";"))
                                        .filter(s -> !s.isEmpty())
                                        .collect(Collectors.toList()));
                        this.add(rod);
                    } else {
                        System.out.println("Invalid product type: " + type);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Number format error at line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
        }
    }

    // Ghi dữ liệu ra file
    public void saveDataToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : this) {
                if (item instanceof TeaPot) {
                    TeaPot tp = (TeaPot) item;
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
                            tp.getDesc(),
                            String.valueOf(tp.getVolume()),
                            tp.getAuthorIds().stream().map(String::valueOf).collect(Collectors.joining(";"))
                    );
                    writer.write(line);
                    writer.newLine();
                } else if (item instanceof Rod) {
                    Rod rod = (Rod) item;
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
                            rod.getDesc(),
                            String.valueOf(rod.getLength()),
                            String.join(";", rod.getAuthorIds())
                    );
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
//Da them IO
