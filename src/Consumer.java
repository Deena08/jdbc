import java.sql.*;

public class Consumer {
     String customerName;
    int consumerID;
    String address;
    String mobileNumber;

    public Consumer(String customerName,int consumerID,String address,String mobileNumber){
        this.customerName=customerName;
        this.consumerID=consumerID;
        this.address=address;
        this.mobileNumber=mobileNumber;
    }
    public void registerComplaint(PreparedStatement st) throws SQLException{}
}
