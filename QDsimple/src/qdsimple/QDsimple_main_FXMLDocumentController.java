/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn.Kafka666
 * @author realsazzad
 * 
*/

package qdsimple;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class QDsimple_main_FXMLDocumentController implements Initializable {
    
    // FXML document loaded variables
    @FXML
    private BorderPane borderpane_main;
    @FXML
    private StackPane stackpane_dialog;
    double stackpane_dialog_h, stackpane_dialog_w;
    @FXML
    private Button cube_button;
    @FXML
    private Button circle_button;
    @FXML
    private Button cone_button;
    @FXML
    private Button cylinder_button;
    @FXML
    private Button sphere_button;
    @FXML
    private Button cube_button211;
    @FXML
    private ImageView line_button;
    @FXML
    private MenuBar menubar_borderpane_main;
    @FXML
    private Menu file_menubar_borderpane_main;
    @FXML
    private MenuItem close_file_menubar_borderpane_main;
    @FXML
    private Menu help_menubar_borderpane_main;
    @FXML
    private MenuItem about_help_menubar_borderpane_main;
    @FXML
    private AnchorPane anchorpaneright_borderpane_main;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dynamically change window size whenever window size is changed
        stackpane_dialog_w = stackpane_dialog.widthProperty().doubleValue();
        stackpane_dialog_h = stackpane_dialog.heightProperty().doubleValue();
        window_resize_listener_f();
        
        
    }    
    
    private void window_resize_listener_f() {
        stackpane_dialog.widthProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_dialog_w = newVal.doubleValue();
            borderpane_main.setPrefWidth(stackpane_dialog_w);
        });
        stackpane_dialog.heightProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_dialog_h = newVal.doubleValue();
            borderpane_main.setPrefHeight(stackpane_dialog_h);
        });
    }

    @FXML
    private void activated_close_file_menubar_borderpane_main_f(ActionEvent event) {
        QDsimple.get_current_instance_QDsimple().exit_program();
    }

    @FXML
    private void activated_about_help_menubar_borderpane_main_f(ActionEvent event) {
        // To blur the background when dialogue box pops in
        BoxBlur blur = new BoxBlur(3, 3, 3);
        
        JFXDialogLayout About_content = new JFXDialogLayout();
        About_content.setHeading(new ImageView("/images/QDsimple.png"));
        About_content.setBody(new Text("QDsimple is an open source quantum device simulation software, with which users will be able to construct\n"
                + "various quantum devices and run simulations on desired sub - domains of said devices. The project is under \n" 
                + "development by the group Artificial Indie Collaborations. For reference to the source code, contact AI collab.\n"));
        
        
        JFXDialog About_dialog = new JFXDialog(stackpane_dialog, About_content, JFXDialog.DialogTransition.CENTER);
        JFXButton About_button = new JFXButton("Okay");
        About_button.setStyle(" -fx-background-color: slateblue; -fx-text-fill: white; ");
        
        About_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                About_dialog.close();
            }
        });
        
        // set the contents within and display
        About_content.setActions(About_button);
        About_dialog.show();
        
        // blurred background when dialog box opened
        About_dialog.setOnDialogOpened((JFXDialogEvent event_opened) ->{
            borderpane_main.setEffect(blur);
        });
        // de - blurred background when dialog box opened
        About_dialog.setOnDialogClosed((JFXDialogEvent event_closed) ->{
            borderpane_main.setEffect(null);
        });
    }
    
}
