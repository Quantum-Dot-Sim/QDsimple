/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Finn.Kafka666
 * 
 * 
*/

package qdsimple;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.stream.IntStream;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;

public class QDsimple_main_FXMLDocumentController implements Initializable {
    
    // *************************************************************************************************
    // FXML Variables
    private SubScene workspace;
    @FXML
    private StackPane stackpane_main;
    @FXML
    private BorderPane borderpane_main;
    @FXML
    private AnchorPane anchorpaneleft_borderpane_main;
    @FXML
    private AnchorPane anchorpanedown_borderpane_main;
    @FXML
    private AnchorPane anchorpanecenter_borderpane_main;
    @FXML
    private AnchorPane anchorpaneright_borderpane_main;
    @FXML
    private ScrollPane scrollpane_anchorpaneright_borderpane_main;
    @FXML
    private VBox vbox_scrollpane;
    @FXML
    private VBox vbox_anchorpanecenter_borderpane_main;
    @FXML
    private MenuItem newworkspace_file_menubar_borderpane_main;
    @FXML
    private MenuItem closeworkspace_file_menubar_borderpane_main;
    @FXML
    private HBox hboxright_anchorpanedown_borderpane_main;
    @FXML
    private Label label1_hboxright_anchorpanedown_borderpane_main;
    @FXML
    private Label label2_hboxright_anchorpanedown_borderpane_main;
    @FXML
    private HBox hboxleft_anchorpanedown_borderpane_main;
    @FXML
    private JFXButton btn_base;
    @FXML
    private Label label_base;
    @FXML
    private JFXButton btn_stack;
    @FXML
    private Label label_stack;
    @FXML
    private JFXButton btn_topgate_circle;
    @FXML
    private JFXButton btn_topgate_rectangle;
    @FXML
    private JFXButton btn_topgate_polygon;
    @FXML
    private Label label_topgate_circle;
    @FXML
    private Label label_topgate_rectangle;
    @FXML
    private Label label_topgate_polygon;
    @FXML
    private Label label_message;
    Camera cam;
    double stackpane_main_h;
    double stackpane_main_w;
    Group group_axes_3D = new Group();
    Group group_device = new Group();
    Group group_main = new Group();
    int selected_shape_index = -1;
    double mouseOldX = 0;
    double mouseOldY = 0;
    double mousePosX = 0;
    double mousePosY = 0;
    double mousePosZ = 0;
    double mouseDeltaX = 0;
    double mouseDeltaY = 0;
    int orient_inversion;
    Translate t, p ,ip;
    static String addon_orientation = "";
    double boundary_proximity_limit = 0.01;
    double projection_transparency = 0.8;
    
    String text_style = "-fx-font-family: Roboto; -fx-font-size: 12; -fx-font-weight: lighter;"
                      + "-jfx-focus-color: #00897b; -jfx-unfocus-color: #006064; -jfx-label-float: true;";
    
    // *************************************************************************************************
    // Primitive Variables
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    // Dynamic Variables
    // *************************************************************************************************
    public JFXTextField textfield_1 = new JFXTextField(); 
    public JFXTextField textfield_2 = new JFXTextField(); 
    public JFXTextField textfield_3 = new JFXTextField();
    public JFXTextField textfield_4 = new JFXTextField();
    public JFXTextField textfield_5 = new JFXTextField();
    public JFXTextField textfield_6 = new JFXTextField();
    public JFXTextField textfield_7 = new JFXTextField();
    public JFXTextField textfield_8 = new JFXTextField();
    public JFXTextField textfield_9 = new JFXTextField();
    public JFXComboBox<String> combobox_1 = new JFXComboBox();
    public JFXComboBox<String> combobox_2 = new JFXComboBox();
    public JFXColorPicker color_picker = new JFXColorPicker();
    public JFXCheckBox checkbox_1 = new JFXCheckBox();
    public Label label_1;
    public JFXToggleButton toggle_1 = new JFXToggleButton();
    
    // *************************************************************************************************
    // Dynamic Variables
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    // *************************************************************************************************
    // Action oriented functions
    private void gate_move_f(Node node, boolean b){
        //Omnidirectional Rotation of the entire subscene
        t  = new Translate();
        p  = new Translate();
        ip = new Translate();
        Rotate rx = new Rotate();
        rx.setAxis(Rotate.X_AXIS); 
        Rotate ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS);
        Rotate rz = new Rotate();
        rz.setAxis(Rotate.Z_AXIS);
        Scale n = new Scale();
        
        node.getTransforms().addAll(t, p, rx, rz, ry, n, ip);
        
        stackpane_main.addEventHandler(MouseEvent.ANY, event -> {
            mousePosX = event.getX();
            mousePosY = event.getY();
        });
        
