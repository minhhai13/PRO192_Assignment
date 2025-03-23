package controller;

import java.util.*;
import java.util.stream.Collectors;
import model.*;

public class RandomItemGenerator {

    private static Random rand = new Random();

    // Danh sách tên ấm trà phổ biến
    private static final String[] TEAPOT_NAMES = {
        "Tây Thi", "Thạch Biều", "Hán Ngoã", "Trúc Đoạn", "Cân Văn",
        "Đề Lương", "Cung Đăng", "Liên Tử", "Tứ Phương", "Tần Quyền", "Đức Chung"
    };

    // Vật liệu làm ấm trà
    private static final String[] TEAPOT_MATERIALS = {
        "Đất tử sa Tử Nê", "Đất tử sa Chu Nê", "Đất tử sa Đoàn Nê",
        "Đất tử sa Đại Hồng Bào", "Đất tử sa Hắc Long Nê", "Sứ Bát Tràng",
        "Sứ Nhật Bản", "Sứ Cảnh Đức Trấn", "Pha lê Tiệp Khắc", "Pha lê Pháp"
    };

    // Danh sách nghệ nhân và vai trò
    private static final String[] ARTIST_NAMES = {
        "Nguyễn Văn A", "Trần Thị B", "Lê Minh C", "Phạm Hồng D", "Hoàng Đức E"
    };
    private static final String[] ARTIST_ROLES = {
        "Tạo hình", "Trang trí", "Khắc hoạ", "Khảm vàng", "Nung ấm"
    };

    // Giải thưởng cho ấm trà
    private static final String[] TEAPOT_AWARD_TEMPLATES = {
        "Giải Nhất cấp tỉnh Nghi Hưng năm %d",
        "Giải Nhì cuộc thi ấm trà đẹp quốc gia Trung Hoa năm %d",
        "Giải Vàng Triển lãm Thủ công Mỹ nghệ châu Á %d"
    };

    // Mô tả ấm trà theo tên
    private static final Map<String, String> TEAPOT_DESCRIPTIONS = new HashMap<>();

    static {
        TEAPOT_DESCRIPTIONS.put("Tây Thi", "Ấm Tử Sa Tây Thi dáng cong mềm mại như dáng đứng của Tây Thi, chất đất tử nê bóng mịn, dung tích 150ml. Lý tưởng pha trà ô long, giữ nhiệt tốt.");
        TEAPOT_DESCRIPTIONS.put("Thạch Biều", "Ấm Thạch Biều dáng gáo đá mộc mạc, chất đất hồng nê, dung tích 200ml. Vòi ngắn, nước chảy mạnh, phù hợp trà Phổ Nhĩ.");
        // Thêm mô tả cho các ấm khác...
    }

    // Danh sách tên cần câu
    private static final String[] ROD_NAMES = {
        "Cá Rô", "Cá Chép", "Trắm Đen", "Giấc Mộng Trung Hoa", "Lâm Xung", "Hồng Kông Đại Lực"
    };

    // Nhà máy sản xuất
    private static final String[] ROD_FACTORIES = {
        "YuFeng", "Daiwa", "Shimano", "Barfilon", "MRT", "Abu Garcia"
    };

    // Chiều dài cần câu (m)
    private static final double[] ROD_LENGTHS = {2.1, 2.4, 3.6, 4.5, 5.4, 6.3, 7.2, 8.1, 9.0, 10.0};

