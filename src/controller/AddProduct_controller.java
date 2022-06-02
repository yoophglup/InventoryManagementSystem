package controller;
/**
 * class controller.AddProduct_controller.java
 */
/**
 * class AddProduct_controller.java
 */
/**
 * @author Chad Self
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
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddProduct_controller implements Initializable {

    public Product newProduct=new Product(0,"",0,0,0,0);
    public TableView partslist;
    public TableView associatedPartstable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn stockColumn;
    public TableColumn priceColumn;
    public TableColumn AssociatedPartId;
    public TableColumn AssociatedPartName;
    public TableColumn AssociatedPartStock;
    public TableColumn AssociatedPartPrice;
    public TextField inputId;
    public TextField inputName;
    public TextField inputStock;
    public TextField inputPrice;
    public TextField inputMin;
    public TextField inputMax;
    public Button save;
    public Button cancel;
    public TextField search_parts;
    public Label outputbox;

    /**
     * This method initializes all tableviews. Sets the cell value factory and loads the list into the parts and associated parts tableviews.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partslist.setItems(Inventory.getAllParts());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartstable.setItems(newProduct.getAllAssociatedParts());
        AssociatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * This method changes the scene to the main menu.
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
     * This method checks if all the input values are valid.  When invalid values are detected an output text label will show information.
     * @return true if all input is valid and false if any values are invalid
     */
    public boolean isinputgood() {

        if (inputName.getCharacters().length() == 0) {
            outputbox.setText("You must enter a Name value.");
            return false;
        }
        if (inputStock.getCharacters().length() == 0) {
            outputbox.setText("Inventory stock level \ncannot be blank.");
            return false;
        }
        try {
            Integer.parseInt(inputStock.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Inventory stock must\n be an integer value.");
            return false;
        }
        ;
        if (inputPrice.getCharacters().length() == 0) {
            outputbox.setText("You must enter a Price value.");
            return false;
        }
        try {
            Double.parseDouble(inputPrice.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Price must \nbe a double value.");
            return false;
        }
        ;
        if (inputMax.getCharacters().length() == 0) {
            outputbox.setText("You must enter a \nMax inventory value.");
            return false;
        }
        try {
            Integer.parseInt(inputMax.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Max inventory must\n be an integer value.");
            return false;
        };
        if (inputMin.getCharacters().length() == 0) {
            outputbox.setText("You must enter a \nMin inventory value.");
            return false;
        }
        try {
            Integer.parseInt(inputMin.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Min inventory must\n be an integer value.");
            return false;
        };
            if ((Integer.parseInt(inputMax.getCharacters().toString())) < (Integer.parseInt(inputMin.getCharacters().toString()))) {
                outputbox.setText("You must enter a \nMin inventory value which is \nless than the Max \ninventory value.");
                return false;
            }
            if (Integer.parseInt(inputStock.getCharacters().toString()) < Integer.parseInt(inputMin.getCharacters().toString())) {
                outputbox.setText("Inv can not be lower \nthan the Min value.");
                return false;
                    }
            if (Integer.parseInt(inputStock.getCharacters().toString()) > Integer.parseInt(inputMax.getCharacters().toString())) {
                outputbox.setText("Inv can not be higher \nthan the Max value.");
                return false; }



        try {
            if (Integer.parseInt(inputStock.getCharacters().toString()) > Integer.parseInt(inputMax.getCharacters().toString())) {
                outputbox.setText("Inv can not be greater \nthan the Max value.");
                return false;
            }
        } catch (Exception e) {
        };
        return true;
    }

    /**
     * This method saves the temp product after calling the method to check if input values are valid.
     * @param actionEvent triggered when user clicks save
     */
    public void saveNewProduct(ActionEvent actionEvent) {
        if (isinputgood()){
        newProduct.setId(Inventory.getAllProducts().size()+1);
        newProduct.setName(inputName.getCharacters().toString());
        newProduct.setPrice(Double.parseDouble(inputPrice.getCharacters().toString()));
        newProduct.setStock(Integer.parseInt(inputStock.getCharacters().toString()));
        newProduct.setMin(Integer.parseInt(inputMin.getCharacters().toString()));
        newProduct.setMax(Integer.parseInt(inputMax.getCharacters().toString()));
        Inventory.addProduct(newProduct);
        try { canceladdProduct(actionEvent);
        } catch (Exception e) {        };
    }}

    /**
     * This method searches the inventory parts for any matching part names.
     * @param thispartialName is the lookup value
     * @return an observable list of matching parts
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
     * This method searches the inventory parts for any matching part IDs.
     * @param thispartialName is the lookup value
     * @return an observable list of matching parts
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
     * This method searches for parts by name or number when key is pressed in search box
     * @param keyEvent is the value of the search text box when a key is pressed
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

    /**
     * This method adds the part selected into the associated parts list for the temp product.
     * @param actionEvent is the part selected when add was clicked
     */
    public void addtoassociatedparts(ActionEvent actionEvent) {
        ObservableList<Part> selectedPart=partslist.getSelectionModel().getSelectedItems();
        for (Part thispart : selectedPart) {
            if (!newProduct.getAllAssociatedParts().contains(thispart)){
                newProduct.addAssociatedPart(thispart);
                outputbox.setText("");

            } else {outputbox.setText("That part is already associated\n with this product.");}
        }
        associatedPartstable.setItems(newProduct.getAllAssociatedParts());
    }

    /**
     * This method removes the part selected from the associated parts list for the temp product.
     * @param actionEvent is triggered when remove is clicked
     */
    public void removeassociatedparts(ActionEvent actionEvent) {
        ObservableList<Part> selectedPart = associatedPartstable.getSelectionModel().getSelectedItems();
        try {
            for (Part thispart : selectedPart) {
                newProduct.deleteAssociatedPart(thispart);
            }
            associatedPartstable.setItems(newProduct.getAllAssociatedParts());
        } catch (Exception e){System.out.println();
        }
    }
}
