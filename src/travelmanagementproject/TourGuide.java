/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmanagementproject;

/**
 *
 * @author Islam
 */
public class TourGuide {
    String GuideName;
    int GuideID;
    int GuideAge;
    int GuideSalary;
    String GuideAvailability;

    public void display(){
        System.out.println(GuideID+" "+GuideName+" "+GuideAge+" "+GuideSalary+" "+GuideAvailability+" ");
    }
}
