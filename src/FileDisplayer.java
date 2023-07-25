
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;



public class FileDisplayer {
	
	@FXML
	private TextArea textarea;
	@FXML
	private MenuItem close_option;
	@FXML
	private MenuItem save_option;
	@FXML
	private MenuItem quit_option;
	private static List<Employee> x = new ArrayList<>();


	public Scene getScene() {
		return this.textarea.getScene();
	}


	// file path : some_file.getAbsolutePath()
	public void loadText(File some_file) throws FileNotFoundException{
		this.textarea.setText("File is loading...");
		String qq = some_file.getAbsolutePath();
		PayrollLoader rollLoader= new PayrollLoader(qq);
		List<Employee> employeeList=new ArrayList<>();
		employeeList=rollLoader.load(); // we have employee list. now we have to give the user controls that allow them to ask for following data.
		// send this to payrollsearch.java.
		FileDisplayer.x=employeeList;
	}
	public static List<Employee> getEmpList()
	{
		return FileDisplayer.x;
	}
}