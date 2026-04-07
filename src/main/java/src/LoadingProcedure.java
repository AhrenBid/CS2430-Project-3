/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author abidling
 */
public class LoadingProcedure 
{
    
    ArrayList<Payload> cargoCatalogue;
    
    public LoadingProcedure(ArrayList<Payload> cargoCatagogue)
    {
        this.cargoCatalogue = new ArrayList<Payload>(cargoCatalogue);
    }
    
    public CargoBay loadHighestRating()
    {
        CargoBay ratingBay = new CargoBay(700);
        
        return ratingBay;
    }
    
    public CargoBay loadLightest()
    {
        CargoBay lightestBay = new CargoBay(700);
        
        return lightestBay;
    }
    
    public CargoBay loadBestRatio()
    {
        CargoBay ratioBay = new CargoBay(700);
        
        return ratioBay;
    }
    
// Loads every possible combination of payloads and returns 
// the top 3 combinations that are under the weight limit of 700
    public ArrayList<CargoBay> loadBruteForce(Payload[] payloads) {
        ArrayList<CargoBay> validSubsets = new ArrayList<CargoBay>();
    
    //loops through every possible combination of payloads using a bitmask
        for (int mask = 0; mask < 4096; mask++) {
            CargoBay forceBay = new CargoBay(700);
        
        // loops through all 12 payloads and adds the current iterationed ones to the cargo bay
            for (int i = 0; i < 12; i++) {
                if ((mask & (1 << i)) != 0) {
                    try {
                    forceBay.addPayload(payloads[i]);
                    } catch (Exception e) {
                    // Skip past excpetion
                    }
                }
            }
        
            // Only takes in valid combinations of payloads that are under 700
            if (forceBay.getCargoWeight() <= 700) {
            validSubsets.add(forceBay);
        }
    }

        // Sorts valids subsets and adds the top 3 into array list top 3
        Collections.sort(validSubsets, (a, b) -> b.getCargoValue() - a.getCargoValue());
        ArrayList<CargoBay> top3 = new ArrayList<CargoBay>();
        top3.add(validSubsets.get(0));
        top3.add(validSubsets.get(1));
        top3.add(validSubsets.get(2));
        return top3;
    }
    
    public CargoBay loadDynamic()
    {
        CargoBay dynamicBay = new CargoBay(700);
        
        return dynamicBay;
    }
    
}
