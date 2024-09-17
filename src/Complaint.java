import java.sql.*;

public class Complaint extends Consumer {
     String complaintType;
     String category;
     String landmark;
     String problemDescription;
    public Complaint(String complaintType,String category,String landmark,String problemDescription, String customerName,int consumerID, String address,String mobileNumber){
        super(customerName,consumerID,address,mobileNumber);
        this.complaintType=complaintType;
        this.category=category;
        this.landmark=landmark;
        this.problemDescription=problemDescription;
    }
    public void registerComplaint(PreparedStatement st) throws SQLException{
        st.setString(1, complaintType);
        st.setString(2, category);
        st.setString(3, landmark);
        st.setString(4, problemDescription);
        st.setString(5, customerName);
        st.setInt(6, consumerID);
        st.setString(7, address);
        st.setString(8, mobileNumber);
        st.executeUpdate();
    }
}
