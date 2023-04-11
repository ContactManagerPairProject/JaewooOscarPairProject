import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class contacts {
    private String name;
    private String phone;

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber() {
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String toString() {
        return name + "|" + phone;
    }
}

public class ContactManager {

    private static List<Contact> contacts = new ArrayList<Contacts>();

    private static File contactFile = new File("contacts.txt");

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadContacts();
        int choice = 0;
        do{
          choice = showMenu();
        switch (choice) {
            case 1:
                viewContacts();
                break;
            case 2:
                addContact();
                break;
            case 3:
                searchContact();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    } while (choice != 5);
        saveContacts();
}


