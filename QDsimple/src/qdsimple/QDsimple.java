/**
 *
 * @author Finn.Kafka666
 * @author realsazzad
 * 
*/

package qdsimple;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QDsimple extends Application {
    
    // Used to manipulate this main program from any controller class
    private static QDsimple QDsimple_main;
    
    // Populate the QDsimple_main object with currently instantiated project object
    public QDsimple(){
        QDsimple_main = this;
    }
    
    // Function returns current project object instance
    public static QDsimple get_current_instance_QDsimple(){
        return QDsimple_main;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("QDsimple_main_FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/QDsimple_logo.png"));
        stage.setTitle("Quantum Device Simulator Package");
        stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // Function to exit program
    public void exit_program() {
        System.exit(0);
    }
    
}
