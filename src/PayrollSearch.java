
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import java.lang.Math;
public class PayrollSearch {
	@FXML
    private TabPane paneTab;
    @FXML
    private TextField jobTitleField;
    @FXML
    private TableView<Employee> top_earner_table;
    @FXML
    private TableColumn<Employee,String> tableNameTop;
    @FXML
    private TableColumn<Employee,Double> tableSalaryTop;
    @FXML
    private TableView<Employee> bot_earner_table;
    @FXML
    private TableColumn<Employee,String> tableNameBot;
    @FXML
    private TableColumn<Employee,Double> tableSalaryBot;
    @FXML
    private Label median;
    @FXML
    private Label average;
    private static String Title;
    // ^^ for job title searching
    // vv for ID/name searching.
    @FXML
    private TextField IDorName;
    @FXML
    private TableView<Job> IDTable;
    @FXML
    private TableColumn<Job,String> IDTitle;
    @FXML
    private TableColumn<Job,Double> IDSalary;
    @FXML
    private TableColumn<Job,String> IDDept;
    private static List<Job> employeeJob;
    //  have employee name, using name we get array of jobs and then get title from that.
	public Scene getScene() {
		return (Scene) paneTab.getScene();
	}
    public Stage getStage() {
		return (Stage) paneTab.getScene().getWindow();
	}
    public void searchJobTitle() // searches job title and updates the table. first order of business is getting that employee list..
    {
        top_earner_table.getItems().clear();
        bot_earner_table.getItems().clear();
        List<Employee> empList = new ArrayList<>();
        empList=FileDisplayer.getEmpList();
        String jobTitle = jobTitleField.getText();
        // now  have loaded list of employees.
        //jobtitle fetching works nicely.
        PayrollSearcher j = new PayrollSearcher(empList);
        List<Employee> topEarners = new ArrayList<>();
        List<Employee> bottomEarners = new ArrayList<>();
        bottomEarners=j.bottomEarnersByPosition(jobTitle, 5);
        topEarners=j.topEarnersByPosition(jobTitle, 5);
        List<Employee> top_employees_in_grid = top_earner_table.getItems();
        top_employees_in_grid.addAll(topEarners);
        List<Employee> bot_employees_in_grid=bot_earner_table.getItems();
        bot_employees_in_grid.addAll(bottomEarners);
        PropertyValueFactory<Employee,String> top_earners_name_getter = new PropertyValueFactory<>("Name");
        tableNameTop.setCellValueFactory(top_earners_name_getter); // top earners name works.
        PropertyValueFactory<Employee,Double> top_earners_salary_getter = new PropertyValueFactory<>("PayFJob");
        tableSalaryTop.setCellValueFactory(top_earners_salary_getter); // top earners salary works
        Title=jobTitle;
        PropertyValueFactory<Employee,String> bot_earners_name_getter = new PropertyValueFactory<>("Name");
        tableNameBot.setCellValueFactory(bot_earners_name_getter); // bot earners name works.
        PropertyValueFactory<Employee,Double> bot_earners_salary_getter = new PropertyValueFactory<>("PayFJob");
        tableSalaryBot.setCellValueFactory(bot_earners_salary_getter); // not bot earners salary works
        // table works, now get labels of median and average to work.
        double medSalary= j.medianSalaryForPosition(jobTitle);
        double avgSalary=j.avgSalaryForJob(jobTitle);
        median.setText("Median Salary is: "+Math.round(medSalary));
        average.setText("Average Salary is: "+Math.round(avgSalary)); //rounded this because too many dec places.
        // JOB DETAILS ARE DONE! NOW TIME FOR SEARCHING VIA ID/NAME
    }
    public void searchIDName()
    { // searches with ID or String Name
        IDTable.getItems().clear();
        List<Employee> empFinal = new ArrayList<>();
        List<Employee> empList = new ArrayList<>();
        empList=FileDisplayer.getEmpList();
        PayrollSearcher j = new PayrollSearcher(empList);
        String IDorNameText =IDorName.getText();
        // this is either and ID or a full name, time to decipher which one. simple logic is see if it has a space.
        String[] qq = new String[2];
        qq = IDorNameText.split(" ");
        boolean ID = false; // is it a transaction ID?
        if(qq.length==1)
        {
            ID=true;
        }
        if(ID==true) //  search via transaction ID. // create payrollsearcher function with .getID from 
        {
            System.out.println("ID tester works."); // works
            List<Employee> jjj = new ArrayList<>();
            jjj=j.findEmployeesByID(IDorNameText);
            empFinal=jjj;
            
             //  now have an employee that matches ID list, now setup table and include info.
        }
        else
        { //  search via first and last name.
            List<Employee> jjj = new ArrayList<>();
            jjj=j.findEmployeesByName(IDorNameText);
            empFinal=jjj;
        } //  now have employee list of everything  may need to overwrite employee list every time but lets wait and see.
        employeeJob=empFinal.get(0).getJobs(); //  now have list of jobs.
        
        List<Job> searchedEmployees = IDTable.getItems();
        searchedEmployees.addAll(employeeJob);
        PropertyValueFactory<Job,String> jobTitleGetter = new PropertyValueFactory<>("Title"); //works
        IDTitle.setCellValueFactory(jobTitleGetter); // job title works
        //
        PropertyValueFactory<Job,Double> jobSalaryGetter = new PropertyValueFactory<>("Pay"); //works
        IDSalary.setCellValueFactory(jobSalaryGetter); 
        //
        PropertyValueFactory<Job,String> jobDepartmentGetter = new PropertyValueFactory<>("Department"); //works
        IDDept.setCellValueFactory(jobDepartmentGetter); 
    }
    public static String getTitle()
    {
        return Title;
    }
}