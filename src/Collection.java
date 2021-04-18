import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Collection implements Serializable {
    private int collectionID;
     private String name;
     private String desc;
     private LocalDate createdDate;
     private int userID;
     private ArrayList<Book> bookArray = new ArrayList<Book>();

     //private String owner;

     private Scanner in = new Scanner(System.in);

     public Collection(){

     }

    public void createCollection(){
        System.out.println("Enter name of collection: ");
        this.name = in.nextLine();
        System.out.println("Enter a description of collection");
        this.desc = in.nextLine();
        createdDate = LocalDate.now();
        setCreatedDate(createdDate);
     }
    public void showCollectionInfo(){
        System.out.println("Name: " + name);
        System.out.println("Description: " + desc);
    }
    public int getCollectionID(){
         return collectionID;
    }
    public void setCollectionID(int collectionID){
         this.collectionID = collectionID;
    }
     public String getName(){
         return name;
     }
     public void setName(String name){
         this.name = name;
     }
     public String getDesc(){return name;}
     public void setDesc(String desc){
         this.desc = desc;
     }
    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate;
    }
    public LocalDate getCreatedDate(){
        return createdDate;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return userID;
    }

    public ArrayList<Book> getBookArray(){
        return bookArray;
    }
    public void setBookArray(){
        this.bookArray = bookArray;
    }
    public void addToBookArray(Book b){
        bookArray.add(b);
    }
    public void arrayToString(ArrayList<String> bookArray){
        for(String elem : bookArray){
            System.out.println(elem+"  ");
        }
    }
    @Override
    public String toString(){
         return this.name + " " + this.desc;
     }

    // delete collection

    //view collection

}

