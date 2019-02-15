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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Cursor;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class QDsimple_main_FXMLDocumentController implements Initializable {
    
    // FXML document loaded variables
    double stackpane_dialog_h, stackpane_dialog_w;
    @FXML
    public StackPane StackPane_Dialog;
    @FXML
    public BorderPane BorderPane_Main;
    @FXML
    public MenuBar MenuBar_BorderPane_Main;
    @FXML
    public Menu File_MenuBar_BorderPane_Main;
    @FXML
    public MenuItem Close_File_MenuBar_BorderPane_Main;
    @FXML
    public Menu Help_MenuBar_BorderPane_Main;
    @FXML
    public MenuItem About_Help_MenuBar_BorderPane_Main;
    @FXML
    public Button cube_button;
    @FXML
    public Button circle_button;
    public Button cone_button;
    @FXML
    public Button cylinder_button;
    @FXML
    public Button sphere_button;
    @FXML
    public Button cube_button211;
    @FXML
    public ImageView line_button;
    @FXML
    public AnchorPane AnchorPaneRight_BorderPane_Main;
    @FXML
    public TextField width_tf;
    @FXML
    public TextField height_tf;
    @FXML
    public TextField radius_tf;
    public Spinner<?> material_spinner;
    public Spinner<?> doping_spinner;
    public Spinner<?> color_spinner;
    @FXML
    public Label shape_id_tf;
    @FXML
    public TextField gate_label_tf;
    @FXML
    public TextField voltage_tf;
    
            
    
    //Custom Variable Declaration  
    SmartGroup group=new SmartGroup();
    Camera pers_camera;
    
    
    String selected_shape;
    int selected_shape_id;
    
    
   
    //Color & material for the shapes
    PhongMaterial blueMaterial = new PhongMaterial();
    PhongMaterial redMaterial = new PhongMaterial();
    PhongMaterial greenMaterial = new PhongMaterial();
    PhongMaterial brownMaterial = new PhongMaterial();
    PhongMaterial orangeMaterial = new PhongMaterial();
    PhongMaterial magentaMaterial = new PhongMaterial();
    PhongMaterial indigoMaterial = new PhongMaterial();
        
    
    @FXML
    private MenuItem new_project;
    private SubScene subscene;
    @FXML
    private Pane center_pane;
    @FXML
    private Button apply_button;
    @FXML
    private ComboBox<String> material_type_cb;
    @FXML
    private ComboBox<String> material_cb;
    @FXML
    private ComboBox<String> doping_cb;
    @FXML
    private Button up_button;
    @FXML
    private Button left_button;
    @FXML
    private Button down_button;
    @FXML
    private Button right_button;
    @FXML
    private Button up_right_button;
    @FXML
    private Button down_left_button;
    @FXML
    private ComboBox<String> shape_list_cb;
    @FXML
    private ComboBox<String> plane_list_cb;
    @FXML
    private TextField depth_tf;
    @FXML
    private Button delete_shape_button;
    @FXML
    private Button up_right_button1;
    @FXML
    private ImageView rotate_x_button;
    @FXML
    private Button rotate_y_button;
    @FXML
    private Button choose_image_button;

    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dynamically change window size whenever window size is changed
        stackpane_dialog_w = StackPane_Dialog.widthProperty().doubleValue();
        stackpane_dialog_h = StackPane_Dialog.heightProperty().doubleValue();
        window_resize_listener_f();
       
       subscene = new SubScene(group, center_pane.getWidth(), center_pane.getHeight(), true, SceneAntialiasing.BALANCED);
       //subscene.setStyle("-fx-background-color: black;");
       
       subscene.autosize();
       center_pane.setDepthTest(DepthTest.ENABLE);
       center_pane.getChildren().add(subscene);
       
       init_colors();
       initMouseControl(group,subscene);
       buildAxes();
       camera_set();
       init_combo_box();
       init_shapes();
       
            
    }
    
    void init_colors()
    {
        //Initiallizing colors
       blueMaterial.setDiffuseColor(Color.LIGHTBLUE);  //GaAs
       blueMaterial.setSpecularColor(Color.BLUE);
       
       redMaterial.setDiffuseColor(Color.CRIMSON);      //Si
       redMaterial.setSpecularColor(Color.RED);
       
       greenMaterial.setDiffuseColor(Color.LIGHTGREEN);  //P
       greenMaterial.setSpecularColor(Color.GREEN);
       
       brownMaterial.setDiffuseColor(Color.BROWN);     //AlGaAs
       brownMaterial.setSpecularColor(Color.SIENNA);
       
       orangeMaterial.setDiffuseColor(Color.CORAL);     //SiO2
       orangeMaterial.setSpecularColor(Color.ORANGE);
       
       magentaMaterial.setDiffuseColor(Color.FUCHSIA);  //Al2O3
       magentaMaterial.setSpecularColor(Color.MAGENTA);
       
       indigoMaterial.setDiffuseColor(Color.AQUAMARINE);    //SiNi
       indigoMaterial.setSpecularColor(Color.AQUA);
          
    }
    
    void init_combo_box()
    {
        material_type_cb.getItems().addAll("Super Conductor","Conductor","Semi Conductor","Insulator");
        material_cb.getItems().addAll("GaAs","Si","P","AlGaAs","SiO2","Al2O3","SiNi");
        doping_cb.getItems().addAll("+ve","-ve","neutral");
        plane_list_cb.getItems().addAll("None","xyz","xzy","yxz","yzx","zxy","zyx");
        shape_list_cb.getItems().addAll("None");
        plane_list_cb.getSelectionModel().selectFirst();
        shape_list_cb.getSelectionModel().selectFirst();
    }
    
    void camera_set()
    {
        pers_camera = new PerspectiveCamera(true);
       // pers_camera = new ParallelCamera();
        pers_camera.translateZProperty().set(-500);
       // pers_camera.translateYProperty().set(-500);

        //Set the clipping planes
        pers_camera.setNearClip(0.01);
        pers_camera.setFarClip(20000);
        subscene.setCamera(pers_camera);
        
    }
    SmartBox[] box;
    SmartCylinder[] cylinder;
    SmartSphere[] sphere;
    int box_no,cylinder_no,sphere_no;
    
    void init_shapes()                    //Allocate memories for the shapes
    {
        box=null;
        cylinder=null;
        sphere=null;
        
        box= new SmartBox[15];
        cylinder= new SmartCylinder[15];
        sphere= new SmartSphere[15];
        box_no=0;
        cylinder_no=0;
        sphere_no=0;
    }
    
    public void window_resize_listener_f() {
        StackPane_Dialog.widthProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_dialog_w = newVal.doubleValue();
            BorderPane_Main.setPrefWidth(stackpane_dialog_w);
            
        });
        StackPane_Dialog.heightProperty().addListener((obs, oldVal, newVal) ->{
            stackpane_dialog_h = newVal.doubleValue();
            BorderPane_Main.setPrefHeight(stackpane_dialog_h);
        });
        
        center_pane.heightProperty().addListener((obs, oldVal, newVal) ->{
            subscene.setHeight(newVal.doubleValue());
            
        });
        center_pane.widthProperty().addListener((obs, oldVal, newVal) ->{
           subscene.setWidth(newVal.doubleValue());
        });
    }
    
    private void buildAxes() {
        
        final Box xAxis = new Box(subscene.getHeight()/15, 1, 1);
        final Box yAxis = new Box(1,subscene.getHeight()/15 , 1);
        final Box zAxis = new Box(1, 1,subscene.getHeight()/15);
        
        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);
    
        group.getChildren().addAll(xAxis, yAxis, zAxis);
        subscene.setRoot(group);
        
         
    }

    @FXML
    public void Activated_Close_File_MenuBar_BorderPane_Main_f(ActionEvent event) {
        QDsimple.get_current_instance_QDsimple().exit_program();
    }

    @FXML
    public void Activated_About_Help_MenuBar_BorderPane_Main_f(ActionEvent event) {
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
    
    @FXML
    private void apply_button_clicked(MouseEvent event) 
    {
        
        PhongMaterial color;
        String mat= material_cb.getValue();
        switch(mat)
        {
            case "GaAs":color=blueMaterial;
                        break;
            case "Si":color=redMaterial;
                        break;            
            case "P":color=greenMaterial;
                        break;
            case "AlGaAs":color=brownMaterial;
                        break;
            case "SiO2":color=orangeMaterial;
                        break; 
            case "Al2O3":color=magentaMaterial;
                        break;
            case "SiNi":color=indigoMaterial;
                        break; 
            default: color=blueMaterial;
                     break;
        }
        
        String dop=doping_cb.getValue();
        String mat_type= material_type_cb.getValue();    
            
            
            
        String stringToSplit = shape_id_tf.getText();    
        String[] tempArray;
        tempArray = stringToSplit.split("_");
        
        if(tempArray[0].equals("box"))
        {
            int id=Integer.parseInt(tempArray[1]);
            double x=Double.parseDouble(height_tf.getText());
            double y=Double.parseDouble(width_tf.getText());
            double z=Double.parseDouble(depth_tf.getText());
            
            box_property_change(id,x,y,z,color,mat_type,dop,mat);
           
            //calling connect_box & passsing parameters 
            
            String str1 = shape_list_cb.getValue(); 
            String str2 = plane_list_cb.getValue();
            
            if(str1.equals("None") || str2.equals("None"))
            {
                
            }
            else
            {
                String[] temp;
                temp = str1.split("_");
                int box_two=Integer.parseInt(temp[1]);
                if(id!=box_two)
                {
                    int index= plane_list_cb.getSelectionModel().getSelectedIndex();
                    connect_box(id,box_two,index);
                }
                
            }
                       
        }
        else if(tempArray[0].equals("cylinder"))
        {
            int id=Integer.parseInt(tempArray[1]);
            
            double r=Double.parseDouble(radius_tf.getText());
            double x=Double.parseDouble(height_tf.getText());
            
            cylinder_property_change(id,x,r,color,mat_type,dop,mat);
            
        }
        else if(tempArray[0].equals("sphere"))
        {
            int id=Integer.parseInt(tempArray[1]);
            
            double r=Double.parseDouble(radius_tf.getText()); 
            sphere_property_change(id,r,color,mat_type,dop,mat);
          
        }
        
        
    }
    
    void get_selected_shape()
    {
        //JFXDialog About_dialog = new JFXDialog(StackPane_Dialog, About_content, JFXDialog.DialogTransition.CENTER);
        String stringToSplit = shape_id_tf.getText();    
        String[] tempArray;
        tempArray = stringToSplit.split("_");
        selected_shape=tempArray[0];
        selected_shape_id= Integer.parseInt(tempArray[1]);
    }
    
    
    @FXML
    private void choose_image_button_clicked(MouseEvent event) 
    {   
        // Getting the current shape type and id
        String stringToSplit = shape_id_tf.getText();    
        String[] tempArray;
        tempArray = stringToSplit.split("_");

        int id=Integer.parseInt(tempArray[1]);
        String shape=tempArray[0];
        
        //if texture wanted to be removed
        if(choose_image_button.getText().equals("Remove Texture"))
        {
            if(shape.equals("box"))
            {
                box[id].texture_file=null;
                box[id].setMaterial(blueMaterial);
            }
            else if(shape.equals("cylinder"))
            {
                cylinder[id].texture_file=null;
                cylinder[id].setMaterial(blueMaterial);
            }
            else if(shape.equals("sphere"))
            {
                sphere[id].texture_file=null;
                sphere[id].setMaterial(blueMaterial);
            }
            choose_image_button.setText("Choose Image");
        }
        //if texture wanted to be included
        else
        {
            FileChooser file_chooser = new FileChooser();
            file_chooser.setTitle("Open Resource File");
            File file = file_chooser.showOpenDialog(new Stage());
                if (file != null) 
                {

                    if(shape.equals("box"))
                    {
                        box[id].texture_file=file.toURI().toString();
                        box[id].set_texture_material();
                    }
                    else if(shape.equals("cylinder"))
                    {
                        cylinder[id].texture_file=file.toURI().toString();
                        cylinder[id].set_texture_material();
                    }
                    else if(shape.equals("sphere"))
                    {
                        sphere[id].texture_file=file.toURI().toString();
                        sphere[id].set_texture_material();
                    }

                }          
        }
        
        
        
    }
    
    Timer timer;
    TimerTask task; 
    int counter=0;
    int i=0;
    

    
    private class MyTimerTask extends TimerTask {
        int offset;
        int axis_no; //1 for X,2 for Y, 3 for Z
        boolean rotate;  
        MyTimerTask(int x, int y, boolean r)
        {
            this.offset=x;
            this.axis_no=y;
            this.rotate=r;
        }
        @Override
        public void run()
        {
            String stringToSplit = shape_id_tf.getText();    
            String[] tempArray;
            tempArray = stringToSplit.split("_");
            int id=Integer.parseInt(tempArray[1]);
            if(tempArray[0].equals("box"))
            { 
                if(axis_no==1)
                {
                   if (this.rotate)
                   {
                       box[id].translateXProperty().set(box[id].getTranslateX() + offset);
                   }
                   else
                   {
                       box[id].setRotationAxis(Rotate.X_AXIS);
                       box[id].rotateProperty().set(box[id].getRotate()+offset);
                   }
                   
                }
                else if(axis_no==2)
                {
                    if (this.rotate)
                   {
                      box[id].translateYProperty().set(box[id].getTranslateY() + offset);
                   }
                   else
                   {
                       box[id].setRotationAxis(Rotate.Y_AXIS);
                       box[id].rotateProperty().set(box[id].getRotate()+offset);
                   }
                    
                }
                else if(axis_no==3)
                {
                   box[id].translateZProperty().set(box[id].getTranslateZ() + offset); 
                }
                

            }
            else if(tempArray[0].equals("cylinder"))
            {
                if(axis_no==1)
                {
                    if (this.rotate)
                    {
                        cylinder[id].translateXProperty().set(cylinder[id].getTranslateX() + offset);
                    }
                   else
                    {
                        cylinder[id].setRotationAxis(Rotate.X_AXIS);
                        cylinder[id].rotateProperty().set(cylinder[id].getRotate()+offset);
                    }
                   
                }
                else if(axis_no==2)
                {
                    if (this.rotate)
                    {        
                        cylinder[id].translateYProperty().set(cylinder[id].getTranslateY() + offset);
                    }
                   else
                    {
                        cylinder[id].setRotationAxis(Rotate.Y_AXIS);
                        cylinder[id].rotateProperty().set(cylinder[id].getRotate()+offset);
                    }
                   
                }
                else if(axis_no==3)
                {
                    cylinder[id].translateZProperty().set(cylinder[id].getTranslateZ() + offset);
                }
                
               

            }
            else if(tempArray[0].equals("sphere"))
            {
                if(axis_no==1)
                {
                    if (this.rotate)
                    {        
                        sphere[id].translateXProperty().set(sphere[id].getTranslateX() + offset); 
                    }
                    else
                    {
                        sphere[id].setRotationAxis(Rotate.X_AXIS);
                        sphere[id].rotateProperty().set(sphere[id].getRotate()+offset);
                    }
                    
                   
                }
                else if(axis_no==2)
                {
                   if (this.rotate)
                    {        
                        sphere[id].translateYProperty().set(sphere[id].getTranslateY() + offset); 
                    }
                    else
                    {
                        sphere[id].setRotationAxis(Rotate.Y_AXIS);
                        sphere[id].rotateProperty().set(sphere[id].getRotate()+offset);
                    } 
                     
                }
                else if(axis_no==3)
                {
                   sphere[id].translateZProperty().set(sphere[id].getTranslateZ() + offset); 
                }
               

            }
            
        }
    }
    
    @FXML
    private void up_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(-5,2,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
       
    }
    
    @FXML
    private void up_button_released(MouseEvent event) {
        counter=0;
        timer.cancel();
        task.cancel();
        
        
    }

    @FXML
    private void left_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(-5,1,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }
    @FXML
    private void left_button_released(MouseEvent event) 
    {
        counter=0;
        timer.cancel();
        task.cancel();
        
    }
    
    
    
    
    @FXML
    private void down_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(5,2,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }
    @FXML
    private void down_button_released(MouseEvent event)
    {
        counter=0;
        timer.cancel();
        task.cancel();
    }
    
    @FXML
    private void right_button_pressed(MouseEvent event)
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(5,1,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }
    @FXML
    private void right_button_released(MouseEvent event) 
    {
       counter=0;
        timer.cancel();
        task.cancel(); 
    }


    @FXML
    private void up_right_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(5,3,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }
    @FXML
    private void up_right_button_released(MouseEvent event) 
    {
       counter=0;
       timer.cancel();
       task.cancel(); 
    }


    @FXML
    private void down_left_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(-5,3,true);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }
    @FXML
    private void down_left_button_released(MouseEvent event) 
    {
       counter=0;
       timer.cancel();
       task.cancel(); 
    }
    
    @FXML
    private void rorate_x_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(5,1,false);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
        
    }
 
    @FXML
    private void rotate_x_button_released(MouseEvent event) 
    {
       counter=0;
       timer.cancel();
       task.cancel(); 
    }
    
    
    @FXML
    private void rotate_y_button_pressed(MouseEvent event) 
    {
        if (counter==0)
        {
            timer= new Timer();
            task=new MyTimerTask(5,2,false);
            timer.scheduleAtFixedRate(task, 0, 100);
            counter++;
        }
    }

    
    @FXML
    private void rotate_y_button_released(MouseEvent event) 
    {
       counter=0;
       timer.cancel();
       task.cancel(); 
    }

    class SmartGroup extends Group 
    {
        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
          r = new Rotate(ang, Rotate.X_AXIS);
          t = t.createConcatenation(r);
          this.getTransforms().clear();
          this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
          r = new Rotate(ang, Rotate.Y_AXIS);
          t = t.createConcatenation(r);
          this.getTransforms().clear();
          this.getTransforms().addAll(t);
        }
    }
    
    class SmartBox extends Box
    {
        String material;
        String doping;
        String material_type;
        int id;
        Image tex;
        PhongMaterial tex_material = new PhongMaterial();
        String texture_file;
        SmartBox(int i,double x, double y, double z)
        {
            this.id=i;
            this.setHeight(x);
            this.setWidth(y);
            this.setDepth(z);
            this.doping="neutral";
            this.material="GaAs"; 
            this.material_type="Semi Conductor";
                      
        }
        
        void setDoping(String d)
        {
            this.doping=d;
        }
        void set_new_material(String m)
        {
            this.material=m;
        }
        void set_material_type(String m)
        {
            this.material_type=m;
        }
       
        void set_texture_material()
        {
            tex=new Image(texture_file);
            this.tex_material.setDiffuseMap(tex);
            this.setMaterial(tex_material);
            
        }
        
        
        
    }
    
    
    
    @FXML
    public void cube_button_clicked(MouseEvent event) 
    {
          
        box[box_no]= new SmartBox(box_no,50,50,50);
        
        box[box_no].setMaterial(blueMaterial);
        
        //box[box_no].setOnMousePressed(pressMouse());
        //box[box_no].setOnMouseDragged(dragMouse());
        
        box[box_no].setOnMouseClicked((MouseEvent me) -> {
            
            set_shape_property(me);
        });
        
        shape_list_cb.getItems().addAll("box_"+box_no);
        group.getChildren().add(box[box_no]);
        subscene.setRoot(group);
        box_no++;
       
        
    }
    
    
   
    void box_property_change(int n,double x,double y, double z,PhongMaterial color, String material_type, String doping, String mat)
    {
        box[n].setHeight(x);
        box[n].setWidth(y);
        box[n].setDepth(z);
        if(box[n].texture_file==null)
        {
            box[n].setMaterial(color);
        }  
         
        box[n].set_material_type(material_type);
        box[n].set_new_material(mat);
        box[n].setDoping(doping);
                 
    }
    
    void connect_box(int n, int m,int side_no)              // n= box no which is to be connected , m= box no where it will connect
    {
        
        double offset_x=box[m].getHeight();
        double offset_y=box[m].getWidth();
        double offset_z=box[m].getDepth();
        
        
        
        if( side_no==1)
        {
            box[n].translateXProperty().set(box[m].getTranslateX());
            box[n].translateYProperty().set(box[m].getTranslateY());
            box[n].translateZProperty().set(box[m].getTranslateZ()+offset_z);
        }
        else if( side_no==2)
        {
            box[n].translateXProperty().set(box[m].getTranslateX());
            box[n].translateYProperty().set(box[m].getTranslateY());
            box[n].translateZProperty().set(box[m].getTranslateZ()-offset_z);
        }
        else if( side_no==3)
        {
            box[n].translateXProperty().set(box[m].getTranslateX()+offset_x);
            box[n].translateYProperty().set(box[m].getTranslateY());
            box[n].translateZProperty().set(box[m].getTranslateZ());
        }
        else if( side_no==4)
        {
            box[n].translateXProperty().set(box[m].getTranslateX()-offset_x);
            box[n].translateYProperty().set(box[m].getTranslateY());
            box[n].translateZProperty().set(box[m].getTranslateZ());
        }
        else if( side_no==5)
        {
            box[n].translateXProperty().set(box[m].getTranslateX());
            box[n].translateYProperty().set(box[m].getTranslateY()+offset_y);
            box[n].translateZProperty().set(box[m].getTranslateZ());
        }
        else if( side_no==6)
        {
            box[n].translateXProperty().set(box[m].getTranslateX());
            box[n].translateYProperty().set(box[m].getTranslateY()- offset_y);
            box[n].translateZProperty().set(box[m].getTranslateZ());
        }
        
        
        
    }
    
    class SmartCylinder extends Cylinder
    {
        String material;
        String doping;
        String color;
        String material_type;
        int id;
        Image tex;
        String texture_file;
        PhongMaterial tex_material= new PhongMaterial();
        SmartCylinder(int i,double x, double r)
        {
            this.id=i;
            this.setHeight(x);
            this.setRadius(r);
            this.doping="neutral";
            this.material="GaAs"; 
            this.material_type="Semi Conductor";
            this.setMaterial(blueMaterial);
            
            
        }
        
        void setDoping(String d)
        {
            this.doping=d;
        }
        void set_new_material(String m)
        {
            this.material=m;
        }
        void set_material_type(String m)
        {
            this.material_type=m;
        }
        
        void set_texture_material()
        {
            tex=new Image(texture_file);
            this.tex_material.setDiffuseMap(tex);
            this.setMaterial(tex_material);
            
        }
        
    }
    
   
    @FXML
    public void cylinder_button_clicked(MouseEvent event) 
    {
        
        cylinder[cylinder_no]= new SmartCylinder(cylinder_no, 20,10);
        
        //cylinder[cylinder_no].setOnMousePressed(pressMouse());
        //cylinder[cylinder_no].setOnMouseDragged(dragMouse());
        cylinder[cylinder_no].setOnMouseClicked((MouseEvent me) -> 
        {
            set_shape_property(me);
        });
       
        
        group.getChildren().add(cylinder[cylinder_no]);
        subscene.setRoot(group);
        cylinder_no++;
    }
    
    void cylinder_property_change(int n,double x,double r,PhongMaterial color, String material_type, String doping, String mat)
    {
        cylinder[n].setHeight(x);
        cylinder[n].setRadius(r);
        cylinder[n].set_new_material(mat);
        if(cylinder[n].texture_file==null)
        {
           cylinder[n].setMaterial(color);  
        }
        
        cylinder[n].set_material_type(material_type);
        cylinder[n].setDoping(doping);
         
    }
    
    class SmartSphere extends Sphere
    {
        String material;
        String doping;
        String material_type;
        int id;
        Image tex;
        String texture_file;
        PhongMaterial tex_material=new PhongMaterial();
        SmartSphere(int i,double r)
        {
            this.id=i;            
            this.setRadius(r);
            this.doping="neutral";
            this.material="GaAs"; 
            this.material_type="Semi Conductor";
            this.setMaterial(blueMaterial);
            
        }
        
        void setDoping(String d)
        {
            this.doping=d;
        }
        void set_new_material(String m)
        {
            this.material=m;
        }
        void set_material_type(String m)
        {
            this.material_type=m;
        }
        void set_texture_material()
        {
            tex=new Image(texture_file);
            this.tex_material.setDiffuseMap(tex);
            this.setMaterial(tex_material);
            
        }
        
    }
    
    @FXML
    private void sphere_button_clicked(MouseEvent event) 
    {
        
        sphere[sphere_no]= new SmartSphere(sphere_no,20);
        
        //sphere[sphere_no].setOnMousePressed(pressMouse());
        //sphere[sphere_no].setOnMouseDragged(dragMouse());
        sphere[sphere_no].setOnMouseClicked((MouseEvent me) -> 
        {
            set_shape_property(me);
        });
        
        group.getChildren().add(sphere[sphere_no]);
        subscene.setRoot(group);
       
        sphere_no++;
    }
    
    void sphere_property_change(int n,double r,PhongMaterial color, String material_type, String doping, String mat)
    {
        
        sphere[n].setRadius(r);
        sphere[n].set_new_material(mat);
        if(sphere[n].texture_file==null)
        {
          sphere[n].setMaterial(color);  
        }
        
        sphere[n].set_material_type(material_type);
        sphere[n].setDoping(doping);
        
       
    }
    

    @FXML
    private void circle_button_clicked(MouseEvent event) {
        Circle circle= new Circle();
        circle.setRadius(10);
        
        group.getChildren().add(circle);
        subscene.setRoot(group);
        
    }

    @FXML
    private void line_button_clicked(MouseEvent event) {
        Line line=new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(100.0f);
        line.setEndY(100.0f);
        
        group.getChildren().add(line);
        subscene.setRoot(group);
        
    }

    @FXML
    private void new_project_clicked(ActionEvent event) 
    {
        group.getChildren().clear();
        subscene.setRoot(group);
        buildAxes();
        camera_set();
        init_shapes();
        shape_list_cb.getItems().clear();
        shape_list_cb.getItems().add("None");
        shape_list_cb.getSelectionModel().select("None");
        
    }
    
    
    private double anchorX, anchorY;
    //Keep track of current angle for x and y
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    //We will update these after drag. Using JavaFX property to bind with object
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
   
    private void initMouseControl(SmartGroup group, SubScene subscene) 
    {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
            xRotate = new Rotate(0, Rotate.X_AXIS),
            yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        subscene.setOnMousePressed(event -> {
          anchorX = event.getSceneX();
          anchorY = event.getSceneY();
          anchorAngleX = angleX.get();
          anchorAngleY = angleY.get();
        });

        subscene.setOnMouseDragged(event -> {
          angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
          angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
        
        subscene.addEventHandler(ScrollEvent.SCROLL, event -> {
        //Get how much scroll was done in Y axis.
	double delta = event.getDeltaY();
        //Add it to the Z-axis location.
	group.translateZProperty().set(group.getTranslateZ() + delta);
        });
        
    }
    
   
    public void set_shape_property(MouseEvent event) 
    {
        if (event.getButton() == MouseButton.PRIMARY) 
        {

            if (event.getSource() instanceof SmartBox) 
            { 
                SmartBox selected_box = (SmartBox) event.getSource();
                int id=selected_box.id;
                shape_id_tf.setText("box_"+id);
                height_tf.setText(Double.toString(box[id].getHeight()));
                width_tf.setText(Double.toString(box[id].getWidth()));
                depth_tf.setText(Double.toString(box[id].getDepth()));
                radius_tf.setEditable(false);
                height_tf.setEditable(true);
                width_tf.setEditable(true);
                depth_tf.setEditable(true);
                material_cb.getSelectionModel().select(box[id].material);
                doping_cb.getSelectionModel().select(box[id].doping);
                material_type_cb.getSelectionModel().select(box[id].material_type);
                
                if(box[id].texture_file!=null)
                {
                    choose_image_button.setText("Remove Texture");
                }
                else
                {
                    choose_image_button.setText("Choose Image");
                }


            }
            else if (event.getSource() instanceof SmartCylinder) 
            { 
                SmartCylinder selected_cylinder = (SmartCylinder) event.getSource();
                int id=selected_cylinder.id;
                shape_id_tf.setText("cylinder_"+id);
                height_tf.setText(Double.toString(cylinder[id].getHeight()));
                width_tf.setEditable(false);
                depth_tf.setEditable(false);
                height_tf.setEditable(true);
                radius_tf.setEditable(true);

                radius_tf.setText(Double.toString(cylinder[id].getRadius()));
                material_cb.getSelectionModel().select(cylinder[id].material);
                doping_cb.getSelectionModel().select(cylinder[id].doping);
                material_type_cb.getSelectionModel().select(cylinder[id].material_type);
                if(cylinder[id].texture_file!=null)
                {
                    choose_image_button.setText("Remove Texture");
                }
                else
                {
                    choose_image_button.setText("Choose Image");
                }

            }
            else if (event.getSource() instanceof SmartSphere) 
            { 
                SmartSphere selected_sphere = (SmartSphere) event.getSource();
                int id=selected_sphere.id;
                shape_id_tf.setText("sphere_"+id);
                height_tf.setEditable(false);
                width_tf.setEditable(false);
                depth_tf.setEditable(false);
                radius_tf.setEditable(true);
                radius_tf.setText(Double.toString(sphere[id].getRadius()));
                material_cb.getSelectionModel().select(sphere[id].material);
                doping_cb.getSelectionModel().select(sphere[id].doping);
                material_type_cb.getSelectionModel().select(sphere[id].material_type);
                if(sphere[id].texture_file!=null)
                {
                    choose_image_button.setText("Remove Texture");
                }
                else
                {
                    choose_image_button.setText("Choose Image");
                }

            }

        }
    }
    
    @FXML
    private void delete_shape_button_clicked(MouseEvent event) 
    {
        String stringToSplit = shape_id_tf.getText();    
        String[] tempArray;
        tempArray = stringToSplit.split("_");
        int id=Integer.parseInt(tempArray[1]);
        String type= tempArray[0];
        
        if(type.equals("box"))
        {
            group.getChildren().remove(box[id]);
            box[id]=null;
        }
        else if(type.equals("cylinder"))
        {
            group.getChildren().remove(cylinder[id]);
            box[id]=null;
        }
        else if(type.equals("sphere"))
        {
            group.getChildren().remove(sphere[id]);
            box[id]=null;
        }
        
        subscene.setRoot(group);
        
    }

    

    
    
    /*
    private double m_nX = 0;
    private double m_nY = 0;
    private double m_nMouseX = 0;
    private double m_nMouseY = 0;


    private EventHandler<MouseEvent> pressMouse() 
    {
	EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() 
        {
            public void handle(MouseEvent event) 
            {
		if (event.getButton() == MouseButton.PRIMARY) 
                {
                    // get the current mouse coordinates according to the scene.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();
                                    
                    // get the current coordinates of the draggable node.
                    if (event.getSource() instanceof SmartBox) 
                    { 
                        SmartBox box = (SmartBox) event.getSource();
                        m_nX = box.getLayoutX();
			m_nY = box.getLayoutY();
                        
                    }
                    else if (event.getSource() instanceof SmartCylinder) 
                    { 
                        SmartCylinder cylinder = (SmartCylinder) event.getSource();
                        m_nX = cylinder.getLayoutX();
			m_nY = cylinder.getLayoutY();
                        
                    }
                    else if (event.getSource() instanceof SmartSphere) 
                    { 
                        SmartSphere sphere = (SmartSphere) event.getSource();
                        m_nX = sphere.getLayoutX();
			m_nY = sphere.getLayoutY();
                        
                    }
					
		}
            }
	};

	return mousePressHandler;
    }
    
    private EventHandler<MouseEvent> dragMouse() 
    {
	EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() 
        {

            public void handle(MouseEvent event)
            {
		if (event.getButton() == MouseButton.PRIMARY) 
                {
                    // find the delta coordinates by subtracting the new mouse
                    // coordinates with the old.
                    double deltaX = event.getSceneX() - m_nMouseX;
                    double deltaY = event.getSceneY() - m_nMouseY;

                    // add the delta coordinates to the node coordinates.
                    m_nX += deltaX;
                    m_nY += deltaY;
                    
                    if (event.getSource() instanceof SmartBox) 
                    { 
                        SmartBox box= (SmartBox) event.getSource();
                        box.setLayoutX(m_nX);
                        box.setLayoutY(m_nY);
                         
                    }
                    else if (event.getSource() instanceof SmartCylinder) 
                    { 
                        SmartCylinder cylinder= (SmartCylinder) event.getSource();
                        cylinder.setLayoutX(m_nX);
                        cylinder.setLayoutY(m_nY);
                         
                    }
                    else if (event.getSource() instanceof SmartSphere) 
                    { 
                        SmartSphere sphere= (SmartSphere) event.getSource();
                        sphere.setLayoutX(m_nX);
                        sphere.setLayoutY(m_nY);
                         
                    }
                    // set the layout for the draggable node.
                    

                    // get the latest mouse coordinate.
                    m_nMouseX = event.getSceneX();
                    m_nMouseY = event.getSceneY();

		}
            }
	};
	return dragHandler;
    }
    
    */
    
    
    
}
