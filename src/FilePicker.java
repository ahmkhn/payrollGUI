
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;


/* Our FilePicker is going to have the same basic parts we saw last class:

Most of the properties will match the names we gave our controls in SceneBuilder, and we will
annotate them with @FXML.

Most of our methods will match the names we gave the event handlers in SceneBuilder for our buttons.
 */

public class FilePicker {

	@FXML
	private TextField file_textfield;
	@FXML
	private Button load_button;
	@FXML
	private Button browse_button;


	private File selected_file; 


	public void browse_for_file(ActionEvent e) {

        /* When we click the file selection button, we want to open one of those fancy popups
        that has all the formatting that we're used to seeing when we select a file.

        It would take us a very long time to make one of those on our own. Fortunately, we don't have
        to -- there's a FileChooser class that does it for us! Using this class is as easy as making
        an object, then calling:

            chooser_variable.showOpenDialog(on_some_stage_variable);
       

         This means we'll need to get a reference to our stage. Fortunately, we can get one from any
         control by getting the control's scene, then getting the window from the scene. (Remember, the
         stage is just a metaphor for the window.)
         */

         FileChooser file_chooser = new FileChooser();

		 Stage main_stage = getStage();

        /* If we want to be extra fancy and the user has selected a file before, we can even
        start our dialog pre-loaded on the folder of their previous selection:
         */
         if ( this.selected_file != null ) {
         file_chooser.setInitialDirectory(this.selected_file.getParentFile());

         }
 

         File dialog_result = file_chooser.showOpenDialog(main_stage);

         if ( dialog_result != null ) {
        	 this.selected_file = dialog_result;
        	 this.file_textfield.setText(this.selected_file.getAbsolutePath());
     	}

	}

	public void load_file(ActionEvent e) throws FileNotFoundException{
		FileDisplayer displayer = Main.getDisplayer();
		Scene scene_of_displayer = displayer.getScene();
		Stage main_window = this.getStage();
		displayer.loadText(this.selected_file);
		main_window.setScene(scene_of_displayer);
		// scene is set to "loading..." now we make it into user controls.
		PayrollSearch searcher=Main.getSearcher();
		Scene scene_of_searcher = searcher.getScene(); // this is scene of payrollSearch.java
		main_window.setScene(scene_of_searcher);

	}


	public Stage getStage() {
		return (Stage) load_button.getScene().getWindow();
	}
}