package view;

import model.*;
import controller.ItemList;
import utils.Utils;
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
            choice = Utils.inputChoice("Choose: ");

            switch (choice) {
                case 1:
                    TeaPot teaPot = new TeaPot();
                    System.out.println("Enter information for the teapot:");
                    teaPot.input();
                    itemList.addItem(teaPot);
                    break;
                case 2:
                    Rod rod = new Rod();
                    System.out.println("Enter information for the fishing rod:");
                    rod.input();
                    itemList.addItem(rod);
                    break;
                case 3:
                    System.out.println("\nList of available products:");
                    Utils.listProducts(itemList);
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
                    itemList.searchByAuthor();
                    break;

                case 7:
                    String id = Utils.inputNonEmptyString("Enter the product ID to update: ");
                    itemList.updateItemById(id, sc);
                    break;
                case 8:
                    String loadFile = Utils.inputNonEmptyString("Enter the filename to load data: ");
                    itemList.loadDataFromFile(loadFile);
                    break;
                case 9:
                    String saveFile = Utils.inputNonEmptyString("Enter the filename to save data: ");
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
