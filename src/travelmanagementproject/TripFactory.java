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
public class TripFactory {
    public Trip getTrip(String tripName){
        if(tripName==null){
            return null;
        }
        if(tripName.equalsIgnoreCase("MedicalTrip")){
            return new MedicalTrip();
        }
        else if(tripName.equalsIgnoreCase("CulturalTrip")){
            return new CulturalTrip();
        }
        else if(tripName.equalsIgnoreCase("ReligiousTrip")){
            return new ReligiousTrip();
        }
        else if(tripName.equalsIgnoreCase("AdventureTrip")){
            return new AdventureTrip();
        }
        else if(tripName.equalsIgnoreCase("SafariTrip")){
            return new SafariTrip();
        }
        return null;
    }
    
}
