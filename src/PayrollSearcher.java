/*
    - Ask a person for their total salary across all jobs.
    - Ask a person how many jobs they hold.
    - Ask a person the title of their highest earning job.
    - Represent a whole person as a string (including all their job titles and their total salary)
 */

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PayrollSearcher {

    private List<Employee> employees;


    public PayrollSearcher(List<Employee> loaded_employees) {
        this.employees = loaded_employees;
    }

    public  List<Employee> findEmployeesByDept(String dept) {
        List<Employee> dept_matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.getPayForDepartment(dept) > 0 ) {
                dept_matches.add(next);
            }
        }

        return dept_matches;

    }


    public  List<Employee> findEmployeesByJobTitle(String job) {
        List<Employee> matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.worksInJob(job) ) {
                matches.add(next);
            }
        }

        return matches;

    }


    public double totalSalaryForDept(String dept) {
        double total_for_dept = 0;

        List<Employee> employees_in_dept = this.findEmployeesByDept(dept);

        for ( Employee employee : employees_in_dept ) {
            total_for_dept = total_for_dept + employee.getPayForDepartment(dept);
        }

        return total_for_dept;
    }


    public double avgSalaryForJob(String job) {

        double total_for_job = 0;
        double num_positions = 0;

        List<Employee> people_in_job = this.findEmployeesByJobTitle(job);

        for ( Employee next_guy : people_in_job ) {
            for ( Job position_they_hold : next_guy.getJobMatches(job)) {
                total_for_job = total_for_job + position_they_hold.getPay();
                num_positions = num_positions + 1;
            }
        }

        return total_for_job / num_positions;
    }


    public  List<Employee> findEmployeesByJobAndDept(String job, String dept) {
        List<Employee> matches = new ArrayList<>();

        for ( Employee next : this.employees ) {
            if ( next.worksInJob(job) && next.getPayForDepartment(dept) > 0 ) {
                matches.add(next);
            }
        }

        return matches;

    }
    public List<Employee> findEmployeesByID(String ID){
        List<Employee> matches = new ArrayList<>();
        for(Employee next:this.employees){
            if(next.getId().equals(ID)){
                matches.add(next);
            }
        }
        return matches;
    }
    public List<Employee> findEmployeesByName(String Name){
        List<Employee> matches=new ArrayList<>();
        for(Employee next:this.employees){
            if(next.getName().equals(Name)){
                matches.add(next);
            }
        }
        return matches;
    }
    
    
    private List<Employee> sortedEarnersByPosition(String job) {
        List<Employee> filtered_employees = findEmployeesByJobTitle(job);
        
        filtered_employees.sort((Employee e,  Employee f) -> {
            double e1_total_pay = e.getPayForJob(job);
            
            double e2_total_pay = f.getPayForJob(job);
            
            if ( e1_total_pay > e2_total_pay ) {
                return -1;
            } else {
                return 1;
            }
        });
        
        return filtered_employees;
        
        
    }

    public List<Employee> topEarnersByPosition(String job, int count) {
        return sortedEarnersByPosition(job).subList(0, count);
    }
    
    public List<Employee> bottomEarnersByPosition(String job, int count) {
        List<Employee> employees = sortedEarnersByPosition(job)
        .stream().filter((e) -> e.getPayForJob(job) > 0).collect(Collectors.toList());
        Collections.reverse(employees);
        
        return employees.subList(0, count);
    }
    
    public double medianSalaryForPosition(String job) {
        List<Employee> employees = sortedEarnersByPosition(job);
        
        int middle_index = employees.size() / 2;
        
        if ( employees.size() % 2 == 0 ) {
            return employees.get(middle_index).getPayForJob(job);
        } else {
            double sum_of_both_middle_values = employees.get(middle_index-1).getPayForJob(job)
                                               + employees.get(middle_index).getPayForJob(job);
            return sum_of_both_middle_values / 2;
        }
    }


}