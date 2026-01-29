import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.io.FileWriter;

public class ia {
    public static int quantity;
    
    public static final File EQ_BORROW_FILE = new File("EqBorrow.txt");
    public static final File NAME_FILE = new File("Name.txt");
    public static final File DATE_FILE = new File("Date.txt");
    public static final File EQ_NAME_FILE = new File("EqName.txt");
    
    public static int[][] matrix = {
      {0, 4, 6, 30},
      {2, 6, 2, 2},
      {10, 2, 21, 12},
      {23, 12}
    };
    
  public static String[][] spec = {
    {"Stapler", "PlasticBottlesL", "Gluegun", "Stationery"},
    {"Arduinobox", "Scissors", "Toolbox", "Gloveboxes"},
    {"PlasticBottlesS", "PlasticBottlesM", "Goggles", "ApronL"},
    {"ScrapPaper","ApronS"}
  };
  
  public static void displayEquipment() {
    // function to display equipment from the 2D matrix
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.println("(" + i + "," + j + "): " + spec[i][j] + " - Quantity Available: " + matrix[i][j]);
        }
    }
}
  
public static String EqBo, EqNa, name, date = "";

public static String[] splitLine(String line) {
  if (line.contains("\\s")) {
    return line.split("\\s+");
  } else {
    // Handle the case where there are no spaces
    String[] result = new String[1];
    result[0] = line;
    return result;
  }
}

public static String[] EQ, NA, DA, EN;
  
public static void main(String[] args){
try {
    //reads the file
    BufferedReader reader = new BufferedReader(new FileReader(EQ_BORROW_FILE));
    BufferedReader r = new BufferedReader(new FileReader(NAME_FILE));
    BufferedReader re = new BufferedReader(new FileReader(DATE_FILE));
    BufferedReader rea = new BufferedReader(new FileReader(EQ_NAME_FILE));


    
    boolean active = true;
    def.smp_menu();
    def.sleep(2000);
    if (EQ_BORROW_FILE.length() != 0 && NAME_FILE.length() != 0 && DATE_FILE.length() != 0 && EQ_NAME_FILE.length() != 0 ){
        try {
            //if the file isn't empty, it will readline and output the information of student needing to return the equipment
            System.out.println("Please remind these students to return their Equipment");
            def.sleep(2000);
            String EqBorrow = reader.readLine(); 
            String Name = r.readLine();
            String Date = re.readLine();
            String EqName = rea.readLine(); 
        
            EQ = EqBorrow.split(" "); 
            NA = Name.split(" ");
            DA = Date.split(" "); 
            EN = EqName.split(" "); 
            System.out.println("============================================");
            for (int f = 0; f < EQ.length; f++){
                System.out.println("Name: " + NA[f]);
                System.out.println("Equipment borrowed, Quantity: " + EN[f] + "," + EQ[f]);
                System.out.println("Date needed to return: " + DA[f]);
                System.out.println("============================================");
                def.sleep(2000);
                
            }
            
            
        } catch (IOException e){
            System.out.println("Loading Error, Please restart this system");
        }
        
        
        
    } else {
        System.out.println("There is no equipment missing or overdue");
    } 
    
} catch(IOException e){
        e.printStackTrace();
    }
    Scanner input = new Scanner(System.in);
    int choice;
    def.picks();
    System.out.println("What would you like to do?");
    while (true){
      if (input.hasNext()){
        // making sure the right input is done
        choice = input.nextInt();
        break;
      } else {
        System.out.println("Please enter a valid input");
      }
    }
    
    while (choice != 4) {
      switch (choice){
        case 1:
          def.borrowEquipment();
          break;

        case 2: 
          Scanner p = new Scanner(System.in);
          System.out.println("Please type down your name");
          String nu = p.nextLine();
          def.returnEquipment(nu);
          break;

        case 3:
          def.updateStorage();
          break;
      

      default:
        System.out.println("Sorry that is not possible");
        System.out.println("Please try again");
        break;
        }
      
      System.out.println("==================================");  
      def.picks();
      choice = input.nextInt();
      }
      
    System.out.println("Thanks for using the system");
    System.out.println("See you again");
    
    if (def.borrow == true){
        try {
        //saves the information into the text file, which will then be saved and not overwritten 
        FileWriter EqBoWriter = new FileWriter(EQ_BORROW_FILE, true);
        EqBoWriter.write(EqBo+ " ");
        EqBoWriter.flush();
        EqBoWriter.close();

        FileWriter nameWriter = new FileWriter(NAME_FILE,true);
        nameWriter.write(name+ " ");
        nameWriter.flush();
        nameWriter.close();

        FileWriter dateWriter = new FileWriter(DATE_FILE,true);
        dateWriter.write(date + " ");
        dateWriter.flush();
        dateWriter.close();

        FileWriter EqNaWriter = new FileWriter(EQ_NAME_FILE,true);
        EqNaWriter.write(EqNa+ " ");
        EqNaWriter.flush();
        EqNaWriter.close();
        
        } catch (Exception e){
        System.out.println("Sorry, It seems that the data was not saved");
        System.out.println("Please Try again"); 
        }
    }

}
}