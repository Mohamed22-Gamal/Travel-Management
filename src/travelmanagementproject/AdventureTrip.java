/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelmanagementproject;

/**
 *
 * @author d
 */
public class AdventureTrip extends Trip{
     public static String tripCategory = "Adventure";

    @Override
     public void showCategory(){      
        System.out.println(tripCategory);
     }
     @Override
     public void checkAvailability(){
        System.out.println(isAvailable);
     }
}
