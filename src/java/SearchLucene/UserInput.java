package SearchLucene;

import java.util.Scanner;

public class UserInput {

    public String getUserInput() {
        String query = "";
        Scanner reader = new Scanner(System.in);
        System.out.println("please enter a query: ");
        query = reader.nextLine();
        return query;
    }
}