    // Giải thưởng cần câu
    private static final String[] ROD_AWARD_TEMPLATES = {
        "Vô địch HHCCTTVN %d",
        "Cần xuất sắc giải CCTV %d",
        "Thiết kế đột phá Hoa Gia Thành %d"
    };

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
        tp.setName(TEAPOT_NAMES[rand.nextInt(TEAPOT_NAMES.length)]);
        tp.setCategory("Ấm Tử Sa"); // Có thể mở rộng thêm loại
        tp.setMaterial(TEAPOT_MATERIALS[rand.nextInt(TEAPOT_MATERIALS.length)]);
        tp.setInPrice(500000 + rand.nextInt(9500000)); // Giá nhập từ 500K đến 10 triệu
        tp.setPrice(tp.getInPrice() * 1.5); // Giá bán = 1.5 lần giá nhập
        tp.setYearOfRelease(2010 + rand.nextInt(15));
        tp.setAuthorIds(generateArtistsWithRoles(rand.nextInt(3) + 1)); // 1-3 nghệ nhân
        tp.setAwards(generateTeapotAwards(rand.nextInt(3)));
        tp.setImages(generateImages(tp.getName().replace(" ", "_"), 2));
        tp.setDesc(TEAPOT_DESCRIPTIONS.getOrDefault(tp.getName(), "Ấm thủ công cao cấp, chất liệu độc đáo."));
        tp.setVolume(100 + (rand.nextInt(11) * 50)); // 100ml, 150ml, ..., 600ml
        return tp;
    }

    private static Rod createRandomRod(int index) {
        Rod rod = new Rod();
        rod.setId("RD" + (index + 1));
        rod.setName("Cần " + ROD_NAMES[rand.nextInt(ROD_NAMES.length)]);
        rod.setCategory(rand.nextBoolean() ? "Cần máy nước ngọt" : "Cần máy đại dương");
        rod.setMaterial(rand.nextBoolean() ? "Carbon" : "Sợi thủy tinh");
        rod.setInPrice(300000 + rand.nextInt(2700000)); // 300K đến 3 triệu
        rod.setPrice(rod.getInPrice() * 1.8);
        rod.setYearOfRelease(2020 + rand.nextInt(5));
        rod.setAuthorIds(Collections.singletonList(ROD_FACTORIES[rand.nextInt(ROD_FACTORIES.length)]));
        rod.setAwards(generateRodAwards(rand.nextInt(2)));
        rod.setImages(generateImages(rod.getName().replace(" ", "_"), 2));
        rod.setDesc(generateRodDescription(rod));
        rod.setLength(ROD_LENGTHS[rand.nextInt(ROD_LENGTHS.length)]);
        return rod;
    }

    // Helper methods
    private static List<String> generateArtistsWithRoles(int count) {
        List<String> artists = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = ARTIST_NAMES[rand.nextInt(ARTIST_NAMES.length)];
            String role = ARTIST_ROLES[rand.nextInt(ARTIST_ROLES.length)];
            artists.add(name + " - " + role);
        }
        return artists;
    }

    private static List<String> generateTeapotAwards(int count) {
        List<String> awards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int year = 2015 + rand.nextInt(10);
            String award = String.format(
                    TEAPOT_AWARD_TEMPLATES[rand.nextInt(TEAPOT_AWARD_TEMPLATES.length)],
                    year
            );
            awards.add(award);
        }
        return awards;
    }

    private static List<String> generateRodAwards(int count) {
        List<String> awards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int year = 2020 + rand.nextInt(5);
            String award = String.format(
                    ROD_AWARD_TEMPLATES[rand.nextInt(ROD_AWARD_TEMPLATES.length)],
                    year
            );
            awards.add(award);
        }
        return awards;
    }

    private static List<String> generateImages(String prefix, int count) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            images.add(String.format(
                    "https://yixingshop.com/images/%s_%d.jpg",
                    prefix.toLowerCase(),
                    i + 1
            ));
        }
        return images;
    }

    private static String generateRodDescription(Rod rod) {
        return String.format(
                "%s sản xuất bởi %s, dài %.1fm. Chất liệu %s bền bỉ, chịu lực tốt. %s",
                rod.getName(),
                rod.getAuthorIds().get(0),
                rod.getLength(),
                rod.getMaterial(),
                rod.getCategory().contains("đại dương") ? "Thiết kế chống mặn, phù hợp câu biển." : "Thích hợp câu nước ngọt."
        );
    }
}
