/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmanagementproject;

import java.util.Date;

/**
 *
 * @author Islam
 */
public abstract class Trip {
      int tripID =0;
      Date tripDate = null;
      String tripCategory = null;
      String tripName = null;
     //public static Tourguide tourguide = null;
      int touristsCanJoint = 0;
      int touristsJoined = 0;
     //public static Tourist touristsjoinedInfo = null;
       String siteToVisit = null;
       int tripPrice = 0;
       int tripProfit = 0;
       boolean isAvailable = false;
     
     public abstract void showCategory();
     public abstract void checkAvailability();

}
