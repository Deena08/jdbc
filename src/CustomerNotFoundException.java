public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String domainType){
        super("\nNo such customer is registered with "+domainType);
    }
}
