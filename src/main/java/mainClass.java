import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;

public class mainClass {
    static void main() throws FileNotFoundException, IOException {
        String filename = "employees.csv";

        // Employee Fields
        int employeeID;
        String name;
        double hrs_worked;
        double pay_rate;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArrayList<Employee> employee_list = new ArrayList<>();
        String input;
        reader.readLine();
        while ((input = reader.readLine()) != null) {
            String [] fields = input.trim().split("\\|");
            if (fields.length != 4) {
                println("ERROR: bad data formatting!");
                System.out.printf("Elements: %d", fields.length);
                return;
            }
            employeeID = Integer.parseInt(fields[0]);
            name = fields[1];
            hrs_worked = Double.parseDouble(fields[2]);
            pay_rate = Double.parseDouble(fields[3]);

            employee_list.add(new Employee(employeeID, name, hrs_worked, pay_rate));
        }
        reader.close();
        println("Employee data read in.");

        String [] write_filenames = {"gross_pay.json", "gross_pay.csv"};
        String write_filename = "";
        boolean quit = false;
        int choice = 0;
        while (!quit) {
            System.out.print("What format would you like your payroll in?\n1 - .json\n2 - .csv\nChoice -> ");
            Scanner sc = new Scanner(System.in);
            switch (sc.nextLine().trim().toLowerCase()) {
                case "1":
                    write_filename = write_filenames[0];
                    choice = 1;
                    quit = true;
                    break;
                case "2":
                    write_filename = write_filenames[1];
                    choice = 2;
                    quit = true;
                    break;
                default:
                    println("Sorry, I didn't recognize that. Please choose 1 or 2.");
            }
        }

        BufferedWriter bufWriter = new BufferedWriter(new FileWriter(write_filename));
        String text;
        if (choice == 1) {
            bufWriter.write("[\n");
        }
        int size = employee_list.size();
        for(int i = 0; i < size; i++) {
            Employee e = employee_list.get(i);
            if (choice == 2) {
                text = String.format("%d|%s|%.2f\n",
                        e.getEmployeeID(),
                        e.getName(),
                        e.getGrossPay());
            } else {
                text = String.format("\t{ \"id\": %d, \"name\" : \"%s\", \"grossPay\" : %.2f }%c\n",
                        e.getEmployeeID(),
                        e.getName(),
                        e.getGrossPay(),
                        (i == size - 1) ? ' ' : ',');
            }
            bufWriter.write(text);
        }
        if (choice == 1) {
            bufWriter.write("]\n");
        }
        bufWriter.close();
        println("Employee data written to " + write_filename);
    }
}
