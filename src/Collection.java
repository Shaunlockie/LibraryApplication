import java.util.Scanner;

public class Collection {
     private String name;
     private String desc;
     private int amountOfBooks;
     private Scanner in = new Scanner(System.in);

     public Collection(){

     }
    public void createCollection(){
        System.out.println("Enter name of collection: ");
        this.name = in.nextLine();
        System.out.println("Enter a description of collection");
        this.desc = in.nextLine();
        System.out.println("Collection: " + name + "\nDescription: " + desc);
    }
     // add book to collection

    // delete collection

    //view collection

}

