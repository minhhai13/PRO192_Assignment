package data;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author MinhHai
 */
public class ItemList extends ArrayList<Item> {

    public ItemList() {
        super();
        generateRandomItems(50);
    }

    // Phương thức sinh ngẫu nhiên các sản phẩm
    private void generateRandomItems(int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            if (rand.nextBoolean()) {
                // Tạo TeaPot ngẫu nhiên
                TeaPot tp = new TeaPot();
                tp.setId("TP" + (i + 1));
                tp.setName("TeaPot " + (i + 1));
                tp.setCategory(getRandomTeaCategory(rand));
                tp.setMaterial(getRandomTeaMaterial(rand));
                tp.setInPrice(100 + rand.nextInt(1000));
                tp.setPrice(tp.getInPrice() + rand.nextInt(500));
                tp.setYearOfRelease(1900 + rand.nextInt(125));
                List<Integer> authors = new ArrayList<>();
                int authorCount = 1 + rand.nextInt(5);
                for (int j = 0; j < authorCount; j++) {
                    authors.add(rand.nextInt(10) + 1);
                }
                tp.setAuthorIds(authors);

                int awardCount = rand.nextInt(4);
                List<String> awardsList = new ArrayList<>();
                for (int j = 0; j < awardCount; j++) {
                    awardsList.add("Award" + (rand.nextInt(5) + 1));
                }
                tp.setAwards(awardsList);

                int imageCount = 1 + rand.nextInt(3);
                List<String> imagesList = new ArrayList<>();
                for (int j = 0; j < imageCount; j++) {
                    imagesList.add("http://example.com/image" + (i + 1) + "_" + (j + 1) + ".jpg");
                }
                tp.setImages(imagesList);

                tp.setDesc("Description of TeaPot " + (i + 1));
                tp.setVolume(100 + rand.nextInt(300));
                this.add(tp);
            } else {
                // Tạo Rod ngẫu nhiên
                Rod rod = new Rod();
                rod.setId("RD" + (i + 1));
                rod.setName("Rod " + (i + 1));
                rod.setCategory(getRandomRodCategory(rand));
                rod.setMaterial(getRandomRodMaterial(rand));
                rod.setInPrice(50 + rand.nextInt(500));
                rod.setPrice(rod.getInPrice() + rand.nextInt(300));
                rod.setYearOfRelease(2000 + rand.nextInt(25));

                int authorCount = rand.nextInt(5) + 1;
                List<String> authors = new ArrayList<>();
                for (int j = 0; j < authorCount; j++) {
                    authors.add("Factory" + (rand.nextInt(10) + 1));
                }
                rod.setAuthorIds(authors);

                int awardCount = rand.nextInt(3) + 1;
                List<String> awards = new ArrayList<>();
                for (int j = 0; j < awardCount; j++) {
                    awards.add("Award" + (rand.nextInt(5) + 1));
                }
                rod.setAwards(awards);

                int imageCount = rand.nextInt(3) + 1;
                List<String> images = new ArrayList<>();
                for (int j = 0; j < imageCount; j++) {
                    images.add("http://example.com/rod_image" + (i + 1) + "_" + (j + 1) + ".jpg");
                }
                rod.setImages(images);

                rod.setDesc("Description of Rod " + (i + 1));
                rod.setLength(2.0 + rand.nextDouble() * 8.0);
                this.add(rod);
            }
        }
    }

    // Một số hàm trợ giúp để chọn ngẫu nhiên dữ liệu cho TeaPot và Rod
    private String getRandomTeaCategory(Random rand) {
        String[] categories = {"Ấm sứ", "Ấm đất", "Ấm thủy tinh", "Ấm pha lê", "Ấm sắt", "Ấm bạc"};
        return categories[rand.nextInt(categories.length)];
    }

    private String getRandomTeaMaterial(Random rand) {
        String[] materials = {"Đất tử sa", "Sứ Bát Tràng", "Sứ Nhật", "Sứ Cảnh Đức", "Đất nâu", "Đất đỏ"};
        return materials[rand.nextInt(materials.length)];
    }

    private String getRandomRodCategory(Random rand) {
        String[] categories = {"Cần câu máy", "Cần câu tay"};
        return categories[rand.nextInt(categories.length)];
    }

    private String getRandomRodMaterial(Random rand) {
        String[] materials = {"Carbon", "Sợi thuỷ tinh", "Kim loại"};
        return materials[rand.nextInt(materials.length)];
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
    public List<Item> searchByAuthor(int author) {
        List<Item> results = new ArrayList<>();
        for (Item item : this) {
            if (item instanceof TeaPot) {
                TeaPot teapot = (TeaPot) item;
                for (int auth : teapot.getAuthorIds()) {
                    if (auth == author) {
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
                        List<Integer> authorIds = Arrays.stream(parts[12].split(";"))
                                .filter(s -> !s.isEmpty())
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        tp.setAuthorIds(authorIds);
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
                        rod.setAuthorIds(Arrays.stream(parts[12].split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
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
