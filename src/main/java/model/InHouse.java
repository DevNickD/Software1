package model;

public class InHouse extends Part{
    // Declare Fields
    private int machineId;

    // Declare Constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    // Declare Methods
    public int getMachineId(){
        return machineId;
    }
    public void setMachineId(int id){
        this.machineId = machineId;
    }
}
