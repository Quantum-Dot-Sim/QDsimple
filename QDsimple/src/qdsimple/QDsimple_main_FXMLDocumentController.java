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
import com.jfoenix.controls.events.JFXDialogEvent;
import java.text.DecimalFormat;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

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
    private JFXTextField textfield_height;
    @FXML
    private JFXTextField textfield_width;
    @FXML
    private JFXTextField textfield_depth;  
    @FXML
    private JFXColorPicker color_picker;
    @FXML
    private JFXButton btn_base;
    @FXML
    private Label label_base;
    @FXML
    private JFXButton btn_stack;
    @FXML
    private Label label_stack;
    @FXML
    private JFXComboBox<String> combobox_material_stack;
    @FXML
    private JFXComboBox<String> combobox_material;
    @FXML
    private VBox vbox_anchorpaneright_borderpane_main;
    @FXML
    private Label label_message;
    @FXML
    private JFXButton cancel_button;
    @FXML
    private JFXButton okay_button;
    @FXML
    private Label label_anchorpaneright_borderpane_main;
    @FXML
    private JFXTextField textfield_component_label;
    @FXML
    private JFXCheckBox checkbox_layout_restrict;
    // FXML Variables
    // *************************************************************************************************
    
    // Primitive Variables
    // *************************************************************************************************
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
    // *************************************************************************************************
    // Primitive Variables
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    // *************************************************************************************************
    // Initializer functions
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
    // Initializer functions
    // *************************************************************************************************  
    
    // *************************************************************************************************
    // Action oriented functions
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
                if(combobox_material_stack.isDisabled()) addon_orientation = "Any";
                else addon_orientation = combobox_material_stack.getValue();
                
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                            if(checkbox_layout_restrict.isSelected()){
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
                if(combobox_material_stack.isDisabled()) addon_orientation = "Any";
                else addon_orientation = combobox_material_stack.getValue();
                
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
                if(!textfield_width.getText().isEmpty() && !textfield_height.getText().isEmpty() && !textfield_depth.getText().isEmpty()){
                    // Enabling buttons for functions
                    okay_button.setDisable(false);
                    cancel_button.setDisable(false);

                    // Set values
                    box_col.setDiffuseColor(Color.web(color_picker.getValue().toString(), projection_transparency));
                    new_b.setWidth(Double.parseDouble(textfield_width.getText()));
                    new_b.setHeight(Double.parseDouble(textfield_height.getText()));
                    new_b.setDepth(Double.parseDouble(textfield_depth.getText()));
                    new_b.setMaterial(box_col);
                }
                else{
                    // Disabling buttons for functions
                    okay_button.setDisable(true);
                    cancel_button.setDisable(true);

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
                combobox_material_stack.setDisable(false);
                combobox_material_stack.setVisible(true);
                checkbox_layout_restrict.setDisable(false);
                checkbox_layout_restrict.setVisible(true);
                
                // Stack shape
                group_device.getChildren().add(new_b);
                // Disabling maneuverability of ALL shapes
                omnidirectional_move_f(group_main, false);
                shape_move_f(new_b, true);

                stackpane_main.addEventHandler(InputEvent.ANY, in_event -> {
                    if(!textfield_width.getText().isEmpty() && !textfield_height.getText().isEmpty() && !textfield_depth.getText().isEmpty() && combobox_material_stack.getValue() != null){
                        // Enabling buttons for functions
                        okay_button.setDisable(false);
                        cancel_button.setDisable(false);
                        
                        // Set values
                        box_col.setDiffuseColor(Color.web(color_picker.getValue().toString(), projection_transparency));
                        new_b.setWidth(Double.parseDouble(textfield_width.getText()));
                        new_b.setHeight(Double.parseDouble(textfield_height.getText()));
                        new_b.setDepth(Double.parseDouble(textfield_depth.getText()));
                        new_b.setMaterial(box_col);
                        
                        // Adjust translation
                        if(combobox_material_stack.getValue() == "Up" && selected_shape_index!= -1){
                            new_b.setTranslateY(- boundary_proximity_limit - ((new_b.getHeight() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
                        }
                        else if(combobox_material_stack.getValue() == "Down" && selected_shape_index!= -1){
                            new_b.setTranslateY(boundary_proximity_limit + ((new_b.getHeight() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getHeight() / 2.0)));
                        }
                        else if(combobox_material_stack.getValue() == "Left" && selected_shape_index!= -1){
                            new_b.setTranslateX(- boundary_proximity_limit - ((new_b.getWidth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
                        }
                        else if(combobox_material_stack.getValue() == "Right" && selected_shape_index!= -1){
                            new_b.setTranslateX(boundary_proximity_limit + ((new_b.getWidth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getWidth() / 2.0)));
                        }
                        else if(combobox_material_stack.getValue() == "Front" && selected_shape_index!= -1){
                            new_b.setTranslateZ( - boundary_proximity_limit - ((new_b.getDepth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
                        }
                        else if(combobox_material_stack.getValue() == "Back" && selected_shape_index!= -1){
                            new_b.setTranslateZ( boundary_proximity_limit + ((new_b.getDepth() / 2.0) + (group_device.getChildren().get(selected_shape_index).getLayoutBounds().getDepth() / 2.0)));
                        }
                    }
                    else{
                        // Disabling buttons for functions
                        okay_button.setDisable(true);
                        cancel_button.setDisable(true);

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
                combobox_material_stack.setDisable(true);
                combobox_material_stack.setVisible(false);
                checkbox_layout_restrict.setDisable(true);
                checkbox_layout_restrict.setVisible(false);
                
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
    private void activated_confirm_add_f(ActionEvent event) {
        // Create Layer
        PhongMaterial box_col = new PhongMaterial();
        box_col.setDiffuseColor(Color.web(color_picker.getValue().toString()));
        Box new_b = new Box(Double.parseDouble(textfield_width.getText()), Double.parseDouble(textfield_height.getText()), Double.parseDouble(textfield_depth.getText()));
        new_b.setMaterial(box_col);
        new_b.getTransforms().addAll(group_device.getChildren().get(group_device.getChildren().size() - 1).getLocalToParentTransform());
        
        // Ensure Physical Bound validity
        boolean b = true;
        if(group_device.getChildren().size() == 1){
            b = true;
        }
        else if(group_device.getChildren().size() > 1){
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
        else{
            b = false;
        }
        
        // Remove projected shapes
        activated_cancel_f(event);

        if(b){
            if(btn_base.isDisable() == false){
                group_device.getChildren().add(new_b);
                
                // Disable add base option
                btn_base.setDisable(true);
                label_base.setDisable(true);
                btn_stack.setDisable(false);
                label_stack.setDisable(false);
            }
            else{
                group_device.getChildren().add(new_b);
            }
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
    
    @FXML
    private void activated_cancel_f(ActionEvent event) {
        //Resetting variables
        selected_shape_index = -1;
        label_message.setText("");
        
        // Enabling omnidirectional Movement
        shape_move_f(group_device.getChildren().get(group_device.getChildren().size() - 1), false);
        omnidirectional_move_f(group_main, true);
        
        // Removing projections
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
    private void display_device_coordinates_f(ActionEvent event) {
        JFXDialogLayout device_coordinates = new JFXDialogLayout();
        device_coordinates.setHeading(new ImageView("/images/device_coordinate.png"));
        Text t = new Text();
        
        String d_c_s = "";
        for(int i = 0; i < group_device.getChildren().size(); i++){
            d_c_s += "Material index (" + (i + 1) + "): " + group_device.getChildren().get(i).getBoundsInParent() + "\n";
        }
        
        t.setText(d_c_s);
        
        t.setStyle("-fx-font-family: Roboto; -fx-font-size: 10px; -fx-text-fill: #616161;");
        device_coordinates.setBody(t);

        JFXDialog device_coordinates_dialog = new JFXDialog(stackpane_main, device_coordinates, JFXDialog.DialogTransition.CENTER);
        JFXButton device_coordinates_button = new JFXButton("Okay");
        device_coordinates_button.setStyle(" -fx-background-color: #00838f; -fx-text-fill: white; ");

        device_coordinates_button.setOnAction((ActionEvent event1) -> {
            device_coordinates_dialog.close();
        });

        // set the contents within and display
        device_coordinates.setActions(device_coordinates_button);
        device_coordinates_dialog.show();
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
        
        // Populating JFXComboBox material type
        combobox_material.getItems().add("Si");
        combobox_material.getItems().add("Ge");
        combobox_material.getItems().add("Al");
        combobox_material.getItems().add("SiGe");
        combobox_material.getItems().add("GaAs");
        combobox_material.getItems().add("AL2O3");
        combobox_material.getItems().add("SiO2");
        
        // Populating JFXComboBox material orientation
        combobox_material_stack.getItems().add("Up");
        combobox_material_stack.getItems().add("Down"); 
        combobox_material_stack.getItems().add("Left");
        combobox_material_stack.getItems().add("Right"); 
        combobox_material_stack.getItems().add("Front");
        combobox_material_stack.getItems().add("Back");
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
    // Main functions
    // ************************************************************************************************* 
}
