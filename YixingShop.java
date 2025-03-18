package pro192_assignment_yixingshop;

import model.*;
import data.ItemList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MinhHai
 */
public class YixingShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ItemList itemList = new ItemList(); // khởi tạo danh sách, tự động sinh 50 sản phẩm ngẫu nhiên
        int choice;

        do {
            // Hiển thị menu
            System.out.println("===== YixingShop Menu =====");
            System.out.println("1. Enter a new TeaPot");
            System.out.println("2. Enter a new Rod");
            System.out.println("3. List all products");
            System.out.println("4. Sort products by Price (descending)");
            System.out.println("5. Sort products by Name (A-Z)");
            System.out.println("6. Search for products by Artisan/Factory name");
            System.out.println("7. Update product information by ID");
            System.out.println("8. Load data from file");
            System.out.println("9. Save data to file");
            System.out.println("10. Exit program");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    TeaPot teaPot = new TeaPot();
                    System.out.println("Enter information for the teapot:");
                    teaPot.input(sc);
                    itemList.addItem(teaPot);
                    break;
                case 2:
                    Rod rod = new Rod();
                    System.out.println("Enter information for the fishing rod:");
                    rod.input(sc);
                    itemList.addItem(rod);
                    break;
                case 3:
                    System.out.println("List of available products:");
                    for (Item item : itemList) {
                        System.out.println(item);
                    }
                    break;
                case 4:
                    itemList.sortItemsByPrice();
                    System.out.println("Sorted by Price (descending).");
                    break;
                case 5:
                    itemList.sortItemsByName();
                    System.out.println("Sorted by Name (A-Z).");
                    break;
                case 6:
                    System.out.print("Search for products by artisan/factory name ");
                    System.out.println("\nWhich product type do you want to search for?\n"
                            + "1. Tea Pot (Search by Artisan ID)\n"
                            + "2. Rod (Search by Factory Name)");
                    try {
                        int productType = Integer.parseInt(sc.nextLine().trim());
                        if (productType == 1) {
                            System.out.print("Enter Artisan ID: ");
                            int authorId = Integer.parseInt(sc.nextLine().trim());
                            List<Item> teaResults = itemList.searchByAuthor(authorId);
                            System.out.println("Search results for Artisan ID " + authorId + ":");
                            for (Item item : teaResults) {
                                System.out.println(item);
                            }
                        } else if (productType == 2) {
                            System.out.print("Enter Factory Name: ");
                            String factoryName = sc.nextLine().trim();
                            List<Item> rodResults = itemList.searchByFactory(factoryName);
                            System.out.println("Search results for Factory " + factoryName + ":");
                            for (Item item : rodResults) {
                                System.out.println(item);
                            }
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter 1 or 2 to select a product type!");
                    }
                    break;

                case 7:
                    System.out.print("Enter the product ID to update: ");
                    String id = sc.nextLine();
                    itemList.updateItemById(id, sc);
                    break;
                case 8:
                    System.out.print("Enter the filename to load data: ");
                    String loadFile = sc.nextLine();
                    itemList.loadDataFromFile(loadFile);
                    break;
                case 9:
                    System.out.print("Enter the filename to save data: ");
                    String saveFile = sc.nextLine();
                    itemList.saveDataToFile(saveFile);
                    break;
                case 10:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 10);

        sc.close();
    }
}
