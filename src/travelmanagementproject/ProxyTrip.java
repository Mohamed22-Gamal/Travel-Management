

package travelmanagementproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;
import java.sql.*;
/**

/**
 *
 * @author ASROCK
 */
public class ProxyTrip extends Trip{
    MedicalTrip medicalTrip;
    CulturalTrip culturalTrip;
    ReligiousTrip religiousTrip;
    AdventureTrip adventureTrip;
    SafariTrip safariTrip;
    String trip;
    public static boolean Available = false ;
    int number1;
    int number2;

    public ProxyTrip(String trip) 
    {
        this.trip =trip;
    
         try{  
        //step1 load the driver class  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
                 

        //step2 create  the connection object  
        Connection con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
                 

        //step3 create the statement object  
        Statement stmt=con.createStatement();
                 

        //step4 execute query  
//        stmt.executeQuery("select TOURISTNUMBER,TOURISTJOINT from TRIP where TRIPNAME =?");
//        stmt.setString();
                
        String SQL = "select TOURISTNUMBER,TOURISTJOINT from TRIP where TRIPNAME =?";
        PreparedStatement pstmt = con.prepareStatement(SQL);
        pstmt.setString(1, trip);
        ResultSet rs=pstmt.executeQuery();
       
                         
       while(rs.next()){  
        number1=rs.getInt("TOURISTNUMBER");
        number2=rs.getInt("TOURISTJOINT");
//        System.out.println(number1);
//        System.out.println(number2);
       }
     

        pstmt.close();
        con.close();  

        }catch(Exception e){ System.out.println(e);} 
        
    }
   
        
    

    @Override
    public void showCategory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void checkAvailability() {
            
        if(number1 == number2)
        {
            System.out.println("This Trip is full");
        }
        else
        {
            System.out.println("full");
        }
            
    } 
       
}
