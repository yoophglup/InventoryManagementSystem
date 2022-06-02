package controller;
/**
 * class controller.modifyPart_controller.java
 */
/**
 * class modifyPart_controller.java
 */

/**
 *
 * @author Chad Self
 */

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class modifyPart_controller  implements Initializable {

    public static ObservableList<Part> parttomodify;
    public static ObservableList<InHouse> parttomodifyInhouse;
    public static ObservableList<Outsourced> parttomodifyOutsourced;
    public static Part justaparttomodify;
    public Label housedLabel;
    public ToggleGroup housegroup;
    public TextField modID;
    public TextField modName;
    public TextField modInv;
    public TextField modPrice;
    public TextField modMax;
    public TextField modMin;
    public Button SaveModPartbutton;
    public Button CancelModPartbutton;
    public TextField AddMachineID;
    public RadioButton inHouseRadio;
    public RadioButton OutsourcedRadio;
    public Label outputbox;

    /**
     * Method to Initialize modifyPart_controller
     **/
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (parttomodifyInhouse.toString().contains("InHouse")) {
            inHouseRadio.setSelected(true);
            housedLabel.setText("Machine ID");
            for (InHouse housedpart : parttomodifyInhouse) {
                modID.setText(String.valueOf(housedpart.getId()));
                modName.setText(housedpart.getName());
                modInv.setText(String.valueOf(housedpart.getStock()));
                modPrice.setText(String.valueOf(housedpart.getPrice()));
                modMax.setText(String.valueOf(housedpart.getMax()));
                modMin.setText(String.valueOf(housedpart.getMin()));
                AddMachineID.setText(String.valueOf(housedpart.getMachineId()));
            }
        }
        if (parttomodifyOutsourced.toString().contains("Outsourced")) {
            OutsourcedRadio.setSelected(true);
            housedLabel.setText("Company Name");
            for (Outsourced outpart : parttomodifyOutsourced) {
                modID.setText(String.valueOf(outpart.getId()));
                modName.setText(outpart.getName());
                modInv.setText(String.valueOf(outpart.getStock()));
                modPrice.setText(String.valueOf(outpart.getPrice()));
                modMax.setText(String.valueOf(outpart.getMax()));
                modMin.setText(String.valueOf(outpart.getMin()));
                AddMachineID.setText(String.valueOf(outpart.getCompanyName()));
            }
        }


    }

    /**
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void cancelModifyPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/InventoryMainScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1040, 390);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param actionEvent sets housedLabel="Machine ID"
     */
    public void onInhouse(ActionEvent actionEvent) {
        housedLabel.setText("Machine ID");
                }

    /**
     * @param actionEvent sets housedLabel="Company Name"
     */
    public void onoutsourced(ActionEvent actionEvent) {
        housedLabel.setText("Company Name");
    }

    /**
     * @return true if all input is valid
     */
    public boolean isinputgood() {
        if (housedLabel.getText()=="") {
            outputbox.setText("You must select either \nIn-House or Outsourced");
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
        } ;
        if ( modMin.getCharacters().length()==0) {
            outputbox.setText("You must enter a \nMin inventory value.");
            return false;
        }
        try {
            Integer.parseInt(modMin.getCharacters().toString());
            if ((Integer.parseInt(modMax.getCharacters().toString()))<(Integer.parseInt(modMin.getCharacters().toString())) ) {
                outputbox.setText("You must enter a \nMin inventory value which is less \nthan the Max inventory value.");
                return false;
            }
        }catch (Exception e) {
            outputbox.setText("Min inventory must\n be an integer value.");
            return false;
        } ;
        if (housedLabel.getText()=="Machine ID") {
            if ( AddMachineID.getCharacters().length()==0) {
                outputbox.setText("You must enter \na Machine ID.");
                return false;
            }
            try {
                Integer.parseInt(AddMachineID.getCharacters().toString());
            } catch (Exception e) {
                outputbox.setText("Machine ID must\n be an integer value.");
                return false;
            };
        }
        if (housedLabel.getText()=="Company Name") {
            if ( AddMachineID.getCharacters().length()==0) {
                outputbox.setText("You must enter \na Company Name.");
                return false;
            }
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
     * @param actionEvent saves the modified part to the inventory
     */
    public void saveModifyPart(ActionEvent actionEvent) {
        Part themodifiedpart=justaparttomodify;
        outputbox.setText("");
        if (isinputgood()){
            try {
                if (housedLabel.getText()=="Company Name") {
                    //Inventory.addPart(new Outsourced(thispartID, modName.getCharacters().toString(), Double.parseDouble(modPrice.getCharacters().toString()), Integer.parseInt(modInv.getCharacters().toString()), Integer.parseInt(modMin.getCharacters().toString()), Integer.parseInt(modMax.getCharacters().toString()), AddMachineID.getCharacters().toString()));
                    Outsourced modifiedpart=new Outsourced(justaparttomodify.getId(), modName.getCharacters().toString(), Double.parseDouble(modPrice.getCharacters().toString()), Integer.parseInt(modInv.getCharacters().toString()), Integer.parseInt(modMin.getCharacters().toString()), Integer.parseInt(modMax.getCharacters().toString()), AddMachineID.getCharacters().toString());
                    Inventory.updatePart(Inventory.getAllParts().indexOf(justaparttomodify),modifiedpart);
                    themodifiedpart=modifiedpart;
                }
                if (housedLabel.getText()=="Machine ID") {
                    //Inventory.addPart(new InHouse(thispartID, modName.getCharacters().toString(), Double.parseDouble(modPrice.getCharacters().toString()), Integer.parseInt(modInv.getCharacters().toString()), Integer.parseInt(modMin.getCharacters().toString()), Integer.parseInt(modMax.getCharacters().toString()), Integer.parseInt(AddMachineID.getCharacters().toString())));
                    InHouse modifiedpart=new InHouse(justaparttomodify.getId(), modName.getCharacters().toString(), Double.parseDouble(modPrice.getCharacters().toString()), Integer.parseInt(modInv.getCharacters().toString()), Integer.parseInt(modMin.getCharacters().toString()), Integer.parseInt(modMax.getCharacters().toString()), Integer.parseInt(AddMachineID.getCharacters().toString()));
                    Inventory.updatePart(Inventory.getAllParts().indexOf(justaparttomodify),modifiedpart);
                    themodifiedpart=modifiedpart;
                }
                for (Product thisProd: Inventory.getAllProducts()){
                    for (Part thisPart: thisProd.getAllAssociatedParts()){
                        if (thisPart==justaparttomodify) {
                            System.out.println(thisProd.getName()+thisPart.getName());
                            thisProd.getAllAssociatedParts().set(thisProd.getAllAssociatedParts().indexOf(thisPart),themodifiedpart);
                        }
                    }
                }
                cancelModifyPart(actionEvent);
            } catch (Exception e) {System.out.println(e);
            } ;
        }

    }
}