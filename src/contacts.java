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

