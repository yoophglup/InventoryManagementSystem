package model;
/**
 * class model.InHouse.java
 */
/**
 * class InHouse.java
 */
/**
 * @author Chad Self
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * @param id to set
     * @param name to set
     * @param price to set
     * @param stock to set
     * @param min to set
     * @param max to set
     * @param machineId to set
     */
    public InHouse(int id,String name,double price,int stock,int min, int max,int machineId){
        super(id,name,price,stock,min,max);
        this.machineId=machineId;
    }

    /**
     * @return the machineId
     */
    public int getMachineId(){
        return this.machineId;
    }

    /**
     * @param newmachineId to set
     */
    public void setMachineId(int newmachineId){
        this.machineId=newmachineId;
    }
}
