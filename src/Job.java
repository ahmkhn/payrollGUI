

public class Job {

    private String department;
    private String position;
    private double pay;
    private String contract;

    public Job(String given_dept, String given_title, double given_pay, String given_contract) {
        this.department = given_dept;
        this.position = given_title;
        this.pay = given_pay;
        this.contract = given_contract;
    }


    public double getPay() {
        return this.pay;
    }

    public String getTitle() {
        return this.position;
    }


    public String getDepartment() {
        return this.department;
    }


    public String toString() {
        return this.position;
    }
}