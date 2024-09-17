import java.sql.*;

public class BillGeneration extends Thread{

    int consumerID;
    Connection con;

    public BillGeneration(int consumerID,Connection con){
        this.consumerID=consumerID;
        this.con=con;
    }

    public void run(){
        try {
            String query = "SELECT * FROM Customer WHERE ConsumerID = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, consumerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("Generating bill for Customer ID: " + consumerID);
                System.out.println("Customer Name: " + rs.getString("CustomerName"));
                System.out.println("Email: " + rs.getString("Email"));

                generateBillForCustomer(consumerID, con);

                System.out.println("Bill generated successfully for Customer ID: " + consumerID);
            } else {
                System.out.println("No Such Customer Exist with the Given Customer ID: " + consumerID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateBillForCustomer(int consumerID,Connection con) throws SQLException{
        String billQuery = "SELECT * FROM Bills WHERE ConsumerID = ?";
        PreparedStatement st = con.prepareStatement(billQuery);
        st.setInt(1, consumerID);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            System.out.println("Bill ID: " + rs.getInt("BillID"));
            System.out.println("Bill Amount: " + rs.getDouble("BillAmount"));
            System.out.println("Due Amount: " + rs.getDouble("DueAmount"));
            System.out.println("Payable Amount: " + rs.getDouble("PayableAmount"));
        }
    }

}

