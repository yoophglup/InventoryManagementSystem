package model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * class model.Inventory.java
 */
/**
 * class Inventory.java
 */
/**
 * @author Chad Self
 */
public class Inventory {
    /**
     * @param allParts to set
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * @param allProducts to set
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     *  @param part to allParts
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     *  @param product added to allProducts
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     *  @param selectedPart removed from allParts and allProducts
     *  @return boolean set to compare if selectedPart equals any part
     */
    public static boolean deletePart(Part selectedPart) {
        boolean booleaninfo=allParts.contains(selectedPart);
        allParts.remove(selectedPart);
        for (Product thisproduct: Inventory.getAllProducts()){
            thisproduct.deleteAssociatedPart(selectedPart);
        }
        return booleaninfo;
    }

    /**
     *  @param selectedProduct removed from allProducts
     *  @return boolean booleaninfo
     */
    public static boolean deleteProduct(Product selectedProduct) {
        boolean booleaninfo=allProducts.contains(selectedProduct);
        allProducts.remove(selectedProduct);
        return booleaninfo;
    }

    /**
     *  @param partid set to id of selected
     *  @return namedParts set to a list of parts which match partid
     */
    public static ObservableList<Part> lookupPart(int partid){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> alltheParts  = Inventory.getAllParts();
        for (Part bp : alltheParts){
            if (bp.getId()==partid){
                namedParts.add(bp);
            }
        }
        return namedParts;
    }

    /**
     *  @param productid set to integer used for look up
     *  @return namedProduct set to a list of all products which Id matches productid
     */
    public static ObservableList<Product> lookupProduct(int productid){
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
        ObservableList<Product> alltheProducts  = Inventory. getAllProducts();
        for (Product bp : alltheProducts){
            if (bp.getId()==productid){
                namedProduct.add(bp);
            }
        }
        return namedProduct;
    }

    /**
     *  @param productName set to the name of the product to look up
     *  @return namedProduct set to a list of all products which match productName
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();
        ObservableList<Product> alltheProducts  = Inventory. getAllProducts();
        for (Product bp : alltheProducts){
            if (bp.getName().toUpperCase().contains(productName.toUpperCase())){
                namedProduct.add(bp);
            }
        }
        return namedProduct;
    }

    /**
     *  @param partName set to the name of the part to look up
     *  @return namedParts list of parts which match partName
     */
    public static ObservableList<Part> LookupPart(String partName){
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> alltheParts  = Inventory.getAllParts();
        for (Part bp : alltheParts){
            if (bp.getName().toUpperCase().contains(partName.toUpperCase())){
                namedParts.add(bp);
            }
        }
        return namedParts;
    }

    /**
     *  @param index set to the index of the part selected
     *  @param selectedPart replace existing parts from allParts using index
     */
    public static void updatePart(int index,Part selectedPart) {
        Inventory.getAllParts().set(index, selectedPart);
    }

    /**
     *  @param index set to index of the part selected
     *  @param newProduct replace existing Product from allProducts using index
     */
    public static void updateProduct(int index,Product newProduct) {
        allProducts.set(index, newProduct);

    }

    /**
     *  @return allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *  @return allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    }