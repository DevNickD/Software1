package model;

/**
 * The class used to create Parts that are manufactured In-House.
 * Since it is a subtype of a Part object, it extends the Part Class.
 */
public class InHouse extends Part{

    /**
     * InHouse parts must have a machineId associated with them.
     */
    private int machineId;

    /**
     * The Constructor for the InHouse class.
     * An id, name, price, stock, minimum stock, maximum stock and machineId is required.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Getter to get the machineId because the Part class has no machineId variable.
     */
    public int getMachineId(){
        return machineId;
    }

    /**
     * Setter to set the machineId because the Part class has no machineId variable.
     */
    public void setMachineId(int id){
        this.machineId = machineId;
    }
}
