package controller;

import java.util.*;
import java.util.stream.Collectors;
import model.*;

public class RandomItemGenerator {

    private static Random rand = new Random();

    // Region: TeaPot Data
    private static final String[] TEAPOT_NAMES = {
        "Tây Thi", "Thạch Biều", "Hán Ngoã", "Trúc Đoạn", "Cân Văn", "Đề Lương",
        "Cung Đăng", "Liên Tử", "Tứ Phương", "Tần Quyền", "Đức Chung", "Long Tuyền",
        "Hồng Hồ", "Tử Sa Cổ", "Thiên Mệnh", "Ngọc Nữ", "Phượng Hoàng", "Lão Tử",
        "Thiên Cơ", "Vũ Trụ", "Minh Nguyệt", "Hoa Sen", "Thần Long", "Bách Hợp"
    };

    private static final String[] TEAPOT_CATEGORIES = {
        "Ấm Tử Sa", "Ấm Sứ", "Ấm Thủy Tinh", "Ấm Pha Lê", "Ấm đất", "Ấm Bạc",
        "Ấm sắt", "Ấm Gốm"
    };

    private static final String[] TEAPOT_MATERIALS = {
        "Tử Nê", "Chu Nê", "Đoàn Nê", "Đại Hồng Bào", "Hắc Long Nê", "Lục Nê",
        "Thiên Thanh Nê", "Sứ Bát Tràng", "sứ NHẬT", "Sứ Cảnh Đức Trấn", "Pha lê Tiệp Khắc",
        "Pha lê Pháp", "Pha lê Tàu", "Gốm Shigaraki"
    };

    private static final String[] ARTIST_NAMES = {
        "Cố Cảnh Châu", "Vương Ấu Quân", "Từ Hán Đường", "Lý Xương Hồng",
        "Trần Quang Sinh", "Phan Hồng Phi", "Ngô Vân Long", "Trương Thủ Khiết",
        "Lưu Bội Phân", "Chu Nghênh Xuân", "Từ Tú Quân", "Lý Tuyền Minh"
    };

    private static final String[] ARTIST_ROLES = {
        "Tạo hình chính", "Trang trí hoa văn", "Khắc thư pháp", "Khảm vàng/bạc",
        "Nung ấm", "Chế tác nắp", "Điêu khắc rồng phượng", "Vẽ men màu",
        "Chế tác vòi", "Thiết kế tổng thể", "Kiểm định chất lượng"
    };

    private static final String[] TEAPOT_AWARD_TEMPLATES = {
        "Giải Kim Tỉnh - Triển lãm Ấm Tử Sa Quốc tế %d",
        "Huy chương Vàng Đấu giá Christie's %d",
        "Giải Đặc biệt Triển lãm Thủ công Mỹ nghệ châu Á %d",
        "Top 10 Ấm đẹp nhất Hội chợ Trà Đạo Kyoto %d",
        "Giải Sáng tạo Nghệ thuật Đương đại Bắc Kinh %d"
    };
    // End Region: TeaPot Data

    // Region: Rod Data
    private static final String[] ROD_NAMES = {
        "Cá Rô Vàng", "Trắm Đen Xuyên Thấu", "Giấc Mộng Trung Hoa", "Lâm Xung Hải Tặc",
        "Hồng Kông Đại Lực", "Thần Câu Bá Vương", "Ngư Long Đao", "Hải Thần Phong Vân",
        "Xích Bích Chiến Thần", "Độc Giác Tiên", "Thiên Ngoại Phi Tiên", "Long Vương Ngư Lôi"
    };

    private static final String[] ROD_CATEGORIES = {
        "Cần máy nước ngọt", "Cần máy đại dương", "Cần tay truyền thống",
        "Cần baitcasting", "Cần spinning", "Cần fly fishing",
        "Cần surf fishing", "Cần ice fishing"
    };

    private static final String[] ROD_MATERIALS = {
        "Carbon 24T", "Carbon 36T", "Sợi thủy tinh composite",
        "Graphite IM6", "Nanocarbon X-Treme", "Hợp kim nhôm siêu nhẹ",
        "Sợi boron", "Kevlar-carbon mix"
    };

    private static final String[] ROD_FACTORIES = {
        "YuFeng Master", "Daiwa Tournament", "Shimano Stella",
        "Barfilon Gold Series", "MRT Titanium", "Abu Garcia Revo",
        "Penfishing Toplux", "Megabass Orochi"
    };

    private static final String[] ROD_AWARD_TEMPLATES = {
        "Vô địch World Fishing Championship %d",
        "Giải Thiết kế Đột phá ICAST %d",
        "Cần xuất sắc nhất giải CCTV Cup %d",
        "Huy chương Vàng Hội chợ Thể thao Châu Á %d",
        "Top 10 Cần câu tốt nhất theo Tạp chí Field & Stream %d"
    };
    // End Region: Rod Data

    private static List<String> generateArtists(int count) {
        Set<String> artists = new LinkedHashSet<>();
        while (artists.size() < count) {
            String name = ARTIST_NAMES[rand.nextInt(ARTIST_NAMES.length)];
            String role = ARTIST_ROLES[rand.nextInt(ARTIST_ROLES.length)];
            artists.add(name + " (" + role + ")");
        }
        return new ArrayList<>(artists);
    }

