import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Defining the contact class which represents a contact with a name and phone number
class Contact{
    private String name;
    private String phone;

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
    //Getter method for the name property
    public String getName(){
        return name;
    }

    //getter method for the phone property
    public String getPhoneNumber() {
        return phone;
    }

    //Setter method for the phone property
    public void setPhone(String phone){
        this.phone=phone;
    }

    //override the toString method to return a string representation of the contact object
    public String toString() {
        return name + "|" + phone;
    }
}

//Defining the ContactManager class
public class ContactManager {

    private static List<Contact> contacts = new ArrayList<Contact>();

    private static File contactsFile = new File("contacts.txt");

    private static Scanner scanner = new Scanner(System.in);

    //Main method which displays a menu and handles user input
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
        //Save contacts to file when program exits
        saveContacts();
    }

    //Load contacts from file
    ////Read each line from the file and split it into name and phone number
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

    //Save contacts to file
    ////Write each contact to the file in the format "name | phone number"
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


    //Display menu options to the user and returns their choice as an integer
    //this shows the main menu

    private static int showMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete and existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option (1, 2, 3, 4, or 5)");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;

    }

    //Displays all contacts in the list of contacts
    private static void viewContacts() {
        System.out.println("Name | Phone number");
        System.out.println("-------------------");
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    //Prompts the user to enter a new contact's name and phone number,creates
    //a new Contact object with this information and adds it to the list of contacts.
    private static void addContact() {
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact contact = new Contact(name, phone);
        contacts.add(contact);
        System.out.println("Contact added.");
    }

    //Prompts the user to enter a name to search for, searches the
    //list of contacts for a contact with that name, and displays any contacts found.
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

    //Prompts the user to enter a name of a contact to delete, searches the list of contacts
    //for a contact with that name, removes the contact if found, and displays a message
    //indicating whether or not the contact was deleted.
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

