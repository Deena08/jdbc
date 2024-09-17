public class EmailException extends Exception{

    public EmailException() {
        super("\nEmail Id already Exists");
    }

}