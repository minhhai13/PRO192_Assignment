package controller;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class RandomItemGenerator {

    private static Random rand = new Random();

    public static List<Item> generate(int n) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (rand.nextBoolean()) {
                items.add(createRandomTeaPot(i));
            } else {
                items.add(createRandomRod(i));
            }
        }
        return items;
    }

    private static TeaPot createRandomTeaPot(int index) {
        TeaPot tp = new TeaPot();
        tp.setId("TP" + (index + 1));
        tp.setName("TeaPot " + (index + 1));
        tp.setCategory(getRandomTeaCategory());
        tp.setMaterial(getRandomTeaMaterial());
        tp.setInPrice(100 + rand.nextInt(1000));
        tp.setPrice(tp.getInPrice() + rand.nextInt(500));
        tp.setYearOfRelease(1900 + rand.nextInt(125));
        tp.setAuthorIds(generateRandomAuthorIds(1 + rand.nextInt(5)));
        tp.setAwards(generateRandomAwards(rand.nextInt(4)));
        tp.setImages(generateImages(index, 1 + rand.nextInt(3), "image"));
        tp.setDesc("Description of TeaPot " + (index + 1));
        tp.setVolume(100 + rand.nextInt(300));
        return tp;
    }

    private static Rod createRandomRod(int index) {
        Rod rod = new Rod();
        rod.setId("RD" + (index + 1));
        rod.setName("Rod " + (index + 1));
        rod.setCategory(getRandomRodCategory());
        rod.setMaterial(getRandomRodMaterial());
        rod.setInPrice(50 + rand.nextInt(500));
        rod.setPrice(rod.getInPrice() + rand.nextInt(300));
        rod.setYearOfRelease(2000 + rand.nextInt(25));
        rod.setAuthorIds(generateFactoryIds(rand.nextInt(5) + 1));
        rod.setAwards(generateRandomAwards(rand.nextInt(3) + 1));
        rod.setImages(generateImages(index, rand.nextInt(3) + 1, "rod_image"));
        rod.setDesc("Description of Rod " + (index + 1));
        rod.setLength(2.0 + rand.nextDouble() * 8.0);
        return rod;
    }

    // Các phương thức trợ giúp
    private static String getRandomTeaCategory() {
        String[] categories = {"Ấm sứ", "Ấm đất", "Ấm thủy tinh", "Ấm pha lê", "Ấm sắt", "Ấm bạc"};
        return categories[rand.nextInt(categories.length)];
    }

    private static String getRandomTeaMaterial() {
        String[] materials = {"Đất tử sa", "Sứ Bát Tràng", "Sứ Nhật", "Sứ Cảnh Đức", "Đất nâu", "Đất đỏ"};
        return materials[rand.nextInt(materials.length)];
    }

    private static String getRandomRodCategory() {
        return rand.nextBoolean() ? "Cần câu máy" : "Cần câu tay";
    }

    private static String getRandomRodMaterial() {
        String[] materials = {"Carbon", "Sợi thuỷ tinh", "Kim loại"};
        return materials[rand.nextInt(materials.length)];
    }

    private static List<Integer> generateRandomAuthorIds(int count) {
        return rand.ints(count, 1, 11).boxed().collect(Collectors.toList());
    }

    private static List<String> generateFactoryIds(int count) {
        return rand.ints(count, 1, 11)
                .mapToObj(i -> "Factory" + i)
                .collect(Collectors.toList());
    }

    private static List<String> generateRandomAwards(int count) {
        return rand.ints(count, 1, 6)
                .mapToObj(i -> "Award" + i)
                .collect(Collectors.toList());
    }

    private static List<String> generateImages(int index, int count, String prefix) {
        return rand.ints(count, 1, 4)
                .mapToObj(j -> String.format("http://example.com/%s%d_%d.jpg", prefix, index + 1, j))
                .collect(Collectors.toList());
    }
}