        if(b == true){
            stackpane_main.setOnMouseDragged((MouseEvent me) -> {
                
                // Get orientation value
                addon_orientation = combobox_2.getValue();
                
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                
                if(addon_orientation == "Up" || addon_orientation == "Down"){
                    
                    if (me.isAltDown() && me.isPrimaryButtonDown()) {
                        rz.setAngle(rz.getAngle() - mouseDeltaX);
                    }
                    else if (me.isPrimaryButtonDown()) {
                        t.setX(t.getX() + mouseDeltaX);
                        t.setY(t.getY() - mouseDeltaY);
                    }
                }
                
                if(addon_orientation == "Left" || addon_orientation == "Right"){
                    
                }
                
                if(addon_orientation == "Front" || addon_orientation == "Back"){
                    
                }
            });
        }
        else{
            stackpane_main.setOnScroll(null);
            stackpane_main.setOnMouseDragged(null);
            stackpane_main.removeEventHandler(MouseEvent.ANY, event -> {});
        }
    }
    
    
    private void shape_move_f(Node box, boolean b){
        
        //Omnidirectional Rotation of the entire subscene
        t  = new Translate();
        p  = new Translate();
        ip = new Translate();
        Rotate rx = new Rotate();
        rx.setAxis(Rotate.X_AXIS); 
        Rotate ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS);
        Rotate rz = new Rotate();
        rz.setAxis(Rotate.Z_AXIS);
        Scale n = new Scale();
        
        box.getTransforms().addAll(t, p, rx, rz, ry, n, ip);
        
        stackpane_main.addEventHandler(MouseEvent.ANY, event -> {
            mousePosX = event.getX();
            mousePosY = event.getY();
        });
        
        if(b == true){
            stackpane_main.setOnMouseDragged((MouseEvent me) -> {
                
                // Get orientation value
                if(combobox_2.getValue() == null) addon_orientation = "Any";
                else addon_orientation = combobox_2.getValue();
                
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                
                if(addon_orientation == "Up" || addon_orientation == "Down"){
                    // Reversing / Aligning the polarity of movement of objects
                    if(addon_orientation == "Down") orient_inversion = -1;
                    else orient_inversion = 1;
                    
                    if (me.isAltDown() && me.isPrimaryButtonDown()) {
                        
                        // If new shape is equal or smaller than selected shape
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getWidth()){
                            t.setX(t.getX() + mouseDeltaX);
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getDepth()){
                            t.setZ(t.getZ() - (orient_inversion * mouseDeltaY));
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                            }
                        }
                        
                        // If new shape is larger than selected shape...
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getWidth()){
                            t.setX(t.getX() + mouseDeltaX);
                            if(checkbox_1.isSelected()){
                                 if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getDepth()){
                            t.setZ(t.getZ() - (orient_inversion * mouseDeltaY));
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                            }
                        }
                    }
                }
                
                if(addon_orientation == "Left" || addon_orientation == "Right"){
                    // Reversing / Aligning the polarity of movement of objects
                    if(addon_orientation == "Right") orient_inversion = -1;
                    else orient_inversion = 1;
                    
                    if (me.isAltDown() && me.isPrimaryButtonDown()) {
                        
                        // If new shape is equal or smaller than selected shape
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getHeight()){
                            t.setY(t.getY() + mouseDeltaY);
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getDepth()){
                            t.setZ(t.getZ() - (orient_inversion * mouseDeltaX));
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                            }
                        }
                        
                        // If new shape is larger than selected shape...
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getHeight()){
                            t.setY(t.getY() + mouseDeltaY);
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getDepth()){
                            t.setZ(t.getZ() - (orient_inversion * mouseDeltaX));
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()){
                                    t.setZ(t.getZ() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()){
                                    t.setZ(t.getZ() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxZ() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()));
                                }
                            }
                        }
                    }
                }
                
                if(addon_orientation == "Front" || addon_orientation == "Back"){
                    // Reversing / Aligning the polarity of movement of objects
                    if(addon_orientation == "Back") orient_inversion = -1;
                    else orient_inversion = 1;
                    
                    if (me.isAltDown() && me.isPrimaryButtonDown()) {
                        
                        // If new shape is equal or smaller than selected shape
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getHeight()){
                            t.setY(t.getY() + mouseDeltaY);
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() >= group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getWidth()){
                            t.setX(t.getX() + (orient_inversion * mouseDeltaX));
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                            }
                        }
                        
                        // If new shape is larger than selected shape...
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getHeight()){
                            t.setY(t.getY() + mouseDeltaY);
                            if(checkbox_1.isSelected()){
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()){
                                    t.setY(t.getY() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()){
                                    t.setY(t.getY() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxY() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY()));
                                }
                            }
                        }
                        if(group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() < group_device.getChildren().get(group_device.getChildren().size() - 1).getLayoutBounds().getWidth()){
                            t.setX(t.getX() + (orient_inversion * mouseDeltaX));
                            if(checkbox_1.isSelected()){
                                 if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                            }
                            else{
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() < group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()){
                                    t.setX(t.getX() - Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX()));
                                }
                                if(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() > group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()){
                                    t.setX(t.getX() + Math.abs(group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMaxX() - group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX()));
                                }
                            }
                        }
                    }
                }
                
                else if(addon_orientation == "Any"){
                    if (me.isAltDown() && me.isPrimaryButtonDown()) {
                        t.setX(t.getX() + mouseDeltaX);
                        t.setY(t.getY() + mouseDeltaY);
                    }
                }
            });
            
            stackpane_main.setOnScroll((ScrollEvent me) -> {
                // Get orientation value
                if(combobox_2.isDisabled()) addon_orientation = "Any";
                else addon_orientation = combobox_2.getValue();
                
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                
                if(addon_orientation == "Any"){
                    t.setZ(t.getZ() + me.getDeltaY());
                }
            });
        }
        else{
            stackpane_main.setOnScroll(null);
            stackpane_main.setOnMouseDragged(null);
            stackpane_main.removeEventHandler(MouseEvent.ANY, event -> {});
        }
    }
    private void omnidirectional_move_f(Group group, boolean b){
        //Omnidirectional Rotation of the entire subscene
        t  = new Translate();
        p  = new Translate();
        ip = new Translate();
        Rotate rx = new Rotate();
        rx.setAxis(Rotate.X_AXIS); 
        Rotate ry = new Rotate();
        ry.setAxis(Rotate.Y_AXIS);
        Rotate rz = new Rotate();
        rz.setAxis(Rotate.Z_AXIS);
        Scale n = new Scale();
        
        group.getTransforms().addAll(t, p, rx, rz, ry, n, ip);
        
        stackpane_main.addEventHandler(MouseEvent.ANY, event -> {
            mousePosX = event.getX();
            mousePosY = event.getY();
        });
        
        if(b == true){
            stackpane_main.setOnMouseDragged((MouseEvent me) -> {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = mousePosX - mouseOldX;
                mouseDeltaY = mousePosY - mouseOldY;
                if (me.isAltDown() && me.isShiftDown() && me.isPrimaryButtonDown()) {
                    rz.setAngle(rz.getAngle() - mouseDeltaX);
                }
                else if (me.isAltDown() && me.isPrimaryButtonDown()) {
                    ry.setAngle(ry.getAngle() - mouseDeltaX);
                    rx.setAngle(rx.getAngle() + mouseDeltaY);
                }
                else if (me.isAltDown() && me.isSecondaryButtonDown()) {
                    double scale = n.getX();
                    double newScale = scale + mouseDeltaX * 0.01;
                    n.setX(newScale); n.setY(newScale); n.setZ(newScale);
                }
                else if (me.isAltDown() && me.isMiddleButtonDown()) {
                    t.setX(t.getX() + mouseDeltaX);
                    t.setY(t.getY() + mouseDeltaY);
                }
            });
        }
        else{
            stackpane_main.setOnMouseDragged(null);
            stackpane_main.removeEventHandler(MouseEvent.ANY, event -> {});
        }
    }
    @FXML
    private void activated_exit_file_menubar_borderpane_main_f(ActionEvent event) {
        QDsimple.get_current_instance_QDsimple().exit_program();
    }

    @FXML
    private void activated_about_help_menubar_borderpane_main_f(ActionEvent event) {
        // To blur the background when dialogue box pops in
        BoxBlur blur = new BoxBlur(3, 3, 3);
        
        JFXDialogLayout About_content = new JFXDialogLayout();
        About_content.setHeading(new ImageView("/images/QDsimple.png"));
        About_content.setBody(new Text("QDsimple is an open source quantum computing device simulation software, with which users will be able to design\n"
                                     + "quantum computing devices and run simulations on desired sub - domains of those devices.\n" 
                                     + "The project is under development by the group Artificial Indie Collaborations. To gain access to the source code,\n" 
                                     + "please contact AIC."));
        
        
        JFXDialog About_dialog = new JFXDialog(stackpane_main, About_content, JFXDialog.DialogTransition.CENTER);
        JFXButton About_button = new JFXButton("Okay");
        About_button.setStyle(" -fx-background-color: slateblue; -fx-text-fill: white; ");
        
        About_button.setOnAction((ActionEvent event1) -> {
            About_dialog.close();
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
    
    @FXML
    private void activated_newworkspace_file_menubar_borderpane_main_f(ActionEvent event) {

        // Alternating transitions in the left panel
        Timeline t = new Timeline();
        
        // Translate left anchorpane
        KeyValue kv_anchorpaneleft_borderpane_main = (anchorpaneleft_borderpane_main.getTranslateX() < 0.0) 
                                                   ? new KeyValue(anchorpaneleft_borderpane_main.translateXProperty(), anchorpaneleft_borderpane_main.getTranslateX() + anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                   : new KeyValue(anchorpaneleft_borderpane_main.translateXProperty(), anchorpaneleft_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
        KeyFrame kf_anchorpaneleft_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneleft_borderpane_main);
        
        // Fadeout Vbox from center anchorpane
        KeyValue kv_vbox_anchorpanecenter_borderpane_main = (vbox_anchorpanecenter_borderpane_main.getOpacity() == 1.0)
                                                          ? new KeyValue(vbox_anchorpanecenter_borderpane_main.opacityProperty(), 0.0, Interpolator.EASE_IN)
                                                          : new KeyValue(vbox_anchorpanecenter_borderpane_main.opacityProperty(), 1.0, Interpolator.EASE_IN);
        KeyFrame kf_vbox_anchorpanecenter_borderpane_main = new KeyFrame(Duration.millis(250), kv_vbox_anchorpanecenter_borderpane_main);
                
        t.getKeyFrames().addAll(kf_anchorpaneleft_borderpane_main, kf_vbox_anchorpanecenter_borderpane_main);
        
        // Enable / Disabling components upon opening / closing new workstation
        if(newworkspace_file_menubar_borderpane_main.isDisable() == true){
            newworkspace_file_menubar_borderpane_main.setDisable(false);
            closeworkspace_file_menubar_borderpane_main.setDisable(true);
            vbox_anchorpanecenter_borderpane_main.setDisable(false);
            hboxleft_anchorpanedown_borderpane_main.setDisable(true);
            hboxleft_anchorpanedown_borderpane_main.setVisible(false);
            disable_draw();
            
            // Remove background grid
            anchorpanecenter_borderpane_main.setStyle("-fx-background-image: none;");
            
            // Remove mouse position texts
            anchorpanecenter_borderpane_main.setOnMouseMoved(null);
            anchorpanecenter_borderpane_main.setOnMouseExited(null);
            label1_hboxright_anchorpanedown_borderpane_main.setText("");
            label2_hboxright_anchorpanedown_borderpane_main.setText("");
            t.play();
        }
        else{
            t.play();
            t.setOnFinished((ActionEvent event_2) -> {
                newworkspace_file_menubar_borderpane_main.setDisable(true);
                closeworkspace_file_menubar_borderpane_main.setDisable(false);
                vbox_anchorpanecenter_borderpane_main.setDisable(true);
                hboxleft_anchorpanedown_borderpane_main.setDisable(false);
                hboxleft_anchorpanedown_borderpane_main.setVisible(true);
                enable_draw();

                // Add background grid
                anchorpanecenter_borderpane_main.setStyle("-fx-background-image: url('/images/back_grid.png');");

                // Add mouse position texts
                anchorpanecenter_borderpane_main.setOnMouseMoved((MouseEvent mouseevent) -> {
                    label1_hboxright_anchorpanedown_borderpane_main.setText("|");
                    label2_hboxright_anchorpanedown_borderpane_main.setText("Pos (" + (int)mouseevent.getX() + ", " + (int)mouseevent.getY() + ")");
                });
                anchorpanecenter_borderpane_main.setOnMouseExited((MouseEvent mouseevent) -> {
                    label1_hboxright_anchorpanedown_borderpane_main.setText("|");
                    label2_hboxright_anchorpanedown_borderpane_main.setText("Pos (0, 0)");
                });
            });
        }
    }
    
    @FXML
    private void activated_x_rotate(ActionEvent event) {
        // Rotate Transition on X-axis
        Timeline t = new Timeline();
  
        group_main.setRotationAxis(Rotate.X_AXIS);
        
        // Rotate by X-axis
        KeyValue kv = new KeyValue(group_main.rotateProperty(), group_main.getRotate() + 90.0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
                
        t.getKeyFrames().addAll(kf, kf);
        t.play();
    }

    @FXML
    private void activated_y_rotate(ActionEvent event) {
        // Rotate Transition on X-axis
        Timeline t = new Timeline();
  
        group_main.setRotationAxis(Rotate.Y_AXIS);
        
        // Rotate by X-axis
        KeyValue kv = new KeyValue(group_main.rotateProperty(), group_main.getRotate() + 90.0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
                
        t.getKeyFrames().addAll(kf, kf);
        t.play();
    }

    @FXML
    private void activated_z_rotate(ActionEvent event) {
        
        Timeline t = new Timeline();
  
        group_main.setRotationAxis(Rotate.Z_AXIS);
        
        // Rotate by X-axis
        KeyValue kv = new KeyValue(group_main.rotateProperty(), group_main.getRotate() + 90.0, Interpolator.EASE_BOTH);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
                
        t.getKeyFrames().addAll(kf, kf);
        t.play();
    }
    
    @FXML
    private void activated_create_base_f(ActionEvent event) {
        
        base_material_property_input_init();
        
        // Transition in right anchor pane
        Timeline t = new Timeline();
        
        // Translate right anchorpane
        KeyValue kv_anchorpaneright_borderpane_main = (anchorpaneright_borderpane_main.getTranslateX() != 0.0) 
                                                   ? new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                   : new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() + anchorpaneright_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
        KeyFrame kf_anchorpaneright_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneright_borderpane_main);
                
        t.getKeyFrames().addAll(kf_anchorpaneright_borderpane_main);
        t.play();
        
        // Set default values to box
        PhongMaterial box_col = new PhongMaterial();
        Box new_b = new Box(0, 0, 0);
        new_b.setMaterial(box_col);
        
        if(anchorpaneright_borderpane_main.getTranslateX() != 0.0){
            
            group_device.getChildren().add(new_b);
            // Disabling maneuverability of ALL shapes
            omnidirectional_move_f(group_main, false);
            shape_move_f(new_b, true);
            
            stackpane_main.addEventHandler(InputEvent.ANY, in_event -> {
                if(!textfield_3.getText().isEmpty() && !textfield_2.getText().isEmpty() && !textfield_4.getText().isEmpty()){

                    // Set values
                    box_col.setDiffuseColor(Color.web(color_picker.getValue().toString(), projection_transparency));
                    new_b.setWidth(Double.parseDouble(textfield_3.getText()));
                    new_b.setHeight(Double.parseDouble(textfield_2.getText()));
                    new_b.setDepth(Double.parseDouble(textfield_4.getText()));
                    new_b.setMaterial(box_col);
                }
                else{
                    // Reset values
                    box_col.setDiffuseColor(null);
                    new_b.setWidth(0);
                    new_b.setHeight(0);
                    new_b.setDepth(0);
                    new_b.setMaterial(box_col);
                }  
            });
        }
        else{
            group_device.getChildren().remove(group_device.getChildren().size() - 1);
            stackpane_main.removeEventHandler(InputEvent.ANY, in_event -> {});
            // Disabling maneuverability of ALL shapes
            shape_move_f(new_b, false);
            omnidirectional_move_f(group_main, true);
        }
    }

    @FXML
    private void activated_add_stack_f(ActionEvent event) {
        
        group_device.setOnMousePressed((MouseEvent me) -> {
            // Select the ID of the shape clicked upon
            selected_shape_index = group_device.getChildren().indexOf(me.getTarget());
            label_message.setText("Selected Shape ID: " + selected_shape_index);
            activated_add_stack_f(event);
        });
        
        
        if(selected_shape_index == -1){
            JFXDialogLayout pick_material = new JFXDialogLayout();
            pick_material.setHeading(new ImageView("/images/pick_shape.png"));
            Text t = new Text("Please click on a component to select where the new layer will stack.");
            t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px;");
            pick_material.setBody(t);

            JFXDialog pick_material_dialog = new JFXDialog(stackpane_main, pick_material, JFXDialog.DialogTransition.BOTTOM);
            JFXButton pick_material_button = new JFXButton("Okay");
            pick_material_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

            pick_material_button.setOnAction((ActionEvent event1) -> {
                pick_material_dialog.close();
            });

            // set the contents within and display
            pick_material.setActions(pick_material_button);
            pick_material_dialog.show();
        }
        else{
            stack_material_property_input_init();
            group_device.setOnMousePressed(null);
            // Transition in right anchor pane
            Timeline t = new Timeline();

            // Translate right anchorpane
            KeyValue kv_anchorpaneright_borderpane_main = (anchorpaneright_borderpane_main.getTranslateX() != 0.0) 
                                                       ? new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                       : new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() + anchorpaneright_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
            KeyFrame kf_anchorpaneright_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneright_borderpane_main);

            t.getKeyFrames().addAll(kf_anchorpaneright_borderpane_main);
            t.play();
            
            // Set default values to box
            PhongMaterial box_col = new PhongMaterial();
            Box new_b = new Box(0, 0, 0);
            new_b.setMaterial(box_col);
            new_b.getTransforms().addAll(group_device.getChildren().get(selected_shape_index).getLocalToParentTransform());
            
            if(anchorpaneright_borderpane_main.getTranslateX() != 0.0){               
                // Stack shape
                group_device.getChildren().add(new_b);
                // Disabling maneuverability of ALL shapes
                omnidirectional_move_f(group_main, false);
                shape_move_f(new_b, true);

                stackpane_main.addEventHandler(InputEvent.ANY, in_event -> {
                    if(!textfield_3.getText().isEmpty() && !textfield_2.getText().isEmpty() && !textfield_4.getText().isEmpty() && combobox_2.getValue() != null){
                        
                        // Set values
                        box_col.setDiffuseColor(Color.web(color_picker.getValue().toString(), projection_transparency));
                        new_b.setWidth(Double.parseDouble(textfield_3.getText()));
                        new_b.setHeight(Double.parseDouble(textfield_2.getText()));
                        new_b.setDepth(Double.parseDouble(textfield_4.getText()));
                        new_b.setMaterial(box_col);
                        
                        // Adjust translation
                        if(combobox_2.getValue() == "Up" && selected_shape_index!= -1){
                            new_b.setTranslateY(- boundary_proximity_limit - ((new_b.getHeight() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
                        }
                        else if(combobox_2.getValue() == "Down" && selected_shape_index!= -1){
                            new_b.setTranslateY(boundary_proximity_limit + ((new_b.getHeight() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
                        }
                        else if(combobox_2.getValue() == "Left" && selected_shape_index!= -1){
                            new_b.setTranslateX(- boundary_proximity_limit - ((new_b.getWidth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
                        }
                        else if(combobox_2.getValue() == "Right" && selected_shape_index!= -1){
                            new_b.setTranslateX(boundary_proximity_limit + ((new_b.getWidth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
                        }
                        else if(combobox_2.getValue() == "Front" && selected_shape_index!= -1){
                            new_b.setTranslateZ( - boundary_proximity_limit - ((new_b.getDepth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
                        }
                        else if(combobox_2.getValue() == "Back" && selected_shape_index!= -1){
                            new_b.setTranslateZ( boundary_proximity_limit + ((new_b.getDepth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
                        }
                    }
                    else{

                        // Reset values
                        box_col.setDiffuseColor(null);
                        new_b.setWidth(0);
                        new_b.setHeight(0);
                        new_b.setDepth(0);
                        new_b.setMaterial(box_col);
                    }  
                });
            }
            else{            
                group_device.getChildren().remove(group_device.getChildren().size() - 1);
                stackpane_main.removeEventHandler(InputEvent.ANY, in_event -> {});
                // Disabling maneuverability of ALL shapes
                selected_shape_index = -1;
                label_message.setText("");
                shape_move_f(new_b, false);
                omnidirectional_move_f(group_main, true);
            }
        }
    }
    
    @FXML
    private void activated_topgate_circle(ActionEvent event) {
        
        group_device.setOnMousePressed((MouseEvent me) -> {
            // Select the ID of the shape clicked upon
            selected_shape_index = group_device.getChildren().indexOf(me.getTarget());
            label_message.setText("Selected Shape ID: " + selected_shape_index);
            activated_topgate_circle(event);
        });
        
        
        if(selected_shape_index == -1){
            JFXDialogLayout pick_material = new JFXDialogLayout();
            pick_material.setHeading(new ImageView("/images/pick_shape.png"));
            Text t = new Text("Please click on a component to select where the new layer will stack.");
            t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px;");
            pick_material.setBody(t);

            JFXDialog pick_material_dialog = new JFXDialog(stackpane_main, pick_material, JFXDialog.DialogTransition.BOTTOM);
            JFXButton pick_material_button = new JFXButton("Okay");
            pick_material_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

            pick_material_button.setOnAction((ActionEvent event1) -> {
                pick_material_dialog.close();
            });

            // set the contents within and display
            pick_material.setActions(pick_material_button);
            pick_material_dialog.show();
        }
        else{
            group_device.setOnMousePressed(null);
            // Transition in right anchor pane
            Timeline t = new Timeline();

            // Translate right anchorpane
            KeyValue kv_anchorpaneright_borderpane_main = (anchorpaneright_borderpane_main.getTranslateX() != 0.0) 
                                                       ? new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                       : new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() + anchorpaneright_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
            KeyFrame kf_anchorpaneright_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneright_borderpane_main);

            t.getKeyFrames().addAll(kf_anchorpaneright_borderpane_main);
            t.play();
            
            // Set default values to box
            Circle new_b = new Circle(0, 0, 0);
            //new_b.getTransforms().addAll(group_device.getChildren().get(selected_shape_index).getLocalToParentTransform());
            
            if(anchorpaneright_borderpane_main.getTranslateX() != 0.0){
                combobox_2.setDisable(false);
                combobox_2.setVisible(true);
                checkbox_1.setDisable(false);
                checkbox_1.setVisible(true);
                
                // Stack shape
                group_device.getChildren().add(new_b);
                // Disabling maneuverability of ALL shapes
                omnidirectional_move_f(group_main, false);
                gate_move_f(new_b, true);

                stackpane_main.addEventHandler(InputEvent.ANY, in_event -> {
                    if(!textfield_3.getText().isEmpty() && combobox_2.getValue() != null){
                        
                        // Set values
                        new_b.setRadius(Double.parseDouble(textfield_3.getText()));
                        new_b.setFill(color_picker.getValue());
                        
                        
                        // Adjust translation
                        if(combobox_2.getValue() == "Up" && selected_shape_index!= -1){
                            new_b.setRotationAxis(Rotate.X_AXIS);
                            new_b.setRotate(90);
                            new_b.setTranslateY(- boundary_proximity_limit  + group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY());
                        }
//                        else if(combobox_2.getValue() == "Down" && selected_shape_index!= -1){
//                            new_b.setTranslateY(boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Left" && selected_shape_index!= -1){
//                            new_b.setTranslateX(- boundary_proximity_limit - ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Right" && selected_shape_index!= -1){
//                            new_b.setTranslateX(boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Front" && selected_shape_index!= -1){
//                            new_b.setTranslateZ( - boundary_proximity_limit - ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Back" && selected_shape_index!= -1){
//                            new_b.setTranslateZ( boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
//                        }
                    }
                    else{

                        // Reset values
                        new_b.setRadius(0);
                        new_b.setFill(null);
                    }  
                });
            }
            else{
                combobox_2.setDisable(true);
                combobox_2.setVisible(false);
                checkbox_1.setDisable(true);
                checkbox_1.setVisible(false);
                
                group_device.getChildren().remove(group_device.getChildren().size() - 1);
                stackpane_main.removeEventHandler(InputEvent.ANY, in_event -> {});
                // Disabling maneuverability of ALL shapes
                selected_shape_index = -1;
                label_message.setText("");
                gate_move_f(new_b, false);
                omnidirectional_move_f(group_main, true);
            }
        }
    }

    @FXML
    private void activated_topgate_rectangle(ActionEvent event) {
        group_device.setOnMousePressed((MouseEvent me) -> {
            // Select the ID of the shape clicked upon
            selected_shape_index = group_device.getChildren().indexOf(me.getTarget());
            label_message.setText("Selected Shape ID: " + selected_shape_index);
            activated_topgate_rectangle(event);
        });
        
        
        if(selected_shape_index == -1){
            JFXDialogLayout pick_material = new JFXDialogLayout();
            pick_material.setHeading(new ImageView("/images/pick_shape.png"));
            Text t = new Text("Please click on a component to select where the new layer will stack.");
            t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px;");
            pick_material.setBody(t);

            JFXDialog pick_material_dialog = new JFXDialog(stackpane_main, pick_material, JFXDialog.DialogTransition.BOTTOM);
            JFXButton pick_material_button = new JFXButton("Okay");
            pick_material_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

            pick_material_button.setOnAction((ActionEvent event1) -> {
                pick_material_dialog.close();
            });

            // set the contents within and display
            pick_material.setActions(pick_material_button);
            pick_material_dialog.show();
        }
        else{
            group_device.setOnMousePressed(null);
            // Transition in right anchor pane
            Timeline t = new Timeline();

            // Translate right anchorpane
            KeyValue kv_anchorpaneright_borderpane_main = (anchorpaneright_borderpane_main.getTranslateX() != 0.0) 
                                                       ? new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                       : new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() + anchorpaneright_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
            KeyFrame kf_anchorpaneright_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneright_borderpane_main);

            t.getKeyFrames().addAll(kf_anchorpaneright_borderpane_main);
            t.play();
            
            // Set default values to box
            Rectangle new_b = new Rectangle(0, 0, 0, 0);
            new_b.getTransforms().addAll(group_device.getChildren().get(selected_shape_index).getLocalToParentTransform());
            
            if(anchorpaneright_borderpane_main.getTranslateX() != 0.0){
                combobox_2.setDisable(false);
                combobox_2.setVisible(true);
                checkbox_1.setDisable(false);
                checkbox_1.setVisible(true);
                
                // Stack shape
                group_device.getChildren().add(new_b);
                // Disabling maneuverability of ALL shapes
                omnidirectional_move_f(group_main, false);
                gate_move_f(new_b, true);

                stackpane_main.addEventHandler(InputEvent.ANY, in_event -> {
                    if(!textfield_3.getText().isEmpty() && !textfield_2.getText().isEmpty() && combobox_2.getValue() != null){
                        
                        // Set values
                        new_b.setHeight(Double.parseDouble(textfield_2.getText()));
                        new_b.setWidth(Double.parseDouble(textfield_2.getText()));
                        new_b.setFill(color_picker.getValue());
                        
                        // Adjust translation
                        if(combobox_2.getValue() == "Up" && selected_shape_index!= -1){
                            new_b.setRotationAxis(Rotate.X_AXIS);
                            new_b.setRotate(90);
                            new_b.setTranslateY(- boundary_proximity_limit  + (group_device.getChildren().get(selected_shape_index).getBoundsInParent().getMinY() - new_b.getBoundsInParent().getMaxY()));
                        }
//                        else if(combobox_2.getValue() == "Down" && selected_shape_index!= -1){
//                            new_b.setTranslateY(boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Left" && selected_shape_index!= -1){
//                            new_b.setTranslateX(- boundary_proximity_limit - ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Right" && selected_shape_index!= -1){
//                            new_b.setTranslateX(boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Front" && selected_shape_index!= -1){
//                            new_b.setTranslateZ( - boundary_proximity_limit - ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
//                        }
//                        else if(combobox_2.getValue() == "Back" && selected_shape_index!= -1){
//                            new_b.setTranslateZ( boundary_proximity_limit + ((new_b.getRadius() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
//                        }
                    }
                    else{

                        // Reset values
                        new_b.setHeight(0);
                        new_b.setWidth(0);
                        new_b.setFill(null);
                    }  
                });
            }
            else{
                combobox_2.setDisable(true);
                combobox_2.setVisible(false);
                checkbox_1.setDisable(true);
                checkbox_1.setVisible(false);
                
                group_device.getChildren().remove(group_device.getChildren().size() - 1);
                stackpane_main.removeEventHandler(InputEvent.ANY, in_event -> {});
                // Disabling maneuverability of ALL shapes
                selected_shape_index = -1;
                label_message.setText("");
                gate_move_f(new_b, false);
                omnidirectional_move_f(group_main, true);
            }
        }
    }

    @FXML
    private void activated_topgate_polygon(ActionEvent event) {
        
    }
    
    private void confirmed_add_base_f(ActionEvent event) {
        // Create Layer
        PhongMaterial box_col = new PhongMaterial();
        box_col.setDiffuseColor(Color.web(color_picker.getValue().toString()));
        Box new_b = new Box(Double.parseDouble(textfield_3.getText()), Double.parseDouble(textfield_2.getText()), Double.parseDouble(textfield_4.getText()));
        new_b.setMaterial(box_col);
        new_b.getTransforms().addAll(group_device.getChildren().get(group_device.getChildren().size() - 1).getLocalToParentTransform());

        // Remove projected shapes
        activated_cancel_f(event);
        
        group_device.getChildren().add(new_b);
        
        // Save shape property
        DecimalFormat df2 = new DecimalFormat("#.##");
        
        String DOTin = "Material\n\t{\n\t\tname = " + combobox_1.getValue() + "\n\t\ttag = " + textfield_1.getText() + "\n\t\tx = " + textfield_5.getText()
                           + "\n\t\tcrystal_structure = " + textfield_6.getText() + "\n\t\tdoping type = " + toggle_1.getText() + "\n\t\tdoping_density = " + textfield_7.getText()
                           + "\n\t\tdoping_ionization_model = " + textfield_8.getText() + "\n\t}";
        DOTin += "\n ~Region\n\t\t{\n\t\t\tshape = cuboid\n\t\t\tregion_number = " + (group_device.getChildren().size() - 1) + "\n\t\t\t"
                   + "priority = " + (group_device.getChildren().size() - 1) + "\n\t\t\tmin = (" + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()) + ")\n\t\t\t"
                   + "max = (" + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()) + ")\n\t\t}";
        
        group_device.getChildren().get(group_device.getChildren().size() - 1).setUserData(DOTin);
        
        // Disable add base option
        btn_base.setDisable(true);
        label_base.setDisable(true);
        btn_stack.setDisable(false);
        label_stack.setDisable(false);
        btn_topgate_circle.setDisable(false);
        label_topgate_circle.setDisable(false);
        btn_topgate_rectangle.setDisable(false);
        label_topgate_rectangle.setDisable(false);
        btn_topgate_polygon.setDisable(false);
        label_topgate_polygon.setDisable(false);
    }
    
    private void confirmed_stack_f(ActionEvent event) {
        // Create Layer
        PhongMaterial box_col = new PhongMaterial();
        box_col.setDiffuseColor(Color.web(color_picker.getValue().toString()));
        Box new_b = new Box(Double.parseDouble(textfield_3.getText()), Double.parseDouble(textfield_2.getText()), Double.parseDouble(textfield_4.getText()));
        new_b.setMaterial(box_col);
        new_b.getTransforms().addAll(group_device.getChildren().get(group_device.getChildren().size() - 1).getLocalToParentTransform());
        
        // Ensure Physical Bound validity
        boolean b = true;
        if(group_device.getChildren().size() > 1){
            double shape_old_minX = 0;
            double shape_old_minY = 0;
            double shape_old_minZ = 0;
            double shape_old_maxX = 0;
            double shape_old_maxY = 0;
            double shape_old_maxZ = 0;
            double shape_new_minX = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX();
            double shape_new_minY = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY();
            double shape_new_minZ = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ();
            double shape_new_maxX = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX();
            double shape_new_maxY = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY();
            double shape_new_maxZ = group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ();
            
            for(int i = 0; i < group_device.getChildren().size() - 1; i++){
                shape_old_minX = group_device.getChildren().get(i).getBoundsInParent().getMinX();
                shape_old_minY = group_device.getChildren().get(i).getBoundsInParent().getMinY();
                shape_old_minZ = group_device.getChildren().get(i).getBoundsInParent().getMinZ();
                shape_old_maxX = group_device.getChildren().get(i).getBoundsInParent().getMaxX();
                shape_old_maxY = group_device.getChildren().get(i).getBoundsInParent().getMaxY();
                shape_old_maxZ = group_device.getChildren().get(i).getBoundsInParent().getMaxZ();
                
                // Identify boundary violation for X coordinates
                if((shape_old_minX <= shape_new_minX && shape_new_minX <= shape_old_maxX) || (shape_old_minX <= shape_new_maxX && shape_new_maxX <= shape_old_maxX) || (shape_new_minX <= shape_old_minX && shape_old_minX <= shape_new_maxX) || (shape_new_minX <= shape_old_maxX && shape_old_maxX <= shape_new_maxX)){
                    // Identify boundary violation for Y coordinates
                    if((shape_old_minY <= shape_new_minY && shape_new_minY <= shape_old_maxY) || (shape_old_minY <= shape_new_maxY && shape_new_maxY <= shape_old_maxY) || (shape_new_minY <= shape_old_minY && shape_old_minY <= shape_new_maxY) || (shape_new_minY <= shape_old_maxY && shape_old_maxY <= shape_new_maxY)){
                        // Identify boundary violation for Z coordinates
                        if((shape_old_minZ <= shape_new_minZ && shape_new_minZ <= shape_old_maxZ) || (shape_old_minZ <= shape_new_maxZ && shape_new_maxZ <= shape_old_maxZ) || (shape_new_minZ <= shape_old_minZ && shape_old_minZ <= shape_new_maxZ) || (shape_new_minZ <= shape_old_maxZ && shape_old_maxZ <= shape_new_maxZ)){
                            // If all three coordinates are violating bounds. it means shapes are overlapped
                            b = false;
                            break;
                        }
                    }
                }
            }
        }
        
        // Remove projected shapes
        activated_cancel_f(event);

        if(b){
            group_device.getChildren().add(new_b);
            
            // Save shape property
            DecimalFormat df2 = new DecimalFormat("#.##");

            String DOTin = "Material\n\t{\n\t\tname = " + combobox_1.getValue() + "\n\t\ttag = " + textfield_1.getText() + "\n\t\tx = " + textfield_5.getText()
                           + "\n\t\tcrystal_structure = " + textfield_6.getText() + "\n\t\tdoping type = " + toggle_1.getText() + "\n\t\tdoping_density = " + textfield_7.getText()
                           + "\n\t\tdoping_ionization_model = " + textfield_8.getText() + "\n\t}";
            DOTin += "\n ~Region\n\t\t{\n\t\t\tshape = cuboid\n\t\t\tregion_number = " + (group_device.getChildren().size() - 1) + "\n\t\t\t"
                   + "priority = " + (group_device.getChildren().size() - 1) + "\n\t\t\tmin = (" + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinX())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinY())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMinZ()) + ")\n\t\t\t"
                   + "max = (" + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxX())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxY())
                   + ", " + df2.format(group_device.getChildren().get(group_device.getChildren().size() - 1).getBoundsInParent().getMaxZ()) + ")\n\t\t}";

            group_device.getChildren().get(group_device.getChildren().size() - 1).setUserData(DOTin);
        }
        else{
            JFXDialogLayout boundary_violation = new JFXDialogLayout();
            boundary_violation.setHeading(new ImageView("/images/red_cross.png"));
            Text t = new Text("New component is intersecting another existing component.\nPlease make sure the shapes do not intersect by changing new layer volume or orientation.");
            t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px; -fx-text-fill: #c62828;");
            boundary_violation.setBody(t);

            JFXDialog boundary_violation_dialog = new JFXDialog(stackpane_main, boundary_violation, JFXDialog.DialogTransition.BOTTOM);
            JFXButton boundary_violation_button = new JFXButton("Okay");
            boundary_violation_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

            boundary_violation_button.setOnAction((ActionEvent event1) -> {
                boundary_violation_dialog.close();
            });

            // set the contents within and display
            boundary_violation.setActions(boundary_violation_button);
            boundary_violation_dialog.show();
        }
    }
    
    private void activated_cancel_f(ActionEvent event) {
        //Resetting variables
        selected_shape_index = -1;
        label_message.setText("");
        
        // Enabling omnidirectional Movement
        shape_move_f(group_device.getChildren().get(group_device.getChildren().size() - 1), false);
        omnidirectional_move_f(group_main, true);
        
        // Removing projections
        //if(group_device.getChildren().get(group_device.getChildren().size() - 1).getClass().toString().equals("class javafx.scene.shape.Box")){
        group_device.getChildren().remove(group_device.getChildren().size() - 1);
        
        // Transition in right anchor pane
        Timeline t = new Timeline();
        
        // Translate right anchorpane
        KeyValue kv_anchorpaneright_borderpane_main = (anchorpaneright_borderpane_main.getTranslateX() != 0.0) 
                                                   ? new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() - anchorpaneleft_borderpane_main.getPrefWidth(), Interpolator.EASE_OUT)
                                                   : new KeyValue(anchorpaneright_borderpane_main.translateXProperty(), anchorpaneright_borderpane_main.getTranslateX() + anchorpaneright_borderpane_main.getPrefWidth(), Interpolator.EASE_IN);
        KeyFrame kf_anchorpaneright_borderpane_main = new KeyFrame(Duration.millis(250), kv_anchorpaneright_borderpane_main);
                
        t.getKeyFrames().addAll(kf_anchorpaneright_borderpane_main);
        t.play();
    }
    
    @FXML
    private void save_structure_file_f(ActionEvent event) {
        Text t = new Text();
        
        String struct = "Structure\n{";
        String geometry = "\n\tGeometry\n\t{";
        int k = 0, l = 0;
        int[] dupe_check = new int[group_device.getChildren().size()];
        //initialize array with invalid values
        for(int f = 0; f <= l; f++){
            dupe_check[f] = -1;
        }
        // To identify similar regions
        String r = "\tregions = (";
        for(int i = 0; i < group_device.getChildren().size(); i++){
            for(k = 0; k < group_device.getChildren().get(i).getUserData().toString().length(); k++){
                if(group_device.getChildren().get(i).getUserData().toString().charAt(k) == '~'){
                    k++;
                    break;
                }
            }
            for(int j = i; j < group_device.getChildren().size(); j++){
                if(group_device.getChildren().get(i).getUserData().toString().substring(0, k-2).equals(group_device.getChildren().get(j).getUserData().toString().substring(0, k-2))){
                    boolean b = false;
                    for(int f = 0; f <= l; f++){
                        if(j == dupe_check[f]){
                            b = true; 
                            break;
                        }
                    }
                    if(b == false){
                        dupe_check[l] = j;
                        l++;
                        r += j + ", ";
                    }
                }
                if(j == group_device.getChildren().size()- 1){
                    if(r.charAt(r.length()-1) == ' '){
                        struct += "\n\n\t" + group_device.getChildren().get(i).getUserData().toString().substring(0, k-4) + r.substring(0, r.length()-2) + ")\n\t";
                    }
                    r = "\tregions = (";
                }
            }
            geometry += "\n\n\t\t" + group_device.getChildren().get(i).getUserData().toString().substring(k, group_device.getChildren().get(i).getUserData().toString().length());
        }
        struct += "}\n" + geometry + "\n\n\t}\n\n}";
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Structure File (*.in)", "*.in");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stackpane_main.getScene().getWindow());
        if (file != null) {
            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.print(struct);
                writer.close();
            } catch (IOException ex) {

            }
        }
    }
    
    // Action oriented functions
    // *************************************************************************************************  
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
        
    // *************************************************************************************************
    // Main functions                  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initializer function & variable calls
        resize_listener_f();
        translator_f();  
    }
    // Main functions
    // ************************************************************************************************* 
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // *************************************************************************************************
    // Initializer functions
    private void base_material_property_input_init(){
        // Material Visual Parameters
        Label l_1 = new Label("Set Visual Parameters");
        l_1.setStyle("-fx-font-family: Roboto; -fx-font-size: 15; -fx-font-weight: bold; -fx-border-color: #ffffff; -fx-border-width: 0px 0px 2px 0px;");
        l_1.setTextFill(Color.web("#00897b"));
        // Material Tag
        textfield_1.setStyle(text_style);
        textfield_1.setPromptText("Tag");
        textfield_1.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Populating JFXComboBox material type
        combobox_1.getItems().clear();
        combobox_1.setStyle(text_style);
        combobox_1.setPromptText("Material Type");
        combobox_1.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        combobox_1.getItems().add("Si");
        combobox_1.getItems().add("Ge");
        combobox_1.getItems().add("Al");
        combobox_1.getItems().add("SiGe");
        combobox_1.getItems().add("GaAs");
        combobox_1.getItems().add("AL2O3");
        combobox_1.getItems().add("SiO2");
        // Material Width
        textfield_2.setStyle(text_style);
        textfield_2.setPromptText("Height (i.e. 1nm - 1000nm)");
        textfield_2.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Height
        textfield_3.setStyle(text_style);
        textfield_3.setPromptText("Width (i.e. 1nm - 1000nm)");
        textfield_3.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Depth
        textfield_4.setStyle(text_style);
        textfield_4.setPromptText("Depth (i.e. 1nm - 1000nm)");
        textfield_4.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Color
        Label l_2 = new Label("Material Color");
        l_2.setStyle(text_style);
        l_2.setTextFill(Color.web("#00897b"));
        color_picker.setTranslateY(-10);
        // Material Physical Parameters
        Label l_3 = new Label("Set Physical Parameters");
        l_3.setStyle("-fx-font-family: Roboto; -fx-font-size: 15; -fx-font-weight: bold; -fx-border-color: #ffffff; -fx-border-width: 0px 0px 2px 0px;");
        l_3.setTextFill(Color.web("#00897b"));
        // Material Band Gap
        textfield_5.setStyle(text_style);
        textfield_5.setPromptText("Band Gap");
        textfield_5.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Crystal Structure
        textfield_6.setStyle(text_style);
        textfield_6.setPromptText("Crystal Structure");
        textfield_6.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Doping Type
        Label l_4 = new Label("Doping Type");
        l_4.setStyle(text_style);
        l_4.setTextFill(Color.web("#00897b"));
        toggle_1.setStyle(text_style);
        toggle_1.setText("P");
        toggle_1.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(toggle_1.getText() == "P") toggle_1.setText("N");
            else toggle_1.setText("P");
        }));
        toggle_1.setPadding(new Insets(-30, 0, -20, -5));
        // Material Doping Density
        textfield_7.setStyle(text_style);
        textfield_7.setPromptText("Doping Density");
        textfield_7.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Doping Ionization Model
        textfield_8.setStyle(text_style);
        textfield_8.setPromptText("Doping Ionization Model");
        textfield_8.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Okay Button
        JFXButton okay_button = new JFXButton("Okay");
        okay_button.setStyle("-fx-background-color: #558b2f; -jfx-button-type: RAISED; -fx-text-fill: #ffffff; -fx-background-radius: 10px;");
        okay_button.setRipplerFill(Color.web("#ffffff"));
        okay_button.setMinSize(75, 25);
        okay_button.setTranslateY(20);
        okay_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if(!textfield_1.getText().isEmpty() && combobox_1.getValue() != null && !textfield_2.getText().isEmpty() && !textfield_3.getText().isEmpty() &&
                    !textfield_4.getText().isEmpty() && !textfield_5.getText().isEmpty() && !textfield_6.getText().isEmpty() && !textfield_7.getText().isEmpty() &&
                    !textfield_8.getText().isEmpty()){
                    confirmed_add_base_f(event);
                }
                else{
                    JFXDialogLayout input_empty = new JFXDialogLayout();
                    input_empty.setHeading(new ImageView("/images/empty.png"));
                    Text t = new Text("Some of the required text fields are empty.\nPlease fill them out accordingly.");
                    t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px; -fx-text-fill: #c62828;");
                    input_empty.setBody(t);

                    JFXDialog input_empty_dialog = new JFXDialog(stackpane_main, input_empty, JFXDialog.DialogTransition.BOTTOM);
                    JFXButton input_empty_button = new JFXButton("Okay");
                    input_empty_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

                    input_empty_button.setOnAction((ActionEvent event1) -> {
                        input_empty_dialog.close();
                    });

                    // set the contents within and display
                    input_empty.setActions(input_empty_button);
                    input_empty_dialog.show();
                }
            }
        });
        // Cancel Button
        JFXButton cancel_button = new JFXButton("Cancel");
        cancel_button.setStyle("-fx-background-color: #f44336; -jfx-button-type: RAISED; -fx-text-fill: #ffffff; -fx-background-radius: 10px;");
        cancel_button.setRipplerFill(Color.web("#ffffff"));
        cancel_button.setMinSize(75, 25);
        cancel_button.setTranslateY(20);
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                activated_cancel_f(event);
            }
        });

        vbox_scrollpane.setSpacing(20);
        vbox_scrollpane.setPadding(new Insets(20, 0, 40, 12));
        vbox_scrollpane.getChildren().clear();
        vbox_scrollpane.getChildren().addAll(l_1, textfield_1, combobox_1, textfield_2, textfield_3, textfield_4, l_2,
                                            color_picker, l_3, textfield_5, textfield_6, l_4, toggle_1,  textfield_7, textfield_8, okay_button, cancel_button);
    }
    
    private void stack_material_property_input_init(){
        // Material Visual Parameters
        Label l_1 = new Label("Set Visual Parameters");
        l_1.setStyle("-fx-font-family: Roboto; -fx-font-size: 15; -fx-font-weight: bold; -fx-border-color: #ffffff; -fx-border-width: 0px 0px 2px 0px;");
        l_1.setTextFill(Color.web("#00897b"));
        // Material Tag
        textfield_1.setStyle(text_style);
        textfield_1.setPromptText("Tag");
        textfield_1.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Populating JFXComboBox material type
        combobox_1.getItems().clear();
        combobox_1.setStyle(text_style);
        combobox_1.setPromptText("Material Type");
        combobox_1.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        combobox_1.getItems().add("Si");
        combobox_1.getItems().add("Ge");
        combobox_1.getItems().add("Al");
        combobox_1.getItems().add("SiGe");
        combobox_1.getItems().add("GaAs");
        combobox_1.getItems().add("AL2O3");
        combobox_1.getItems().add("SiO2");
        // Material Width
        textfield_2.setStyle(text_style);
        textfield_2.setPromptText("Height (i.e. 1nm - 1000nm)");
        textfield_2.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Height
        textfield_3.setStyle(text_style);
        textfield_3.setPromptText("Width (i.e. 1nm - 1000nm)");
        textfield_3.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Depth
        textfield_4.setStyle(text_style);
        textfield_4.setPromptText("Depth (i.e. 1nm - 1000nm)");
        textfield_4.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Color
        Label l_2 = new Label("Material Color");
        l_2.setStyle(text_style);
        l_2.setTextFill(Color.web("#00897b"));
        color_picker.setTranslateY(-10);
        // Populating JFXComboBox material stack orientation
        combobox_2.getItems().clear();
        combobox_2.setStyle(text_style);
        combobox_2.setPromptText("Stack Orientation");
        combobox_2.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        combobox_2.getItems().add("Up");
        combobox_2.getItems().add("Down"); 
        combobox_2.getItems().add("Left");
        combobox_2.getItems().add("Right"); 
        combobox_2.getItems().add("Front");
        combobox_2.getItems().add("Back");
        // Boundary limit checkbox
        checkbox_1.setStyle("-fx-font-family: Roboto; -fx-font-size: 12; -fx-font-weight: lighter;"
                      + "-jfx-checked-color: #00897b; -jfx-unchecked-color: #006064; -jfx-label-float: true;");
        checkbox_1.setText("Restrict Layout Bounds to Base");
        // Material Physical Parameters
        Label l_3 = new Label("Set Physical Parameters");
        l_3.setStyle("-fx-font-family: Roboto; -fx-font-size: 15; -fx-font-weight: bold; -fx-border-color: #ffffff; -fx-border-width: 0px 0px 2px 0px;");
        l_3.setTextFill(Color.web("#00897b"));
        // Material Band Gap
        textfield_5.setStyle(text_style);
        textfield_5.setPromptText("Band Gap");
        textfield_5.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Crystal Structure
        textfield_6.setStyle(text_style);
        textfield_6.setPromptText("Crystal Structure");
        textfield_6.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Doping Type
        Label l_4 = new Label("Doping Type");
        l_4.setStyle(text_style);
        l_4.setTextFill(Color.web("#00897b"));
        toggle_1.setStyle(text_style);
        toggle_1.setText("P");
        toggle_1.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(toggle_1.getText() == "P") toggle_1.setText("N");
            else toggle_1.setText("P");
        }));
        toggle_1.setPadding(new Insets(-30, 0, -20, -5));
        // Material Doping Density
        textfield_7.setStyle(text_style);
        textfield_7.setPromptText("Doping Density");
        textfield_7.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Material Doping Ionization Model
        textfield_8.setStyle(text_style);
        textfield_8.setPromptText("Doping Ionization Model");
        textfield_8.setPrefWidth(vbox_scrollpane.getPrefWidth()- 35);
        // Okay Button
        JFXButton okay_button = new JFXButton("Okay");
        okay_button.setStyle("-fx-background-color: #558b2f; -jfx-button-type: RAISED; -fx-text-fill: #ffffff; -fx-background-radius: 10px;");
        okay_button.setRipplerFill(Color.web("#ffffff"));
        okay_button.setMinSize(75, 25);
        okay_button.setTranslateY(20);
        okay_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if(!textfield_1.getText().isEmpty() && combobox_1.getValue() != null && combobox_2.getValue() != null && !textfield_2.getText().isEmpty() && !textfield_3.getText().isEmpty() &&
                    !textfield_4.getText().isEmpty() && !textfield_5.getText().isEmpty() && !textfield_6.getText().isEmpty() && !textfield_7.getText().isEmpty() &&
                    !textfield_8.getText().isEmpty()){
                    confirmed_stack_f(event);
                }
                else{
                    JFXDialogLayout input_empty = new JFXDialogLayout();
                    input_empty.setHeading(new ImageView("/images/empty.png"));
                    Text t = new Text("Some of the required text fields are empty.\nPlease fill them out accordingly.");
                    t.setStyle("-fx-font-family: Roboto; -fx-font-size: 15px; -fx-text-fill: #c62828;");
                    input_empty.setBody(t);

                    JFXDialog input_empty_dialog = new JFXDialog(stackpane_main, input_empty, JFXDialog.DialogTransition.BOTTOM);
                    JFXButton input_empty_button = new JFXButton("Okay");
                    input_empty_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

                    input_empty_button.setOnAction((ActionEvent event1) -> {
                        input_empty_dialog.close();
                    });

                    // set the contents within and display
                    input_empty.setActions(input_empty_button);
                    input_empty_dialog.show();
                }
            }
        });
        // Cancel Button
        JFXButton cancel_button = new JFXButton("Cancel");
        cancel_button.setStyle("-fx-background-color: #f44336; -jfx-button-type: RAISED; -fx-text-fill: #ffffff; -fx-background-radius: 10px;");
        cancel_button.setRipplerFill(Color.web("#ffffff"));
        cancel_button.setMinSize(75, 25);
        cancel_button.setTranslateY(20);
        cancel_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                activated_cancel_f(event);
            }
        });

        vbox_scrollpane.setSpacing(20);
        vbox_scrollpane.setPadding(new Insets(20, 0, 40, 12));
        vbox_scrollpane.getChildren().clear();
        vbox_scrollpane.getChildren().addAll(l_1, textfield_1, combobox_1, textfield_2, textfield_3, textfield_4, l_2,
                                            color_picker, combobox_2, checkbox_1, l_3, textfield_5, textfield_6, l_4, toggle_1,  textfield_7, textfield_8, okay_button, cancel_button);
    }
    
    private void resize_listener_f() {
        // Dynamically change window size whenever window size is changed  
        
        stackpane_main.widthProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_main_w = newVal.doubleValue();
            borderpane_main.setPrefWidth(stackpane_main_w);
        });
        stackpane_main.heightProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_main_h = newVal.doubleValue();
            borderpane_main.setPrefHeight(stackpane_main_h);
        });
    }
    
    private void translator_f() {
        // Pre-translate various containers for transitioning later when action has been made
        anchorpaneleft_borderpane_main.setTranslateX( -(anchorpaneleft_borderpane_main.getPrefWidth()));
        anchorpaneright_borderpane_main.setTranslateX(anchorpaneright_borderpane_main.getPrefWidth());
    }
    
    
    public void enable_draw(){
        // Print 3D Axes
        
        PhongMaterial x_color = new PhongMaterial(Color.web( "#f44336", projection_transparency));
        PhongMaterial y_color = new PhongMaterial(Color.web( "#4caf50", projection_transparency));
        PhongMaterial z_color = new PhongMaterial(Color.web( "#2196f3", projection_transparency));
        
        
        Cylinder x_axis =  new Cylinder(anchorpanecenter_borderpane_main.getWidth() * 0.005, anchorpanecenter_borderpane_main.getHeight() * 0.20);
        Cylinder y_axis =  new Cylinder(anchorpanecenter_borderpane_main.getWidth() * 0.005, anchorpanecenter_borderpane_main.getHeight() * 0.20);
        Cylinder z_axis =  new Cylinder(anchorpanecenter_borderpane_main.getWidth() * 0.005, anchorpanecenter_borderpane_main.getHeight() * 0.20);
        
        x_axis.setMaterial(x_color);
        y_axis.setMaterial(y_color);
        z_axis.setMaterial(z_color);
        
        x_axis.setRotationAxis(Rotate.Z_AXIS);
        x_axis.setRotate(90.0);
        z_axis.setRotationAxis(Rotate.X_AXIS);
        z_axis.setRotate(90.0);
        z_axis.setTranslateZ(-(z_axis.getHeight() / 2.0 + z_axis.getRadius()));
        
        x_axis.setLayoutX(x_axis.getHeight() / 2.0 + x_axis.getRadius() * 2.0);
        x_axis.setLayoutY(x_axis.getHeight());
        y_axis.setLayoutX(y_axis.getRadius());
        y_axis.setLayoutY(y_axis.getHeight() / 2.0 - y_axis.getRadius());
        z_axis.setLayoutX(z_axis.getRadius());
        z_axis.setLayoutY(z_axis.getHeight());
        
        group_axes_3D.getChildren().addAll(x_axis, y_axis, z_axis);
        group_axes_3D.setLayoutX(anchorpanecenter_borderpane_main.getWidth() * 0.1);
        group_axes_3D.setLayoutY(anchorpanecenter_borderpane_main.getHeight() * 0.70); 
        
        // Create a Subscene for better graphics manipulation
        workspace = new SubScene(group_main, anchorpanecenter_borderpane_main.getWidth(), anchorpanecenter_borderpane_main.getHeight(), true, SceneAntialiasing.BALANCED);
        
        // Create a camera for perspective view
        cam = new PerspectiveCamera(false);
        cam.setNearClip(0.0001);
        cam.setFarClip(1000.0);
        workspace.setCamera(cam);
        
        // Add subscene to center anchorpane
        anchorpanecenter_borderpane_main.getChildren().add(workspace);
        
        // Define various groups
        group_main.getChildren().add(group_axes_3D);
        group_main.getChildren().add(group_device);
        group_device.setLayoutX(workspace.getWidth() * 0.5);
        group_device.setLayoutY(workspace.getHeight() * 0.5);
        group_device.setTranslateZ(- 150);
        
        // Enable Omnidirectional Transformations
        omnidirectional_move_f(group_main, true);
    }
    
    public void disable_draw(){
        anchorpanecenter_borderpane_main.getChildren().remove(workspace);
    }
    // Initializer functions
    // *************************************************************************************************  
}
