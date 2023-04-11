import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Contact{
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

    private static List<Contact> contacts = new ArrayList<Contact>();

    private static File contactsFile = new File("contacts.txt");

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadContacts();
        int choice = 0;
        do {
            choice = showMenu();
            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContacts();
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

    private static void loadContacts() {
        try {
            Scanner fileScanner = new Scanner(contactsFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String phone = parts[1].trim();
                Contact contact = new Contact(name, phone);
                contacts.add(contact);
            }
            fileScanner.close();
            System.out.println("Contacts loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("Contacts file not found. Creating a new file.");
            saveContacts();
        }
    }

    private static void saveContacts() {
        try {
            PrintWriter writer = new PrintWriter(contactsFile);
            for (Contact contact : contacts) {
                writer.println(contact.getName() + " | " + contact.getPhoneNumber());
            }
            writer.close();
            System.out.println("Contacts saved to file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error saving contacts to file.");
        }
    }

    private static int showMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete and existing Contact.");
        System.out.println("5. Exit");
        System.out.println(" Enter an option (1, 2, 3, 4, or 5");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;

    }

    private static void viewContacts() {
        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    private static void addContact() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact contact = new Contact(name, phone);
        contacts.add(contact);
        System.out.println("Contact added.");
    }

    private static void searchContacts() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found with name " + name);
        }
    }
    private static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eneter the name of the contact you want to delete:");
        String name = scanner.nextLine();
        int index = -1;
        for (int i=0; i < contacts.size(); i++) {
            if(contacts.get(i).getName().equalsIgnoreCase(name)){
                index=i;
                break;
            }
        }
        if(index == -1){
            System.out.println("contact not found");
            return;
        }

        Contact deletedContact = contacts.remove(index);
        System.out.println("Successfully deleted contact:");
        System.out.println(deletedContact.getName() + "|" + deletedContact.getPhoneNumber());
    }
}

