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
    private static void loadcontacts(){
        try{
            Scanner fileScanner = new Scanner(contactFile);
            while(fileScanner.hasNextLine()){
                String line= fileScanner.nextLine();
                String[] parts=line.split("\\|");
                String name =parts[0].trim();
                String phone=parts[1].trim();
                Contact contact=new contact(name, phone);
                contacts.add(contact);
            }
            fileScanner.close();
            System.out.println("Contacts loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("Contacts file not found. Creating a new file.");
            saveContacts();
        }
    }
    private static void saveContacts(){
        try{
            PrintWriter write = new PrintWriter(contactFile);
            for (Contact contact : contacts){
                writer.println(contact.getName() + "|" + contact.getPhoneNumber());
            }
            writer.close();
            System.out.println("contacts saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving Contacts to file");
        }
    }

    private static int showMenu(){
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete and existing Contact.");
        System.out.println("5. Exit");
        System.out.println(" Enter an option (1, 2, 3, 4, or 5");
        int choice = scanner.nextInt();
        scanner.nextLine()
                return choice;
    }


}


