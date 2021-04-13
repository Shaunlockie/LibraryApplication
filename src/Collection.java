import java.io.Serializable;
import java.util.Scanner;

public class Collection implements Serializable {
     private String name;
     private String desc;
     private int amountOfBooks;
     //private String owner;
     private Scanner in = new Scanner(System.in);

     public Collection(){

     }
    public void createCollection(){
        System.out.println("Enter name of collection: ");
        this.name = in.nextLine();
        System.out.println("Enter a description of collection");
        this.desc = in.nextLine();
    }
    public void showCollectionInfo(){
        System.out.println("Name: " + name);
        System.out.println("Description: " + desc);
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
     /*public String getOwner(){
         return owner;
     }
     //public void setOwner(String owner){
         this.owner = owner;
     }*/

     @Override
     public String toString(){
         return this.name + " " + this.desc;
     }

    // delete collection

    //view collection

}

