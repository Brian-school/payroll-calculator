public class Employee {
    private int employeeID;
    private String name;
    private double hrs_worked;
    private double pay_rate;

    public Employee(int employeeID, String name, double hrs_worked, double pay_rate) {
        this.employeeID = employeeID;
        this.name = name;
        this.hrs_worked = hrs_worked;
        this.pay_rate = pay_rate;
    }

    public Employee() {
        employeeID = 0;
        name = null;
        hrs_worked = 0.0;
        pay_rate = 0.0;
    }

    public double getGrossPay() {
        return hrs_worked * pay_rate;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public double getHrs_worked() {
        return hrs_worked;
    }

    public double getPay_rate() {
        return pay_rate;
    }

    public void setPay_rate(double pay_rate) {
        this.pay_rate = pay_rate;
    }

    public void setHrs_worked(double hrs_worked) {
        this.hrs_worked = hrs_worked;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }


}
