/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmanagementproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;
import java.sql.*;
import java.util.Date;
import java.util.Map;


/**
 *
 * @author Islam
 */
public class Employee implements Prototype {
    
    String EmpName;
    int EmpID;
    int EmpSalary;
    int EmpAge;
    Connection con;
    PreparedStatement pstmt;
        
    public Employee(String name, int ID, int salary, int age){
        
        this.EmpName = name;
        this.EmpID = ID;
        this.EmpSalary = salary;
        this.EmpAge = age;
        
        try{  
        //step1 load the driver class  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
                 

        //step2 create  the connection object  
        con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  

        con.close();  

        }catch(Exception e){ System.out.println(e);} 
        
        
    }
    
    

    @Override
    public Prototype GetClone() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void AddTrip(int TripID,String tripCategory,String tripName,int touristsCanJoint, String tourguidename,
          java.sql.Date  tripDate,int touristsJoined,String siteToVisit,int tripPrice,int tripProfit){
        
        try 
        {            
            String SQL = "INSERT INTO TRIPS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, TripID);
            pstmt.setDate(2, tripDate);
            pstmt.setString(3, tripCategory);
            pstmt.setString(4, tripName);
            pstmt.setString(5, tourguidename);
            pstmt.setInt(6, touristsCanJoint);
            pstmt.setInt(7,touristsJoined );
            pstmt.setString(8, siteToVisit);
            pstmt.setInt(9, tripPrice);
            pstmt.setInt(10, tripProfit);
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EditTrip(int TripID,String tripCategory,String tripName,int touristsCanJoint, String tourguide,
            Date tripDate,int touristsJoined,String siteToVisit,int tripPrice,int tripProfit){
        
         try 
        {            
            String SQL = "update TRIP set TRIPID='"+TripID+"',TRIPCATEGORY='"+tripCategory+"',TRIPNAME='"+tripName+"',TOURISTNUMBERR='"+touristsCanJoint+"',TOURISTNUMBERR='"+touristsCanJoint+"',TOURGUIDE='"+tourguide+"',TRIPDATE='"+tripDate+"',TOURISTJOINT='"+touristsJoined+"',SITEDETAILS='"+siteToVisit+"',TRIPPRICE='"+tripPrice+"',PROFIT='"+tripProfit+"' where TRIPNAME='"+tripName+"' ";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void CancelTrip(Trip tripID){
        try
        {
            String SQL = "DELETE FROM TRIP WHERE TRIPID ='"+tripID+"'";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     TourGuide tourGuide;
    public void AddTourGuideInfo(String GuideName,int GuideID,int GuideAge,int GuideSalary,String GuideAvailability)
    {
        tourGuide=new TourGuide();
        tourGuide.GuideAge=GuideAge;
        tourGuide.GuideAvailability=GuideAvailability;
        tourGuide.GuideID=GuideID;
        tourGuide.GuideName=GuideName;
        tourGuide.GuideSalary=GuideSalary;
         try 
        {            
            String SQL = "INSERT INTO TOURGUIDE VALUES ( ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, GuideID);
            pstmt.setString(2, GuideName);
            pstmt.setInt(3, GuideAge);
            pstmt.setInt(4, GuideSalary);
            pstmt.setObject(5, GuideAvailability);
            
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void EditTourGuideInfo(String GuideName,int GuideID,int GuideAge,int GuideSalary,Trip GuideBeforeTrips)
    {
         try 
        {            
            String SQL = "update TOURGUIDE set TOURGUIDEID='"+GuideID+"',TOURGUIDENAME='"+GuideName+"',TOURGUIDEAGE='"+GuideAge+"',TOURGUIDESALARY='"+GuideSalary+"',TOURGUIDETRIPSID='"+GuideBeforeTrips+"' ";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
            
        } 
         catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteTourGuideInfo(int GuideID)
    {
         try
        {
            String SQL = "DELETE FROM TOURGUIDE WHERE TOURGUIDEID ='"+GuideID+"'";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TourGuide ViewTourGuideInfo(int GuideID){
        int id=0;
        String name="";
        int age=0;
        int salary=0;
        String avail="";
        
        try
        {
            String SQL = "SELECT * FROM TOURGUIDE WHERE TOURGUIDEID ='"+GuideID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
       
                         
            while(rs.next())
            {  
                id=rs.getInt("TOURGUIDEID");
                name=rs.getString("TOURGUIDENAME");
                age=rs.getInt("TOURGUIDEAGE");
                salary=rs.getInt("TOURGUIDESALARY");
                avail=rs.getString("TOURGUIDEAVAILABILITY");
            }
                pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        tourGuide=new TourGuide();
        tourGuide.GuideID=id;
        tourGuide.GuideName=name;
        tourGuide.GuideSalary=salary;
        tourGuide.GuideAvailability=avail;
        tourGuide.GuideAge=age ;
        tourGuide.display();
        return tourGuide;
    }
    
    public void AssignTourGuide(int GuideID,int TripID)
    {
        String avail = null;
        try
        {
            String SQL = "SELECT TOURGUIDEAVAILABILITY FROM TOURGUIDE WHERE TOURGUIDEID ='"+GuideID+"'";
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {  
                avail = rs.getString("TOURGUIDEAVAILABILITY");
            }
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(avail == "Y"||avail=="y"){
            
            try 
            {            
                String SQL = "INSERT INTO TOURGUIDETRIPS VALUES ( ?, ?)";
                pstmt = con.prepareStatement(SQL);
                pstmt.setInt(1,TripID );
                pstmt.setInt(2, GuideID);

                pstmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Tourguide is not available");
                    
        }
    }
    
    public Tourist ViewClientsDetails(int ClientID){
        String ClientName="";
        int clientId=-0;
        int ClientAge=0;
        int[] tripPastsId = new int [20];
        int tripPastsIdCount=0;
        int[] tripsCurrentId= new int [20];
        int tripsCurrentIdCount=0;
        int[] tripsComingtId= new int [20];
        int tripsComingtIdCount=0;
        try
        {
            String SQL = "SELECT * FROM CLIENT WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            
             while(rs.next())
            {  
                ClientName =rs.getString("CLIENTNAME");
                clientId = rs.getInt("CLIENTID");              
                ClientAge =rs.getInt("CLIENTAGE");

            }
            pstmt.close();
            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            String SQL = "SELECT TRIPID FROM CLIENTCOMINGTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int coloumncount=rsmd.getColumnCount();
              while(rs.next())
            {  
                tripPastsId[rs.getRow()]=rs.getInt("TRIPID");
                tripPastsIdCount++;

            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            String SQL = "SELECT TRIPID FROM CLIENTCURRENTTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int coloumncount=rsmd.getColumnCount();
            while(rs.next())
            {  
                tripsCurrentId[rs.getRow()]=rs.getInt("TRIPID");
                tripsCurrentIdCount++;
            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try
        {
            String SQL = "SELECT TRIPID FROM CLIENTPREVIOUSTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int coloumncount=rsmd.getColumnCount();
          while(rs.next())
            {    
                tripsComingtId[rs.getRow()]=rs.getInt("TRIPID");
                tripsComingtIdCount++;
            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
         Tourist tourist=new Tourist();
         tourist.TouristAge=ClientAge;
         tourist.TouristID=clientId;
         tourist.TouristComingTrips=tripsComingtId;
         tourist.TouristCurrentTrips=tripsCurrentId;
         tourist.TouristPreviousTrips=tripPastsId;
         tourist.TouristPreviousTripsCount=tripPastsIdCount;
         tourist.TouristCurrentTripsCount=tripsCurrentIdCount;
         tourist.TouristComingTripsCount=tripsComingtIdCount;
         
         return tourist;
    }
    
    public int CalcCategoryIncome(String CategoryName){
        
        TripFactory Tf=new TripFactory();
        Trip trip=Tf.getTrip(CategoryName);
        int total=0;
        try
        {
            String SQL = "SELECT TRIPPRICE,TOURISTJOINT FROM TRIP WHERE TRIPCATEGORY ='"+CategoryName+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int coloumncount=rsmd.getColumnCount();
            while(rs.next())
            {    
                total += (rs.getInt("TRIPPRICE") * rs.getInt("TOURISTJOINT"));
            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public int CalcAllCategoriesIncome(){
        int total=0;
        try
        {
            String SQL = "SELECT TRIPPRICE,TOURISTJOINT FROM TRIP";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            while(rs.next())
            {    
                total += (rs.getInt("TRIPPRICE") * rs.getInt("TOURISTJOINT"));
            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public String[] ViewClientsComplain()
    {
        String[] Complains=new String[50];
       
        try
        {
            String SQL = "SELECT COMPLAIN FROM COMPLAIN";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            int coloumncount=rsmd.getColumnCount();
          
            while(rs.next())
            {    

                Complains[rs.getRow()]=rs.getString("COMPLAIN");
            }
            pstmt.close();
            
        }
        
        catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Complains[1]==null) 
        {
         System.out.println(" We have not Complains !");
        }
        return Complains;
    }
    
    
    
    
    
    
    
    
}
