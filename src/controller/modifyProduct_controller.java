package controller;
/**
 * class controller.modifyProduct_controller.java
 */
/**
 * class modifyProduct_controller.java
 */
/**
 * @author Chad Self
 */
/**
 * RUNTIME ERROR
 *
 */

import javafx.collections.FXCollections;
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
 * Method to Initialize modifyProduct_controller
 **/
    public class modifyProduct_controller  implements Initializable {
    //public static boolean isfromdelete;
    public static Product producttomodify;
    public Product NewProductfromSelected = new Product(producttomodify.getId(), producttomodify.getName(), producttomodify.getPrice(), producttomodify.getStock(), producttomodify.getMin(), producttomodify.getMax());
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn stockColumn;
    public TableColumn priceColumn;
    public TableColumn Product_Id;
    public TableColumn Product_Name;
    public TableColumn Product_Inv_Levels;
    public TableColumn Product_Price;
    public TableView partslist;
    public TableView associatedPartstable;
    public TextField modID;
    public TextField modName;
    public TextField modInv;
    public TextField modPrice;
    public TextField modMax;
    public TextField modMin;
    public TextField search_parts;
    public Label outputbox;
    public Button savebutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modID.setText(String.valueOf(producttomodify.getId()));
        modName.setText(producttomodify.getName());
        modInv.setText(String.valueOf(producttomodify.getStock()));
        modPrice.setText(String.valueOf(producttomodify.getPrice()));
        modMax.setText(String.valueOf(producttomodify.getMax()));
        modMin.setText(String.valueOf(producttomodify.getMin()));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partslist.setItems(Inventory.getAllParts());
        Product_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Product_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Product_Inv_Levels.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Product_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Part> thislist=producttomodify.getAllAssociatedParts();
        for (Part selectedPart :thislist ){
            NewProductfromSelected.addAssociatedPart(selectedPart);
        }

        associatedPartstable.setItems(NewProductfromSelected.getAllAssociatedParts());





    }

    /**
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void canceladdProduct(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/InventoryMainScreen.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1040, 390);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param actionEvent adds selected part to associated parts
     */
    public void addtoassociatedparts(ActionEvent actionEvent) {
        ObservableList<Part> selectedPart=partslist.getSelectionModel().getSelectedItems();
        for (Part thispart : selectedPart) {
            if (!NewProductfromSelected.getAllAssociatedParts().contains(thispart)){
            NewProductfromSelected.addAssociatedPart(thispart);
            outputbox.setText("");

            } else {outputbox.setText("That part is already associated\n with this product.");}
        }
        associatedPartstable.setItems(NewProductfromSelected.getAllAssociatedParts());

    }

    /**
     * @param actionEvent adds removes part from associated parts
     */
    public void removeassociatedparts(ActionEvent actionEvent) {
            ObservableList<Part> selectedPart = associatedPartstable.getSelectionModel().getSelectedItems();
            try {
               for (Part thispart : selectedPart) {
                   NewProductfromSelected.deleteAssociatedPart(thispart);
               }
               associatedPartstable.setItems(NewProductfromSelected.getAllAssociatedParts());
           } catch (Exception e){System.out.println();
            }
    }

    /**
     * @return boolean true if CONFIRMATION Alert is not CANCEL_CLOSE
     */
    public boolean confirmdelete(){
            Alert areyousure = new Alert(Alert.AlertType.CONFIRMATION);
            areyousure.setTitle("CONFIRMATION");
            areyousure.setHeaderText("You are about to delete the selected product.");
            areyousure.setContentText("This cannot be undone. Are you sure?");
            areyousure.showAndWait();
            System.out.println(areyousure.getResult().getButtonData().toString());
            if (areyousure.getResult().getButtonData().toString() == "CANCEL_CLOSE") {
                return false;
            }
            return true;
        }


    /**
     * @return boolean true if all values are valid
     */
    public boolean isinputgood(){
        if (producttomodify.getId()==0){
            outputbox.setText("You did not select a product to\n modify.  Return to the main screen \nand select a product to modify. \n Press cancel to continue.");
        return false;
        }
    if ( modName.getCharacters().length()==0) {
        outputbox.setText("You must enter a Name value.");
        return false;
    }
    if ( modInv.getCharacters().length()==0) {
        outputbox.setText("Inventory stock level \ncannot be blank.");
        return false;
    }
    try {
        Integer.parseInt(modInv.getCharacters().toString());
    }catch (Exception e) {
        outputbox.setText("Inventory stock must\n be an integer value.");
        return false;
    } ;
    if ( modPrice.getCharacters().length()==0) {
        outputbox.setText("You must enter a Price value.");
        return false;
    }
    try {
        Double.parseDouble(modPrice.getCharacters().toString());
    }catch (Exception e) {
        outputbox.setText("Price must \nbe a double value.");
        return false;
    } ;
    if ( modMax.getCharacters().length()==0) {
        outputbox.setText("You must enter a \nMax inventory value.");
        return false;
    }
    try {
        Integer.parseInt(modMax.getCharacters().toString());
    }catch (Exception e) {
        outputbox.setText("Max inventory must\n be an integer value.");
        return false;
    }
    if ( modMin.getCharacters().length()==0) {
        outputbox.setText("You must enter a \nMin inventory value.");
        return false;
    }
    try {
        Integer.parseInt(modMin.getCharacters().toString());
        if ((Integer.parseInt(modMax.getCharacters().toString()))<(Integer.parseInt(modMin.getCharacters().toString())) ) {
            outputbox.setText("You must enter a \nMin inventory value which is \nless than the Max \ninventory value.");

            return false;
        }
    }catch (Exception e) {
        outputbox.setText("Min inventory must\n be an integer value.");
        return false;
    }
    try {
        if ( Integer.parseInt(modInv.getCharacters().toString()) < Integer.parseInt(modMin.getCharacters().toString())){
            outputbox.setText("Inv can not be lower \nthan the Min value.");
            return false;
        }
    }catch (Exception e){}
    try {
        if ( Integer.parseInt(modInv.getCharacters().toString()) > Integer.parseInt(modMax.getCharacters().toString())){
            outputbox.setText("Inv can not be greater \nthan the Max value.");
            return false;
        }
    }catch (Exception e){}
    return true;
}

    /**
     * @param actionEvent saves the modified product
     */
    public void saveModifyProduct(ActionEvent actionEvent) {

        if (isinputgood()){
            NewProductfromSelected.setName(modName.getCharacters().toString());
            NewProductfromSelected.setPrice(Double.parseDouble(modPrice.getCharacters().toString()));
            NewProductfromSelected.setStock(Integer.parseInt(modInv.getCharacters().toString()));
            NewProductfromSelected.setMin(Integer.parseInt(modMin.getCharacters().toString()));
            NewProductfromSelected.setMax(Integer.parseInt(modMax.getCharacters().toString()));
            Inventory.updateProduct(Inventory.getAllProducts().indexOf(producttomodify),NewProductfromSelected);
            try { canceladdProduct(actionEvent);
                    } catch (Exception e) {        };
                }

        }

    /**
     * @return namedParts from String
     */
    private ObservableList<Part> searchbyPartName(String thispartialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> alltheParts = Inventory.getAllParts();
        for (Part bp : alltheParts) {
            if (bp.getName().contains(thispartialName)) {
                namedParts.add(bp);
            }
        }
        return namedParts;
    }

    /**
     * @return namedParts from int
     */
    private ObservableList<Part> searchbyPartNumber(int thispartialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> alltheParts = Inventory.getAllParts();
        for (Part bp : alltheParts) {
            if (bp.getId() == thispartialName) {
                namedParts.add(bp);
            }
        }
        return namedParts;
    }

   /**
     * @param  possibleNumber checked if it can be parsed as an integer
     * @return boolean true if possibleNumber can be parsed as an integer
     */
    public static boolean isaNumber(String possibleNumber) {
        try {
            Integer.parseInt(possibleNumber);
            return true;
        } catch (Exception e) {return false;}
    }

    /**
     * @param keyEvent search for parts by name or number when key is pressed in search box
     */
    public void searchpart(KeyEvent keyEvent) {
        ObservableList<Part> p = searchbyPartName(search_parts.getCharacters().toString());
        if (p.size() < 1) {
            if (isaNumber(search_parts.getCharacters().toString())) {
                int queryint = Integer.parseInt(search_parts.getCharacters().toString());
                ObservableList<Part> pn = searchbyPartNumber(queryint);
                p = pn;
            }
        }
        partslist.setItems(p);
    }

}
