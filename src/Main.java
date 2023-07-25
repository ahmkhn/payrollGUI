import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;



/*
Objectives for Today

By the end of today, you will be able to:
  * Create JavaFX applications that use more than one scene to switch between different "views" in a program.
  * Generate modal dialogs for prompting users and choosing files.
  * Set the values of JavaFX controls from within code.
 */



public class Main extends Application {


  private static FilePicker picker;
  private static FileDisplayer displayer;
  private static PayrollSearch searcher;

  public void start(Stage primaryStage) throws IOException {

        /*
        Just like before, we're going to load the two views we made with the FXMLLoader.
         */
        URL picker_scene_location = Main.class.getResource("FilePicker.fxml");
        FXMLLoader loader = new FXMLLoader(picker_scene_location);
        Parent root = loader.load();
        Scene picker_scene = new Scene(root);
        this.picker = loader.getController();


        URL displayer_scene_location = Main.class.getResource("FileDisplayer.fxml");
        FXMLLoader display_loader = new FXMLLoader(displayer_scene_location);
        Parent display_root = display_loader.load();
        Scene display_scene = new Scene(display_root);
         this.displayer = display_loader.getController();


        URL searcher_scene_location=Main.class.getResource("PayrollSearch.fxml");
        FXMLLoader search_loader=new FXMLLoader(searcher_scene_location);
        Parent search_root= search_loader.load();
        Scene search_scene=new Scene(search_root);
          this.searcher=search_loader.getController();
        // For this app, we'll want to start on the view where we can select our files:
        primaryStage.setScene(picker_scene);
        primaryStage.show();

  }

      /*
    While we're at it, let's add some getters so that we have access to our Controllers
      in the other parts of our program:
     */
    public static FileDisplayer getDisplayer(){
        return Main.displayer;
    }

    public static FilePicker getPicker(){
        return Main.picker;
    }
    public static PayrollSearch getSearcher()
    {
      return Main.searcher;
    }
}