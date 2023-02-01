package model;

public class Outsourced extends Part{
    // Declare Fields
    private String companyName;

    // Declare Constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    // Declare Methods
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

}