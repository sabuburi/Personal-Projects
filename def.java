import java.util.Scanner;
import java.util.Arrays;

public class def {
    
   public static boolean borrow = false; 
   static void smp_menu(){
        System.out.println("===============================================================");
        System.out.println("  Welcome to the MakerSpace SMP (Storage Management Program)! ");
        System.out.println("===============================================================");

    }
   static int[][] ori = {
      {0, 4, 6, 30},
      {2, 6, 2, 2},
      {10, 2, 21, 12},
      {23, 0, 0, 12}
    };
    
    public static void returnEquipment(String na) {
        Scanner sa = new Scanner(System.in);
        int index = Arrays.asList(ia.NA).indexOf(na);
        String equipmentType = ia.EN[index];
        System.out.println("============================================");
        System.out.println("Name: " + na);
        System.out.println("Equipment borrowed, Quantity: " + equipmentType + "," + ia.EQ[index]);
        System.out.println("Date needed to return: " + ia.DA[index]);
        System.out.println("============================================");
        System.out.println("Is this the person that wants to return the equipment?(y/n)");
        String decision = sa.nextLine().toLowerCase();
        if (decision.equals("y")) {
            int borrowedQuantity = Integer.parseInt(ia.EQ[index]);

            // Add the borrowed quantity back to the corresponding equipment type in the matrix
            for (int i = 0; i < ia.spec.length; i++) {
                for (int j = 0; j < ia.spec[i].length; j++) {
                    if (ia.spec[i][j] == equipmentType) {
                        ia.matrix[i][j] =+ borrowedQuantity;
                        break;
                    }
                }
            }

            // Update the equipment availability array
            ia.EN[index] = "";
            ia.EQ[index] = "";
            ia.DA[index] = "";

            System.out.println("Equipment successfully returned.");
            } else {
            System.out.println("Please enter a valid decision (y/n).");
            }
    }

    

   
   public static int[][] resetArray(int[][] modifiedArray) {
    int[][] newArray = new int[def.ori.length][def.ori[0].length];
    for (int i = 0; i < def.ori.length; i++) {
        for (int j = 0; j < def.ori[i].length; j++) {
            newArray[i][j] = def.ori[i][j];
        }
    }
    return newArray;
}
    
    public static boolean isValidDateFormat(String dateString) {
      //making sure the date format is correct
      String regex = "^\\d{4}[\\/\\-]\\d{2}[\\/\\-]\\d{2}$";
      return dateString.matches(regex);
    }
    
    public static void borrowEquipment() {
    ia.displayEquipment();
    Scanner scan = new Scanner(System.in);

    System.out.println("Type down the row that the object is in (0-3):");
    int i = scan.nextInt();

    System.out.println("Type down the column that the object is in (0-3):");
    int j = scan.nextInt();

    // Check if the quantity is available
    if (ia.matrix[i][j] != 0) {
        // Ask for the quantity to borrow
        System.out.println("How many would you like to borrow?");
        int quantityToBorrow = scan.nextInt();

        // Check if the requested quantity is available
        if (quantityToBorrow <= ia.matrix[i][j]) {
            // Mark the equipment as borrowed
            ia.matrix[i][j] -= quantityToBorrow;

            // Ask for the borrower's name
            System.out.println("Please write your preferred name: ");
            String nama = scan.nextLine();
            nama = scan.nextLine();

            // Ask for the date of return
            System.out.println("Please write down the date of return in this format (YYYY/MM/DD): ");
            String returnDate = scan.nextLine();

            // Validate the date format
            while (!isValidDateFormat(returnDate)) {
                System.out.println("Invalid date format. Please enter the date again in the format (YYYY/MM/DD): ");
                returnDate = scan.nextLine();
            }

            // Display the borrowing information
            System.out.println("Name: " + nama);
            System.out.println("Equipment Borrowed: " + ia.spec[i][j]);
            System.out.println("Quantity Borrowed: " + quantityToBorrow);
            System.out.println("Return Date: " + returnDate);

            // Update the borrowing information
            ia.EqBo = String.valueOf(quantityToBorrow);
            ia.EqNa = String.valueOf(ia.spec[i][j]);
            ia.name = nama;
            ia.date = returnDate;

            // Set the borrow flag
            borrow = true;
        } else {
            System.out.println("Sorry, the requested quantity is not available.");
            System.out.println("Please try checking other equipment or reducing the quantity.");
        }
    } else {
        System.out.println("Sorry, the selected equipment is not available.");
        System.out.println("Please try checking other equipment.");
    }

    scan.close();
}

    
    public static void updateStorage(){
    Scanner scanner = new Scanner(System.in);

    ia.displayEquipment();

    // Prompt user to choose whether to update broken or new equipment
    System.out.println("Is there any new or broken equipment?(n/b): ");
    String option = scanner.nextLine().toLowerCase();

    // Handle broken equipment
    if (option.equals("b")) {
        // Get row and column of broken equipment
        System.out.println("Type the row that the equipment is in (0-3):");
        int r = scanner.nextInt();

        System.out.println("Type the column that the equipment is in (0-3):");
        int c = scanner.nextInt();

        // Get quantity of broken equipment
        System.out.println("How much of this equipment is broken? ");
        int quantityToRemove = scanner.nextInt();

        // Check if the quantity to remove is valid
        if (quantityToRemove <= ia.matrix[r][c]) {
            System.out.println("Alright, I found the broken equipment");
            ia.matrix[r][c] -= quantityToRemove; // Remove the broken quantity from the existing quantity

            System.out.println("Broken equipment successfully removed.");
        } else {
            System.out.println("Sorry, that amount does not make sense.");
            System.out.println("Please try again.");
        }
    }
    // Handle new equipment
    else if (option.equals("n")) {
        // Get row and column of new equipment
        System.out.println("Type the row that the equipment is in (0-3):");
        int r = scanner.nextInt();

        System.out.println("Type the column that the equipment is in (0-3):");
        int c = scanner.nextInt();

        // Get quantity of new equipment
        System.out.println("How much of this equipment do you want to add? ");
        int quantity = scanner.nextInt();

        ia.matrix[r][c] += quantity; // Add the new quantity to the existing quantity

        System.out.println("Equipment successfully added.");
    }
    // Handle invalid option
    else {
        System.out.println("Sorry that is not a choice.");
        System.out.println("Going back to the menu now.");
    }
}

    
    static void picks(){
        System.out.println("What would you like to do? ");
        System.out.println("1. Borrow Equipment  ");
        System.out.println("2. Return Equipment ");
        System.out.println("3. View Storage");
        System.out.println("4. Quit");
    }

    static void sleep(int i){
        try {
            Thread.sleep(i); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }   

    
}