    private static List<String> generateAwards(String[] templates, int baseYear, int yearRange, int count) {
        List<String> awards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int year = baseYear + rand.nextInt(yearRange);
            String template = templates[rand.nextInt(templates.length)];
            awards.add(String.format(template, year));
        }
        return awards;
    }

    private static List<String> generateImages(String type, String name, int count) {
        List<String> images = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int randomNumber = rand.nextInt(1000); // Số ngẫu nhiên từ 0-999
            String imageUrl = String.format(
                    "https://yixingshop.com/images/%s/%s_%d.jpg",
                    type.toLowerCase(),
                    name.replace(" ", "_"),
                    randomNumber
            );
            images.add(imageUrl);
        }
        return images;
    }
    // End Region: Common Utilities

    public static List<Item> generate(int n) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add(rand.nextBoolean() ? createPremiumTeaPot(i) : createProRod(i));
        }
        return items;
    }

    private static TeaPot createPremiumTeaPot(int index) {
        TeaPot tp = new TeaPot();
        String name = TEAPOT_NAMES[rand.nextInt(TEAPOT_NAMES.length)];

        tp.setId("TP-" + (1000 + index));
        tp.setName(name);
        tp.setCategory(TEAPOT_CATEGORIES[rand.nextInt(TEAPOT_CATEGORIES.length)]);
        tp.setMaterial(TEAPOT_MATERIALS[rand.nextInt(TEAPOT_MATERIALS.length)]);
        tp.setInPrice(5_000_000 + rand.nextInt(95_000_000)); // 5tr - 100tr
        tp.setPrice(tp.getInPrice() * (2 + rand.nextDouble())); // Giá bán 2-3x giá nhập
        tp.setYearOfRelease(2000 + rand.nextInt(24));
        tp.setAuthorIds(generateArtists(rand.nextInt(5) + 3)); // 3-8 nghệ nhân
        tp.setAwards(generateAwards(TEAPOT_AWARD_TEMPLATES, 2005, 20, rand.nextInt(4)));
        tp.setImages(generateImages("teapot", name, 3 + rand.nextInt(3)));
        tp.setDesc(generateTeapotDescription(tp));
        tp.setVolume(80 + (rand.nextInt(21) * 10)); // 80ml - 280ml

        return tp;
    }

    private static String generateTeapotDescription(TeaPot tp) {
        return String.format("%s - %s làm từ %s. %s Dung tích %.1fml, trọng lượng %dg. %s",
                tp.getName(),
                tp.getCategory(),
                tp.getMaterial(),
                "Tác phẩm nghệ thuật độc bản với",
                tp.getVolume(), // Đã sửa thành %.1f
                150 + rand.nextInt(300),
                "Kèm hộp gỗ mun chạm khắc thủ công, giấy chứng nhận nguyên liệu và nghệ nhân."
        );
    }

    private static Rod createProRod(int index) {
        Rod rod = new Rod();
        String baseName = ROD_NAMES[rand.nextInt(ROD_NAMES.length)];

        rod.setId("RD-" + (2000 + index));
        rod.setName(baseName + " " + ROD_FACTORIES[rand.nextInt(ROD_FACTORIES.length)]);
        rod.setCategory(ROD_CATEGORIES[rand.nextInt(ROD_CATEGORIES.length)]);
        rod.setMaterial(ROD_MATERIALS[rand.nextInt(ROD_MATERIALS.length)]);
        rod.setInPrice(1_000_000 + rand.nextInt(19_000_000)); // 1tr - 20tr
        rod.setPrice(rod.getInPrice() * (1.5 + rand.nextDouble()));
        rod.setYearOfRelease(2015 + rand.nextInt(10));
        rod.setAuthorIds(Collections.singletonList(ROD_FACTORIES[rand.nextInt(ROD_FACTORIES.length)]));
        rod.setAwards(generateAwards(ROD_AWARD_TEMPLATES, 2018, 7, rand.nextInt(3)));
        rod.setImages(generateImages("rod", baseName, 2 + rand.nextInt(2)));
        rod.setDesc(generateRodDescription(rod));
        rod.setLength(2.1 + (0.3 * rand.nextInt(27))); // 2.1m - 10m

        return rod;
    }

    private static String generateRodDescription(Rod rod) {
        return String.format("%s - %s chất liệu %s. %s Chiều dài %.1fm, trọng lượng %dg, tải trọng tối đa %dkg. %s",
                rod.getName(),
                rod.getCategory(),
                rod.getMaterial(),
                rod.getCategory().contains("đại dương") ? "Thiết kế kháng mặn với khoen Fuji SiC" : "Khoen dẫn dây ceramic siêu nhẹ",
                rod.getLength(),
                120 + rand.nextInt(280),
                5 + rand.nextInt(25),
                "Tay cầm EVA cao cấp, kèm túi đựng chống nước và phiếu bảo hành 5 năm."
        );
    }
}
