import java.util.Scanner;

public class Collection {
     private String name;
     private String desc;
     private int amountOfBooks;
     private String owner;
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
    public void showCollectionInfo(){
        System.out.println("Name: " + name);
        System.out.println("Description: " + desc);
        System.out.println("Owner: " + owner);
    }
     public String getColName(String name){
         return name;
     }
     public void setColName(){
         this.name = name;
     }

    // delete collection

    //view collection

}

