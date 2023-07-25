
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PayrollLoader {

    private String path;

    public PayrollLoader(String path) {
        this.path = path;
    }



    public List<Employee> load() throws FileNotFoundException {

        List<Employee> employees = new ArrayList<>();


        Scanner in = new Scanner(new File(this.path));

        String headers = in.nextLine();
        String[] headers_split = headers.split(",");


        while ( in.hasNextLine() ) {

            String next_line = in.nextLine();

            String[] contract_parts = next_line.split(",");

            String last_name = contract_parts[get_id(headers_split, "name_last")];
            String first_name = contract_parts[get_id(headers_split, "name_first")];
            String transaction = contract_parts[get_id(headers_split, "trans_no")];
            String dept = contract_parts[get_id(headers_split, "department_division")];
            String title = contract_parts[get_id(headers_split, "position_title")];
            String pay_text = contract_parts[get_id(headers_split, "pay_total_actual")];
            String contract = contract_parts[get_id(headers_split, "contract")];

            if (  isNumber(pay_text) ) {
                double pay = Double.parseDouble(pay_text);


                Job next_job = new Job(dept, title, pay, contract);
                Employee this_employee = null;

                if ( employees.size() > 0 ) {
                    Employee last_employee = employees.get(employees.size()-1);
                    if ( last_employee.getId().equals(transaction) ) {
                        this_employee = last_employee; //<- this person has the same ID, they're who I'm looking for!
                    }
                }//if there were previously made employees

                if ( this_employee == null ) {//we didn't find a matching employee, we need to make one
                    this_employee = new Employee(transaction, last_name, first_name);
                    employees.add(this_employee);
                }


                this_employee.addJob(next_job);
            }//if the pay and id number are valid

        }//while there are jobs left


        return employees;


    }//load function



    private int get_id(String[] headers, String name) {
        for ( int i=0; i<headers.length; i++) {
            String header = headers[i];
            if ( name.equals(header) ) {
                return i;
            }
        }

        return -1;
    }


    private static boolean isNumber(String s) {
        for ( int i=0; i<s.length(); i++) {
            char next_character = s.charAt(i);
            if ( false == Character.isDigit(next_character) && !(next_character == '.')) {
                return false;
            }
        }


        return true;
    }

}