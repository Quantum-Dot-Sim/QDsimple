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
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class QDsimple_main_FXMLDocumentController implements Initializable {
    
    // FXML document loaded variables
    @FXML
    private BorderPane BorderPane_Main;
    @FXML
    private MenuBar MenuBar_BorderPane_Main;
    @FXML
    private Menu File_MenuBar_BorderPane_Main;
    @FXML
    private MenuItem Close_File_MenuBar_BorderPane_Main;
    @FXML
    private Menu Help_MenuBar_BorderPane_Main;
    @FXML
    private MenuItem About_Help_MenuBar_BorderPane_Main;
    @FXML
    private AnchorPane AnchorPaneRight_BorderPane_Main;
    @FXML
    private StackPane StackPane_Dialog;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dynamically change window size
        Window_resize_listener_f();
        
    }    
    
    @FXML
    private void Window_resize_listener_f() {
        StackPane_Dialog.widthProperty().addListener((obs, oldVal, newVal) ->{
            BorderPane_Main.setPrefWidth(newVal.doubleValue());
        });
        StackPane_Dialog.heightProperty().addListener((obs, oldVal, newVal) ->{
            BorderPane_Main.setPrefHeight(newVal.doubleValue());
        });
    }

    @FXML
    private void Activated_Close_File_MenuBar_BorderPane_Main_f(ActionEvent event) {
        QDsimple.get_current_instance_QDsimple().exit_program();
    }

    @FXML
    private void Activated_About_Help_MenuBar_BorderPane_Main_f(ActionEvent event) {
        // To blur the background when dialogue box pops in
        BoxBlur blur = new BoxBlur(3, 3, 3);
        
        JFXDialogLayout About_content = new JFXDialogLayout();
        About_content.setHeading(new ImageView("/images/QDsimple.png"));
        About_content.setBody(new Text("QDsimple is an open source quantum device simulation software, with which users will be able to construct\n"
                + "various quantum devices and run simulations on desired sub - domains of said devices. The project is under \n" 
                + "development by the group Artificial Indie Collaborations. For reference to the source code, contact AI collab.\n"));
        
        JFXDialog About_dialog = new JFXDialog(StackPane_Dialog, About_content, JFXDialog.DialogTransition.CENTER);
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
            BorderPane_Main.setEffect(blur);
        });
        // de - blurred background when dialog box opened
        About_dialog.setOnDialogClosed((JFXDialogEvent event_closed) ->{
            BorderPane_Main.setEffect(null);
        });
    }
    
}
