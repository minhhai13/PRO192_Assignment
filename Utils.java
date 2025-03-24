package controller;

/**
 *
 * @author MinhHai
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Item;
import model.Rod;
import model.TeaPot;

public class Utils {

    private static final Scanner sc = new Scanner(System.in);

    // Input a non-empty string
    public static String inputNonEmptyString(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty! Please try again.");
        }
    }

    // Input a positive integer
    public static int inputPositiveInteger(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine().trim());
                if (number > 0) {
                    return number;
                }
                System.out.println("Value must be greater than 0! Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    // Input a non-negative integer
    public static int inputNonNegativeInteger(String message) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(sc.nextLine().trim());
                if (number >= 0) {
                    return number;
                }
                System.out.println("Value cannot be negative! Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    // Input a positive double
    public static double inputPositiveDouble(String message) {
        double number;
        while (true) {
            try {
                System.out.print(message);
                number = Double.parseDouble(sc.nextLine().trim());
                if (number > 0) {
                    return number;
                }
                System.out.println("Value must be greater than 0! Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a decimal number.");
            }
        }
    }

    // Input a valid year
    public static int inputYear(String message) {
        int year;
        while (true) {
            try {
                System.out.print(message);
                year = Integer.parseInt(sc.nextLine().trim());
                if (year >= 1900) {
                    return year;
                }
                System.out.println("Year must greater than 1900. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    // Input choice 1 or 2 in Search
    public static int inputProductType(String message) {
        int productType;
        while (true) {
            try {
                System.out.print(message);
                productType = Integer.parseInt(sc.nextLine().trim());
                if (productType == 1 || productType == 2) {
                    return productType;
                }
                System.out.println("Value must be 1 or 2. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }

    // Input choice
    public static int inputChoice(String message) {
        int choice;
        while (true) {
            try {
                System.out.print(message);
                choice = Integer.parseInt(sc.nextLine().trim());
                if (choice >= 1 && choice <= 10) {
                    return choice;
                }
                System.out.println("Input must be in (1-10). Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an number in (1-10).");
            }
        }
    }

    // Input a list of strings
    public static List<String> inputStringList(String message) {
        int count = inputNonNegativeInteger("Enter the number of " + message + ": ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String item = inputNonEmptyString("Enter " + message + " " + (i + 1) + ": ");
            list.add(item);
        }
        return list;
    }

    public static void listProducts(ArrayList<Item> itemList) {
        System.out.println(String.format(
                "%-8s | %-22s | %-17s | %-17s | %15s | %15s | %-8s | %-27s | %6s | %-27s | %-27s | %-50s",
                "ID", "Name", "Category", "Material", "In Price (VND)", "Price (VND)", "Vol/Len", "Author/Factory", "Year", "Awards", "Images", "Description"
        ));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Item item : itemList) {
            if (item instanceof TeaPot) {
                System.out.println(((TeaPot) item));
            } else if (item instanceof Rod) {
                System.out.println(((Rod) item));
            }
        }
        System.out.println();
    }

    public static String limitLength(String str, int maxLength) {
        if (str == null) {
            return "";
        }
        if (str.length() <= maxLength) {
            return String.format("%-" + maxLength + "s", str); // Thêm khoảng trắng nếu cần
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    public static String formatCurrency(double amount) {
        return String.format("%,15.2f", amount); // Tăng từ 12 lên 15 ký tự
    }
}
