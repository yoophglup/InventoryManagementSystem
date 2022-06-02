package controller;
/**
 * class controller.AddPart_controller.java
 */
/**
 * class AddPart_controller.java
 */
/**
 * @author Chad Self
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class AddPart_controller {
    public Label housedLabel;
    public RadioButton inHouseRadio;
    public ToggleGroup housegroup;
    public RadioButton OutsourcedRadio;
    public TextField AddID;
    public TextField AddName;
    public TextField AddInv;
    public TextField AddPrice;
    public TextField AddMax;
    public TextField AddMin;
    public TextField AddMachineID;
    public Button SaveAddPart;
    public Button CancelAddPart;
    public Label outputbox;

    /**
     * This method changes the scene to the main menu.
     * @param actionEvent changes the scene
     * @throws  IOException actionEvent
     */
    public void canceladdparts(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/InventoryMainScreen.fxml")));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1040, 390);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method changes the label to "Machine ID".
     * @param actionEvent inHouseRadio radio button is selected
     */
    public void onInhouse(ActionEvent actionEvent) {
        housedLabel.setText("Machine ID");
    }

    /**
     * This method changes the label to "Company Name".
     * @param actionEvent OutsourcedRadio radio button is selected
     */
    public void onoutsourced(ActionEvent actionEvent) {
        housedLabel.setText("Company Name");
    }

    /**
     * This method checks if all the input values are valid.  When invalid values are detected an output text label will
     * show information and false will be returned.
     * @return true if all input is valid
     */
    public boolean isinputgood() {
        if (housedLabel.getText() == "") {
            outputbox.setText("You must select either \nIn-House or Outsourced");
            System.out.println("RadioNotSelected");
            return false;
        }
        if (AddName.getCharacters().length() == 0) {
            outputbox.setText("You must enter a Name value.");
            return false;
        }
        if (AddInv.getCharacters().length() == 0) {
            outputbox.setText("Inventory stock level \ncannot be blank.");
            return false;
        }
        try {
            Integer.parseInt(AddInv.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Inventory stock must\n be an integer value.");
            return false;
        }
        if (AddPrice.getCharacters().length() == 0) {
            outputbox.setText("You must enter a Price value.");
            return false;
        }
        try {
            Double.parseDouble(AddPrice.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Price must \nbe a double value.");
            return false;
        }
        if (AddMax.getCharacters().length() == 0) {
            outputbox.setText("You must enter a \nMax inventory value.");
            return false;
        }
        try {
            Integer.parseInt(AddMax.getCharacters().toString());
        } catch (Exception e) {
            outputbox.setText("Max inventory must\n be an integer value.");
            return false;
        }
        if (AddMin.getCharacters().length() == 0) {
            outputbox.setText("You must enter a \nMin inventory value.");
            return false;
        }
        try {
            Integer.parseInt(AddMin.getCharacters().toString());
            if ((Integer.parseInt(AddMax.getCharacters().toString())) < (Integer.parseInt(AddMin.getCharacters().toString()))) {
                outputbox.setText("You must enter a \nMin inventory value which is less \nthan the Max inventory value.");
                return false;
            }
        } catch (Exception e) {
            outputbox.setText("Min inventory must\n be an integer value.");
            return false;
        }
        if (housedLabel.getText() == "Machine ID") {
            if (AddMachineID.getCharacters().length() == 0) {
                outputbox.setText("You must enter \na Machine ID.");
                return false;
            }
            try {
                Integer.parseInt(AddMachineID.getCharacters().toString());
            } catch (Exception e) {
                outputbox.setText("Machine ID must\n be an integer value.");
                return false;
            }
        }
        if (housedLabel.getText() == "Company Name") {
            if (AddMachineID.getCharacters().length() == 0) {
                outputbox.setText("You must enter \na Company Name.");
                return false;
            }
        }

        try {
            if (Integer.parseInt(AddInv.getCharacters().toString()) < Integer.parseInt(AddMin.getCharacters().toString())) {
                outputbox.setText("Inv can not be lower \nthan the Min value.");
                return false;
            }
        } catch (Exception e) {
        }
        try {
            if (Integer.parseInt(AddInv.getCharacters().toString()) > Integer.parseInt(AddMax.getCharacters().toString())) {
                outputbox.setText("Inv can not be greater \nthan the Max value.");
                return false;
            }
        } catch (Exception e) {
        }

        return true;
    }
    /**

     * This method saves a new part by parsing the data entered after calling the method to check if input values are valid.
     * @param actionEvent is triggered when save is clicked.
     */
    public void saveAddPart(ActionEvent actionEvent) {
        outputbox.setText("");
        if (isinputgood()){
                try {
                    if (housedLabel.getText()=="Company Name") {
                        Inventory.addPart(new Outsourced(Inventory.getAllParts().size() + 1, AddName.getCharacters().toString(), Double.parseDouble(AddPrice.getCharacters().toString()), Integer.parseInt(AddInv.getCharacters().toString()), Integer.parseInt(AddMin.getCharacters().toString()), Integer.parseInt(AddMax.getCharacters().toString()), AddMachineID.getCharacters().toString()));
                    }
                    if (housedLabel.getText()=="Machine ID") {
                        Inventory.addPart(new InHouse(Inventory.getAllParts().size() + 1, AddName.getCharacters().toString(), Double.parseDouble(AddPrice.getCharacters().toString()), Integer.parseInt(AddInv.getCharacters().toString()), Integer.parseInt(AddMin.getCharacters().toString()), Integer.parseInt(AddMax.getCharacters().toString()), Integer.parseInt(AddMachineID.getCharacters().toString())));
                    }
                    canceladdparts(actionEvent);
                } catch (Exception e) {} ;
            }
        }
    }

