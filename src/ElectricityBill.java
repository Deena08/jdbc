import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ElectricityBill extends Bills {

    public ElectricityBill(int billNumber,int consumerID, double billAmount, double dueAmount, double payableAmount){
        super(billNumber,consumerID,billAmount,dueAmount,payableAmount);
    }

    @Override
    public void insertBill(PreparedStatement st) throws SQLException {
        st.setInt(1, this.billNumber);
        st.setInt(2, this.consumerID);
        st.setDouble(3, this.billAmount);
        st.setDouble(4, this.dueAmount);
        st.setDouble(5, this.payableAmount);
        st.executeUpdate();
    }
}
