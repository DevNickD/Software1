package model;

/**
 * The class used to create Parts that are manufactured by Outsourced companies.
 * Since it is a subtype of a Part object, it extends the Part Class.
 */
public class Outsourced extends Part{

    /**
     * Outsourced parts must have a Company Name associated with them.
     */
    private String companyName;

    /**
     * The Constructor for the Outsourced class.
     * An id, name, price, stock, minimum stock, maximum stock and company name is required.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Getter to get the Company Name because the Part class has no Company Name variable.
     */
    public String getCompanyName(){
        return companyName;
    }

    /**
     * Setter to set the Company Name because the Part class has no Company Name variable.
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

}