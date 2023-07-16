/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelmanagementproject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;
/**
 *
 * @author d
 */
public class TravelManagementProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SignIn s =new SignIn();
        s.setVisible(true);
        
        
     try{  
        //step1 load the driver class  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
                 

        //step2 create  the connection object  
        Connection con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");  
                 

        //step3 create the statement object  
        Statement stmt=con.createStatement();
                 

        //step4 execute query  
        ResultSet rs=stmt.executeQuery("select * from TRIP where TRIPID=104 "); 
                         
        while(rs.next()){
            int tid=rs.getInt("TRIPID");
            String tname=rs.getString("TRIPNAME");
            System.out.println(tname);
        }
                
                       
//         System.out.println(sofa);
                        

                         
         

        //step5 close the connection object  
        con.close();  

        }catch(Exception e){ System.out.println(e);} 

        
    
        // TODO code application logic here
        TripFactory Factory = new TripFactory();
        Trip p = Factory.getTrip("MedicalTrip");
        p.showCategory();
    }
    
}
