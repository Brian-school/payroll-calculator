import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;

import static java.lang.IO.println;

public class mainClass {
    static void main() throws FileNotFoundException, IOException {
        String filename = "employees.csv";
        boolean quit = false;

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
                quit = true;
                break;
            }
            employeeID = Integer.parseInt(fields[0]);
            name = fields[1];
            hrs_worked = Double.parseDouble(fields[2]);
            pay_rate = Double.parseDouble(fields[3]);

            employee_list.add(new Employee(employeeID, name, hrs_worked, pay_rate));
        }
        reader.close();
        for (int i = 0; i < employee_list.size(); i++) {
            System.out.printf("EID %2d NAME %s GROSS PAY %.2f\n",
                    employee_list.get(i).getEmployeeID(),
                    employee_list.get(i).getName(),
                    employee_list.get(i).getGrossPay());
        }

    }
}
