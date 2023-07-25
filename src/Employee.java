


import java.util.List;
import java.util.ArrayList;

public class Employee {

    private String transaction_id;
    private String last_name;
    private String first_name;
    private List<Job> jobs;


    public Employee(String given_id, String given_last, String given_first) {
        this.transaction_id = given_id;
        this.last_name = given_last;
        this.first_name = given_first;

        this.jobs = new ArrayList<>();
    }



    public void addJob(Job contract) {
        this.jobs.add(contract);
    }



    public String getId() {
        return this.transaction_id;
    }



    public double getPayForDepartment(String department) {
        double payForDepartment = 0;
        for ( Job next_job : this.jobs ) {
            if ( next_job.getDepartment().contains(department) ) {
                payForDepartment = payForDepartment + next_job.getPay();
            }
        }

        return payForDepartment;
    }


    public double getTotalPay() {
        double total = 0;
        for ( Job next_job : this.jobs ) {
            total = total + next_job.getPay();
        }

        return total;
    }

    public int getNumJobs() {
        return this.jobs.size();
    }


    public boolean worksInJob(String partial_job) {

        for ( Job next_job : this.jobs ) {
            if ( next_job.getTitle().contains(partial_job)) {
                return true;
            }
        }

        return false;
    }


    public List<Job> getJobMatches(String title) {
        List<Job> matches = new ArrayList<>();

        for ( Job next_job : this.jobs ) {
            if ( next_job.getTitle().contains(title)) {
                matches.add(next_job);
            }
        }

        return matches;
    }
    
    public double getPayForJob(String title) {
        return getJobMatches(title).stream().mapToDouble(Job::getPay).sum();
    }
    public String getName()
    {
        return(this.first_name+" "+this.last_name);
    }
    public List<Job> getJobs()
    {
        return this.jobs;
    }
    public double getPayFJob() {
        String title = PayrollSearch.getTitle();
        return getJobMatches(title).stream().mapToDouble(Job::getPay).sum();
    }
    @Override
    public String toString() {
        String rep = "";

        String all_jobs = "";
        for ( Job next_job : this.jobs ) {
            all_jobs = all_jobs + " " + next_job;
        }

        rep = this.last_name + ", " + this.first_name + " (" + all_jobs + ") - $" + this.getTotalPay();

        return rep;
    }

}