/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmanagementproject;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;
import java.sql.*;
import java.util.Date;

/**
 *
 * @author Islam
 */
public class Tourist {
    
    String TouristName;
    int TouristID;
    int TouristAge;
    int TouristPreviousTripsCount;
    int TouristCurrentTripsCount;
    int TouristComingTripsCount;
    int[] TouristPreviousTrips;
    int[] TouristCurrentTrips
    int[] TouristComingTrips;
    
    Connection con;
    PreparedStatement pstmt;
    
    public Tourist(){
        
        try{  
        //step1 load the driver class  
        Class.forName("oracle.jdbc.driver.OracleDriver");  
                 

        //step2 create  the connection object  
        con=DriverManager.getConnection(  
        "jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  

        con.close();  

        }catch(Exception e){ System.out.println(e);} 
        
    }
    
    public void Register(int TouristID, String TouristName,int TouristAge){
        
        try 
        {            
            String SQL = "INSERT INTO TOURIST VALUES ( ?, ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, TouristID);
            pstmt.setString(2, TouristName);
            pstmt.setInt(3, TouristAge);
            
            pstmt.close();

        } catch (SQLException ex) {
            System.out.println("travelmanagementproject.Tourist.Register()");
        }  
    }
    
    public boolean Login(int TouristID, String TouristName){
        
        int clientID = -1; 
        try
        {
            String SQL = "SELECT * FROM CLIENT WHERE CLIENTID ='"+TouristID+"'&& CLIENTNAME='"+TouristName+"'";
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {  
                clientID = rs.getInt("CLIENTID");
            }
            pstmt = con.prepareStatement(SQL);
                        pstmt.close();


        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(clientID == -1){
            return false;
        }
        else{
            return true;
        }
       
    }
    
    public Trip BrowseTrips(String TripName)
    {
        
        int id=0;
        Date tripDate = null;
        String category = "";
        String name="";
        String site = "";
        int price = 0;
        
        
        try
        {
            String SQL = "SELECT * FROM TRIP WHERE TRIPNAME ='"+TripName+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
       
                         
            while(rs.next())
            {  
                id = rs.getInt("TRIPID");
                tripDate = rs.getDate("TRIPDATE");
                category = rs.getString("TRIPCATEGORY");
                name = rs.getString("TRIPNAME");
                site = rs.getString("SITEDETAILS");
                price = rs.getInt("TRIPPRICE");
            }
                pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        TripFactory t=new TripFactory();
       Trip trip= t.getTrip(category);
        
        trip.tripID = id;
        trip.tripName = name;
        trip.tripDate = tripDate;
        trip.tripCategory = category;
        trip.tripPrice = price ;
        trip.siteToVisit = site;
        
        return trip;
       
    }
    
    public void BookTrip(int Clientid, int Tripid){

        
        try 
        {            
            String SQL = "INSERT INTO TOURISTSTRIPS VALUES ( ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, Tripid);
            pstmt.setInt(2, Clientid);

            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {            
            String SQL = "INSERT INTO CLIENTCOMINGTRIPS VALUES ( ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, Clientid);
            pstmt.setInt(2, Tripid);

            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CancelRegistration(Tourist TouristID){
        
        try
        {
            String SQL = "DELETE FROM CLIENT WHERE CLIENTID ='"+TouristID+"'";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
        }catch(SQLException ex)
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void EditAccount(int TouristID, String TouristName,int TouristAge){
        
        try 
        {            
            String SQL = "update CLIENT set CLIENTID='"+TouristID+"',CLIENTNAME='"+TouristName+"',CLIENTAGE='"+TouristAge+"'";
            pstmt = con.prepareStatement(SQL);
            pstmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] ViewPreviousTrips(int ClientID){
        
        int[] TripId = new int[50];
        int count =0;
      
        try
        {
            String SQL = "SELECT TRIPID FROM CLIENTPREVIOUSTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
       
           
            while(rs.next())
            {  
                TripId[rs.getRow()] = rs.getInt("TRIPID");
                count++;
            }
            pstmt.close();

        }catch(Exception e){ System.out.println(e);} 
     
       String [] TripsNames=new String[10];
       try{   
 
            for(int i=1;i<=count;i++){
                
            String SQL = "SELECT TRIPNAME FROM TRIP WHERE TRIPID ='"+TripId[i]+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
             
            while(rs.next())
            {  
               TripsNames[i] = rs.getString("TRIPNAME");
            }
            pstmt.close();}
                 
            for (int i = 1;i<=count;i++){
              System.out.println(TripId[i]+" "+TripsNames[i]);}
            

        }catch(Exception e){ System.out.println(e);} 
        
    return TripsNames;
    }
    
    public String[] ViewCurrentTrips(int ClientID){
        
         int[] TripId = new int[50];
        int count =0;
      
        try
        {
            String SQL = "SELECT TRIPID FROM CLIENTCURRENTTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
       
           
            while(rs.next())
            {  
                TripId[rs.getRow()] = rs.getInt("TRIPID");
                count++;
            }
            pstmt.close();

        }catch(Exception e){ System.out.println(e);} 
     
       String [] TripsNames=new String[10];
       try{   
 
            for(int i=1;i<=count;i++){
                
            String SQL = "SELECT TRIPNAME FROM TRIP WHERE TRIPID ='"+TripId[i]+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
             
            while(rs.next())
            {  
               TripsNames[i] = rs.getString("TRIPNAME");
            }
            pstmt.close();}
                 
            for (int i = 1;i<=count;i++){
              System.out.println(TripId[i]+" "+TripsNames[i]);}
            

        }catch(Exception e){ System.out.println(e);} 
       
       return TripsNames;
        
    }
    
    public String[] ViewComingTrips(int ClientID){
        
         int[] TripId = new int[50];
        int count =0;
      
        try
        {
            String SQL = "SELECT TRIPID FROM CLIENTCOMINGTRIPS WHERE CLIENTID ='"+ClientID+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
       
           
            while(rs.next())
            {  
                TripId[rs.getRow()] = rs.getInt("TRIPID");
                count++;
            }
            pstmt.close();

        }catch(Exception e){ System.out.println(e);} 
     
       String [] TripsNames=new String[10];
       try{   
 
            for(int i=1;i<=count;i++){
                
            String SQL = "SELECT TRIPNAME FROM TRIP WHERE TRIPID ='"+TripId[i]+"'";
            pstmt = con.prepareStatement(SQL);
            ResultSet rs=pstmt.executeQuery();
             
            while(rs.next())
            {  
               TripsNames[i] = rs.getString("TRIPNAME");
            }
            pstmt.close();}
                 
            for (int i = 1;i<=count;i++){
              System.out.println(TripId[i]+" "+TripsNames[i]);}
            

        }catch(Exception e){ System.out.println(e);} 
       
       return TripsNames;
        
    }
    
    public void MakeComplain(int clientID, String complain){
        
        try 
        {            
            String SQL = "INSERT INTO COMPLAIN VALUES ( ?, ?)";
            pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, clientID);
            pstmt.setString(2, complain);
            
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
        
   
    
}
