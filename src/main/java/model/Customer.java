package model;

/**
 * The type Customer.
 */
public class Customer {

    private String customerId;
    private String firstName;

    private String lastName;

    private String contactNumber;

    private String address;

    private String nicNumber;

    /**
     * Instantiates a new Customer.
     *
     * @param customerId    the customer id
     * @param firstName     the first name
     * @param lastName      the last name
     * @param contactNumber the contact number
     * @param address       the address
     * @param nicNumber     the nic number
     */
    public Customer(String customerId, String firstName, String lastName, String contactNumber, String address, String nicNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.nicNumber = nicNumber;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer id.
     *
     * @param customerId the customer id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets contact number.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets contact number.
     *
     * @param contactNumber the contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets nic number.
     *
     * @return the nic number
     */
    public String getNicNumber() {
        return nicNumber;
    }

    /**
     * Sets nic number.
     *
     * @param nicNumber the nic number
     */
    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }
}
