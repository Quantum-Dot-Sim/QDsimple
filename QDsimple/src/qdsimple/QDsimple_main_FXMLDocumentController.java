/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qdsimple;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Finn.Kafka666
 * @author realsazzad
 */
public class QDsimple_main_FXMLDocumentController implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
