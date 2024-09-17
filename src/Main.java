import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static boolean isvalidEmailID(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean checkEmailExists(Connection con,String email)throws SQLException {
        String query = "SELECT COUNT(*) FROM Customer WHERE Email = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        Scanner b=new Scanner(System.in);
        String userName="";
        String password1="";
        String jdbcURL="jdbc:derby:TcsDataBase1;create=true";

        con= DriverManager.getConnection(jdbcURL,userName,password1);


        System.out.print("\n[1] Create TAble \n[2] Add Bills \n[3] Register Compliant \n[4] Bill Generation \n[5] Domain Search");
        int choice=b.nextInt();b.nextLine();
        if(choice==1) {

        	   String customerTable = "CREATE TABLE Customer (" +
		                "ConsumerID INT NOT NULL PRIMARY KEY, " +
		                "BillNumber INT NOT NULL, " +
		                "Title VARCHAR(10), " +
		                "CustomerName VARCHAR(50), " +
		                "Email VARCHAR(50), " +
		                "MobileNumber VARCHAR(10), " +
		                "UserID VARCHAR(20), " +
		                "Password VARCHAR(30), " +
		                "ConfirmPassword VARCHAR(30))";
		        st=con.prepareStatement(customerTable);
		        st.executeUpdate();


            System.out.println("Customer table created successfully.");

            String insertIntoTable = "INSERT INTO Customer (ConsumerID, BillNumber, Title, CustomerName, Email, MobileNumber, UserID, Password, ConfirmPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            st = con.prepareStatement(insertIntoTable);
            for(int i=0;i<5;i++) {
                System.out.println("\nEnter details for customer " + (i + 1));

                System.out.print("\nConsumer ID (13 digits): ");
                long consumerID = b.nextLong();

                System.out.print("\nBill Number (5 digits): ");
                int billNumber = b.nextInt();
                b.nextLine();

                System.out.print("\nTitle (Mr/Mrs): ");
                String title = b.nextLine();

                System.out.print("\nCustomer Name: ");
                String customerName = b.nextLine();

                System.out.print("\nEmail: ");
                String email = b.nextLine();

                boolean emailIsValid = false;
                while (!emailIsValid) {
                    if (!isvalidEmailID(email)) {
                        System.out.print("\nEnter a valid Email: ");
                        email = b.nextLine();
                    } else {
                        try {
                            if (checkEmailExists(con, email)) {
                                emailIsValid = false;
                                throw new EmailException();
                            } else {
                                emailIsValid = true;
                            }
                        } catch (EmailException e) {
                            System.out.println(e.getMessage());
                            System.out.print("Please enter a different Email: ");
                            email = b.nextLine();
                        }
                    }
                }

                System.out.print("\nMobile Number: ");
                String mobileNumber = b.nextLine();

                System.out.print("\nUser ID (5 to 20 characters): ");
                String userID = b.nextLine();

                System.out.print("\nPassword: ");
                String password = b.nextLine();

                System.out.print("\nConfirm Password: ");
                String confirmPassword = b.nextLine();

                st.setLong(1, consumerID);
                st.setInt(2, billNumber);
                st.setString(3, title);
                st.setString(4, customerName);
                st.setString(5, email);
                st.setString(6, mobileNumber);
                st.setString(7, userID);
                st.setString(8, password);
                st.setString(9, confirmPassword);
                st.executeUpdate();
                System.out.print("\nCustomer "+(i+1)+" registered successfully \n");
            }
            System.out.println("\nCustomers have been registered successfully!");
        } else if (choice==2) {
            /*String billTable2="CREATE TABLE Bills (" +
                    "BillID INT PRIMARY KEY, " +
                    "ConsumerID INT, " +
                    "BillAmount DECIMAL(10, 2), " +
                    "DueAmount DECIMAL(10, 2), " +
                    "PayableAmount DECIMAL(10, 2), " +
                    "FOREIGN KEY (ConsumerID) REFERENCES Customer(ConsumerID))";
            st=con.prepareStatement(billTable2);
            st.executeUpdate();
*/
            String billValues="INSERT INTO Bills(BillID,consumerID,BillAmount,DueAmount,PayableAmount) Values(?,?,?,?,?)";
            st=con.prepareStatement(billValues);
            for(int i=0;i<10;i++){
                System.out.print("\n Enter Bill Number: ");
                int billNumber2=b.nextInt();

                System.out.print("\n Enter Consumer Id: ");
                int consumerId2=b.nextInt();

                System.out.print("\n Enter Bill Amount: ");
                double billAmount=b.nextDouble();

                System.out.print("\n Enter Due Amount; ");
                double dueAmount=b.nextDouble();

                System.out.print("\n Enter Payable Amount: ");
                double payableAmount=b.nextDouble();

                Bills bill=new ElectricityBill(billNumber2,consumerId2,billAmount,dueAmount,payableAmount);
                bill.insertBill(st);

                System.out.print("\n Inserted SuccessFully");

            }
        } else if (choice==3) {
            String complaintTable="CREATE TABLE Complaint("+
                    "complaintType varchar(50)," +
                    "category varchar(50)," +
                    "LandMark varchar(30)," +
                    "customerName varchar(30)," +
                    "problem varchar(150)," +
                    "consumerId int ," +
                    "address varchar(100)," +
                    "mobileNUmber int)";
            st=con.prepareStatement(complaintTable);
            st.executeUpdate();

            String insertIntoComplaints="INSERT INTO Complaint(complaintType,category," +
                    "LandMark,problem,customerName,consumerId,address,mobileNUmber)Values (?,?,?,?,?,?,?,?)";
            st=con.prepareStatement(insertIntoComplaints);

            System.out.print("\nEnter Complaint Type: ");
            String complaintType = b.nextLine();

            System.out.print("\nEnter Category: ");
            String category = b.nextLine();


            System.out.print("\nEnter Landmark: ");
            String landmark = b.nextLine();


            System.out.print("\nEnter Problem: ");
            String problem = b.nextLine();


            System.out.print("\nEnter Customer Name: ");
            String customerName = b.nextLine();



            System.out.print("\nEnter Consumer Number: ");
            int consumerID = b.nextInt();

            b.nextLine();

            System.out.print("\nEnter Address: ");
            String address = b.nextLine();


            System.out.print("\nEnter Mobile Number: ");
            String mobileNumber = b.nextLine();


            Consumer cons=new Complaint(complaintType,landmark,category,problem,customerName,consumerID,address,mobileNumber);
            cons.registerComplaint(st);


            System.out.println("\nSuccessfully registered Complaint!\n");
        } else if (choice==4) {
            System.out.print("\n Enter the Number of Bills to be Generated");
            int n=b.nextInt();

            for(int i=0;i<n;i++){
                System.out.print("Enter Consumer ID: ");
                int consumerId = b.nextInt();

                BillGeneration billThread=new BillGeneration(consumerId,con);

                billThread.start();

            }
        } else if (choice==5) {
            try {
                String emailQry = "SELECT ConsumerID, CustomerName, Email FROM Customer where Email LIKE ? ORDER BY consumerID ASC";

                st = con.prepareStatement(emailQry);
                System.out.print("Enter Domain [Gmail / Yahoo / Hotmail]: ");
                String domainType = b.nextLine();
                st.setString(1, "%" + domainType + "%");
                ResultSet rs = st.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    System.out.println(rs.getString("consumerID") + " " + rs.getString("CustomerName") + " " + rs.getString("Email"));
                    found = true;
                }
                if (!found) {
                    throw new CustomerNotFoundException(domainType);
                }
            }catch (CustomerNotFoundException e){
                System.out.print(e.getMessage());
            }
        }

    }
}




