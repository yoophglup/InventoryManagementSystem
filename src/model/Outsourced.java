package model;
/**
* model.Outsourced.java
*/
/**
 * Outsourced.java
 */
/**
 * @author Chad Self
 */
public class Outsourced extends Part {
    private String companyName;
    /**
     * @param id to set
     * @param name to set
     * @param price to set
     * @param stock to set
     * @param min to set
     * @param max to set
     * @param companyName to set
     */
    public Outsourced(int id,String name,double price,int stock,int min, int max,String companyName){
        super(id,name,price,stock,min,max);
        this.companyName=companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName(){
        return this.companyName;
    }

    /**
     * @param CompanyName to set
     */
    public void setCompanyName(String CompanyName){
        this.companyName=CompanyName;
    }
}