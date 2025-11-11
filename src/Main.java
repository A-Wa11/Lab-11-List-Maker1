import java.util.ArrayList;
import java.util.Scanner;

class ListMaker_NoSafeInput {

    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        boolean done = false;

        do {
            displayList();

            String choice = getMenuChoice("Choose [A]dd, [D]elete, [I]nsert, [P]rint, [Q]uit: ");

            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (getYesNo("Are you sure you want to quit (Y/N)? ")) {
                        done = true;
                    }
                    break;
            }

        } while (!done);

        System.out.println("Goodbye!");
    }


    private static void addItem() {
        System.out.print("Enter an item to add: ");
        String item = in.nextLine();
        list.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (list.size() == 0) {
            System.out.println("List is empty.");
            return;
        }

        printNumberedList();
        int index = getIntInRange("Enter the number of the item to delete: ", 1, list.size());
        list.remove(index - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        System.out.print("Enter an item to insert: ");
        String item = in.nextLine();

        int index = getIntInRange(
                "Enter position to insert (1 to " + (list.size() + 1) + "): ",
                1,
                list.size() + 1
        );

        list.add(index - 1, item);
        System.out.println("Item inserted.");
    }

    private static void printList() {
        if (list.size() == 0) {
            System.out.println("List is empty.\n");
            return;
        }

        System.out.println("\n----- CURRENT LIST -----");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("------------------------\n");
    }

    private static String getMenuChoice(String prompt) {
        String choice;
        while (true) {
            System.out.print(prompt);
            choice = in.nextLine().trim();

            if (choice.matches("[AaDdIiPpQq]")) {
                return choice;
            }

            System.out.println("Invalid input. Enter A, D, I, P, or Q.");
        }
    }

    private static boolean getYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = in.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) return true;
            if (response.equalsIgnoreCase("N")) return false;

            System.out.println("Please enter Y or N.");
        }
    }

    private static int getIntInRange(String prompt, int min, int max) {
        int num;
        while (true) {
            System.out.print(prompt);

            if (in.hasNextInt()) {
                num = in.nextInt();
                in.nextLine(); // clear rest of line

                if (num >= min && num <= max) {
                    return num;
                }
            } else {
                in.nextLine(); // discard invalid input
            }

            System.out.println("Please enter a number from " + min + " to " + max + ".");
        }
    }

    private static void printNumberedList() {
        System.out.println("\n----- NUMBERED LIST -----");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%3d) %s\n", i + 1, list.get(i));
        }
        System.out.println("-------------------------\n");
    }

    private static void displayList() {
        System.out.println("\n=========== LIST MAKER ===========");
        if (list.size() == 0) {
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d) %s\n", i + 1, list.get(i));
            }
        }
        System.out.println("==================================");
    }
}