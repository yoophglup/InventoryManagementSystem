package controller;
/**
 * class controller.inventoryMainScreen_controller.java
 */
/**
 * class inventoryMainScreen_controller.java
 */
/**
 *
 * @author Chad Self
 */
/**
 * RUNTIME ERROR
 * A runtime error is resolved in this class for the 'searchparts' method
 */
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
/**
  * Method to Initialize inventoryMainScreen_controller
 **/
 public class inventoryMainScreen_controller implements Initializable {
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn stockColumn;
    public TableColumn priceColumn;

    public TableColumn Product_Id;
    public TableColumn Product_Name;
    public TableColumn Product_Inv_Levels;
    public TableColumn Product_Price;

    public TextField queryTF;
    public TextField queryTFP;
    public TableView allParts;
    public TableView allProducts;

    public Button add_parts;
    public Button modify_parts;
    public Button delete_parts;
    public Button quitButton;
    public Button add_products;
    public Button modify_products;
    public Button delete_products;

    /**
     * This method initializes all tableviews. Sets the cell value factory and loads the list into the parts and product tableviews.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allParts.setItems(Inventory.getAllParts());
        allProducts.setItems(Inventory.getAllProducts());

        Product_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Product_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Product_Inv_Levels.setCellValueFactory(new PropertyValueFactory<>("stock"));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * This method changes the scene to add products.
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void add_products_clicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddProductScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 880, 640);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method changes the scene to add parts.
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void add_parts_clicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddPartScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method changes the scene to modify parts.
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void modify_parts_clicked(ActionEvent actionEvent) throws IOException {
        modifyPart_controller.parttomodify=allParts.getSelectionModel().getSelectedItems();
        modifyPart_controller.parttomodifyInhouse=allParts.getSelectionModel().getSelectedItems();
        modifyPart_controller.parttomodifyOutsourced=allParts.getSelectionModel().getSelectedItems();
        for (Part thispart:modifyPart_controller.parttomodify){
            modifyPart_controller.justaparttomodify=thispart;
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/modifyPart.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method changes the scene to modify products.
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void modify_products_clicked(ActionEvent actionEvent) throws IOException {
        ObservableList<Product> selectedProduct=allProducts.getSelectionModel().getSelectedItems();
        for (Product thisproduct : selectedProduct ){
            modifyProduct_controller.producttomodify=thisproduct;
       }
        if (allProducts.getSelectionModel().isEmpty()){
            modifyProduct_controller.producttomodify=new Product(0,"Nothing Selected",0.00,0,0,0);
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/modifyProduct.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 880, 640);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * An input number is converted into an integer.  If an exception occurs this method will return false.  If the value coverts to an integer this method will return true.
     * @param possibleNumber is checked if it can be converted to int
     * @return true if possibleNumber can be converted to integer or false if it cannot.
     */
    public static boolean isaNumber(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
            return true;
        } catch (Exception e) {return false;}
    }

    /**
     * RUNTIME ERROR <br>
     * This method originally caused a NumberFormatException runtime error. Implementing the method, 'isaNumber(String string)', and itegrading with the if statement, 'if (isaNumber(queryTF.getCharacters().toString())) {}' , resolved this error.  The if statement prevents the application from converting a noncovertable character into an integer.
     * @param actionEvent if a key is typed in the parts searchbox this event is triggered
     */
    public void searchparts(KeyEvent actionEvent) {
        ObservableList<Part> p = Inventory.LookupPart(queryTF.getCharacters().toString());
        if (p.size() < 1) {
            if (isaNumber(queryTF.getCharacters().toString())) {

                int queryint = Integer.parseInt(queryTF.getCharacters().toString());
                ObservableList<Part> pn = Inventory.lookupPart(queryint);
                p = pn;
            }
        }
        allParts.setItems(p);

    }

    /**
     * An input is checked if it partially matches any product name. Add all matches to a list. If it matches then set the product tableview to match the results. If there are no matches, then check if input is an integer.  If input is an integer, then check if it matches any product ID. Create a list of all products with matching ID and display the list in the products tableview.
     * @param actionEvent if a key is typed in the product searchbox this event is triggered
     */
    public void searchproduct(KeyEvent actionEvent) {
        ObservableList<Product> pa = Inventory.lookupProduct(queryTFP.getCharacters().toString());
        if (pa.size() < 1) {
            if (isaNumber(queryTFP.getCharacters().toString())) {
                int queryint = Integer.parseInt(queryTFP.getCharacters().toString());
                ObservableList<Product> pn = Inventory.lookupProduct(queryint);
                pa = pn;
            }
        }
        allProducts.setItems(pa);
    }

    /**
     * An alert pop up will confirm if the user would like to delete a part.  If the user clicks Ok then the part is deleted, if the user clicks cancel then no part is deleted.
     * @param actionEvent this event is triggered when the delete parts button is clicked
     */
    public void delete_parts_clicked(ActionEvent actionEvent) {
        Alert areyousure = new Alert(Alert.AlertType.CONFIRMATION);
        areyousure.setTitle("Are you Sure");
        areyousure.setContentText("You are about to delete the selected Inventory Part."+"  \nThis will remove the part from all product associations.  \nThis cannot be undone. ");
        areyousure.showAndWait();
        if (areyousure.getResult().getButtonData().toString() == "OK_DONE") {
            ObservableList<Part> thisparts = allParts.getSelectionModel().getSelectedItems();
            for (Part bp : thisparts) {
                Inventory.deletePart(bp);
                if (allParts.getSelectionModel().isEmpty()){
                    return;
                }
            }
        }


    }

    /**
     * An alert pop up will confirm if the user would like to delete a product.  If the user clicks Ok then the part is deleted, if the user clicks cancel then no part is deleted. <p> If the user clicks ok, but the product contains associated parts, then the user is presented with an error screen.  The user can click return to menu or modify part to remove existing associated parts.</p>
     * @param actionEvent this event is triggered when the delete product button is clicked
     */
    public void delete_product_clicked(ActionEvent actionEvent) {
        Alert areyousure = new Alert(Alert.AlertType.CONFIRMATION);
        areyousure.setTitle("CONFIRMATION");
        areyousure.setHeaderText("You are about to delete the selected product.");
        areyousure.setContentText("This cannot be undone. Are you sure?");
        areyousure.showAndWait();
        if (areyousure.getResult().getButtonData().toString() == "OK_DONE") {
            ObservableList<Product> thisproducts = allProducts.getSelectionModel().getSelectedItems();
            for (Product bp : thisproducts) {
                if (bp.getAllAssociatedParts().size()==0){
                Inventory.deleteProduct(bp);
                    if (allProducts.getSelectionModel().isEmpty()){
                        return;
                    }
                    else {Inventory.deleteProduct(bp);}
                    modifyProduct_controller.producttomodify=new Product(0,"Nothing Selected",0.00,0,0,0);

                }else {
                    ButtonType errortoModify = new ButtonType("Return to Main", ButtonBar.ButtonData.OK_DONE);
                    Alert errorcontainsassociations = new Alert(Alert.AlertType.ERROR, "Error",  errortoModify);
                    errorcontainsassociations.setTitle("Error - Unable to delete selected product ");
                    errorcontainsassociations.setHeaderText("Unable to delete product due to existing part associations. \nRemove all parts associated with this product to delete.");
                    errorcontainsassociations.setContentText("Associated Parts can be removed from the product using the Modify \nProduct after clicking on a product.");
                    errorcontainsassociations.showAndWait().orElse(errortoModify);



                }

            }
        }
    }
    public void quit_clicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}

