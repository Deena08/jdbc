import java.sql.*;

public class Bills {
    int billNumber;
    int consumerID;
    double billAmount;
    double dueAmount;
    double payableAmount;

    public Bills(int billNumber,int consumerID, double billAmount, double dueAmount, double payableAmount){
        this.billNumber=billNumber;
        this.consumerID=consumerID;
        this.billAmount=billAmount;
        this.dueAmount=dueAmount;
        this.payableAmount=payableAmount;
    }

    public void insertBill(PreparedStatement st)throws SQLException{

    }
}
