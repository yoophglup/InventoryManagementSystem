package main;
/**
 * class main.Main.java
 * Java 11 + JAVAfx
 * --module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics
 *
 */
/**
 * class Main.java
 */
/**
 *
 * @author Chad Self
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * JavaDoc Location - InventoryManagementSystem.zip(unzipped location)\ims-javadoc\index.html<br>
 * Inventory Management System javaDoc files are stored in a folder named
 * 'ims-javadoc' stored in the root of the zip file
 * <p>
 * FUTURE ENHANCEMENT <br>
 * Add save data to a file<br>
 * Add load data from a file<br>
 * Add login window<br>
 * Add 'Create New User' scene<br>
 * Add 'Remove User' scene<br>
 * Add 'Modify User' scene<br>
 * Add a 'delete all parts associated' button<br>
 *
 */
public class Main extends Application {

    /**
     * This method changes the scene to the main menu.
     * @param primaryStage creates the main menu scene
     * @throws IOException primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Second Loading");
        Parent root= FXMLLoader.load(getClass().getResource("/view/InventoryMainScreen.fxml"));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root,1040,390));
        primaryStage.setX(110);
        primaryStage.setY(5);
        primaryStage.show();
    }
    /**
     * This method adds the test data used in the application.
     */
    private static void addTestData(){
       InHouse nails=new InHouse(1, "nails", .12, 783, 1, 99999,116);
       InHouse boards=new InHouse(2, "boards", 2.25, 562, 1, 99999,116);
       InHouse siding=new InHouse(3, "siding", 3.15, 558, 1, 99999,116);
       InHouse shingles=new InHouse(4, "shingles", .97, 342, 1, 99999,116);
       Outsourced screws=new Outsourced(5, "screws", .19, 56, 1, 115,"ScrewsRUsLLC");
       Outsourced anchors=new Outsourced(6, "anchors", .19, 100, 1, 115,"MountsRUsLLC");
       Outsourced kidcar=new Outsourced(7, "kidcar", 225.99, 115, 1, 115,"MountsRUsLLC");

       Inventory.addPart(nails);
       Inventory.addPart(boards);
       Inventory.addPart(siding);
       Inventory.addPart(shingles);
       Inventory.addPart(screws);
       Inventory.addPart(anchors);
       Inventory.addPart(kidcar);

       Product Doll_House=new Product(1,"Doll House",505.50,299,1,415);
       Inventory.addProduct(Doll_House);
       Doll_House.addAssociatedPart(nails);
       Doll_House.addAssociatedPart(screws);
       Doll_House.addAssociatedPart(shingles);
       Doll_House.addAssociatedPart(boards);
       Doll_House.addAssociatedPart(siding);

       Product racetrack=new Product(2,"Racetrack",225.50,299,1,415);
       Inventory.addProduct(racetrack);
       racetrack.addAssociatedPart(boards);
       racetrack.addAssociatedPart(screws);
       racetrack.addAssociatedPart(anchors);

       Product racetrackpluskidcar=new Product(3,"Kid Car + Track",599.99,299,1,415);
       Inventory.addProduct(racetrackpluskidcar);
       racetrackpluskidcar.addAssociatedPart(boards);
       racetrackpluskidcar.addAssociatedPart(screws);
       racetrackpluskidcar.addAssociatedPart(anchors);
       racetrackpluskidcar.addAssociatedPart(kidcar);

   }
    /**
     * This method calls the method to load the test data, then pass args
     * to the start method through launch
     * @param args main Launch arguments
     */
    public static void main(String[] args){
        System.out.println("First Loading");
        addTestData();
        launch(args);
    }
}
